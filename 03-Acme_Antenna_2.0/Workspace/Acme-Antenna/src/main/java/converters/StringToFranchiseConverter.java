package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.FranchiseRepository;
import domain.Franchise;

@Component
@Transactional
public class StringToFranchiseConverter implements Converter<String, Franchise>{

	@Autowired
	FranchiseRepository	franchiseRepository;


	@Override
	public Franchise convert(final String text) {
		Franchise result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.franchiseRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}