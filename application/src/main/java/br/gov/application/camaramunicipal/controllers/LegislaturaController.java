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

import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.LegislatureServicePort;
import br.gov.application.camaramunicipal.utils.FactoryResponseEntity;
@RestController
@RequestMapping("/api/legislatura")
public class LegislaturaController
{
    private final LegislatureServicePort service;

    private static final FactoryResponseEntity response = new FactoryResponseEntity();

    public LegislaturaController(LegislatureServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<LegisLatureSimpleDTO> findAll(@RequestParam Map<String, String> inputs) {
        return service.findAll(inputs);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    public Page<LegisLatureSimpleDTO> findAll(@RequestParam Map<String, String> inputs, @PathVariable int offSet, @PathVariable int pageSize) {
        return service.findAll(inputs, offSet, pageSize);
    }

    @PostMapping
    public LegislatureDTO save(@RequestBody LegislatureDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public LegislatureDTO findById(@RequestBody LegislatureDTO dto, @PathVariable Long id) {
        return service.save(dto, id);
    }

    @GetMapping("/{id}")
    public LegislatureDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return response.create(("Legislatura excluida com sucesso."), HttpStatus.OK);
    }
}