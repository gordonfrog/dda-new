package com.nandy.rmm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandy.rmm.model.BodyInfo;
//import com.nandy.rmm.repository.BodyRepository;

@Service
public class RMMService {
	
//	@Autowired
//	private BodyRepository repository;

//    public RMMService(BodyRepository bodyRepository) {
//        this.repository = bodyRepository;
//    }
	
	private List<BodyInfo> bodies;

	public RMMService() {
      this.bodies = new ArrayList<BodyInfo>(); 
      BodyInfo body = new BodyInfo();
      body.setEngine(null);
      body.setId("1");
      body.setThickness("Thick");
      body.setType("type1");
      this.bodies.add(body);
  }
		
	public BodyInfo create(BodyInfo entity) {  // save the raw material
		//return repository.save(entity);
		if (this.bodies == null) {this.bodies = new ArrayList<BodyInfo>();}
		else {this.bodies.add(entity); return entity;}
		return null;
	}

	public List<BodyInfo> get(String type) {
		//return repository.findByType(type);
		if (this.bodies == null) {this.bodies = new ArrayList<BodyInfo>();}
		else {
			if (type != null && type.length() > 0) {return this.bodies.stream().filter(b -> b.getType().equalsIgnoreCase(type)).collect(Collectors.toList());}
		}
		return null;
	}

	public List<BodyInfo> getAll() {
		//return repository.findAll();
		if (this.bodies == null) {this.bodies = new ArrayList<BodyInfo>();}
		else {return this.bodies;}
		return null;
	}

}
