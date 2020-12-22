package com.bstu.cloudserver.models.FileStorage.SharedFile;

import org.hibernate.annotations.Entity;
import org.springframework.data.jpa.repository.JpaRepository;


@Entity
public interface SharedFileJPA extends JpaRepository<SharedFile, Long>
{
    SharedFile getSharedFileByIdEquals(String id);
}


