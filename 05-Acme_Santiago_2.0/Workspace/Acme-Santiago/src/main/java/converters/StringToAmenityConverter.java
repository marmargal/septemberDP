package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AmenityRepository;
import domain.Amenity;

@Component
@Transactional
public class StringToAmenityConverter implements Converter<String, Amenity>{

	@Autowired
	AmenityRepository	amenityRepository;


	@Override
	public Amenity convert(final String text) {
		Amenity result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.amenityRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
