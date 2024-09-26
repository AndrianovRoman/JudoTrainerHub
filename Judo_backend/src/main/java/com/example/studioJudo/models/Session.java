package com.example.studioJudo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="session")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    @OneToOne(mappedBy = "session")
    @JsonIgnore
    private Booking booking;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
