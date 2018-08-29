
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Inn;

@Component
@Transactional
public class InnToStringConverter implements Converter<Inn, String> {

	@Override
	public String convert(final Inn inn) {
		String result;

		if (inn == null)
			result = null;
		else
			result = String.valueOf(inn.getId());
		return result;
	}

}
