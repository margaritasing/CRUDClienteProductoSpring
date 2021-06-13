package com.example.crudclienteproductospring.Controller;

import com.example.crudclienteproductospring.Entidad.Cliente;
import com.example.crudclienteproductospring.Servicio.ClienteService;
import com.example.crudclienteproductospring.Servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    public final ClienteService clienteService;
    public final ProductoService productoService;

    @Autowired
    public ClienteController(ClienteService clienteService, ProductoService productoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
    }


    @GetMapping("")
    public String getClientes(Model modelo){
        modelo.addAttribute("clientes", clienteService.getClientes());
        return "ver-clientes";
    }

    @GetMapping("/editar/{idcliente}")
    public String editCliente(Model modelo, @PathVariable String idcliente){
        List<Cliente> cliente= clienteService.getClienteByIdcliente(idcliente);
        modelo.addAttribute("cliente", cliente.get(0));
        return "guardar-cliente";
    }


    @GetMapping("/buscar")
    public String buscar(@RequestParam Optional<String> nombreORid, @RequestParam Optional<String> empresa){
        if(empresa.isPresent() && !empresa.get().equals("0"))
            return "redirect:/clientes/empresa/"+empresa.get();
        if(nombreORid.isPresent() && !nombreORid.get().isEmpty())
            try{
                Integer id = Integer.parseInt(nombreORid.get());
                return "redirect:/clientes/id/"+id;
            }catch (NumberFormatException e){
                return "redirect:/clientes/nombre/"+nombreORid.get();
            }
        return "redirect:/clientes";
    }


    @PostMapping("/guardar")
    public String save(@ModelAttribute Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @DeleteMapping("/borrar/{idcliente}")
    public String deleteById(@PathVariable Integer idcliente){
        clienteService.deleteById(idcliente);
        return "success";
    }










}
