package com.apress.springrecipes.sequence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.apress.springrecipes.shop.BannerLoader;
import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan("com.apress.springrecipes.shop")
public class ShopConfiguration {
	
	@Value("${endofyear.discount:0}")
	private double specialEndofYearDiscount;
	
	@Value("classpath:banner.txt")
	private Resource banner;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public Product aaa() {
		Battery p1 = new Battery();
		p1.setName("AAA");
        p1.setPrice(2.5);
        p1.setRechargeable(true);
        return p1;
	}
	
	@Bean
	public Product cdrw() {
		 Disc p2 = new Disc("CD-RW", 1.5);
	     p2.setCapacity(700);
	     return p2;
	}
	
	@Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 3.0);
        p2.setCapacity(700);
        p2.setPrice(specialEndofYearDiscount);
        return p2;
    }
	
	@Bean
	public BannerLoader bannerLoader()
	{
		BannerLoader bl = new BannerLoader();
		bl.setBanner(banner);
		return bl;
	}

}
