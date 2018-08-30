package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Warehouse;

@Component
@Transactional
public class WarehouseToStringConverter implements Converter<Warehouse, String> {

	@Override
	public String convert(final Warehouse warehouse) {
		String result;

		if (warehouse == null)
			result = null;
		else
			result = String.valueOf(warehouse.getId());
		return result;
	}
}
