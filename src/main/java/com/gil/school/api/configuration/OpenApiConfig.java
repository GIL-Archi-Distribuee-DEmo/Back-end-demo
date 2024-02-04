package com.gil.school.api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(
	info=@io.swagger.v3.oas.annotations.info.Info(
		title ="gil Engine API" ,
		description = "This Project is build by GIL M1 ROUEN  " ,
		version = "1.0" ,
	contact =@io.swagger.v3.oas.annotations.info.Contact(
		url = "Gil ",
		name = "GIL",
		email = "email")
	)
)
public class OpenApiConfig {
}
