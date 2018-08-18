package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HikeRepository;
import domain.Hike;

@Service
@Transactional
public class HikeService {

	// Managed repository
	@Autowired
	private HikeRepository hikeRepository;

	// Suporting services
	// Constructors

	public HikeService() {
		super();
	}

	// Simple CRUD methods

	public Hike create() {
		Hike res;
		res = new Hike();

		return res;
	}

	public Collection<Hike> findAll() {
		Collection<Hike> res;
		res = this.hikeRepository.findAll();
		return res;
	}

	public Hike findOne(final int id) {
		Assert.isTrue(id != 0);
		Hike res;
		res = this.hikeRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Hike save(final Hike hike) {
		Assert.notNull(hike);
		Hike res;

		res = this.hikeRepository.save(hike);
		return res;
	}

	public void delete(Hike hike) {
		Assert.notNull(hike);
		Assert.isTrue(hike.getId() != 0);
		Assert.isTrue(this.hikeRepository.exists(hike.getId()));
		this.hikeRepository.delete(hike);
	}

	// Other business methods

	public void flush() {
		this.hikeRepository.flush();
	}
}
