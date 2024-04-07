package com.innoit.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "BRAND")
public class BrandEntity implements Serializable {

    @Id
    @Column(name="BRAND_ID", length = 10)
    private String id;

    @Column(name="BRAND_NAME", length = 300)
    private String name;

    @OneToMany(mappedBy = "brandEntity", cascade = CascadeType.ALL)
    private Set<PriceEntity> pricesEntities = new HashSet<>();

}
