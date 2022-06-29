package br.gov.application.camaramunicipal.utils;

import java.sql.Timestamp;
public class ServiceUtil
{
    public Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public String removeSpecialCharacters(String str)
    {
        return str.replaceAll("[-+.^:,/]","");
    }

    public String makeSlug(String str)
    {
        return new FactorySlugUtil().create(str);
    }
}