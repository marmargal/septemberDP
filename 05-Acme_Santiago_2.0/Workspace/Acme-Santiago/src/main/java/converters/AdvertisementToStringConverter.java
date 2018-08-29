
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Advertisement;

@Component
@Transactional
public class AdvertisementToStringConverter implements Converter<Advertisement, String> {

	@Override
	public String convert(final Advertisement advertisement) {
		String result;

		if (advertisement == null)
			result = null;
		else
			result = String.valueOf(advertisement.getId());
		return result;
	}

}
