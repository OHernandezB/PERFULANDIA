package com.productos.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productos.models.Producto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private ArrayList<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", "Descripción del producto 1", 100.0));
        productos.add(new Producto(2, "Producto 2", "Descripción del producto 2", 200.0));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{codigo}")
        public ResponseEntity<?> getByCodigo(@PathVariable String codigo) {
            for (Producto producto: productos){
                if (producto.getCodigo().equals(codigo)) {
                    return ResponseEntity.ok(producto);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
                    
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Producto producto) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
               return ResponseEntity.status(HttpStatus.CONFLICT)
                          .body("Ya existe un codigo con ese producto");  
            }
        }productos.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

}
