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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone_num;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reserv = new ArrayList<>();

    @Builder(builderMethodName = "createUser")
     public Customer(String name, String phone_num) {
        this.name = name;
        this.phone_num = phone_num;
    }






}
