package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;

@Component
@Transactional
public class EventToStringConverter implements Converter<Event, String> {

	@Override
	public String convert(final Event event) {
		String result;

		if (event == null)
			result = null;
		else
			result = String.valueOf(event.getId());
		return result;
	}
}
