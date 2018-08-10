package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.BannerRepository;
import domain.Banner;

public class StringToBannerConverter implements Converter<String, Banner> {

	@Autowired
	BannerRepository bannerRepository;

	@Override
	public Banner convert(final String text) {
		Banner result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.bannerRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
