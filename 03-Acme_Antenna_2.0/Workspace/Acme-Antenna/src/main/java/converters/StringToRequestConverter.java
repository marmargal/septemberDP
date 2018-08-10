package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RequestRepository;
import domain.Request;

@Component
@Transactional
public class StringToRequestConverter implements Converter<String, Request> {

	@Autowired
	RequestRepository requestRepository;

	@Override
	public Request convert(final String text) {
		Request result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.requestRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
