package logger;

import android.util.Log;

public class Logger
{
    static boolean ENABLE_LOG = true;

    public static void d(Object object, String message)
        {
            if (ENABLE_LOG)
                Log.d("" + object.getClass(), message);
        }

    public static void i(Object object, String message)
        {
            if (ENABLE_LOG)
                Log.i("" + object.getClass(), message);
        }

    public static void v(Object object, String message)
        {
            if (ENABLE_LOG)
                Log.v("" + object.getClass(), message);
        }

    public static void e(Object object, String string)
        {
            if (ENABLE_LOG)
                {
                    Log.e("" + object.getClass(), string);
                }
        }
}
