package com.HR.Grandson.HR.Grandson.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HR.Grandson.HR.Grandson.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
}
