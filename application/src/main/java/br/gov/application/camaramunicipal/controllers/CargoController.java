package br.gov.application.camaramunicipal.controllers;

import java.util.List;

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
@RestController
@RequestMapping("/api/cargo/")
public class CargoController
{

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id)
    {
        return new FactoryResponseEntity().create(("Cargo excluido com sucesso."), HttpStatus.OK);
    }
}