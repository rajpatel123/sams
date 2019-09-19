package com.e.driver.Model.Category;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceResponse {

    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

