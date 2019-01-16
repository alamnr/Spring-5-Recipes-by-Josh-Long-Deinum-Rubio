package com.apress.springrecipes;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.BufferedWriter;
import java.io.Writer;
import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.sequence.config.ShopConfiguration;
import com.apress.springrecipes.shop.Cashier;

public class SpringCoreTaskTest {

	@Test
	public void testPojoInitializationAndDestructionWithAnnotation()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field privateField = Cashier.class.getDeclaredField("writer");
		privateField.setAccessible(true);

		Field privateFieldOut = BufferedWriter.class.getDeclaredField("out");
		privateFieldOut.setAccessible(true);

		Cashier cashier = new Cashier();

		BufferedWriter fieldValue = (BufferedWriter) privateField.get(cashier);
		System.out.println("fieldValue = " + fieldValue);

		assertNull(fieldValue);

		ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

		cashier = context.getBean("cashier", Cashier.class);
		fieldValue = (BufferedWriter) privateField.get(cashier);
		System.out.println("fieldValue = " + fieldValue);

		Writer outValue = (Writer) privateFieldOut.get(fieldValue);
		System.out.println("outValue = " + outValue);

		assertNotNull(fieldValue);
		assertNotNull(outValue);

		((ConfigurableApplicationContext) context).close();

		fieldValue = (BufferedWriter) privateField.get(cashier);
		System.out.println("fieldValue = " + fieldValue);

		outValue = (Writer) privateFieldOut.get(fieldValue);
		System.out.println("outValue = " + outValue);

		assertNotNull(fieldValue);

		assertNull(outValue);

	}

}
