package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

abstract class tile{
    private final String name;
    private final int position;
    tile(String name,int position){
        this.name=name;
        this.position=position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
    public abstract void shake() throws Exception;

}
abstract class sound_making_tiles extends tile{
    private final int move_position;
    public sound_making_tiles(String name,int position, int move_position){
        super(name,position);
        this.move_position=move_position;
    }

    public int getMove_position() {
        return move_position;
    }
}
abstract class normal_tile extends tile{
    public normal_tile(String name,int position){
        super(name,position);
    }
}
class white extends normal_tile{
    public white(int position){
        super("white tile",position);
    }
    @Override
    public void shake(){
        System.out.println("I am a White tile!");
    }
}
class Snake extends sound_making_tiles{
    public Snake(int positon, int move_position){
        super("snake tile",positon,move_position);
    }
    @Override
    public void shake() throws snakebite_exception{
        throw new snakebite_exception("Hiss...! I am a Snake, you go back"+getMove_position()+" tiles!");
    }
}
class Vulture extends sound_making_tiles{
    public Vulture(int positon, int move_position){
        super("vulture tile",positon,move_position);
    }
    @Override
    public void shake() throws vulturebite_exception{
        throw new vulturebite_exception("Yapping...! I am a Vulture, you go back"+getMove_position()+" tiles!");
    }
}
class Cricket extends sound_making_tiles{
    public Cricket(int positon, int move_position){
        super("Cricket tile",positon,move_position);
    }
    @Override
    public void shake() throws cricketbite_exception{
        throw new cricketbite_exception("Chirp...! I am a Cricket,, you go back"+getMove_position()+" tiles!");
    }
}
class Trampoline extends sound_making_tiles{
    public Trampoline(int positon, int move_position){
        super("trampoline tile",positon,move_position);
    }
    @Override
    public void shake() throws trampoline_exception{
        throw new trampoline_exception("PingPong! I am a Trampoline, you advance"+getMove_position() +" tiles");
    }
}
class user{
    private final String name;
    private int curr_position;
    private int no_of_snake_bites;
    private int no_of_vulture_bites;
    private int no_of_cricket_bites;
    private int no_of_trmaploine_jumps;
    public user(String name){
        this.name=name;
        this.no_of_snake_bites=0;
        this.no_of_vulture_bites=0;
        this.no_of_cricket_bites=0;
        this.no_of_trmaploine_jumps=0;
    }

    public int getNo_of_snake_bites() {
        return no_of_snake_bites;
    }

    public void setNo_of_snake_bites(int no_of_snake_bites) {
        this.no_of_snake_bites = no_of_snake_bites;
    }

    public int getNo_of_vulture_bites() {
        return no_of_vulture_bites;
    }

    public void setNo_of_vulture_bites(int no_of_vulture_bites) {
        this.no_of_vulture_bites = no_of_vulture_bites;
    }

    public int getNo_of_cricket_bites() {
        return no_of_cricket_bites;
    }

    public void setNo_of_cricket_bites(int no_of_cricket_bites) {
        this.no_of_cricket_bites = no_of_cricket_bites;
    }

    public int getNo_of_trmaploine_jumps() {
        return no_of_trmaploine_jumps;
    }

    public void setNo_of_trmaploine_jumps(int no_of_trmaploine_jumps) {
        this.no_of_trmaploine_jumps = no_of_trmaploine_jumps;
    }

    public String getName() {
        return name;
    }

    public int getCurr_position() {
        return curr_position;
    }

    public void setCurr_position(int curr_position) {
        this.curr_position = curr_position;
    }
}
class dice{
    private final int faces=6;
    public int roll_dice(){
        Random rand = new Random();
        return (rand.nextInt(6)+1);
    }

}
class Computer{
    private final dice d;
    private int position;
    private int no_of_rolls;

    public int getPosition() {
        return position;
    }

    public int getNo_of_rolls() {
        return no_of_rolls;
    }

    public void setNo_of_rolls(int no_of_rolls) {
        this.no_of_rolls = no_of_rolls;
    }

