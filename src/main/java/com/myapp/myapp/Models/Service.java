package com.myapp.myapp.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ServiceId ;
    @Column(name = "name")
    private  String name ;
    @ManyToOne
    @JoinColumn(name = "DirectionId" , nullable = false)
    private Direction direction ;
	public long getServiceId() {
		return ServiceId;
	}
	public void setServiceId(long serviceId) {
		ServiceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
