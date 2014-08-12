package com.primerajunta.emailsender.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primerajunta.emailsender.model.Track;
import com.primerajunta.emailsender.model.User;
import com.primerajunta.emailsender.model.Track.Kind;
import com.primerajunta.emailsender.repository.TrackRepository;

@Service
public class TrakingService {

	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private String domainUrl;
	
	public void track(String url, User user) {
		int firstSlash = url.indexOf('/');
		char code = url.charAt(firstSlash + 1);
		switch (code) {
		case 'o': trackOpen(user);
			break;
		case 'a': trackAccessFromEmail(user);
			break;
		case 'b': trackBuyFromEmail(user);
			break;
		case 'r': trackRegisterFromEmail(user);
			break;
		default:
			throw new InvalidTrackingException();
		}
	}
	
	public void trackOpen(User user) {
		persistTrack(user, Kind.OPEN);
	}

	public void trackAccessFromEmail(User user) {
		persistTrack(user, Kind.ACCESS_FROM_EMAIL);
	}

	public void trackBuyFromEmail(User user) {
		persistTrack(user, Kind.BUY_FROM_EMAIL);
	}
	
	public void trackRegisterFromEmail(User user) {
		persistTrack(user, Kind.REGISTER_FROM_EMAIL);
	}

	private void persistTrack(User user, Kind kind) {
		trackRepository.save(new Track(user, kind));
	}

}
