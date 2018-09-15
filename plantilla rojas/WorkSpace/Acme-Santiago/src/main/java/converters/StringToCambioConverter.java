package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CambioRepository;
import domain.Cambio;

@Component
@Transactional
public class StringToCambioConverter implements Converter<String, Cambio> {

	@Autowired
	CambioRepository cambioRepository;

	@Override
	public Cambio convert(final String text) {
		Cambio result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.cambioRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
