package com.myapp.myapp.Controller;


import com.myapp.myapp.Models.Service;
import com.myapp.myapp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceController {

    @Autowired
   private ServiceRepository serviceRepository ;


    @GetMapping("/all")
    public List<Service> GetallService(){
        return serviceRepository.findAll();
    }
    @GetMapping("service/{id}")
    public Optional<Service> getServiceById(@PathVariable(name = "id")   long id ){
        return serviceRepository.findById(id);
    }
    @GetMapping("service/{DirectionId}")
    public Optional<List<Service>> getAllServiceByDirection(@PathVariable(name = "DirectionId") long DirectionId ){
        return serviceRepository.findAllByDirection(DirectionId);
    }
    @PostMapping("/create")
    public Service createService(@RequestBody  Service service){
        return serviceRepository.save(service);
    }
    @PutMapping("/update/{id}")
    public Service updateService (@PathVariable(name = "id") long id ,@RequestBody Service service)throws Exception{
        Service service1 = serviceRepository.getById(id);
        try {
            if (service1 != null){
                service1.setName(service.getName());
            }
        }catch (Exception exception){
            throw new Exception(exception);
        }return serviceRepository.save(service1);
    }
    @DeleteMapping("/delete")
    public void delete(long id ){
        try {
            Service service = serviceRepository.getById(id);
            serviceRepository.delete(service);
        }
        catch (NullPointerException notFoundException){
            throw new NullPointerException();
        }
    }
}
