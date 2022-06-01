package com.moondysmell.yanoljaclone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long id;

    private String accomCode;

    private String accomName;

    private int locationCode;

    private String address;

    private RoomType type;

    private String roomName;

    private int roomCnt;

    private int price;

    private String detail;

}
