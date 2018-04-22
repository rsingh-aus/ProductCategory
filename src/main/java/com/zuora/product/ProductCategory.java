package com.zuora.product;

import java.util.List;

public class ProductCategory {

    private String categoryName;
    private List<ProductCategory> subCategories;
    private ProductCategory parentCategory;
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<ProductCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public ProductCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ProductCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
}
