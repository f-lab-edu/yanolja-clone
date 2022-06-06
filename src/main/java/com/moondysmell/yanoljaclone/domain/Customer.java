package com.moondysmell.yanoljaclone.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="Customer")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    //@SequenceGenerator(name = "accommodation_id_seq", sequenceName = "customer_user_id_seq")

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name="phone_num")
    private String phoneNum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reserv = new ArrayList<>();

    @Builder(builderMethodName = "createUser")
     public Customer(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }






}
