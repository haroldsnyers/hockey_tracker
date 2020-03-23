package ck.edu.com.hockey_tracker;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Implement Locale Configuration in the Application class
 * here we set settings locale on onConfigurationChanged and attachBaseContext.
 */

public class MultiLanguageApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
