package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Requirement;

@Component
@Transactional
public class RequirementToStringConverter implements Converter<Requirement, String>{

	@Override
	public String convert(Requirement requirement) {
		String result;

		if (requirement == null)
			result = null;
		else
			result = String.valueOf(requirement.getId());

		return result;
	}

}
