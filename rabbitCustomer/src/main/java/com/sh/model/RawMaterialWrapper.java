package com.sh.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class RawMaterialWrapper {
	
	@JsonProperty(value="carBodies")
	List<BodyInfo> bodyInfos;
	
	@JsonProperty(value="carBody")
	BodyInfo bodyInfo;

	public RawMaterialWrapper(List<BodyInfo> bodyInfos) {
		this.bodyInfos = bodyInfos;
	}
	
	public RawMaterialWrapper(BodyInfo bodyInfo) {
		this.bodyInfo = bodyInfo;
	}

}
