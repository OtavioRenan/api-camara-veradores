package br.gov.application.camaramunicipal.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.application.camaramunicipal.utils.FactoryResponseEntity;
import br.gov.application.camaramunicipal.models.VereadorModel;
import br.gov.application.camaramunicipal.services.VereadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vereador/")
@RequiredArgsConstructor
public class VereadorController
{
    private final VereadorService service;

    @GetMapping
    public List<VereadorModel> findAll()
    {
        return this.service.findAll();
    }
    
    @GetMapping("{id}")
    public Object find(@PathVariable Long id)
    {
        return this.service.find(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody VereadorModel model)
    {
        return this.service.save(model);
    }

    @PutMapping("{id}")
    public Object update(@Valid @RequestBody VereadorModel model, @PathVariable Long id)
    {
        return this.service.update(model, id);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id)
    {
        this.service.delete(id);
        return new FactoryResponseEntity().create(("Vereador (a) excluido (a) com sucesso."), HttpStatus.OK);
    }
}