package com.example.demoWebClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class DemoWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebClientApplication.class, args);
		String strRegex = "\\*";
		String strReplacement = "\\\\\\\\";
		String name="hello\\oew\\hi";
		Pattern p = Pattern.compile(strRegex);
		Matcher m = p.matcher(name);
		name = m.replaceAll(strReplacement);
		System.out.println(name);
	}

}
