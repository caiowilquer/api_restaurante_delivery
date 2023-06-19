package delivery.api.controller;

import delivery.api.model.Entrega;
import delivery.api.service.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Entrega>> all() {
        Iterable<Entrega> entregas = entregaService.findAll();
        if (!entregas.iterator().hasNext()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entregas);
    }

    @PostMapping
    public ResponseEntity<Entrega> save(@RequestBody Entrega entrega) {
        Entrega entregaSave = entregaService.save(entrega);
        return ResponseEntity.status(HttpStatus.CREATED).body(entregaSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Integer id) {
        Entrega entrega = entregaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega com o id " + id + " não encontrado"));
        return ResponseEntity.ok(entrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> update(@RequestBody Entrega novaEntrega, @PathVariable Integer id) {
        return entregaService.findById(id)
                .map(entrega -> {
                    entrega.setBairro(novaEntrega.getBairro() != null ? novaEntrega.getBairro() : entrega.getBairro());
                    entrega.setCep(novaEntrega.getCep() != null ? novaEntrega.getCep() : entrega.getCep());
                    entrega.setCidade(novaEntrega.getCidade() != null ? novaEntrega.getCidade() : entrega.getCidade());
                    entrega.setNumero(novaEntrega.getNumero() != null ? novaEntrega.getNumero() : entrega.getNumero());
                    entrega.setRua(novaEntrega.getRua() != null ? novaEntrega.getRua() : entrega.getRua());
                    entrega.setComplemento(novaEntrega.getComplemento() != null ? novaEntrega.getComplemento() : entrega.getComplemento());
                    entrega.setTaxa_entrega(novaEntrega.getTaxa_entrega() != null ? novaEntrega.getTaxa_entrega() : entrega.getTaxa_entrega());

                    Entrega entregaAtualizada = entregaService.update(entrega);
                    return ResponseEntity.ok(entregaAtualizada);
                })
                .orElseGet(() -> {
                    novaEntrega.setId(id);
                    Entrega entregaSalva = entregaService.save(novaEntrega);
                    return ResponseEntity.status(HttpStatus.CREATED).body(entregaSalva);
                });
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEntrega(@PathVariable Integer id) {
        if (!entregaService.existsById(id)) {
            throw new RuntimeException("Entrega com o id " + id + " não encontrado");
        }
        entregaService.destroy(id);
        return ResponseEntity.noContent().build();
    }
}
