package br.com.gustavo.inventarioAPI.application.service;

import br.com.gustavo.inventarioAPI.application.dto.product.ProductRequestDTO;
import br.com.gustavo.inventarioAPI.application.dto.product.ProductResponseDTO;
import br.com.gustavo.inventarioAPI.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO productRequestDTO);

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(UUID id);

    List<ProductResponseDTO> getAllProducts();

    void deleteProduct(UUID id);

    Product convertDTOToEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO convertEntityToDTO(Product product);
}
