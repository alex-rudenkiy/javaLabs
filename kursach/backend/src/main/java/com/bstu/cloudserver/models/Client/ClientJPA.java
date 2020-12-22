package com.bstu.cloudserver.models.Client;

import org.hibernate.annotations.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Entity
public interface ClientJPA extends JpaRepository<Client, Long>
{
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Boolean existsByPasshash(String passhash);
    List<Client> findClientByNameEquals(String name);
}


