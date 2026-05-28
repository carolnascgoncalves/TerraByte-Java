package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.interfaces.TipoSoloService;
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
@RequestMapping("/api/tipoSolo")
@RequiredArgsConstructor
public class TipoSoloResource {
    private final TipoSoloService tipoSoloService;

    @PostMapping
    public ResponseEntity<TipoSoloRequest> create(@Valid @RequestBody TipoSoloRequest request ){
        TipoSolo entidade = request.toEntity(request);
        TipoSolo savedEntity = this.tipoSoloService.create(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.tipoSoloService.existsById(id)) {
            this.tipoSoloService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoSoloResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.tipoSoloService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(TipoSoloResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSoloResponse> fetchById( @PathVariable UUID id ){
        return this.tipoSoloService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(TipoSoloResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
