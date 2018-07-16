package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Visa;

@Component
@Transactional
public class VisaToStringConverter implements Converter<Visa, String> {

	@Override
	public String convert(final Visa visa) {
		String result;

		if (visa == null)
			result = null;
		else
			result = String.valueOf(visa.getId());

		return result;
	}

}
