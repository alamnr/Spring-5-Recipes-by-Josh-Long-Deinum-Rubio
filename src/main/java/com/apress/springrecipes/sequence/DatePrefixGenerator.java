package com.apress.springrecipes.sequence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DatePrefixGenerator implements PrefixGenerator {
	
	private String pattern;

	public DatePrefixGenerator() {
		super();
		
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPrefix() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }

	@Override
	public String toString() {
		return "DatePrefixGenerator [pattern=" + pattern + "]";
	}
	
	
	

}
