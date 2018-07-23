package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Investigator;

@Component
@Transactional
public class InvestigatorToStringConverter implements Converter<Investigator, String>{

	@Override
	public String convert(final Investigator investigator) {
		String result;

		if (investigator == null)
			result = null;
		else
			result = String.valueOf(investigator.getId());
		return result;
	}
}
