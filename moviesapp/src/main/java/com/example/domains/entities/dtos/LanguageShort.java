package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface LanguageShort {
	@Value("#{target.languageId}")
	int getId();
	@Value("#{target.name}")
	String getNombre();
}
