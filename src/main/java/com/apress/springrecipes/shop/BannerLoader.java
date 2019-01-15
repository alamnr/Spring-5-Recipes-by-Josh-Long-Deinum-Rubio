package com.apress.springrecipes.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;

public class BannerLoader {
	
	private Resource banner;
	
	
	
	
	public void setBanner(Resource banner) {
		this.banner = banner;
	}




	@PostConstruct
	public void showBanner() throws IOException {
		InputStream in = banner.getInputStream();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		while (true) {
            String line = bufferedReader.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
		bufferedReader.close();
	}
	

}
