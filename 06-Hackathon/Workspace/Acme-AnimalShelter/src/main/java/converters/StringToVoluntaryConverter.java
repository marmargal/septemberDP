package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.VoluntaryRepository;
import domain.Voluntary;

@Component
@Transactional
public class StringToVoluntaryConverter implements Converter<String, Voluntary> {

	@Autowired
	VoluntaryRepository voluntaryRepository;

	@Override
	public Voluntary convert(final String text) {
		Voluntary result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.voluntaryRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
