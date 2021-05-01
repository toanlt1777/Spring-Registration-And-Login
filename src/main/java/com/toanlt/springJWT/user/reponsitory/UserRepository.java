package com.toanlt.springJWT.user.reponsitory;

import com.toanlt.springJWT.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByEmail(String email);

    Optional<AppUser> findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("update AppUser user set user.enabled = true where user.email = ?1")
    int enableUser(String email);
}