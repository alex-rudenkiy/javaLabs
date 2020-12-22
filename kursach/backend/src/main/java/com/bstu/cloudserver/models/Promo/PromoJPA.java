package com.bstu.cloudserver.models.Promo;

import org.hibernate.annotations.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Entity
public interface PromoJPA extends JpaRepository<Promo, Long>
{
    List<Promo> findByNameEquals(String name);
    List<Promo> findBySecretEquals(String name);
    List<Promo> findByIsDefaultEquals(Boolean value);
    List<Promo> findByIsEnabledEquals(Boolean value);
}


