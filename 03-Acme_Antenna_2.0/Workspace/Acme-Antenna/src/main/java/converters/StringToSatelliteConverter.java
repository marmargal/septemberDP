package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SatelliteRepository;
import domain.Satellite;

@Component
@Transactional
public class StringToSatelliteConverter implements Converter<String, Satellite>{

	@Autowired
	SatelliteRepository	satelliteRepository;


	@Override
	public Satellite convert(final String text) {
		Satellite result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.satelliteRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
