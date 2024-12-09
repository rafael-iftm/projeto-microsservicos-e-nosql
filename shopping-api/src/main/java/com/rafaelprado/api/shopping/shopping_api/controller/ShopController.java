package com.rafaelprado.api.shopping.shopping_api.controller;

import com.rafaelprado.api.shopping.shopping_api.model.DTO.ShopDTO;
import com.rafaelprado.api.shopping.shopping_api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * Endpoint básico para retornar todos os dados de compras.
     */
    @GetMapping
    public List<ShopDTO> getAll() {
        return shopService.getAll();
    }

    /**
     * Buscar uma compra por ID.
     */
    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable String id) {
        return shopService.findById(id);
    }

    /**
     * Salvar uma nova compra no banco de dados.
     */
    @PostMapping
    public ShopDTO save(@RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    /**
     * Buscar compras por identificador de usuário.
     * Ex: http://localhost:8080/shopping/shopByUser?userIdentifier=user_123
     */
    @GetMapping("/shopByUser")
    public List<ShopDTO> getByUser(@RequestParam String userIdentifier) {
        return shopService.findByUser(userIdentifier);
    }

    /**
     * Buscar compras por intervalo de datas.
     * Ex: http://localhost:8080/shopping/shopByDate?start=2023-12-01T00:00:00&end=2023-12-10T00:00:00

     */
    @GetMapping("/shopByDate")
    public List<ShopDTO> getByDate(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return shopService.findByDate(start, end);
    }

    /**
     * Buscar compras por identificador de produto.
     */
    @GetMapping("/product/{productIdentifier}")
    public List<ShopDTO> findByProductIdentifier(@PathVariable String productIdentifier) {
        return shopService.findByProductIdentifier(productIdentifier);
    }

    /**
     * Endpoint avançado: busca por filtro com base em data e valor mínimo.
     * Ex: http://localhost:8080/shopping/search?dataInicio=2023-12-01T00:00:00&dataFim=2023-12-10T00:00:00&valorMinimo=50

     */
    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(value = "dataInicio", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(value = "dataFim", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(value = "valorMinimo", required = true) Double valorMinimo) {

        return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    /**
     * Endpoint avançado: gerar relatório por intervalo de datas.
     * Ex: http://localhost:8080/shopping/search?dataInicio=2023-12-01T00:00:00&dataFim=2023-12-10T00:00:00&valorMinimo=50

     */
    @GetMapping("/report")
    public List<ShopDTO> getReportByDate(
            @RequestParam(value = "dataInicio", required = true) LocalDateTime dataInicio,
            @RequestParam(value = "dataFim", required = true) LocalDateTime dataFim) {
        return shopService.getReportByDate(dataInicio, dataFim);
    }
}
