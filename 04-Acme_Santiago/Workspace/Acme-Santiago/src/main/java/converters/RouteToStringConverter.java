
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Route;

@Component
@Transactional
public class RouteToStringConverter implements Converter<Route, String> {

	@Override
	public String convert(final Route route) {
		String result;

		if (route == null)
			result = null;
		else
			result = String.valueOf(route.getId());
		return result;
	}

}
