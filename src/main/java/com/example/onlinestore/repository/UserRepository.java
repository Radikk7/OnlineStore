package com.example.onlinestore.repository;

import com.example.onlinestore.model.User;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String username);


}
