package com.jtj.jwtm.repository;

import com.jtj.jwtm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);

}
