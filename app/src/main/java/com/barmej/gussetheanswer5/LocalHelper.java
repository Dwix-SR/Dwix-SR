package com.barmej.gussetheanswer5;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocalHelper {
    public static Context setlocale(Context context,String language){
        return updateResourcesLegacy(context, language);
    }
    @SuppressWarnings("deprecation")
    private static  Context updateResourcesLegacy(Context context,String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return context;

    }
}
