package delivery.api.controller;

import delivery.api.model.Cliente;
import delivery.api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    ResponseEntity<Iterable<Cliente>> all() {
        Iterable<Cliente> clientes = clienteService.findAll();
        if (!clientes.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSave = clienteService.save(cliente);
        return new ResponseEntity<>(clienteSave, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com o id " + id + " não encontrado"));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    ResponseEntity<Cliente> update(@RequestBody Cliente novoCliente, @PathVariable Integer id) {
        return clienteService.findById(id)
                .map(cliente -> {
                    if (novoCliente.getNome() != null) {
                        cliente.setNome(novoCliente.getNome());
                    }
                    if (novoCliente.getCpf() != null) {
                        cliente.setCpf(novoCliente.getCpf());
                    }
                    if (novoCliente.getEmail() != null) {
                        cliente.setEmail(novoCliente.getEmail());
                    }
                    if (novoCliente.getTelefone() != null) {
                        cliente.setTelefone(novoCliente.getTelefone());
                    }
                    return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    novoCliente.setId(id);
                    return new ResponseEntity<>(clienteService.save(novoCliente), HttpStatus.OK);
                });
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteCliente(@PathVariable Integer id) {
        if (!clienteService.existsById(id)) {
            throw new NoSuchElementException("Cliente com o id " + id + " não encontrado");
        }
        clienteService.destroy(id);
        return ResponseEntity.noContent().build();
    }

}
