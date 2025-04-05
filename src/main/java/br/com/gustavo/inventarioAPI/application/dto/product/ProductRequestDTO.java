package br.com.gustavo.inventarioAPI.application.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, Integer quantity) {
}
