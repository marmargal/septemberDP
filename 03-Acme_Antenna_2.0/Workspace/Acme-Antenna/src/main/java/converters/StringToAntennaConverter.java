package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AntennaRepository;
import domain.Antenna;

@Component
@Transactional
public class StringToAntennaConverter implements Converter<String, Antenna>{

	@Autowired
	AntennaRepository	antennaRepository;


	@Override
	public Antenna convert(final String text) {
		Antenna result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.antennaRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
