package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.InnRepository;
import domain.Innkeeper;
import domain.Inn;

@Service
@Transactional
public class InnService {

	// Managed repository
	@Autowired
	private InnRepository innRepository;

	// Suporting services
	@Autowired
	private InnkeeperService innkeeperService;

	// Constructors

	public InnService() {
		super();
	}

	// Simple CRUD methods

	public Inn create() {
		Inn res;
		res = new Inn();

		Innkeeper innkeeper = innkeeperService.findByPrincipal();
		res.setInnkeeper(innkeeper);

		return res;
	}

	public Collection<Inn> findAll() {
		Collection<Inn> res;
		res = this.innRepository.findAll();
		return res;
	}

	public Inn findOne(final int id) {
		Assert.isTrue(id != 0);
		Inn res;
		res = this.innRepository.findOne(id);
		return res;
	}

	public Inn save(final Inn inn) {
		Assert.notNull(inn);
		this.innkeeperService.checkAuthority();

		Inn res;

		res = this.innRepository.save(inn);
		return res;
	}

	public void delete(Inn inn) {
		Assert.notNull(inn);
		Assert.isTrue(inn.getId() != 0);
		Assert.isTrue(this.innRepository.exists(inn.getId()));
		this.innRepository.delete(inn);
	}

	// Other business methods

	public void flush() {
		this.innRepository.flush();
	}

	public Collection<Inn> findCcExpirationYear(int a, int b) {
		return innRepository.findCcExpirationYear(a, b);
	}
}
