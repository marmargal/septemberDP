
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Cambio;

@Component
@Transactional
public class CambioToStringConverter implements Converter<Cambio, String> {

	@Override
	public String convert(final Cambio cambio) {
		String result;

		if (cambio == null)
			result = null;
		else
			result = String.valueOf(cambio.getId());
		return result;
	}

}
