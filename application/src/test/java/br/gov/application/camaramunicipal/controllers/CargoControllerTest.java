package br.gov.application.camaramunicipal.controllers;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.gov.application.camaramunicipal.models.CargoModel;

import lombok.RequiredArgsConstructor;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CargoController.class)
@RequiredArgsConstructor
public class CargoControllerTest
{
    private void find_all_test() throws Exception
    {
        
    }

    private CargoModel createModel()
    {
        CargoModel model = new CargoModel();
        model.setId(1L);
        model.setNome("Model nome teste");
        model.setDescricao("Model descrição teste");
        
        return model;
    }
}