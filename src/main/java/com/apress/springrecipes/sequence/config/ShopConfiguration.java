package com.apress.springrecipes.sequence.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

import com.apress.springrecipes.shop.BannerLoader;
import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Cashier;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ProductCreatorInstance;
import com.apress.springrecipes.shop.ProductCreatorStatic;

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
	public ProductCreatorInstance productCreatorInstanceMethodfactory() {
		
		ProductCreatorInstance productCreatorInstanceFactory = new ProductCreatorInstance();
		Map<String,Product> products = new HashMap<>();
		products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Disc("CD-RW", 1.5));
        products.put("dvdrw", new Disc("DVD-RW", 3.0));
        productCreatorInstanceFactory.setProducts(products);
        return productCreatorInstanceFactory;
	}

	/*
	 * @Bean public Product aaa() { Battery p1 = new Battery(); p1.setName("AAA");
	 * p1.setPrice(2.5); p1.setRechargeable(true); return p1; }
	 */
	
	@Bean
	public Product aaa() {
		// using static factory method
		//return ProductCreatorStatic.createProduct("aaa");
		
		// using instance factory method
		return productCreatorInstanceMethodfactory().createProduct("aaa");
	}

	/*
	 * @Bean public Product cdrw() { Disc p2 = new Disc("CD-RW", 1.5);
	 * p2.setCapacity(700); return p2; }
	 */
	
	@Bean
	public Product cdrw() {
		// using static factory method
		// return ProductCreatorStatic.createProduct("cdrw");
		
		// using instance factory method
		return productCreatorInstanceMethodfactory().createProduct("cdrw");
	}

	/*
	 * @Bean public Product dvdrw() { Disc p2 = new Disc("DVD-RW", 3.0);
	 * p2.setCapacity(700); p2.setPrice(specialEndofYearDiscount); return p2; }
	 */
	
	@Bean
	public Product dvdrw() {
		// using static factory method
		// return ProductCreatorStatic.createProduct("dvdrw");
		
		// using instance factory method
		return productCreatorInstanceMethodfactory().createProduct("dvdrw");
	}

	@Bean
	public BannerLoader bannerLoader() {
		BannerLoader bl = new BannerLoader();
		bl.setBanner(banner);
		return bl;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	// @Bean(initMethod="openFile", destroyMethod="closeFile")
	@Bean
	public Cashier cashier() {

		String path = System.getProperty("java.io.tmpdir") + "/cashier";
		Cashier c1 = new Cashier();
		c1.setFileName("checkout");
		c1.setPath(path);
		System.out.println(path.toString());
		return c1;
	}

}
