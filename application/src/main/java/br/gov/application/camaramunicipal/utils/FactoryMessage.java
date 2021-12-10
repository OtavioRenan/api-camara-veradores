package br.gov.application.camaramunicipal.utils;

import java.util.HashMap;

public class FactoryMessage
{
    public HashMap<String, String> create(String message)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);
        return map;
    }
}