package ascentitllc.submittions.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ascentitllc.submittions.entity.FileInfo;

@Repository
public interface FileInfoRepository extends MongoRepository<FileInfo, String> {
    
}