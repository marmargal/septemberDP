package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Stand;

@Component
@Transactional
public class StandToStringConverter implements Converter<Stand, String> {

	@Override
	public String convert(final Stand stand) {
		String result;

		if (stand == null)
			result = null;
		else
			result = String.valueOf(stand.getId());
		return result;
	}
}
