package com.moondysmell.yanoljaclone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;


@Data
@Entity
@Table(name="accommodation")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodation_id_seq")
    @SequenceGenerator(name = "accommodation_id_seq", sequenceName = "accommodation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "accom_code")
    private String accomCode;

    @Column(name = "accom_name")
    private String accomName;

//    private int locationCode;
    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private String type;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_cnt")
    private int roomCnt;

    @Column(name = "price")
    private int price;

    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_code", referencedColumnName = "code")
    @JsonIgnore
    private LocationCode locationCode;

}
