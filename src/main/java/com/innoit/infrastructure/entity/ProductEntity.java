package com.innoit.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {

    @Id
    @Column(name="PRODUCT_ID",length = 10)
    private String id;

    @Column(name="PRODUCT_NAME", length = 300)
    private String name;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private Set<PriceEntity> pricesEntities = new HashSet<>();


}
