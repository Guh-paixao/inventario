package br.com.gustavo.inventarioAPI.domain.repository;

import br.com.gustavo.inventarioAPI.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
