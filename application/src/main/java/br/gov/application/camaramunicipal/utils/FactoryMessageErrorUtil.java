package br.gov.application.camaramunicipal.utils;

import java.util.HashMap;

public class FactoryMessageErrorUtil
{
    public HashMap<String, Object> create(String message)
    {
        HashMap<String, String> error = new HashMap<>();
        error.put("error", message);

        HashMap<String, Object> map = new HashMap<>();
        map.put("message", error);

        return map;
    }

    public HashMap<String, Object> create(String field, String message)
    {
        HashMap<String, String> fld = new HashMap<>();
        fld.put(field, message);

        HashMap<String, Object> map = new HashMap<>();
        map.put("message", fld);
        return map;
    }
}