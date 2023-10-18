package ascentitllc.submittions.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ascentitllc.submittions.entity.FileInfo;
import ascentitllc.submittions.entity.Profile;
import ascentitllc.submittions.entity.User;
import ascentitllc.submittions.repo.Repo;
import ascentitllc.submittions.repo.Userrepo;
import jakarta.persistence.Id;

@Service
public class ServiceImple implements Serviceascent {
	@Autowired
	private Repo profileRepository;
	@Autowired
	private Userrepo userrepo;

	@Override
	public Profile saveProfile(Profile profile) {
		return profileRepository.save(profile);

	}

	@Override
	public List<Profile> getallprofiles() {
		return profileRepository.findAll();
	}
	@Override
	public Profile getbyid(String id) {
		Optional<Profile> optional = profileRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
	@Override
	public User saveUser(User user) {
		return userrepo.save(user);
	}

	@Override
	public User login(String gmail, String password) {
		return userrepo.findByGmailAndPassword(gmail, password);
	}
	@Override
	public Profile edit(Profile profile ,String id) {
		return profileRepository.save(profile);
	}
}