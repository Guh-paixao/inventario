package br.com.gustavo.inventarioAPI.application.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, String description, BigDecimal price, Integer quantity) {
}
