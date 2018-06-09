package com.hw4_2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class GameResultTest
{
    private GameResult test;

    @Before
    public void setUp(){
        test = new GameResult();
    }

    @After
    public void tearDown(){
        test = null;
    }

    @Test
    public void testGameSystem() {
        test.setPlayerChoice(1);
        test.setComChoice(1);
        test.judge();
        assertEquals(2, test.getResult());

        test.setPlayerChoice(1);
        test.setComChoice(2);
        test.judge();
        assertEquals(0, test.getResult());

        test.setPlayerChoice(1);
        test.setComChoice(3);
        test.judge();
        assertEquals(1, test.getResult());

        test.setPlayerChoice(2);
        test.setComChoice(1);
        test.judge();
        assertEquals(1, test.getResult());

        test.setPlayerChoice(2);
        test.setComChoice(2);
        test.judge();
        assertEquals(2, test.getResult());

        test.setPlayerChoice(2);
        test.setComChoice(3);
        test.judge();
        assertEquals(0, test.getResult());

        test.setPlayerChoice(3);
        test.setComChoice(1);
        test.judge();
        assertEquals(0, test.getResult());

        test.setPlayerChoice(3);
        test.setComChoice(2);
        test.judge();
        assertEquals(1, test.getResult());

        test.setPlayerChoice(3);
        test.setComChoice(3);
        test.judge();
        assertEquals(2, test.getResult());
    }
}
