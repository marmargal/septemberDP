
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Compostela;

@Component
@Transactional
public class CompostelaToStringConverter implements Converter<Compostela, String> {

	@Override
	public String convert(final Compostela compostela) {
		String result;

		if (compostela == null)
			result = null;
		else
			result = String.valueOf(compostela.getId());
		return result;
	}

}
