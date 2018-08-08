
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Subscription;

@Component
@Transactional
public class SubscriptionToStringConverter implements Converter<Subscription, String> {

	@Override
	public String convert(final Subscription subscription) {
		String result;

		if (subscription == null)
			result = null;
		else
			result = String.valueOf(subscription.getId());
		return result;
	}

}
