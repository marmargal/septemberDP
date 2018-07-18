package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PersonalSection;

@Component
@Transactional
public class PersonalSectionToStringConverter implements Converter<PersonalSection, String> {

	@Override
	public String convert(final PersonalSection personalSection) {
		String result;

		if (personalSection == null)
			result = null;
		else
			result = String.valueOf(personalSection.getId());

		return result;
	}
}