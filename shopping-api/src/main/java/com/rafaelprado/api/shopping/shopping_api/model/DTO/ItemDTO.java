package com.rafaelprado.api.shopping.shopping_api.model.DTO;

import com.rafaelprado.api.shopping.shopping_api.model.Item;
import lombok.Data;

@Data
public class ItemDTO {

    private String productIdentifier;
    private Double price;

    public static ItemDTO fromModel(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setProductIdentifier(item.getProductIdentifier());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
