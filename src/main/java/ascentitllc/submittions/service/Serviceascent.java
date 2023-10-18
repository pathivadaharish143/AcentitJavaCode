package ascentitllc.submittions.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ascentitllc.submittions.entity.FileInfo;
import ascentitllc.submittions.entity.Profile;
import ascentitllc.submittions.entity.User;

@Service
public interface Serviceascent {
	 public Profile saveProfile(Profile profile);
	 public List<Profile> getallprofiles();
	 public Profile getbyid(String id);
     public User saveUser(User user);
     public User login(String gmail, String password);
     public Profile edit(Profile profile,String id);
}
