package com.example.demo.stores.repository;

import com.example.demo.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        return mongoTemplate.save(user);
    }

    public User updateNameByUsername(String username, String name) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("name", name);
        return mongoTemplate.findAndModify(query, update, User.class);
    }

    public List<User> getUsersSortedByDob(String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by("dateOfBirth").descending() : Sort.by("dateOfBirth").ascending();
        Query query = new Query().with(sort);
        return mongoTemplate.find(query, User.class);
    }

    public void deleteByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query, User.class);
    }
}