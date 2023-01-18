package com.nandy.rmm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "engine")
public class EngineInfo {

	@Id	
	private String id;
	
	private String make;
	private String model;
	private String type;
}
