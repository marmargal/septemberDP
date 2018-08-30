package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Voluntary;

@Component
@Transactional
public class VoluntaryToStringConverter implements Converter<Voluntary, String> {

	@Override
	public String convert(final Voluntary voluntary) {
		String result;

		if (voluntary == null)
			result = null;
		else
			result = String.valueOf(voluntary.getId());
		return result;
	}
}
