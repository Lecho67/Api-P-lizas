package com.Polizas.Polizas.Controllers;

import com.Polizas.Polizas.Persistence.Entities.CompraPoliza;
import com.Polizas.Polizas.Persistence.Entities.Poliza;
import com.Polizas.Polizas.Persistence.Entities.Transaccion;
import com.Polizas.Polizas.Persistence.Entities.Usuario;
import com.Polizas.Polizas.Persistence.Repositories.CompraPolizaRepository;
import com.Polizas.Polizas.Persistence.Repositories.PolizaRepository;
import com.Polizas.Polizas.Persistence.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompraController {

    @Autowired
    private CompraPolizaRepository compraPolizaRepository;

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    // 3.Recurso: Compra de Póliza   Nota: Falta añadirle la informacion de la clase Transaccion por favor ayuda con eso
    @PostMapping("/api/compra-polizas")
    public ResponseEntity<CompraPoliza> createCompraPoliza(
            @RequestParam Long polizaId,
            @RequestParam Long usuarioId,
            @RequestBody CompraPoliza request
    ) {
        Optional<Poliza> optionalPoliza = polizaRepository.findById(polizaId);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

        if (optionalPoliza.isPresent() && optionalUsuario.isPresent()) {
            Poliza poliza = optionalPoliza.get();
            Usuario usuario = optionalUsuario.get();

            CompraPoliza compraPoliza = new CompraPoliza();
            compraPoliza.setFormaDePago(request.getFormaDePago());
            compraPoliza.setPoliza(poliza);
            compraPoliza.setUsuario(usuario);

            // Aquí puedes manejar la información de la transacción
            Transaccion transaccion = new Transaccion();
            transaccion.setMonto(request.getTransaccion().getMonto());
            transaccion.setMoneda(request.getTransaccion().getMoneda());
            compraPoliza.setTransaccion(transaccion);

            CompraPoliza savedCompraPoliza = compraPolizaRepository.save(compraPoliza);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompraPoliza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4.Recurso: Detalles de Póliza por Usuario
    @GetMapping("/api/poliza/{id}")

    public List<Poliza> findPolizaByUserId(@PathVariable("id") Long id){
        return compraPolizaRepository.findPolizaByUserId(id);
    }

}
