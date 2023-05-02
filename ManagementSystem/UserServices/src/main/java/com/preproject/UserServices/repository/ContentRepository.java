package com.preproject.UserServices.repository;

import com.preproject.UserServices.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {
    public Content findByContentid(String contentid);
}
