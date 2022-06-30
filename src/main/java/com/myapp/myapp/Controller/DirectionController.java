package com.myapp.myapp.Controller;


import com.myapp.myapp.Models.Direction;
import com.myapp.myapp.Models.Service;
import com.myapp.myapp.Repository.DirectionRepository;
import com.myapp.myapp.Repository.ServiceRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/direction")
public class DirectionController {


    @Autowired
    private DirectionRepository directionRepository ;


    @GetMapping("/all")
    public ResponseEntity< List<Direction>> GetallDirection(){
        List<Direction> directions = directionRepository.findAll();
        return new ResponseEntity<>(directions, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<Direction> GetByid(@PathVariable(name = "id") long id ) {
    	return directionRepository.findById(id);
    }
    @PostMapping("/create")
    public Direction createDirection(@RequestBody Direction direction){
        return directionRepository.save(direction);
    }
    @PutMapping("/update/{id}")
    public Direction updateDirection(@PathVariable(name = "id") long id ,@RequestBody Direction direction)throws Exception{
        Direction direction1 = directionRepository.getById(id);
        try {
            if (direction1 != null){
                direction1.setName(direction.getName());
            }
        }catch (Exception exception){
            throw new Exception(exception);
        }return directionRepository.save(direction1);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") long id ){
        try {
            Direction direction = directionRepository.getById(id);
            directionRepository.delete(direction);
        }
        catch (NullPointerException notFoundException){
            throw new NullPointerException();
        }
    }
}

