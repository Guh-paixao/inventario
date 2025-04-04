package br.com.gustavo.inventarioAPI.application.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, Integer quantity) {
}
