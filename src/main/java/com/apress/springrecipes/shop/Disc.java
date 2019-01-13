package com.apress.springrecipes.shop;

public class Disc extends Product {
	
	private int capacity;

	public Disc() {
		super();
	}

	public Disc(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Disc [capacity=" + capacity + ", toString()=" + super.toString() + "]";
	}
	
	

}
