package com.myapp.myapp.Controller;


import com.myapp.myapp.Models.Role;
import com.myapp.myapp.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role){
        roleService.saveRole(role) ;
        return ResponseEntity.ok(role) ;

    }

    @GetMapping("/all")
    public List<Role> findallRole(){
       return  roleService.getallRole();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Role>> findById(@PathVariable long id){
        Optional<Role> role =roleService.getOne(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable int id , @RequestBody Role role ) throws ResourceNotFoundException {

        return roleService.update(id ,role)  ;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id ) {
        roleService.delete(id);

    }

}
