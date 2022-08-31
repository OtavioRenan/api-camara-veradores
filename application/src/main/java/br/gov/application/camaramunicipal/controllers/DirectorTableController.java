package br.gov.application.camaramunicipal.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.utils.FactoryResponseEntity;


@RestController
@RequestMapping("/api/director-table")
public class DirectorTableController {
    
    private final DirectorTableServicePort service;

    private static final FactoryResponseEntity response = new FactoryResponseEntity();

    public DirectorTableController(DirectorTableServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<DirectorTableSimpleDTO> findAll(@RequestParam Map<String, String> inputs) {
        return service.findAll(inputs);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    public Page<DirectorTableSimpleDTO> findAll(@RequestParam Map<String, String> inputs, @PathVariable int offSet, @PathVariable int pageSize) {
        return service.findAll(inputs, offSet, pageSize);
    }

    @PostMapping
    public DirectorTableDTO save(@RequestBody DirectorTableDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public DirectorTableDTO findById(@RequestBody DirectorTableDTO dto, @PathVariable Long id) {
        return service.save(dto, id);
    }

    @GetMapping("/{id}")
    public DirectorTableDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return response.create(("Comissão excluida com sucesso."), HttpStatus.OK);
    }
}