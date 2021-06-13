package com.example.crudclienteproductospring.Repositorio;

import com.example.crudclienteproductospring.Entidad.Cliente;
import com.example.crudclienteproductospring.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findAll();

}
