package abhimaan.com.androidjokelib;

import com.abhimaan.myapplication.backend.myApi.MyApi;
import com.abhimaan.myapplication.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Abhimaan on 14/03/16.
 */
public class FetchJoke
{
    private static MyApi myApiService = null;

    public String retriveJokeFromServer()
        {
//            Logger.i(this, "running in back ground");
            if (myApiService == null)
                {  // Only do this once
                    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            // options for running against local devappserver
                            // - 10.0.2.2 is localhost's IP address in Android emulator
                            // - turn off compression when running against local devappserver
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
                            {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?>
                                                               abstractGoogleClientRequest)
                                        throws IOException

                                    {
                                        abstractGoogleClientRequest.setDisableGZipContent(true);
                                    }
                            });
                    // end options for devappserver
                    builder.setApplicationName("Joke app");
                    myApiService = builder.build();
//                    Logger.d(this, "created myApiService");
                }


            try
                {

                    MyBean joke = myApiService.sayHi().execute();
//                    Logger.i(this, "calling service myApiService get joke" + joke.getData());
//                    Logger.i(this, "cto string" + joke);
                    return parseJoke(joke);

                } catch (IOException e)
                {
                    return e.getMessage();
                }

        }

    private String parseJoke(MyBean result)
        {
            String joke = null;
            try
                {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    joke = jsonObject.getString("data");
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            return joke;
        }
}
