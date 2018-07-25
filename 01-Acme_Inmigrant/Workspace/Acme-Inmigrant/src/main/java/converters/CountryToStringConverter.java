package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Country;

@Component
@Transactional
public class CountryToStringConverter implements Converter<Country, String> {

	@Override
	public String convert(Country country) {
		String result;

		if (country == null)
			result = null;
		else
			result = String.valueOf(country.getId());

		return result;
	}

}
