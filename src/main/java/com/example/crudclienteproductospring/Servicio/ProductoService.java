package com.example.crudclienteproductospring.Servicio;

import com.example.crudclienteproductospring.Entidad.Cliente;
import com.example.crudclienteproductospring.Entidad.Producto;
import com.example.crudclienteproductospring.Repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    public final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getProducto(){
        return productoRepository.findAll();
    }

    public Producto save (Producto producto){
        return productoRepository.save(producto);
    }

    public void deleteById(Integer id){
        productoRepository.deleteById(id);
    }


    public List<Producto> getProductoByIdproducto(String idproducto) {
        Integer id;
        try {
            id = Integer.parseInt(idproducto);
        }catch (NumberFormatException e){
            id = 0;
        }
        return productoRepository.findAllById(List.of(id));
    }
}
