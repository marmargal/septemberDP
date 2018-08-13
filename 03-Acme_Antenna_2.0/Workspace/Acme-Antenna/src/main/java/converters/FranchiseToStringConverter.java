package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Franchise;

@Component
@Transactional
public class FranchiseToStringConverter implements Converter<Franchise, String>{

	@Override
	public String convert(final Franchise franchise) {
		String result;

		if (franchise == null)
			result = null;
		else
			result = String.valueOf(franchise.getId());

		return result;
	}
}