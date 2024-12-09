package com.rafaelprado.api.shopping.shopping_api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "shop")
public class Shop {
    @Id
    private String id;
    private String userIdentifier;
    private Date date;
    private List<Item> items;
    private Double total;
}
