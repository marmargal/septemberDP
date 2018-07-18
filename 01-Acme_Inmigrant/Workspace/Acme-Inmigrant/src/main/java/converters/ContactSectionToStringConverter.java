package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ContactSection;

@Component
@Transactional
public class ContactSectionToStringConverter implements Converter<ContactSection, String> {

	@Override
	public String convert(final ContactSection contactSection) {
		String result;

		if (contactSection == null)
			result = null;
		else
			result = String.valueOf(contactSection.getId());

		return result;
	}
}