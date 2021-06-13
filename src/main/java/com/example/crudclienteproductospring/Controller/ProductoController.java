package com.example.crudclienteproductospring.Controller;

import com.example.crudclienteproductospring.Entidad.Cliente;
import com.example.crudclienteproductospring.Entidad.Producto;
import com.example.crudclienteproductospring.Servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {
    
    public final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("")
    public String getProductos(Model modelo){
        modelo.addAttribute("productos", productoService.getProducto());
        return "ver-productos";
    }

    @GetMapping("productos/editar/{idproductos}")
    public String editCliente(Model modelo, @PathVariable String idproducto){
        List<Producto> producto = productoService.getProductoByIdproducto(idproducto);
        modelo.addAttribute("productos", producto.get(0));
        return "guardar-cliente";
    }

    @GetMapping("productos/buscar")
    public String buscar(@RequestParam Optional<String> nombreORid){
         if(nombreORid.isPresent() && !nombreORid.get().isEmpty())
            try{
                Integer id = Integer.parseInt(nombreORid.get());
                return "redirect:/productos/id/"+id;
            }catch (NumberFormatException e){
                return "redirect:/productos/nombre/"+nombreORid.get();
            }
        return "redirect:/productos";
    }

    @PostMapping("productos/guardar")
    public String save(@ModelAttribute Producto producto){
        productoService.save(producto);
        return "redirect:/clientes";
    }

    @DeleteMapping("productos/borrar/{idproducto}")
    public String deleteById(@PathVariable Integer idproducto){
        productoService.deleteById(idproducto);
        return "success";
    }

}
