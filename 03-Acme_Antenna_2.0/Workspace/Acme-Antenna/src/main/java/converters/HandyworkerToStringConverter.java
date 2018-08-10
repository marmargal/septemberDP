package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Handyworker;

@Component
@Transactional
public class HandyworkerToStringConverter implements Converter<Handyworker, String> {
	
	@Override
	public String convert(final Handyworker handyworker) {
		String result;

		if (handyworker == null)
			result = null;
		else
			result = String.valueOf(handyworker.getId());

		return result;
	}
}
