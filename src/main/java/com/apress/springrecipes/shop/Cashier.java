package com.apress.springrecipes.shop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Cashier {

	private String fileName;
	private String path;
	private BufferedWriter writer;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void openFile() throws IOException {
		
		File targetDir = new File(path);
		
		if(!targetDir.exists()) {
			targetDir.mkdir();
		}
		
		File checkoutFile = new File(path,fileName+".txt");
		
		if(!checkoutFile.exists()) {
			checkoutFile.createNewFile();
		}
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile,true)));
	}
	
	public void checkout(ShopingCart cart) throws IOException {
		writer.write(new Date()+"\t"+cart.getItems()+"\r\n");
		writer.flush();
	}
	
	public void closeFile() throws IOException {
		writer.close();
	}

}
