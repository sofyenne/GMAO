package com.myapp.myapp.Controller;

import com.myapp.myapp.Dto.UserDto;


import com.myapp.myapp.Models.User;
import com.myapp.myapp.SreviceImp.UserServiceImpl;

import javassist.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {


    @Autowired
    private UserServiceImpl userService ;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody User user)throws Exception{
        userService.Save(user) ;
       UserDto userdto = new UserDto();
        BeanUtils.copyProperties(user , userdto);
        return ResponseEntity.ok(userdto) ;

    }

    @GetMapping("/all")
    public List<User> findallUsers(){
         List<User> userList = userService.findallusers();
         return userList ;
    }
    

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService.findById(id);
    }
    @GetMapping("/")
    public List<UserDto> getByName(@RequestParam(value="name") String name ){
        List<User> userList = userService.getByname(name) ;
        List<UserDto> userDtoList = new ArrayList<UserDto>() ;
        for (User item:userList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(item ,userDto );
            userDtoList.add(userDto);
        }

        return  userDtoList ;
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id , @RequestBody User user ) throws ResourceNotFoundException {

    	return userService.update(id, user)  ; 
    }

    @PutMapping("/active/{id}")
    public User activeUser(@PathVariable int id ) throws Exception {
        return userService.active(id) ;
    }
    
    @DeleteMapping("/{id}")
    public void deleteuser(@PathVariable int id ) {
    	User user = userService.findById(id);
    	userService.deleteOne(user);
    }
    
}
