package com.Polizas.Polizas.Controllers;

import com.Polizas.Polizas.Persistence.Entities.CompraPoliza;
import com.Polizas.Polizas.Persistence.Entities.Poliza;
import com.Polizas.Polizas.Persistence.Entities.Usuario;
import com.Polizas.Polizas.Persistence.Respositories.CompraPolizaRespository;
import com.Polizas.Polizas.Persistence.Respositories.PolizaRepository;
import com.Polizas.Polizas.Persistence.Respositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CompraController {

    @Autowired
    private CompraPolizaRespository compraPolizaRepository;

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/api/compra-polizas")
    public ResponseEntity<CompraPoliza> createCompraPoliza(@RequestParam Long polizaId, @RequestParam Long usuarioId, @RequestBody CompraPoliza request) {
        Optional<Poliza> optionalPoliza = polizaRepository.findById(polizaId);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

        if (optionalPoliza.isPresent() && optionalUsuario.isPresent()) {
            Poliza poliza = optionalPoliza.get();
            Usuario usuario = optionalUsuario.get();

            CompraPoliza compraPoliza = new CompraPoliza();
            compraPoliza.setFormaDePago(request.getFormaDePago());
            compraPoliza.setPoliza(poliza);
            compraPoliza.setUsuario(usuario);

            CompraPoliza savedCompraPoliza = compraPolizaRepository.save(compraPoliza);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompraPoliza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
