package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Donation;

@Component
@Transactional
public class DonationToStringConverter implements
		Converter<Donation, String> {

	@Override
	public String convert(final Donation donation) {
		String result;

		if (donation== null)
			result = null;
		else
			result = String.valueOf(donation.getId());
		return result;
	}
}
