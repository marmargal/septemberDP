package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EducationSection;

@Component
@Transactional
public class EducationSectionToStringConverter implements Converter<EducationSection, String> {

	@Override
	public String convert(final EducationSection educationSection) {
		String result;

		if (educationSection == null)
			result = null;
		else
			result = String.valueOf(educationSection.getId());

		return result;
	}
}