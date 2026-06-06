package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.resources.dtos.DefensivoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.DefensivoResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.DefensivoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@SecurityRequirement(name = "bearerAuth")
public class DefensivoResource {
    private final DefensivoService defensivoService;

    @GetMapping("/listar")
    @Cacheable(value = "defensivoCache")
    public ResponseEntity<List<DefensivoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10,sort = "tipo",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(
                this.defensivoService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(DefensivoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @Cacheable(value="defensivoCache", key="#id")
    public ResponseEntity<DefensivoResponse> fetchById( @PathVariable UUID id ){
        return this.defensivoService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(DefensivoResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/tipo/{nomeTipo}")
    @Cacheable(value="defensivoTipoCache", key="#tipo")
    public ResponseEntity<List<DefensivoResponse>> fetchByTipo(@PathVariable String nomeTipo,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){

        return ResponseEntity.ok(
                defensivoService.fetchByTipo(nomeTipo.toUpperCase(), pageable)
                        .getContent()
                        .stream()
                        .map(DefensivoResponse::toDto)
                        .toList()
        );
    }
}
