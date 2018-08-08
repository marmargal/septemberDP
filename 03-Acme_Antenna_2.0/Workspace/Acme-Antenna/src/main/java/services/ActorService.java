package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private ActorRepository actorRepository;

		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public ActorService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Collection<Actor> findAll() {
			Collection<Actor> result;

			result = this.actorRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Actor findOne(final int actorId) {
			Assert.isTrue(actorId != 0);

			Actor result;

			result = this.actorRepository.findOne(actorId);
			Assert.notNull(result);

			return result;
		}

		public Actor save(final Actor actor) {
			Assert.notNull(actor);
			Actor result;
			Assert.isTrue(actor.equals(this.findByPrincipal()));

			result = this.actorRepository.save(actor);
			return result;
		}

		public void delete(final Actor actor) {
			Assert.notNull(actor);
			Assert.isTrue(actor.getId() != 0);
			Assert.isTrue(this.actorRepository.exists(actor.getId()));
			Assert.isTrue(actor.equals(this.findByPrincipal()));

			this.actorRepository.delete(actor);
		}

		// Other business methods -------------------------------------------------

		public UserAccount findUserAccount(final Actor actor) {
			Assert.notNull(actor);

			UserAccount result;

			result = actor.getUserAccount();

			return result;
		}

		public Actor findByPrincipal() {
			Actor result = null;
			UserAccount userAccount;

			try {
				userAccount = LoginService.getPrincipal();
				Assert.notNull(userAccount);
				result = this.findByUserAccount(userAccount);
				Assert.notNull(result);

			} catch (final Exception e) {
				e.printStackTrace();
			}

			return result;
		}

		public Actor findByUserAccount(final UserAccount userAccount) {
			Assert.notNull(userAccount);

			Actor result;

			result = this.actorRepository.findByUserAccountId(userAccount.getId());
			Assert.notNull(result);

			return result;
		}
}
