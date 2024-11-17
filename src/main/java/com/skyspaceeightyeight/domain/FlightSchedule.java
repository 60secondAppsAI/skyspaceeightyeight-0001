package com.skyspaceeightyeight.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="flight_schedules")
@Getter @Setter @NoArgsConstructor
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="flight_schedule_id")
	private Integer flightScheduleId;
    
  	@Column(name="departure_date")
	private Date departureDate;
    
	




}
