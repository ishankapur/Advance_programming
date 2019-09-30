package com.company;


import org.junit.Assert;
import org.junit.Test;

import static com.company.Main.deserialize;
import static com.company.Main.serialize;

public class tileTest {
    private Game game_curr;

    @Test(expected = snakebite_exception.class)
    public void snake_bite_test() throws Exception {
        tile snake = new Snake(0,-1);
        snake.shake();
    }
    @Test(expected = vulturebite_exception.class)
    public void vulture_bite_test() throws Exception {
        tile vulture = new Vulture(0,-1);
        vulture.shake();
    }
    @Test(expected = cricketbite_exception.class)
    public void cricket_bite_test() throws Exception {
        tile cricket = new Cricket(0,-1);
        cricket.shake();
    }
    @Test(expected = trampoline_exception.class)
    public void trampoline_jump_test() throws Exception {
        tile trampoline = new Trampoline(0,-1);
        trampoline.shake();
    }
    @Test(expected = GameWinException.class)
    public void game_win_test() throws Exception {
        Game game = new Game(100,"test",4);
        game.start();
        game.start();
        game.start();
        game.start();
    }

    @Test
    public void Serialize_test() {
        Game game = new Game(100,"test",4);
        try {
            game.start();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        try {
            serialize(game);
            game_curr=game;
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
    }
    @Test
    public void DeSerialize_test() {
        Game game = new Game(100,"test",4);
        try {
            game.start();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        try {
            serialize(game);
            game_curr=game;
        }
        catch (Exception e){
            Assert.assertFalse(true);
        }
        try {
             game= deserialize("test.txt");
            Assert.assertTrue(game_curr.equals(game));
        }
        catch(Exception e){
            Assert.assertFalse(true);
        }




    }
}