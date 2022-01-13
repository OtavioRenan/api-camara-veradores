package br.gov.application.camaramunicipal.utils;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceUtil
{
    public void modelExists(Optional<?> model)
    {
        new FactoryExceptionNotFund().create(model, "Registro não encontrado.");
    }

    public void modelExists(Optional<?> model, String message)
    {
        new FactoryExceptionNotFund().create(model, message);
    }

    public Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public String removeSpecialCharacters(String str)
    {
        return str.replaceAll("[-+.^:,]","");
    }
}
