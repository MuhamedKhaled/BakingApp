package com.example.mohamed.bakingapp.models;

import com.google.gson.annotations.SerializedName;

public class RecipeImage {
    @SerializedName("id")
    public long id;

    @SerializedName("image")
    public String imageUrl;

    public RecipeImage() { }

    public RecipeImage(long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeImage that = (RecipeImage) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
