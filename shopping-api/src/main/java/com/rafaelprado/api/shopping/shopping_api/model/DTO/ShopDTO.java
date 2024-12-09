package com.rafaelprado.api.shopping.shopping_api.model.DTO;

import com.rafaelprado.api.shopping.shopping_api.model.Shop;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopDTO {

    private String id;
    private String userIdentifier;
    private java.util.Date date;
    private List<ItemDTO> items;
    private Double total;

    // Construtor padrão
    public ShopDTO() {}

    // Construtor para mapear Shop para ShopDTO
    public static ShopDTO fromModel(Shop shop) {
        ShopDTO dto = new ShopDTO();
        dto.setId(shop.getId());
        dto.setUserIdentifier(shop.getUserIdentifier());
        dto.setDate(shop.getDate());
        dto.setTotal(shop.getTotal());
        dto.setItems(shop.getItems().stream().map(ItemDTO::fromModel).collect(Collectors.toList()));
        return dto;
    }
}
