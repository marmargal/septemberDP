package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.HandyworkerRepository;

import domain.Handyworker;

@Component
@Transactional
public class StringToHandyworkerConverter implements Converter<String, Handyworker> {

	@Autowired
	HandyworkerRepository handyworkerRepository;

	@Override
	public Handyworker convert(final String text) {
		Handyworker result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.handyworkerRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
