package com.moondysmell.yanoljaclone.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="location_code")
public class LocationCode {
    @Id
    private int code;

    private String location;

//    @OneToMany(mappedBy = "locationCode", cascade = CascadeType.ALL)
//    @OneToMany
//    private List<Accommodation> accommodations = new ArrayList<>();
}
