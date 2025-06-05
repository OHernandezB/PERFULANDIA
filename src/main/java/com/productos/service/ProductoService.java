package com.productos.service;

import com.productos.models.Producto;
import com.productos.dto.ProductoDTO;
import com.productos.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDTO ToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioUnitario(),
                producto.getCatergoria(),
                producto.getActivo()
        );
    }

     private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setIdProducto(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setCatergoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());
        return producto;
    }

    public List<ProductoDTO> listar(){
        return productoRepository.findAll().stream()
                .map(this::ToDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Integer id) {
        Producto producto  =  productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
            return ToDTO(producto);
    }

    public ProductoDTO actualizar(Integer id, ProductoDTO productoDTO) {
        Producto existente =  productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

            existente.setNombre(productoDTO.getNombre());
            existente.setDescripcion(productoDTO.getDescripcion());
            existente.setPrecioUnitario(productoDTO.getPrecioUnitario());
            existente.setCatergoria(productoDTO.getCategoria());
            existente.setActivo(productoDTO.getActivo());
            Producto actualizado = productoRepository.save(existente);
            return ToDTO(actualizado);
    }

    public void eliminar(Integer id){
        productoRepository.findById(id);
    }






}
