package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.resources.dtos.PlantioRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.PlantioResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.PlantioService;
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
@RequestMapping("/api/plantio")
@RequiredArgsConstructor
public class PlantioResource {
    private final PlantioService plantioService;
    /*
    @PostMapping
    public ResponseEntity<PlantioRequest> create(@Valid @RequestBody PlantioRequest request ){
        Plantio entidade = request.toEntity(request);
        Plantio savedEntity = this.plantioService.create(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.plantioService.existsById(id)) {
            this.plantioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

     */

    @GetMapping("/listar")
    public ResponseEntity<List<PlantioResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.plantioService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(PlantioResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantioResponse> fetchById( @PathVariable UUID id ){
        return this.plantioService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(PlantioResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
