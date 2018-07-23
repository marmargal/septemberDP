package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ActorRepository;
import repositories.DecisionRepository;

import domain.Actor;
import domain.Decision;

@Component
@Transactional
public class StringToDecisionConverter implements Converter<String, Decision>{

	@Autowired
	DecisionRepository	decisionRepository;


	@Override
	public Decision convert(final String text) {
		Decision result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.decisionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
