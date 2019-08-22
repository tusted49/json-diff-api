package com.weas.jsondiffapi.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "data")
public class Data {

    @Id
    private Long id;

    private String left;

    private String right;

}
