package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Event;


@Service
@Transactional
public class EventService {

	// Managed repository

	@Autowired
	private EventRepository eventRepository;

	// Suporting services

	// Constructors

	public EventService() {
		super();
	}

	// Simple CRUD methods

	public Event create() {
		Event res = new Event();
		
		Date publicationDate = new Date(System.currentTimeMillis()-1000);
		res.setPublicationDate(publicationDate);

		return res;

	}

	public Collection<Event> findAll() {
		Collection<Event> res;
		res = eventRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Event findOne(int eventId) {
		Assert.isTrue(eventId != 0);
		Event res;
		res = eventRepository.findOne(eventId);
		Assert.notNull(res);
		return res;
	}

	public Event save(Event event) {
		Event res;
		res = eventRepository.save(event);
		return res;
	}

	public void delete(Event event) {
		Assert.notNull(event);
		Assert.isTrue(event.getId() != 0);
		Assert.isTrue(eventRepository.exists(event.getId()));
		eventRepository.delete(event);
	}

	// Other business methods
	
	public Collection<Event> findEventByCenter(int centerId){
		Collection<Event> events = new ArrayList<Event>();
		events = this.eventRepository.findEventByCenter(centerId);
		return events;
	}
	
	public Event saveAndFlush(Event event){
		return eventRepository.saveAndFlush(event);
	}

	public Collection<Event> findEventNotEnd(){
		Collection<Event> events = new ArrayList<Event>();
		events = this.eventRepository.findEventNotEnd(new Date());
		return events;
	}
}
