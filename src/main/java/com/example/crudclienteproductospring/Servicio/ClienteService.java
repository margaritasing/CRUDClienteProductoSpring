package com.example.crudclienteproductospring.Servicio;

import com.example.crudclienteproductospring.Entidad.Cliente;
import com.example.crudclienteproductospring.Repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    public final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public Cliente save (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getClienteByIdcliente(String idCliente){
        Integer id;
        try {
             id = Integer.parseInt(idCliente);
        }catch (NumberFormatException e){
            id = 0;
        }
        return clienteRepository.findAllById(List.of(id));
    }

    public void deleteById(Integer id){
        clienteRepository.deleteById(id);
    }









}
