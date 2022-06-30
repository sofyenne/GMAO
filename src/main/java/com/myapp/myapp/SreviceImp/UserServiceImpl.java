package com.myapp.myapp.SreviceImp;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myapp.myapp.Models.User;
import com.myapp.myapp.Repository.UserRepository;
import com.myapp.myapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private PasswordEncoder passswordEncoder ;
    @Autowired
    private JavaMailSender javaMailSender ;

    @Override
    public User active(int id) throws Exception  {
            try {
                Optional<User> usertoactivate = userRepository.findById(id);
                if (usertoactivate.isPresent()){
                    usertoactivate.get().setActive(!usertoactivate.get().isActive());
                    userRepository.save(usertoactivate.get());
                }return usertoactivate.get();
            }catch (Exception e){
                throw new Exception(e);
            }
    }

    @Override
    public User Save(User user) throws Exception {
        try{
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User res = userRepository.save(user);
        if (res !=  null){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setFrom("pfegmao@gmail.com");
            StringBuilder body = new StringBuilder();
            body.append("hi : ");
            body.append(res.getFirstname());
            body.append("\n");
            body.append("Your identifier  is  :  ");
            body.append(user.getEmail());
            body.append("\n");
            body.append("Your default password is :  ");
            body.append(password);
            message.setText(String.valueOf(body));
            try {
            	javaMailSender.send(message);
			} catch (MailException e) {
				return res ;
			}
            
        }
        return res ;
    }catch (Exception exception){
            throw new Exception(exception);
        }
    }
    @Override
    public List<User> findallusers() {
        return userRepository.findAll();
    }
    @Override
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id) ;
        if (user.isPresent()){
            return user.get() ;
        }
        throw new NullPointerException() ;

    }
    @Override
     public void deleteOne(User user ){
        userRepository.delete(user);
     }


    public List<User>  getByname(String name) {

       List<User> user =  userRepository.findByFirstname(name);
         return user;


    }
     @Override
    public User update(int id , User user) throws ResourceNotFoundException{
    	User existUser = userRepository.getOne(id);
    	
    	 if (existUser!=null) {
    		 
    		 existUser.setEmail(user.getEmail());
    		 existUser.setFirstname(user.getFirstname());
    		 existUser.setPhone(user.getPhone());
    		 existUser.setMatricule(user.getMatricule());
    		 existUser.setPassword(passswordEncoder.encode(user.getPassword()));
    		 existUser.setLastname(user.getLastname());
    		 existUser.setRole(user.getRole());
    		 existUser.setService(user.getService());
    		 existUser.setDirection(user.getDirection());
    		 
    		return userRepository.save(existUser);
    	 }
    	 
    	  throw new ResourceNotFoundException("user not found");
    			
    	
    }
}
