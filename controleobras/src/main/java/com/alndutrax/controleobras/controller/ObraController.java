package com.alndutrax.controleobras.controller;

import com.alndutrax.controleobras.model.Obra;
import com.alndutrax.controleobras.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/obras")
public class ObraController {

    @Autowired
    private ObraRepository obraRepository;

    // GET /obras
    @GetMapping
    public List<Obra> listarObras() {
        return obraRepository.findAll();
    }

    // GET /obras/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarPorId(@PathVariable Long id) {
        Optional<Obra> obra = obraRepository.findById(id);
        return obra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /obras
    @PostMapping
    public Obra criarObra(@RequestBody Obra obra) {
        return obraRepository.save(obra);
    }

    // PUT /obras/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizarObra(@PathVariable Long id, @RequestBody Obra novaObra) {
        Optional<Obra> optional = obraRepository.findById(id);
        if (optional.isPresent()) {
            Obra obra = optional.get();
            obra.setNome(novaObra.getNome());
            obra.setEndereco(novaObra.getEndereco());
            obra.setResponsavel(novaObra.getResponsavel());
            return ResponseEntity.ok(obraRepository.save(obra));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /obras/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarObra(@PathVariable Long id) {
        if (obraRepository.existsById(id)) {
            obraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
