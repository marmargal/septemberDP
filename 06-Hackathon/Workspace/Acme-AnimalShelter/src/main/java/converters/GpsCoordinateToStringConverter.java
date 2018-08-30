package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.GpsCoordinate;

@Component
@Transactional
public class GpsCoordinateToStringConverter implements
		Converter<GpsCoordinate, String> {

	@Override
	public String convert(final GpsCoordinate gpsCoordinate) {
		String result;
		StringBuilder builder;

		if (gpsCoordinate == null)
			result = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(
						Double.toString(gpsCoordinate.getLatitude()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(
						Double.toString(gpsCoordinate.getLongitude()), "UTF-8"));

				result = builder.toString();
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		return result;
	}
}