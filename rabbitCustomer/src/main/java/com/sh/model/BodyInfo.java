package com.sh.model;

import java.util.List;


//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class BodyInfo {

	private String id;
	
	private String type;
	private String thickness;
	
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
		super();
		this.id = id;
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
