package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Inkeeper;

@Component
@Transactional
public class InkeeperToStringConverter implements Converter<Inkeeper, String> {
	
	@Override
	public String convert(final Inkeeper inkeeper) {
		String result;

		if (inkeeper == null)
			result = null;
		else
			result = String.valueOf(inkeeper.getId());

		return result;
	}
}