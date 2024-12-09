package com.rafaelprado.api.shopping.shopping_api.repositories;

import com.rafaelprado.api.shopping.shopping_api.model.Shop;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    /**
     * Buscar compras pelo identificador do usuário.
     */
    List<Shop> findByUserIdentifier(String userIdentifier);

    /**
     * Buscar compras pelo identificador do produto.
     */
    List<Shop> findByItems_ProductIdentifier(String productIdentifier);

    /**
     * Buscar compras em um intervalo de datas com valor total maior que o mínimo informado.
     */
    List<Shop> findByDateBetweenAndTotalGreaterThan(LocalDateTime start, LocalDateTime end, Double totalMin);

    /**
     * Buscar compras no intervalo de datas para gerar relatório.
     */
    List<Shop> findByDateBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
}
