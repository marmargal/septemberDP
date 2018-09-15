
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Amenity;

@Component
@Transactional
public class AmenityToStringConverter implements Converter<Amenity, String> {

	@Override
	public String convert(final Amenity amenity) {
		String result;

		if (amenity == null)
			result = null;
		else
			result = String.valueOf(amenity.getId());
		return result;
	}

}
