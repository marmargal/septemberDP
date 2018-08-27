package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Innkeeper;

@Component
@Transactional
public class InnkeeperToStringConverter implements Converter<Innkeeper, String> {
	
	@Override
	public String convert(final Innkeeper innkeeper) {
		String result;

		if (innkeeper == null)
			result = null;
		else
			result = String.valueOf(innkeeper.getId());

		return result;
	}
}