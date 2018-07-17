package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.OfficerRepository;
import domain.Officer;

public class StringToOfficerConverter implements Converter<String, Officer>{

	@Autowired
	OfficerRepository	officerRepository;

	@Override
	public Officer convert(final String text) {
		Officer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.officerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
