package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.HikeRepository;
import domain.Hike;

@Component
@Transactional
public class StringToHikeConverter implements Converter<String, Hike>{

	@Autowired
	HikeRepository	hikeRepository;


	@Override
	public Hike convert(final String text) {
		Hike result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.hikeRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
