
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Decision;

@Component
@Transactional
public class DecisionToStringConverter implements Converter<Decision, String> {

	@Override
	public String convert(final Decision decision) {
		String result;

		if (decision == null)
			result = null;
		else
			result = String.valueOf(decision.getId());
		return result;
	}

}
