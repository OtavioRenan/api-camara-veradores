package br.gov.application.camaramunicipal.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import java.util.Locale;
import java.util.regex.Pattern;

public class FactorySlugUtil
{
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String create(String input)
    {
        String str = input.replaceAll("[-+.^:,/]","");
        String nowhitespace = WHITESPACE.matcher(str).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}