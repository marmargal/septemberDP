package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarehouseRepository;
import domain.Warehouse;


@Service
@Transactional
public class WarehouseService {

	// Managed repository

	@Autowired
	private WarehouseRepository warehouseRepository;

	// Suporting services

	@Autowired
	private BossService bossService;
	
	// Constructors

	public WarehouseService() {
		super();
	}

	// Simple CRUD methods

	public Warehouse create() {
		Warehouse res = new Warehouse();
		
		res.setCapacity(0);
		res.setStock(0);
		res.setDogFood(0);
		res.setCatFood(0);
		res.setBirdFood(0);
		
		return res;

	}

	public Collection<Warehouse> findAll() {
		Collection<Warehouse> res;
		res = warehouseRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Warehouse findOne(int warehouseId) {
		Assert.isTrue(warehouseId != 0);
		Warehouse res;
		res = warehouseRepository.findOne(warehouseId);
		return res;
	}

	public Warehouse save(Warehouse warehouse) {
		this.bossService.checkAuthority();
		Warehouse res;
		res = warehouseRepository.saveAndFlush(warehouse);
		return res;
	}

	public void delete(Warehouse warehouse) {
		Assert.notNull(warehouse);
		Assert.isTrue(warehouse.getId() != 0);
		Assert.isTrue(warehouseRepository.exists(warehouse.getId()));
		warehouseRepository.delete(warehouse);
	}

	// Other business methods
	
	

}
