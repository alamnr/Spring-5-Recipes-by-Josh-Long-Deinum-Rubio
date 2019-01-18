package com.apress.springrecipes.shop;

import java.util.Map;

public class ProductCreatorInstance {

	private Map<String,Product> products;

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}
	
	public Product createProduct(String productId) {
		Product product = products.get(productId);
		if(null != product) {
			return product;
		}
		throw new IllegalArgumentException("Unknown Product");
	}
	
	
}
