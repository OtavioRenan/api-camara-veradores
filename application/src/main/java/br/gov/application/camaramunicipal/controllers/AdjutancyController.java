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

import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.utils.FactoryResponseEntity;
@RestController
@RequestMapping("/api/adjutancy")
public class AdjutancyController
{
    private final AdjutancyServicePort service;

    private static final FactoryResponseEntity response = new FactoryResponseEntity();

    public AdjutancyController(AdjutancyServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<AdjutancySimpleDTO> findAll(@RequestParam Map<String, String> inputs) {
        return service.findAll(inputs);
    }

    @GetMapping("/seed")
    public void seed() {
        service.seed();
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    public Page<AdjutancySimpleDTO> findAll(@RequestParam Map<String, String> inputs, @PathVariable int offSet, @PathVariable int pageSize) {
        return service.findAll(inputs, offSet, pageSize);
    }

    @PostMapping
    public AdjutancyDTO save(@RequestBody AdjutancyDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public AdjutancyDTO findById(@RequestBody AdjutancyDTO dto, @PathVariable Long id) {
        return service.save(dto, id);
    }

    @GetMapping("/{id}")
    public AdjutancyDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return response.create(("Cargo excluido com sucesso."), HttpStatus.OK);
    }
}