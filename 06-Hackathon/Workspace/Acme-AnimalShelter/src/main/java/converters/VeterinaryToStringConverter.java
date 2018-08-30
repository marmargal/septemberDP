package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Veterinary;

@Component
@Transactional
public class VeterinaryToStringConverter implements
		Converter<Veterinary, String> {

	@Override
	public String convert(final Veterinary veterinary) {
		String result;

		if (veterinary == null)
			result = null;
		else
			result = String.valueOf(veterinary.getId());
		return result;
	}
}
