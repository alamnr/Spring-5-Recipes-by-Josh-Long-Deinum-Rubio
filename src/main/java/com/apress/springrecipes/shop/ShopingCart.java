package com.apress.springrecipes.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy // the POJO is decorated with the @Lazy annotation, if the POJO is never required by the application or referenced by another POJO, it’s never instantiated
public class ShopingCart {

	private List<Product> items = new ArrayList<Product>();

	public void addItem(Product item) {
		items.add(item);
	}

	public List<Product> getItems() {
		return items;
	}

}
