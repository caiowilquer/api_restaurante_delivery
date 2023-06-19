package delivery.api.controller;

import delivery.api.model.Pedido;
import delivery.api.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService crudPedido) {
        this.pedidoService = crudPedido;
    }

    @GetMapping
    ResponseEntity<Iterable<Pedido>> all() {
        Iterable<Pedido> pedidos = pedidoService.findAll();
        if (!pedidos.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
        Pedido pedidoSave = pedidoService.save(pedido);
        return new ResponseEntity<>(pedidoSave, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com o id " + id + " não encontrado "));
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Pedido> update(@RequestBody Pedido novoPedido, @PathVariable Integer id) {
        Pedido pedidoUpadte = pedidoService.findById(id)
                .map(pedido -> {
                    pedido.setCliente(novoPedido.getCliente() == null ? pedido.getCliente() : novoPedido.getCliente());
                    pedido.setDescricao(novoPedido.getDescricao() == null ? pedido.getDescricao() : novoPedido.getDescricao());
                    pedido.setEntregue(novoPedido.getEntregue() == null ? pedido.getEntregue() : novoPedido.getEntregue());
                    pedido.setEntrega(novoPedido.getEntrega() == null ? pedido.getEntrega() : novoPedido.getEntrega());
                    pedido.setData_pedido(novoPedido.getData_pedido() == null ? pedido.getData_pedido() : novoPedido.getData_pedido());
                    pedido.setValor_total(novoPedido.getValor_total() == null ? pedido.getValor_total() : novoPedido.getValor_total());
                    return pedidoService.update(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return pedidoService.save(novoPedido);
                });
        return new ResponseEntity<>(pedidoUpadte, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteEntrega(@PathVariable Integer id) {
        if (!pedidoService.existsById(id)) {
            throw new RuntimeException("Pedido com o id " + id + " não encontrado");
        }
        pedidoService.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
