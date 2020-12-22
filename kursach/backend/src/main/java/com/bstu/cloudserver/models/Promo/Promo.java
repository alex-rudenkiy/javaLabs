package com.bstu.cloudserver.models.Promo;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@RequiredArgsConstructor
public class Promo implements Serializable
{
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

    @Column(name = "description")
    @Getter
    @Setter
    @NonNull
    private String description;

    @Column(name = "secret")
    @Getter
    @Setter
    @NonNull
    private String secret;

    @Column(name = "filespace")
    @Getter
    @Setter
    @NonNull
    private int filespace;

    @Column(name = "cost")
    @Getter
    @Setter
    @NonNull
    private int cost;

    @Column(name = "isEnabled")
    @Getter
    @Setter
    @NonNull
    private Boolean isEnabled;

    @Column(name = "isDefault")
    @Getter
    @Setter
    @NonNull
    private Boolean isDefault;

    public void Protected(){

    }
}


