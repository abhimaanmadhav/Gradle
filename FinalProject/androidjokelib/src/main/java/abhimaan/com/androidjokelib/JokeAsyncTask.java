package abhimaan.com.androidjokelib;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import logger.Logger;

/**
 * Created by Abhimaan on 09/03/16.
 */
public class JokeAsyncTask extends AsyncTask<Void, Void, String>
{

    private WeakReference<JokeCallBack> calBack;

    public JokeAsyncTask(JokeCallBack callBack)
        {
            super();
            calBack = new WeakReference<JokeCallBack>(callBack);
            Logger.i(this, "created async task");
        }

    @Override
    protected String doInBackground(Void... params)
        {
            return new FetchJoke().retriveJokeFromServer();
        }

    @Override
    protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Logger.i(this, " service myApiService get joke called result avaliable " + s);
            Logger.i(this, " calBack.get() != null " + (calBack.get() != null));
            if (calBack.get() != null)
                {
                    calBack.get().onRecieved(s);
                }
        }
}
