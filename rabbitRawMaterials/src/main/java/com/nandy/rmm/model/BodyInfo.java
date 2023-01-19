package com.nandy.rmm.model;

import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;

import com.nandy.rmm.event.CascadeSave;

//@Document(collection = "body")
public class BodyInfo {

	//@Id	
	private String id;
	
	private String type;
	private String thickness;
	
	//@DBRef  //no one-to-many required for mongodb
	//@CascadeSave //Cascade All Custom Annotation for mongodb - save child then parent
	private List<EngineInfo> engine;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public List<EngineInfo> getEngine() {
		return engine;
	}

	public void setEngine(List<EngineInfo> engine) {
		this.engine = engine;
	}

	public BodyInfo(String id, String type, String thickness, List<EngineInfo> engine) {
		this.id = id;
		this.type = type;
		this.thickness = thickness;
		this.engine = engine;
	}
	
	public BodyInfo(String type, String thickness, List<EngineInfo> engine) {
		this.type = type;
		this.thickness = thickness;
		this.engine = engine;
	}

	public BodyInfo() {
		
	}

	@Override
	public String toString() {
		return "BodyInfo [id=" + id + ", type=" + type + ", thickness=" + thickness + ", engine=" + engine + "]";
	}
	
	
	
}
