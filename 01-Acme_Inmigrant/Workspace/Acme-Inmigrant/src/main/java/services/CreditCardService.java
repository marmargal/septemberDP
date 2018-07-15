package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed repositories --------------------------------------------------

	@Autowired
	private CreditCardRepository creditCardRepository;

	// Constructor -----------------------------------------------------------

	public CreditCardService() {
		super();
	}

	// Supporting services ----------------------------------------------------

	// Simple CRUD methods ----------------------------------------------------

	public CreditCard create() {
		final CreditCard result;
		result = new CreditCard();
		return result;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;
		result = this.creditCardRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public CreditCard findOne(final int creditCardId) {
		CreditCard result;

		result = this.creditCardRepository.findOne(creditCardId);
		Assert.notNull(result);

		return result;
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		CreditCard saved;
		if (this.creditCardRepository.findSameCreditcard(
				creditCard.getNumber(), creditCard.getCVV()) != null)
			saved = this.creditCardRepository.findSameCreditcard(
					creditCard.getNumber(), creditCard.getCVV());
		else
			saved = this.creditCardRepository.save(creditCard);
		return saved;
	}

	public void delete(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		this.creditCardRepository.delete(creditCard);
	}

}