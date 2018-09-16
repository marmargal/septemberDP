package services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PruebaRepository;
import domain.Prueba;
import domain.User;
import forms.PruebaForm;

@Service
@Transactional
public class PruebaService {

	// Managed repository
	@Autowired
	private PruebaRepository pruebaRepository;

	// Suporting services
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator;

	// Constructors

	public PruebaService() {
		super();
	}

	// Simple CRUD methods

	public Prueba create() {
		this.userService.checkAuthority();
		Prueba res;
		res = new Prueba();
		User user = userService.findByPrincipal();
		
		res.setTracer(generateUniqueCode());
		res.setUser(user);
		
		return res;
	}

	public Collection<Prueba> findAll() {
		Collection<Prueba> res;
		res = this.pruebaRepository.findAll();
		return res;
	}

	public Prueba findOne(final int id) {
		Assert.isTrue(id != 0);
		Prueba res;
		res = this.pruebaRepository.findOne(id);
		return res;
	}

	public Prueba save(final Prueba prueba) {
		this.userService.checkAuthority();
		Assert.notNull(prueba);
		
		Prueba res;
		Date moment = new Date(System.currentTimeMillis() - 1000);
		
		prueba.setMoment(moment);
		
		res = this.pruebaRepository.save(prueba);
		return res;
	}

	public void delete(Prueba prueba) {
		this.userService.checkAuthority();
		if(prueba.getId() != 0){
			Assert.isTrue(prueba.getRoute() == null);
		}
		Assert.notNull(prueba);
		Assert.isTrue(prueba.getId() != 0);
		Assert.isTrue(this.pruebaRepository.exists(prueba.getId()));
		this.pruebaRepository.delete(prueba);
	}

	// Other business methods

	public void flush() {
		this.pruebaRepository.flush();
	}
	
	private String generateUniqueCode() {
		String tracer;
		LocalDate date;
		
		date = new LocalDate();
		
		//? es como un if
		tracer = 
				String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) +
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear()) +
					String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-" +
					generateAlphanumercicRandom();

		return tracer;
	}
	
	public String generateAlphanumercicRandom(){
		String upper = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		String lower = upper.toLowerCase(Locale.ROOT);
		String digits = "0123456789";
		String alphanumeric = upper + lower + digits + "_";
		
		SecureRandom rnd = new SecureRandom();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<5; i++){
			sb.append(alphanumeric.charAt(rnd.nextInt(alphanumeric.length())));
		}
		return sb.toString();
	}

	public Collection<Prueba> findPruebasByUser(int userId){
		Collection<Prueba> pruebas = new ArrayList<Prueba>();
		pruebas = this.pruebaRepository.findPruebasByUser(userId);
		return pruebas;
	}
	
	public Collection<Prueba> findPruebasWithoutDecision(){
		Collection<Prueba> pruebas = new ArrayList<Prueba>();
		pruebas = this.pruebaRepository.findPruebasWithoutDecision();
		return pruebas;
	}
	
	public Collection<Prueba> findPruebasByRouteAndWithoutDecision(int routeId){
		Collection<Prueba> pruebas = new ArrayList<Prueba>();
		pruebas = this.pruebaRepository.findPruebasByRouteAndWithoutDecision(routeId);
		return pruebas;
	}
	
	public PruebaForm construct(Prueba prueba) {
		PruebaForm res = new PruebaForm();

		res.setId(prueba.getId());
		res.setTitle(prueba.getTitle());
		res.setDescription(prueba.getDescription());
		res.setRoute(prueba.getRoute());
		res.setGauge(prueba.getGauge());

		return res;
	}

	public Prueba reconstruct(PruebaForm pruebaForm, BindingResult binding) {
		Assert.notNull(pruebaForm);

		Prueba res = new Prueba();

		if (pruebaForm.getId() != 0)
			res = this.findOne(pruebaForm.getId());
		else
			res = this.create();

		res.setTitle(pruebaForm.getTitle());
		res.setDescription(pruebaForm.getDescription());
		res.setRoute(pruebaForm.getRoute());
		res.setGauge(pruebaForm.getGauge());

		this.validator.validate(res, binding);

		return res;
	}
	
	

}
