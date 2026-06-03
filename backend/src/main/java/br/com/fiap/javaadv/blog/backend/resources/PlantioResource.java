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

    @GetMapping("/solo/{idTipoSolo}")
    public ResponseEntity<List<PlantioResponse>> fetchByTipoSolo(@PathVariable UUID idTipoSolo,
                                                                 @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                plantioService.fetchByTipoSolo(idTipoSolo, pageable)
                        .getContent()
                        .stream()
                        .map(PlantioResponse::toDto)
                        .toList()
        );
    }

    @GetMapping("/defensivo/{idDefensivo}")
    public ResponseEntity<List<PlantioResponse>> fetchByDefensivo(@PathVariable UUID idDefensivo,
            @ParameterObject
            @PageableDefault(page = 0, size = 10)
            Pageable pageable){

        return ResponseEntity.ok(
                plantioService.fetchByDefensivo(idDefensivo, pageable)
                        .getContent()
                        .stream()
                        .map(PlantioResponse::toDto)
                        .toList()
        );
    }
}
