package de.itsstuttgart.chessserver.util.repositories;

import de.itsstuttgart.chessserver.util.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * created by paul on 15.01.21 at 20:10
 */
@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByUsername(String username);
    boolean existsByUsername(String username);
}
