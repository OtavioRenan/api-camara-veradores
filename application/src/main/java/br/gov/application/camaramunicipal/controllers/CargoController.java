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
import br.gov.application.camaramunicipal.exceptions.DefaultException;
import br.gov.application.camaramunicipal.models.CargoModel;
import br.gov.application.camaramunicipal.services.CargoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cargo/")
@RequiredArgsConstructor
public class CargoController
{
    private final CargoService service;

    private FactoryResponseEntity response = new FactoryResponseEntity();

    @GetMapping
    public List<CargoModel> findAll()
    {
        return this.service.findAll();
    }
    
    @GetMapping("{id}")
    public Object find(@PathVariable Long id)
    {
        try {
            return this.service.find(id);
        } catch (DefaultException e) {
            return this.response.create(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return this.response.create(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object save(@Valid @RequestBody CargoModel model)
    {
        try {
            return this.service.save(model);
        } catch (DefaultException e) {
            return this.response.create(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return this.response.create(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Object update(@Valid @RequestBody CargoModel model, @PathVariable Long id)
    {
        try {
            return this.service.update(model, id);
        } catch (DefaultException e) {
            return this.response.create(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return this.response.create(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id)
    {
        try {
            this.service.delete(id);
            return this.response.create("Cargo excluido com sucesso.", HttpStatus.OK);
        } catch (DefaultException e) {
            return this.response.create(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return this.response.create(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}