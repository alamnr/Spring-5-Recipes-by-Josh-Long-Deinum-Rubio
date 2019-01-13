package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.sequence.SequenceGenerator;

@Configuration
public class SequenceGeneratorConfiguration {
	
	@Bean
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator sequenceGenerator = new SequenceGenerator();
		sequenceGenerator.setPrefix("30");
		sequenceGenerator.setSuffix("A");
		sequenceGenerator.setInitial(10000);
		return sequenceGenerator;
	}

}
