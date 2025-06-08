package com.synergisticit.searchgateway.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="roomtype")
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeId;
	private String name;
}
