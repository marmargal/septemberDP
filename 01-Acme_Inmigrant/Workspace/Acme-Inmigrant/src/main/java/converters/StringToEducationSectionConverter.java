package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EducationSectionRepository;

import domain.EducationSection;

@Component
@Transactional
public class StringToEducationSectionConverter implements Converter<String, EducationSection> {

	@Autowired
	EducationSectionRepository educationSectionRepository;

	@Override
	public EducationSection convert(final String text) {
		EducationSection result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.educationSectionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
