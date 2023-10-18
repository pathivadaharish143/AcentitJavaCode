package ascentitllc.submittions.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ascentitllc.submittions.entity.Profile;

@Repository
public interface Repo extends MongoRepository<Profile, String> {

}
