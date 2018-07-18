package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PersonalSectionRepository;

import domain.PersonalSection;

@Component
@Transactional
public class StringToPersonalSectionConverter implements Converter<String, PersonalSection> {

	@Autowired
	PersonalSectionRepository personalSectionRepository;

	@Override
	public PersonalSection convert(final String text) {
		PersonalSection result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.personalSectionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
