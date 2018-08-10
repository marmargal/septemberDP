package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Banner;

@Component
@Transactional
public class BannerToStringConverter implements Converter<Banner, String> {
	
	@Override
	public String convert(final Banner banner) {
		String result;

		if (banner == null)
			result = null;
		else
			result = String.valueOf(banner.getId());

		return result;
	}
}
