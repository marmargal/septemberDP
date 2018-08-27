package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity{

	// Attributes ----------------------------------------------

		private Collection<String> tabooWords;

		@NotNull
		@NotEmpty
		@ElementCollection
		public Collection<String> getTabooWords() {
			return tabooWords;
		}

		public void setTabooWords(Collection<String> tabooWords) {
			this.tabooWords = tabooWords;
		}
}
