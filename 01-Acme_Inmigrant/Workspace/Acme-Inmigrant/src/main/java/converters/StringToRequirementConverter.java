package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RequirementRepository;

import domain.Requirement;

@Component
@Transactional
public class StringToRequirementConverter implements Converter<String, Requirement>{
	
	@Autowired
	RequirementRepository	requirementRepository;

	@Override
	public Requirement convert(final String text) {
		Requirement result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.requirementRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
