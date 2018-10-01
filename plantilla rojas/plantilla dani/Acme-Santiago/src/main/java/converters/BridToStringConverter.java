
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Brid;

@Component
@Transactional
public class BridToStringConverter implements Converter<Brid, String> {

	@Override
	public String convert(final Brid brid) {
		String result;

		if (brid == null)
			result = null;
		else
			result = String.valueOf(brid.getId());
		return result;
	}

}
