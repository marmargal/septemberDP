package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoticeRepository;
import security.Authority;
import security.LoginService;
import domain.Notice;
import domain.Voluntary;

@Service
@Transactional
public class NoticeService {

	// Managed repository

	@Autowired
	private NoticeRepository noticeRepository;

	// Suporting services

	@Autowired
	private VoluntaryService voluntaryService;

	// Constructors

	public NoticeService() {
		super();
	}

	// Simple CRUD methods

	public Notice create() {
		Notice res = new Notice();
		this.voluntaryService.checkAuthority();

		Voluntary voluntary = this.voluntaryService.findByPrincipal();
		res.setVoluntary(voluntary);

		Date date = new Date(System.currentTimeMillis() - 1000);
		res.setDate(date);

		res.setDiscarded(false);

		return res;

	}

	public Collection<Notice> findAll() {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin));
		Collection<Notice> res;
		res = noticeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Notice findOne(int noticeId) {
		Assert.isTrue(noticeId != 0);
		Notice res;
		res = noticeRepository.findOne(noticeId);
		Assert.notNull(res);
		return res;
	}

	public Notice save(Notice notice) {
		Notice res;
		res = noticeRepository.save(notice);
		return res;
	}

	public void delete(Notice notice) {
		Assert.notNull(notice);
		Assert.isTrue(notice.getId() != 0);
		Assert.isTrue(noticeRepository.exists(notice.getId()));
		noticeRepository.delete(notice);
	}

	// Other business methods

	public Collection<Notice> findNonDiscarded() {
		Collection<Notice> res;
		res = noticeRepository.findNonDiscarded();
		Assert.notNull(res);
		return res;
	}

	public Notice discardedTrue(int noticeId) {
		Assert.isTrue(noticeRepository.exists(noticeId));

		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin));
		Notice notice = noticeRepository.findOne(noticeId);
		notice.setDiscarded(true);
		return notice;
	}

	public void flush() {
		this.noticeRepository.flush();

	}

}
