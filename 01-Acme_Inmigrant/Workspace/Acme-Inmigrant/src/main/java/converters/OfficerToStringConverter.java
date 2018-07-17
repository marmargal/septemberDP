package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Officer;

@Component
@Transactional
public class OfficerToStringConverter implements Converter<Officer, String> {

	@Override
	public String convert(Officer officer) {
		String result;

		if (officer == null)
			result = null;
		else
			result = String.valueOf(officer.getId());

		return result;
	}

}
