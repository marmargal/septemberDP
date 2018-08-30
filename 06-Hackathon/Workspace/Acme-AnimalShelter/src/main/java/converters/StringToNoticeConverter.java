package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.NoticeRepository;
import domain.Notice;

@Component
@Transactional
public class StringToNoticeConverter implements Converter<String, Notice> {

	@Autowired
	NoticeRepository noticeRepository;

	@Override
	public Notice convert(final String text) {
		Notice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.noticeRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
