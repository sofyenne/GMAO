package com.myapp.myapp.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private  int cin  ;
    @Column(unique = true , nullable = false)
    private String email ;

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false )
    private String firstname ;
    @Column(nullable = false)
    private String lastname ;
    @Column(nullable = false)
    private String password ;
    @Column(nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd" ,shape=JsonFormat.Shape.STRING)
    private LocalDateTime date  = LocalDateTime.now();
    private boolean active = false ;
    @ManyToOne
    @JoinColumn(name = "role_id" , nullable = false)
    private Role role ;
    private String matricule ;
    private String phone ;
    @ManyToOne
    @JoinColumn(name = "service_id" , nullable = false)
    private Service service ;
    @ManyToOne
    @JoinColumn(name = "Direction_id" , nullable = false)
    private Direction direction ;
    
    public User() {} ;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User(int cin, String email, Role role, String firstname, String lastname, LocalDateTime date , String password,String matricule,String phone , Direction direction , Service service) {
      

        this.email = email;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.password = password ;
        this.cin = cin ;
        this.matricule = matricule;
        this.phone = phone ;
        this.service = service ; 
        this.direction = direction ; 
    }





    public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
