
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Hike;

@Component
@Transactional
public class HikeToStringConverter implements Converter<Hike, String> {

	@Override
	public String convert(final Hike hike) {
		String result;

		if (hike == null)
			result = null;
		else
			result = String.valueOf(hike.getId());
		return result;
	}

}
