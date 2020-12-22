package com.bstu.cloudserver.models.Client;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor

public class Client implements Serializable
{

    private static final long serialVersionUID = 382157955767771714L;



    @Id
    @Column(name = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Getter
    @Setter
    private String id;



    @Column(name = "name")
    @Getter
    @Setter
    @NonNull
    private String name;

    @Column(name = "email")
    @Getter
    @Setter
    @NonNull
    private String email;

    @Column(name = "passhash")
    @Getter
    @Setter
    @NonNull
    private String passhash;
}


