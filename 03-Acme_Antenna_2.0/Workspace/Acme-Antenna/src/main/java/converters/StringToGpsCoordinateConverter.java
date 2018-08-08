package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.GpsCoordinate;

@Component
@Transactional
public class StringToGpsCoordinateConverter implements Converter<String, GpsCoordinate> {
	
	@Override
	public GpsCoordinate convert(final String text) {
		GpsCoordinate result;
		String parts[];

		if (text == null)
			result = null;
		else
			try {
				parts = text.split("\\|");
				result = new GpsCoordinate();
				result.setLatitude(Double.valueOf(URLDecoder.decode(parts[1], "UTF-8")));
				result.setLongitude(Double.valueOf(URLDecoder.decode(parts[2], "UTF-8")));

			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		return result;
	}
}