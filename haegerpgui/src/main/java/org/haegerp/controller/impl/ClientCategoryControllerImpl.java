package org.haegerp.controller.impl;

import java.util.List;

import org.haegerp.controller.ClientCategoryController;
import org.haegerp.entity.ClientCategory;
import org.haegerp.entity.repository.client.ClientCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCategoryControllerImpl implements ClientCategoryController {

	@Autowired
	private ClientCategoryRepository clientCategoryRepository;
	
	public List<ClientCategory> getAllCategories() {
		return clientCategoryRepository.findAll();
	}

}