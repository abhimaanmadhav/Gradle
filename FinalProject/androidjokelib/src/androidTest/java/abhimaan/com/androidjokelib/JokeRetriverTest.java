package abhimaan.com.androidjokelib;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import logger.Logger;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokeRetriverTest
        implements JokeCallBack
{
    public JokeRetriverTest()
        {

        }

    @Test
    public void asynchTest()
        {
            Logger.i(this, "test started");
            new JokeAsyncTask(JokeRetriverTest.this).execute();

            //Assuming the call will not take more than 20 seconds
            try
                {
                    Thread.sleep(20000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
        }

    @Override
    public void onRecieved(String joke)
        {
            Assert.assertTrue("not a vaild joke joke empty", (joke != null && !joke.isEmpty()));
            Assert.assertEquals("great joke", joke);
        }
}