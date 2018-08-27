package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Component
@Transactional
public class StringToConfigurationConverter implements Converter<String, Configuration>{

	@Autowired
	ConfigurationRepository	configurationRepository;

	@Override
	public Configuration convert(final String text) {
		Configuration result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.configurationRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