    private boolean incage;
    public Computer(){
        d=new dice();
        position=0;
        incage=true;
    }
    public int roll(){
        return d.roll_dice();
    }
    public void move(int k,int length) throws Exception{
        if (position==0 && incage && k==6) {
            incage=false;
            throw new OutOfCageException(" out of cage ");
        }
        else if (position<length-1 && position+k>=length){
            throw new MoreException("");
        }
        else if(position==0 && incage && k!=6 ){
            throw new NotOutOfCageException(" OOPs you need 6 to start ");
        }
        else if (position==length-1){
            throw new GameWinException("");
        }
        else if(position>0 && position+k<0 ){
            position=0;
            throw new less_thanException("");
        }
        else{
            position=position+k;
        }
        if (position==length-1){
            throw new GameWinException("");
        }

    }
}
class OutOfCageException extends Exception{
    public OutOfCageException(String message){
        super(message);
    }
}
class less_thanException extends Exception{
    public less_thanException(String message){
        super(message);
    }
}
class NotOutOfCageException extends Exception{
    public NotOutOfCageException(String message){
        super(message);
    }
}
class GameWinException extends Exception{
    public GameWinException(String message){
        super(message);
    }
}
class MoreException extends Exception{
    public MoreException(String message){
        super(message);
    }
}
class snakebite_exception extends Exception{
    public snakebite_exception(String message){
        super(message);
    }
}
class vulturebite_exception extends Exception{
    public vulturebite_exception(String message){
        super(message);
    }
}
class cricketbite_exception extends Exception{
    public cricketbite_exception(String message){
        super(message);
    }
}
class trampoline_exception extends Exception{
    public trampoline_exception(String message){
        super(message);
    }
}
class InvalidIntegerException extends Exception{
    public InvalidIntegerException(String message){
        super(message);
    }
}
class Game{
    private final ArrayList<tile> map;
    private final int no_of_tiles;
    private final Computer computer;
    private final user u;
    private final int snake_tiles;
    private final int vulture_tiles;
    private final int cricket_tiles;
    private final int trampoline_tiles;
    private final int move_of_snake_tiles;
    private final int move_of_vulture_tiles;
    private final int move_of_cricket_tiles;
    private final int move_of_trampoline_tiles;
    public Game(int length, Scanner s) throws Exception{
            if (length<1){
                throw new InvalidIntegerException("wrong input please enter integer greater than 1");
            }
            System.out.println(">>Setting up the race track...");
            this.no_of_tiles=length;
            map=new ArrayList<>(length);
            computer = new Computer();
            Random rand = new Random();
            move_of_snake_tiles=rand.nextInt(10)+1;
            move_of_cricket_tiles=rand.nextInt(10)+1;
            move_of_vulture_tiles=rand.nextInt(10)+1;
            move_of_trampoline_tiles=rand.nextInt(10)+1;
            int k,l,st=0,vt=0,ct=0,tt=0;
            String name;
            tile t;
            for (int i=0;i<length;i++){
                k=rand.nextInt(2);
                if (k==1){
                    l=rand.nextInt(4);
                    if (l==0){
                        t=new Snake(i,-1*move_of_snake_tiles);
                        st+=1;
                    }
                    else if(l==1){
                        t=new Vulture(i,-1*move_of_vulture_tiles);
                        vt+=1;
                    }
                    else if(l==2){
                        t=new Cricket(i,-1*move_of_cricket_tiles);
                        ct+=1;
                    }
                    else if(l==3){
                        t=new Trampoline(i,move_of_trampoline_tiles);
                        tt+=1;
                    }
                    else{
                        System.out.println("wrong code");
                        t=new white(i);
                    }
                }
                else{
                    t=new white(i);
                }
                map.add(t);
            }
            snake_tiles=st;
            vulture_tiles=vt;
            cricket_tiles=ct;
            trampoline_tiles=tt;
            System.out.println(">>Danger: There are "+snake_tiles+", "+cricket_tiles+", "+vulture_tiles+" numbers of Snakes, Cricket, and Vultures respectively on your track!");
            System.out.println(">>Danger: Each Snake, Cricket, and Vultures can throw you back by "+move_of_snake_tiles+", "+move_of_cricket_tiles+", "+move_of_vulture_tiles+" number of Tiles respectively!");
            System.out.println(">>Good News: There are "+trampoline_tiles+" number of Trampolines on your track!");
            System.out.println(">>Good News: Each Trampoline can help you advance by "+move_of_trampoline_tiles+" number of Tiles");
            System.out.println(">>Enter the Player Name");
            name=s.next();
            u=new user(name);
            System.out.println(">>starting game with "+u.getName()+" on Tile 1");
            System.out.println(">>Control transferred to Computer for rolling the Dice for "+u.getName());


    }
    public void start(){
        boolean game_win=false;
        int r;
        while(!game_win){
            try{
                r=computer.roll();
                System.out.print(">>[Roll] "+computer.getNo_of_rolls()+" "+ u.getName()+" rolled "+ r +" at tile "+(computer.getPosition()+1));
                computer.move(r,no_of_tiles);
                System.out.println(" landed on tile "+(computer.getPosition()+1));
                System.out.println(" trying to shake tile "+(computer.getPosition()+1));
                map.get(computer.getPosition()).shake();
            }
            catch (OutOfCageException e){
                System.out.println(" You are out of cage ,you get a free roll");

            }
            catch (NotOutOfCageException e){
                System.out.println(e.getMessage());
            }
            catch (MoreException e){
                System.out.println(" more than required");
                System.out.println(" landed on tile "+(computer.getPosition()+1));
            }
            catch (snakebite_exception e){
                System.out.println(e.getMessage());
                try {
                    computer.move(-1*move_of_snake_tiles,no_of_tiles);
                    System.out.println(u.getName()+"moved to tile "+(computer.getPosition()+1));
                } catch (Exception ex) {
                    System.out.println(u.getName()+" can't go less so moved to tile 1");
                } finally {
                    u.setNo_of_snake_bites(u.getNo_of_snake_bites()+1);
                }

            }
            catch (vulturebite_exception e){
                System.out.println(e.getMessage());
                try {
                    computer.move(-1*move_of_vulture_tiles,no_of_tiles);
                    System.out.println(u.getName()+" moved to tile "+(computer.getPosition()+1));
                } catch (Exception ex) {
                    System.out.println(u.getName()+" can't go less so moved to tile 1");
                } finally {
                    u.setNo_of_vulture_bites(u.getNo_of_vulture_bites()+1);
                }
            }
            catch (cricketbite_exception e){
                System.out.println(e.getMessage());
                try {
                    computer.move(-1*move_of_cricket_tiles,no_of_tiles);
                    System.out.println(u.getName()+" moved to tile "+(computer.getPosition()+1));
                } catch (Exception ex) {
                    System.out.println(u.getName()+" can't go less so moved to tile 1");
                } finally {
                    u.setNo_of_cricket_bites(u.getNo_of_cricket_bites()+1);
                }
            }
            catch (trampoline_exception e){
                System.out.println(e.getMessage());
                try {
                    computer.move(move_of_trampoline_tiles,no_of_tiles);
                    System.out.println(u.getName()+" moved to tile "+(computer.getPosition()+1));
                }
                catch (Exception ex) {
                    System.out.println(" more than required");
                }
                finally {
                    u.setNo_of_trmaploine_jumps(u.getNo_of_trmaploine_jumps()+1);
                }
            }
            catch(GameWinException e){
                System.out.println("\n"+u.getName()+" wins the race in "+(computer.getNo_of_rolls())+" rolls");
                game_win=true;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                computer.setNo_of_rolls(computer.getNo_of_rolls()+1);
            }
        }
        System.out.println("Total Snake Bites = " +u.getNo_of_snake_bites());
        System.out.println("Total Vulture Bites = " +u.getNo_of_vulture_bites());
        System.out.println("Total Cricket Bites = " +u.getNo_of_cricket_bites());
        System.out.println("Total Trampolines = " +u.getNo_of_trmaploine_jumps());
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        Game game = null;
        Boolean valid=false,valid_integr=false;
        int l;
        while(!valid) {
            try {
                System.out.println(">>Enter total number of tiles on the race track (length)");
                l = s.nextInt();
                game=new Game(l,s);
                valid=true;
            }
            catch (InputMismatchException e){
                System.out.println(">>please enter an integer");
                s= new Scanner(System.in);
            }
            catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>Hit enter to start the game");
        s.nextLine();
        s.nextLine();
        System.out.println(">>Game started =============================");
        game.start();
    }
}
