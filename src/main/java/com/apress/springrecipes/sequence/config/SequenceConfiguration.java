package com.apress.springrecipes.sequence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import com.apress.springrecipes.sequence.DatePrefixGenerator;
import com.apress.springrecipes.sequence.PrefixGenerator;
import com.apress.springrecipes.sequence.SequenceGenerator;

@Configuration
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {

	/*
	 * @Bean public DatePrefixGenerator datePrefixGenerator() {
	 * 
	 * DatePrefixGenerator dpg = new DatePrefixGenerator();
	 * dpg.setPattern("yyyyMMdd"); return dpg;
	 * 
	 * }
	 */

	@Value("#{datePrefixGenerator}")
	private PrefixGenerator prefixGenerator;

	@Bean
	@DependsOn("datePrefixGenerator") // the declaration @DependsOn("datePrefixGenerator") ensures the 	datePrefixGenerator bean is created before the sequenceGenerator bean
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator sequenceGenerator = new SequenceGenerator();
		sequenceGenerator.setInitial(100000);
		sequenceGenerator.setSuffix("A");
		// sequenceGenerator.setPrefixGeneratorProperty(datePrefixGenerator());
		// sequenceGenerator.setPrefixGeneratorProperty(prefixGenerator);
		return sequenceGenerator;

	}

}
