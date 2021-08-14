package com.dtg.meetroom.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "meetingroom")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String startHour;
    @Column(nullable = false)
    private String endHour;
}
