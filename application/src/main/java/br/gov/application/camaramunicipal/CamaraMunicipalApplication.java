package br.gov.application.camaramunicipal;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamaraMunicipalApplication {
	@PostConstruct
	public void setTimeZone() {
	  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args)
	{
		SpringApplication.run(CamaraMunicipalApplication.class, args);
	}
}