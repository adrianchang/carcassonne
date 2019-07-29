package edu.cmu.cs.cs214.hw4.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p1;
    @Before
    public void setAPlayer() {
        p1 = new Player("Adrian");
    }

    @Test
    public void testSendAndgetFollower(){
        for (int i = 0; i < 7; i++) {
            Follower f = p1.sendAFollower();
            assertEquals(f.getBoss(), p1);
            assertEquals(f.getBoss().getName(), p1.getName());
            assertEquals(p1.getName(), "Adrian");
        }
        assertEquals(p1.getScore(), 0);
        assertEquals(p1.sendAFollower(), null);
    }

    @Test
    public void sentPlayerTest(){
        for (int i = 0; i < 7; i++) {
            p1.sendAFollower();
        }
        assertEquals(p1.sendAFollower(), null);
    }



}