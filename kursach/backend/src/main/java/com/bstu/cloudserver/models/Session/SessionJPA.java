package com.bstu.cloudserver.models.Session;

import com.bstu.cloudserver.models.Client.Client;
import org.hibernate.annotations.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Entity
public interface SessionJPA extends JpaRepository<Session, Long>
{
    List<Session> findSessionByTokenEquals(String token);
    void deleteAllByClientEquals(Client client);
}


