
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Antenna;

@Component
@Transactional
public class AntennaToStringConverter implements Converter<Antenna, String> {

	@Override
	public String convert(final Antenna antenna) {
		String result;

		if (antenna == null)
			result = null;
		else
			result = String.valueOf(antenna.getId());
		return result;
	}

}
