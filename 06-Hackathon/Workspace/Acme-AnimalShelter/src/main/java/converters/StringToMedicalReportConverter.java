package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MedicalReportRepository;
import domain.MedicalReport;

@Component
@Transactional
public class StringToMedicalReportConverter implements
		Converter<String, MedicalReport> {

	@Autowired
	MedicalReportRepository medicalReportRepository;

	@Override
	public MedicalReport convert(final String text) {
		MedicalReport result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.medicalReportRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
