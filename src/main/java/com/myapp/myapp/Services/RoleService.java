package com.myapp.myapp.Services;

import com.myapp.myapp.Models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

     List<Role> getallRole();
     Role saveRole(Role role);
     Optional<Role> getOne(long id );
     void delete(long id );
     Role update(long id , Role role );


}
