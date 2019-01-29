package detect.context.lln.Model;

import android.graphics.drawable.Drawable;

public class MainModel {

    private final String nameApp;
    private final String appPackage;
    private final Drawable logo;

    public MainModel(String appPackage, String nameApp, Drawable logo) {
        this.appPackage = appPackage;
        this.nameApp = nameApp;
        this.logo = logo;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getNameApp() {
        return nameApp;
    }

    public Drawable getLogo() {
        return logo;
    }

}
