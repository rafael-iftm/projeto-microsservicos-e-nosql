package com.rafaelprado.api.shopping.shopping_api.model;

import java.util.List;
import java.util.stream.Collectors;

import com.rafaelprado.api.shopping.shopping_api.model.DTO.ItemDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private String productIdentifier;
    private Double price;
}
