package br.gov.application.camaramunicipal.services;

import org.junit.jupiter.api.Test;

import br.gov.application.camaramunicipal.models.CargoModel;

public class CargoServiceTest
{

    @Test
    public void find_all_test()
    {
    }

    private CargoModel createModel()
    {
        CargoModel model = new CargoModel();
        model.setNome("Model nome teste");
        model.setDescricao("Model descrição teste");
        
        return model;
    }
}