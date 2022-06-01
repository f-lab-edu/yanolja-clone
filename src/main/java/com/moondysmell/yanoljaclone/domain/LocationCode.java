package com.moondysmell.yanoljaclone.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="LocationCode")
public class LocationCode {
    @Id
    private int code;

    private String location;
}
