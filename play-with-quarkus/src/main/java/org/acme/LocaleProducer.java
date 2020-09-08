package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Locale;

@ApplicationScoped
public class LocaleProducer {

    @Produces
    @Named("de")
    public Locale getDefault(){
        return  Locale.getDefault();
    }

    @Produces
    @Named("en_US")
    public Locale getEnUSLocale(){
        return  Locale.US;
    }

    @Produces
    @Named("es_ES")
    public Locale getEsEsLocale(){
        return new Locale("es","Es");
    }

    @Produces
    @SpainLocale
    public Locale getSpainLocale(){
        return new Locale("es","Es");
    }

    @Produces
    String timeZone="Asia/Kolkata";

}
