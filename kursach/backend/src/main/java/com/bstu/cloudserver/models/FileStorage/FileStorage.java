package com.bstu.cloudserver.models.FileStorage;

import com.bstu.cloudserver.models.Client.Client;
import com.bstu.cloudserver.models.Promo.Promo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class FileStorage implements Serializable {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Getter
    @Setter
    private String id;

    @OneToOne
    @JoinColumn(name = "client")
    @NonNull
    private Client client;

    @OneToMany
    @JoinColumn(name = "promo")
    @NonNull
    private List<Promo> promo;

}


