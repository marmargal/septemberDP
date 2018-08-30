package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Notice;

@Component
@Transactional
public class NoticeToStringConverter implements Converter<Notice, String> {

	@Override
	public String convert(final Notice notice) {
		String result;

		if (notice == null)
			result = null;
		else
			result = String.valueOf(notice.getId());
		return result;
	}
}
