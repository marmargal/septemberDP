package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.InnService;
import services.RegistrytService;
import utilities.AbstractTest;
import domain.Inn;
import domain.Registry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseInnkeeper extends AbstractTest {

	@Autowired
	private InnService innService;

	@Autowired
	private RegistrytService registrytService;

	/*
	 * 21. An actor who is authenticated as an innkeeper must be able to: 1.
	 * Register the day when a user stayed at his or her inn.
	 */

	@Test
	public void RouteAdminTest() {
		final Object testingData[][] = {
				// positivo, el innkeeper1 hace un registro en un inn suyo
				{ "innkeeper1", "02/02/2010", "inn1", null },
				// negativo, el innkeeper1 hace un registro en un inn suyo con
				// la fecha mal
				{ "innkeeper1", "02/02/2019", "inn1",
						IllegalArgumentException.class },
				// negativo, el user1 hace un registro en un inn
				{ "innkeeper1", "02/02/2010", "inn1",
						IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegistryInnkeeper((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);
		}
	}

	private void templateRegistryInnkeeper(String nameInnkeeper,
			String dateName, String innName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameInnkeeper);
			Registry registry = this.registrytService.create();
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			final Date date = format.parse(dateName);
			registry.setDate(date);
			Inn inn = this.innService.findOne(super.getEntityId(innName));
			Collection<Registry> registries = inn.getRegistries();
			registries.add(registry);
			inn.setRegistries(registries);
			this.innService.save(inn);
			Assert.isTrue(this.innService.findOne(inn.getId()).getRegistries()
					.contains(registry));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
