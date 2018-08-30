package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Pet;

@Component
@Transactional
public class PetToStringConverter implements
		Converter<Pet, String> {

	@Override
	public String convert(final Pet pet) {
		String result;

		if (pet == null)
			result = null;
		else
			result = String.valueOf(pet.getId());
		return result;
	}
}
