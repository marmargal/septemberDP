package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Boss;

@Component
@Transactional
public class BossToStringConverter implements Converter<Boss, String> {

	@Override
	public String convert(final Boss boss) {
		String result;

		if (boss == null)
			result = null;
		else
			result = String.valueOf(boss.getId());
		return result;
	}
}
