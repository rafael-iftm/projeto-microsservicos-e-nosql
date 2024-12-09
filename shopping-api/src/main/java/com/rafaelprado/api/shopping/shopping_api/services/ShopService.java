package com.rafaelprado.api.shopping.shopping_api.services;

import com.rafaelprado.api.shopping.shopping_api.model.DTO.ItemDTO;
import com.rafaelprado.api.shopping.shopping_api.model.DTO.ShopDTO;
import com.rafaelprado.api.shopping.shopping_api.model.Item;
import com.rafaelprado.api.shopping.shopping_api.model.Shop;
import com.rafaelprado.api.shopping.shopping_api.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
    }

    public ShopDTO save(ShopDTO shopDTO) {
        Shop shop = convertToEntity(shopDTO);
        Shop savedShop = shopRepository.save(shop);
        return ShopDTO.fromModel(savedShop);
    }

    public ShopDTO findById(String id) {
        return shopRepository.findById(id)
            .map(ShopDTO::fromModel)
            .orElseThrow(() -> new RuntimeException("Compra não encontrada com ID: " + id));
    }

    public List<ShopDTO> findByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findByUserIdentifier(userIdentifier);
        return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
    }

    public List<ShopDTO> findByDate(LocalDateTime start, LocalDateTime end) {
        List<Shop> shops = shopRepository.findByDateBetween(start, end);
        return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
    }

    public List<ShopDTO> findByProductIdentifier(String productIdentifier) {
        List<Shop> shops = shopRepository.findByItems_ProductIdentifier(productIdentifier);
        if (shops.isEmpty()) {
            throw new RuntimeException("Nenhuma compra encontrada com o identificador do produto: " + productIdentifier);
        }
        return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
    }

    public List<ShopDTO> getShopsByFilter(LocalDateTime dataInicio, LocalDateTime dataFim, Double valorMinimo) {
    List<Shop> shops = shopRepository.findByDateBetweenAndTotalGreaterThan(dataInicio, dataFim, valorMinimo);
    return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
}

    public List<ShopDTO> getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Shop> shops = shopRepository.findByDateBetween(dataInicio, dataFim);
        return shops.stream().map(ShopDTO::fromModel).collect(Collectors.toList());
    }

    private Shop convertToEntity(ShopDTO shopDTO) {
        return Shop.builder()
            .id(shopDTO.getId())
            .userIdentifier(shopDTO.getUserIdentifier())
            .date(shopDTO.getDate())
            .items(convertToEntity(shopDTO.getItems())) // Converte os itens de DTO para Entity
            .total(shopDTO.getTotal())
            .build();
    }

    private List<Item> convertToEntity(List<ItemDTO> itemDTOs) {
        return itemDTOs.stream()
            .map(dto -> Item.builder()
                .productIdentifier(dto.getProductIdentifier())
                .price(dto.getPrice())
                .build())
            .collect(Collectors.toList());
    }
}
