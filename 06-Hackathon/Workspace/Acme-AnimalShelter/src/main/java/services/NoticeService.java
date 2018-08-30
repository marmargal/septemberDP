package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoticeRepository;
import domain.Notice;


@Service
@Transactional
public class NoticeService {

	// Managed repository

	@Autowired
	private NoticeRepository noticeRepository;

	// Suporting services

	// Constructors

	public NoticeService() {
		super();
	}

	// Simple CRUD methods

	public Notice create() {
		Notice res = new Notice();
		
		return res;

	}

	public Collection<Notice> findAll() {
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
	
	

}
