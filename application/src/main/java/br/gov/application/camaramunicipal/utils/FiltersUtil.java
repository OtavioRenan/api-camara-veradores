package br.gov.application.camaramunicipal.utils;

import java.util.Map;

public class FiltersUtil {
    public boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return map.getKey().equals(column) && !map.getValue().isEmpty();
    }

    public boolean filterEmptry(Map<String, String> map) {
        for(Map.Entry<String, String> v : map.entrySet()) {
            if(!v.getValue().isEmpty() && v.getValue() != null) { return false; }
        }

        return true;
    }
}
