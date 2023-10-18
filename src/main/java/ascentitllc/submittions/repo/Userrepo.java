package ascentitllc.submittions.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import ascentitllc.submittions.entity.User;

public interface Userrepo extends MongoRepository<User,String> {
	User findByGmailAndPassword(String gmail,String password);
}
