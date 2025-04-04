package br.com.gustavo.inventarioAPI.application.service;

import br.com.gustavo.inventarioAPI.application.dto.ProductRequestDTO;
import br.com.gustavo.inventarioAPI.application.dto.ProductResponseDTO;

import java.util.UUID;

public interface ProductService {
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO productRequestDTO);
}
