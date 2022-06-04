package com.myapp.myapp.Services;

import com.myapp.myapp.Models.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    User Save(User user ) throws Exception;

    List<User> findallusers();

    User findById(int id);

    void deleteOne(User user);

    User update(int id, User user) throws ResourceNotFoundException;

    User active (int id) throws Exception;
}
