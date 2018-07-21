package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Answer;

@Component
@Transactional
public class AnswerToStringConverter implements Converter<Answer, String> {

	@Override
	public String convert(Answer answer) {
		String result;

		if (answer == null)
			result = null;
		else
			result = String.valueOf(answer.getId());

		return result;
	}

}
