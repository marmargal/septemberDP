
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Walk;

@Component
@Transactional
public class WalkToStringConverter implements Converter<Walk, String> {

	@Override
	public String convert(final Walk walk) {
		String result;

		if (walk == null)
			result = null;
		else
			result = String.valueOf(walk.getId());
		return result;
	}

}
