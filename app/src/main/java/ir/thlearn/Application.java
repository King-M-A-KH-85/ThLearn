package ir.thlearn;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Application extends android.app.Application {
    public static String SERVER = "https://thcode.ir";

    public static RequestQueue volley;
    public static Context contect;

    @Override
    public void onCreate() {
        super.onCreate();
        contect = getApplicationContext();
        volley = Volley.newRequestQueue(this);
    }

    public static SharedPreferences getPreferences() {
        return contect.getSharedPreferences("DATA", MODE_PRIVATE);
    }
}
