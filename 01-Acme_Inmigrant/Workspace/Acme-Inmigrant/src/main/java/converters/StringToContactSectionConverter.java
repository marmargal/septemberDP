package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ContactSectionRepository;

import domain.ContactSection;

@Component
@Transactional
public class StringToContactSectionConverter implements Converter<String, ContactSection> {

	@Autowired
	ContactSectionRepository contactSectionRepository;

	@Override
	public ContactSection convert(final String text) {
		ContactSection result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.contactSectionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
