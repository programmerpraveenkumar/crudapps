package com.crudapp.Repository;


import com.crudapp.Model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long> {
    @Query("select user from UserModel user where user.email = ?1")
    Optional<UserModel> getUserByEmail(String email);
}
