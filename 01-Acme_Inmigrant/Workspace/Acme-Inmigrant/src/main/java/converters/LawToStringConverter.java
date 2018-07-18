package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Law;

@Component
@Transactional
public class LawToStringConverter implements Converter<Law, String> {

	@Override
	public String convert(Law law) {
		String result;

		if (law == null)
			result = null;
		else
			result = String.valueOf(law.getId());

		return result;
	}
}
