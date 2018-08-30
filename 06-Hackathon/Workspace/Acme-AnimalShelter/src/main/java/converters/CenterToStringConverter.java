package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Center;

@Component
@Transactional
public class CenterToStringConverter implements Converter<Center, String> {

	@Override
	public String convert(final Center center) {
		String result;

		if (center == null)
			result = null;
		else
			result = String.valueOf(center.getId());
		return result;
	}
}
