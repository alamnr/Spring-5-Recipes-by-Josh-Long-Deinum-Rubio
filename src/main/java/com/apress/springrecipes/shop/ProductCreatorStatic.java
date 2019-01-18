package com.apress.springrecipes.shop;

public class ProductCreatorStatic {
	
	public static Product createProduct(String productId) {
		if("aaa".equals(productId)) {
			return new Battery("AAA",2.5);
		} else if("cdrw".equals(productId)) {
			return new Disc("CD-RW",1.5);
		} else if("dvdrw".equals(productId)) {
			return new Disc("DVD-RW",3.0);
		}
		throw new IllegalArgumentException("Unknown Product");
	}

}
