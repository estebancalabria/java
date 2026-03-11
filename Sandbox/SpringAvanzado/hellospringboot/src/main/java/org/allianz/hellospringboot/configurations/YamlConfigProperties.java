package org.allianz.hellospringboot.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.*;

@Getter @Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YamlConfigProperties {
	private String idioma;
	
	@org.springframework.beans.factory.annotation.Value("${custom.autor}")
	private String autor;
}
