package com.myapp.myapp.SreviceImp;

import com.myapp.myapp.Models.Role;
import com.myapp.myapp.Repository.RoleRepository;
import com.myapp.myapp.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository ;
    @Override
    public List<Role> getallRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getOne(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void delete(long id) {
         Role role =roleRepository.getById(id);
         roleRepository.delete(role);
    }

    @Override
    public Role update(long id, Role newrole) throws ResourceNotFoundException {
        Role existRole = roleRepository.getById(id);
        if(existRole!=null){
            existRole.setRole(newrole.getRole());
            return roleRepository.save(existRole);
        }

        throw new ResourceNotFoundException("user not found");
    }
}
