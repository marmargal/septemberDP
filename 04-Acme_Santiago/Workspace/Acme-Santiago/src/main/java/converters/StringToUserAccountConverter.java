package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import security.UserAccountRepository;

@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount> {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	public UserAccount convert(final String text) {
		UserAccount result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.userAccountRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}