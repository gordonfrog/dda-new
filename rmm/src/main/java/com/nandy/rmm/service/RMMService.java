package com.nandy.rmm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandy.rmm.model.BodyInfo;
import com.nandy.rmm.repository.BodyRepository;

@Service
public class RMMService {
	
	@Autowired
	private BodyRepository repository;

//    public RMMService(BodyRepository bodyRepository) {
//        this.repository = bodyRepository;
//    }

	public BodyInfo create(BodyInfo entity) {  // save the raw material
		return repository.save(entity);
	}

	public List<BodyInfo> get(String type) {
		return repository.findByType(type);
		
	}

	public List<BodyInfo> getAll() {
		return repository.findAll();
	}

}
