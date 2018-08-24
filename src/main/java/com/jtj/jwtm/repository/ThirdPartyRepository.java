package com.jtj.jwtm.repository;

import com.jtj.jwtm.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Long> {

    @Query("select userId from ThirdParty where type = :type and value = :value")
    Optional<Long> findUserIdByThirdName(@Param("type") String type, @Param("value") String value);

}
