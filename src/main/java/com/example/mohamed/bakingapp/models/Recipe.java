package com.example.mohamed.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mohamed.bakingapp.models.Ingredient;
import com.example.mohamed.bakingapp.models.Step;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe implements Parcelable {
    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String recipeName;

    public ArrayList<Ingredient> ingredients;
    public ArrayList<Step> steps;
    public int servings;

    @SerializedName("image")
    public String imageUrl;

    public Recipe() {
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public Recipe(long id, String recipeName, int servings, String imageUrl) {
        this();
        this.id = id;
        this.recipeName = recipeName;
        this.servings = servings;
        this.imageUrl = imageUrl;
    }

    protected Recipe(Parcel in) {
        this();
        id = in.readLong();
        recipeName = in.readString();
        servings = in.readInt();
        imageUrl = in.readString();
        in.readTypedList(ingredients, Ingredient.CREATOR);
        in.readTypedList(steps, Step.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(recipeName);
        dest.writeInt(servings);
        dest.writeString(imageUrl);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
