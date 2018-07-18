package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.WorkSection;

@Component
@Transactional
public class WorkSectionToStringConverter implements Converter<WorkSection, String> {

	@Override
	public String convert(final WorkSection workSection) {
		String result;

		if (workSection == null)
			result = null;
		else
			result = String.valueOf(workSection.getId());

		return result;
	}
}