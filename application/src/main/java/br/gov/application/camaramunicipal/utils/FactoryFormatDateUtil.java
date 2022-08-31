package br.gov.application.camaramunicipal.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class FactoryFormatDateUtil {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now( ZoneId.of("America/Sao_Paulo") );

    private static final String DT_BR = "dd/MM/yyyy";
   
    private String simpleDateFormatDate(Date date) { return Objects.isNull(date) ? null : new SimpleDateFormat(DT_BR).format(date); }

    private String simpleDateFormatDate(Timestamp date) { return Objects.isNull(date) ? null : new SimpleDateFormat(DT_BR).format(date); }

    public Timestamp getNowWithZoneIdBr() { return Timestamp.valueOf(LOCAL_DATE_TIME); }

    public String formatDateBr(Date date) { return simpleDateFormatDate(date); }

    public String formatDateBr(Timestamp date) { return simpleDateFormatDate(date); }
}