package com.nandy.srm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	private String color;
	private String model;
	private RawMaterialWrapper wrapper;
	@Override
	public String toString() {
		return "Car [color=" + color + ", model=" + model + ", wrapper=" + wrapper + "]";
	}
	
}
