package com.abhimaan;

public class Joke {

    public String jokeString="great joke";;
    public Joke()
        {
        }


    public String getJokeString()
        {
            //TODO return jokes
            System.out.print("getting joke "+jokeString);
            return jokeString;
        }

    public void setJokeString(String jokeString)
        {
            this.jokeString = jokeString;
        }

    @Override
    public String toString()
        {
            return "Joke{" +
                    "jokeString='" + jokeString + '\'' +
                    '}';
        }
}
