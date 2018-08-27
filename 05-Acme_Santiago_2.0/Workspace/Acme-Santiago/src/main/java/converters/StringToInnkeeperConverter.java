package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.InnkeeperRepository;
import domain.Innkeeper;

@Component
@Transactional
public class StringToInnkeeperConverter implements Converter<String, Innkeeper> {

	@Autowired
	InnkeeperRepository	innkeeperRepository;

	@Override
	public Innkeeper convert(final String text) {
		Innkeeper result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.innkeeperRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}