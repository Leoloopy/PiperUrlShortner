package repositories;

import Model.Links;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepo extends MongoRepository<Links, String> {
    Links findLinksByOriginalString(String s);
}
