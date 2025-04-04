package br.com.gustavo.inventarioAPI.application.service.impl;

import br.com.gustavo.inventarioAPI.application.dto.ProductRequestDTO;
import br.com.gustavo.inventarioAPI.application.dto.ProductResponseDTO;
import br.com.gustavo.inventarioAPI.application.service.ProductService;
import br.com.gustavo.inventarioAPI.domain.entity.Product;
import br.com.gustavo.inventarioAPI.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = convertDTOToEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);

        return convertEntityToDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(UUID ID, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(ID)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + ID));

        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setQuantity(productRequestDTO.quantity());
        product.setPrice(productRequestDTO.price());

        Product updatedProduct = productRepository.save(product);
        return convertEntityToDTO(updatedProduct);

    }

    public ProductResponseDTO getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        return convertEntityToDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertEntityToDTO)
                .toList();
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private Product convertDTOToEntity(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .quantity(productRequestDTO.quantity())
                .price(productRequestDTO.price())
                .build();
    }

    private ProductResponseDTO convertEntityToDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
