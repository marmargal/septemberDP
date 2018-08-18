package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.InkeeperRepository;
import domain.Inkeeper;

@Component
@Transactional
public class StringToInkeeperConverter implements Converter<String, Inkeeper> {

	@Autowired
	InkeeperRepository	inkeeperRepository;

	@Override
	public Inkeeper convert(final String text) {
		Inkeeper result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.inkeeperRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}