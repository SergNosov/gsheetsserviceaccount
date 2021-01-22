package com.svn.gsheetsserviceaccount.repositories;

import com.svn.gsheetsserviceaccount.model.UserCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCardRepository extends MongoRepository<UserCard, ObjectId> {
}
