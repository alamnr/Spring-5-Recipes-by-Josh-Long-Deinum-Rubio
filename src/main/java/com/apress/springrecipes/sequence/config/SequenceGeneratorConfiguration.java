package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.apress.springrecipes.sequence.SequenceGenerator;

@Configuration
@ComponentScan(
		includeFilters= {
				@ComponentScan.Filter(
						type = FilterType.REGEX,
						pattern = {"com.apress.springrecipes.sequence.*Dao",
									"com.apress.springrecipes.sequence.*Service"})
		},
		excludeFilters= {
				@ComponentScan.Filter(
						type= FilterType.ANNOTATION,
						classes= {org.springframework.stereotype.Controller.class})	}
		
		)
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
