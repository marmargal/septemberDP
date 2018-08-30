package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MedicalReport;

@Component
@Transactional
public class MedicalReportToStringConverter implements
		Converter<MedicalReport, String> {

	@Override
	public String convert(final MedicalReport medicalReport) {
		String result;

		if (medicalReport == null)
			result = null;
		else
			result = String.valueOf(medicalReport.getId());
		return result;
	}
}
