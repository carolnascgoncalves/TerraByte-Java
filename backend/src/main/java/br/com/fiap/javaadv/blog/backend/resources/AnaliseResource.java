package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnaliseRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnaliseResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.AnaliseService;
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
@RequestMapping("/api/analise")
@RequiredArgsConstructor
public class AnaliseResource {
    private final AnaliseService analiseService;

    @PostMapping
    public ResponseEntity<AnaliseRequest> create(@RequestParam UUID idEndereco, @RequestParam UUID idPlantio){
        var request = AnaliseRequest.builder().idEndereco(idEndereco).idPlantio(idPlantio).build();

        AnalisePlantio entidade = request.toEntity(request);
        AnalisePlantio savedEntity = this.analiseService.create(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnaliseResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.analiseService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(AnaliseResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnaliseResponse> fetchById( @PathVariable UUID id ){
        return this.analiseService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(AnaliseResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
