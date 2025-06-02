package com.productos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos.models.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // No additional methods are needed as JpaRepository provides basic CRUD operations

}
