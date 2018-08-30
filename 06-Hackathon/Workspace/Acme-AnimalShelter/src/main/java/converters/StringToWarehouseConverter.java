package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.WarehouseRepository;
import domain.Warehouse;

@Component
@Transactional
public class StringToWarehouseConverter implements Converter<String, Warehouse> {

	@Autowired
	WarehouseRepository warehouseRepository;

	@Override
	public Warehouse convert(final String text) {
		Warehouse result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.warehouseRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
