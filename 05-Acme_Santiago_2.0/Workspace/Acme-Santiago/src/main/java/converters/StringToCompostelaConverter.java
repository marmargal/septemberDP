
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CompostelaRepository;
import domain.Compostela;

@Component
@Transactional
public class StringToCompostelaConverter implements Converter<String, Compostela> {

	@Autowired
	CompostelaRepository	compostelaRepository;


	@Override
	public Compostela convert(final String text) {
		Compostela result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.compostelaRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
