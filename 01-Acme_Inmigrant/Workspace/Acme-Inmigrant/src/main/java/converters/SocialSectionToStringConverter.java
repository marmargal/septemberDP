package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SocialSection;

@Component
@Transactional
public class SocialSectionToStringConverter implements Converter<SocialSection, String> {

	@Override
	public String convert(final SocialSection socialSection) {
		String result;

		if (socialSection == null)
			result = null;
		else
			result = String.valueOf(socialSection.getId());

		return result;
	}
}