package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.resources.dtos.DefensivoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.DefensivoResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.DefensivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/defensivo")
@RequiredArgsConstructor
public class DefensivoResource {
    private final DefensivoService defensivoService;

    @PostMapping
    public ResponseEntity<DefensivoRequest> create(@Valid @RequestBody DefensivoRequest request ){
        Defensivo entidade = request.toEntity(request);
        Defensivo savedEntity = this.defensivoService.create(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.defensivoService.existsById(id)) {
            this.defensivoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<DefensivoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.defensivoService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(DefensivoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefensivoResponse> fetchById( @PathVariable UUID id ){
        return this.defensivoService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(DefensivoResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
