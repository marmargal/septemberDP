package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.WorkSectionRepository;

import domain.WorkSection;

@Component
@Transactional
public class StringToWorkSectionConverter implements Converter<String, WorkSection> {

	@Autowired
	WorkSectionRepository workSectionRepository;

	@Override
	public WorkSection convert(final String text) {
		WorkSection result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.workSectionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
