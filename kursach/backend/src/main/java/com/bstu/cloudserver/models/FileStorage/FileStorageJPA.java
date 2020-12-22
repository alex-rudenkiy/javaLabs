package com.bstu.cloudserver.models.FileStorage;

import org.hibernate.annotations.Entity;
import org.springframework.data.jpa.repository.JpaRepository;


@Entity
public interface FileStorageJPA extends JpaRepository<FileStorage, Long>
{

}


