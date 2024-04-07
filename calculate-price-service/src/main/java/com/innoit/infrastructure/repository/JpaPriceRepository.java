package com.innoit.infrastructure.repository;

import com.innoit.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, String> {

    @Query("SELECT p FROM PriceEntity p  WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productEntity.id = :productId " +
            "AND p.brandEntity.id = :brandId " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findAllByPricesByApplicationDateAndProductIdAndBrandId(@Param("applicationDate") LocalDateTime applicationDate,
                                                                             @Param("productId") String productId,
                                                                             @Param("brandId") String brandId);
}



