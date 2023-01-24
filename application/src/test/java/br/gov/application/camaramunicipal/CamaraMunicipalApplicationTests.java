package br.gov.application.camaramunicipal;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamaraMunicipalApplicationTestsc {

	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}