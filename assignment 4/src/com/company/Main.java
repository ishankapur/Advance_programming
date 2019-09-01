package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
interface game_func{
    void start_game(Scanner s);
}
abstract class heroes{
    private float HP;
    private int attack_damage;
    private int defense;
    private int Defense;
    private final int special_move;
    private int no_of_moves;
    private int Hero_XP;
    private int level;
    private float Max_HP;
    private boolean defend;
    private final String special;
    private ArrayList<sidekick> sidekicks;
    public heroes(int special_move){
        this.defend=false;
        this.level=1;
        this.Max_HP=100;
        this.HP=100;
        this.Hero_XP=0;
        this.no_of_moves=0;
        this.special_move=special_move;
        this.sidekicks=new ArrayList<>();

        if (special_move==1){
            this.special="Attack and defense attributes get boosted by 5 for the next 3\n" +
                    "moves.";
        }
        else if(special_move==2){
            this.special="Cast a spell which reduces the opponent's HP by 5% for the next\n" +
                    "3 moves.";
        }
        else if(special_move==3){
            this.special="Steal 30% of opponents HP.";
        }
        else if(special_move==4){
            this.special="Increase own HP by 5% for the next 3 moves.";
        }
        else{
            this.special="";
        }

    }

    public ArrayList<sidekick> getSidekicks() {
        return sidekicks;
    }
    public sidekick findsidekick(){
        if (sidekicks.size()==0){
            return null;
        }
        sidekick sd= sidekicks.get(0);
        for (int i=1;i<sidekicks.size();i++){
            if(sd.compareTo(sidekicks.get(i))<0){
                sd=sidekicks.get(i);
            }
        }
        return sd;
    }

    public void setSidekicks(ArrayList<sidekick> sidekicks) {
        this.sidekicks = sidekicks;
    }

    public void sDefense(int Defense){
        this.Defense=Defense;
    }
    public int gDefense(){
        return this.Defense;
    }
    public float attack(){
        return attack_damage;
    }
    public void lose_HP(float k){
        if (defense<=k){
            this.HP=this.HP-k+defense;
        }
        if (HP<0){
            HP=0;
        }
        this.defense=0;

    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getMax_HP() {
        return Max_HP;
    }

    public void setMax_HP(float max_HP) {
        Max_HP = max_HP;
    }

    public int getHero_XP() {
        return Hero_XP;
    }

    public void setHero_XP(int hero_XP) {
        Hero_XP = hero_XP;
    }

    public int getNo_of_moves() {
        return no_of_moves;
    }

    public void setNo_of_moves(int no_of_moves) {
        this.no_of_moves = no_of_moves;
    }

    public int getSpecial_move() {
        return special_move;
    }


    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }


    public void setAttack_damage(int attack_damage) {
        this.attack_damage = attack_damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack_damage() {
        return attack_damage;
    }

    public boolean isDefend() {
        return defend;
    }

    public void setDefend(boolean defend) {
        this.defend = defend;
    }

    public String getSpecial() {
        return special;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void acivateDefense(){
        this.defense=Defense;
    }
    public void add_sidekick(sidekick s){
        this.sidekicks.add(s);
    }
    public void delete_sidekick(sidekick s){
        this.sidekicks.remove(s);
    }
    public abstract int special_attack(monsters m);
    public void add_XP(int m){
        this.Hero_XP+=m;
        if (Hero_XP>=20 && Hero_XP<670 && level==1){
            this.level=2;
            this.Max_HP=150;
            System.out.println("Level Up: level:2");
            this.setAttack_damage(getAttack_damage()+1);
            this.sDefense(gDefense()+1);

        }
        else if(Hero_XP>=60 && Hero_XP<120 && level ==2){
            this.level=3;
            this.Max_HP=200;
            System.out.println("Level Up: level:3");
            this.setAttack_damage(getAttack_damage()+1);
            this.sDefense(gDefense()+1);
        }
        else if(Hero_XP>=120 && level==3){
            this.level=4;
            this.Max_HP=250;
            System.out.println("Level Up: level:4");
            this.setAttack_damage(getAttack_damage()+1);
            this.sDefense(gDefense()+1);
        }
        this.HP=Max_HP;
        System.out.println("HP refilled : "+Max_HP);
    }
}
class warrior extends heroes{
    public warrior(){
        super(1);
        this.setAttack_damage(10);
        this.setDefense(0);
        this.sDefense(3);
    }
    @Override
    public int special_attack(monsters m){
        this.setAttack_damage(this.getAttack_damage()+5);
        this.setDefense(this.gDefense()+5);
        this.acivateDefense();
        if (this.getHP()>this.getMax_HP()){
            this.setHP(this.getMax_HP());
        }
        return 3;
    }
}
class mage extends heroes{
    public mage(){
        super(2);
        this.setAttack_damage(5);
        this.setDefense(0);
        this.sDefense(5);

    }
    @Override
    public int special_attack(monsters m){
        m.lose_HP(5*m.getHP()/100);
        if (this.getHP()>this.getMax_HP()){
            this.setHP(this.getMax_HP());
        }
        return 3;
    }
}
class theif extends heroes{
    public theif(){
        super(3);
        this.setAttack_damage(6);
        this.setDefense(0);
        this.sDefense(4);

    }
    @Override
    public int special_attack(monsters m){
        float y;
        y=3*m.getHP()/10;
        m.lose_HP(y);
        this.setHP(this.getHP()+y);
        if (this.getHP()>this.getMax_HP()){
            this.setHP(this.getMax_HP());
        }
        return 0;
    }
}
class healer extends heroes{
    public healer(){
        super(4);
        this.setAttack_damage(4);
        this.setDefense(0);
        this.sDefense(8);

    }
    @Override
    public int special_attack(monsters m){
        this.setHP(this.getHP()*105/100);
        if (this.getHP()>this.getMax_HP()){
            this.setHP(this.getMax_HP());
        }
        return 3;
    }
}
abstract class sidekick implements Comparable<sidekick>{
    private int XP;
    private float attack_damage;
    private float HP;
    private int expendable_XP;
    private boolean ability_acyivated=false;
    abstract public void special_attack(heroes h, monsters m);
    abstract public void special_attack_ends(heroes h);
    abstract public float attack();
    abstract public void print_HP();
    public sidekick(){
        this.HP=100;
        this.expendable_XP=0;
    }
    public void lose_HP(float k){
        this.setHP(this.getHP()-k);
        if (this.getHP()<=0){
            this.setHP(0);
        }
    }

    public boolean isAbility_acyivated() {
        return ability_acyivated;
    }
    public void increase_damage(int s){
        expendable_XP+=s;
        this.attack_damage+=(int)(expendable_XP/5);
        expendable_XP=expendable_XP%5;
    }

    public void setAbility_acyivated(boolean ability_acyivated) {
        this.ability_acyivated = ability_acyivated;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public float getAttack_damage() {
        return attack_damage;
    }

    public void setAttack_damage(float attack_damage) {
        this.attack_damage = attack_damage;
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }
    @Override
    public int compareTo(sidekick o) {
        return this.getXP()-o.getXP();
    }
}
class minions extends sidekick implements Cloneable{
    private boolean ability;
    private minions m1;
    private minions m2;
    private minions m3;
    public minions(int XP){
        this.setXP(0);
        this.setAttack_damage((float)1+XP/2);
        this.ability=true;
        this.m1=null;
        this.m2=null;
        this.m3=null;

    }

    public boolean isAbility() {
        return ability;
    }
    @Override
    public void print_HP(){
        if (this.isAbility_acyivated()){
            System.out.println("sidekick HP:"+this.getHP());
            System.out.println("sidekick HP:"+m1.getHP());
            System.out.println("sidekick HP:"+m2.getHP());
            System.out.println("sidekick HP:"+m3.getHP());
        }
        else{
            System.out.println("sidekick HP:"+this.getHP());
        }
    }

    @Override
    public void special_attack(heroes h,monsters m){
        m1=clone();
        m2=clone();
        m3=clone();
        this.setAbility_acyivated(true);
        this.ability=false;
    }
    public minions clone(){
        minions m = this;
        return m;
    }
    @Override
    public void special_attack_ends(heroes h){
        m1=null;
        m2=null;
        m3=null;
        this.setAbility_acyivated(false);
    }
    @Override
    public float attack(){
        if (this.isAbility_acyivated()){
            return this.getAttack_damage()+m1.getAttack_damage()+m2.getAttack_damage()+m3.getAttack_damage();
        }
        return this.getAttack_damage();
    }



}
class knight extends sidekick{
    public knight(int XP){
        this.setXP(0);
        this.setAttack_damage((float)2+XP/2);
    }
    @Override
    public void special_attack(heroes h,monsters m){
        if (m instanceof Zombies){
            h.sDefense(h.gDefense()+5);
        }
        this.setAbility_acyivated(true);
    }
    @Override
    public void special_attack_ends(heroes h){
        if (this.isAbility_acyivated()) {
            h.sDefense(h.gDefense() - 5);
            this.setAbility_acyivated(false);
        }
    }
    @Override
    public float attack(){
        return this.getAttack_damage();
    }
    @Override
    public void print_HP(){
        System.out.println("siddkick HP: "+this.getHP());
    }
}

abstract class monsters{
    private final int level;
    private float attack_damage;
    private float HP;
    private final int monster_XP;
    private final int max_HP;
    public monsters(int level,int max_HP){
        this.max_HP=max_HP;
        this.level=level;
        this.monster_XP=level*20;

    }

    public int getMax_HP() {
        return max_HP;
    }

    public int getLevel() {
        return level;
    }

    public float getAttack_damage() {
        return attack_damage;
    }

    public void setAttack_damage(float attack_damage) {
        this.attack_damage = attack_damage;
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public int getMonster_XP() {
        return monster_XP;
    }
    public float attack(){
        Random rand= new Random();
        float a =(float)((1+ rand.nextGaussian())*HP/8);
        while(a<=0 || a>=HP/4){
            a =(float)((1+ rand.nextGaussian())*HP/8);
        }
        System.out.println("    d "+HP);
        System.out.println(a);
        return a;
    }
    public void lose_HP(float k){
        this.HP=this.HP-k;
        if (HP<0){
            HP=0;
        }
    }

}
class goblin extends monsters{
    public goblin(){
        super(1,100);
        this.setHP(100);
        this.setAttack_damage(getHP()/4);

    }

}
class Zombies extends monsters{
    public Zombies(){
        super(2,150);
        this.setHP(150);
        this.setAttack_damage(getHP()/4);

    }

}
class fiends extends monsters{
    public fiends(){
        super(3,200);
        this.setHP(200);
        this.setAttack_damage(getHP()/4);

    }

}
class LIOFANG extends monsters{
    private final String special_move="reduces the HP of the opponent by half.";
    public LIOFANG(){
        super(4,250);
        this.setHP(250);
        this.setAttack_damage(getHP()/4);

    }
    public float special_move(heroes hero){
        return hero.getHP()/2;
    }

}
class Game implements game_func{
    private final map game_map;
    private heroes hero;
    private boolean game_finished=false;
    public Game(heroes hero){
        game_map=new map();
        this.hero=hero;
    }
    public ArrayList hints(){
        ArrayList<Integer> sof= new ArrayList<>();
        sof.add(0);
        for (int i=1;i<=40;i++){
            sof.add(game_map.getGame_layout_graph().get(i).getLevel()+sof.get((i-1)/3));
        }
        return sof;
    }
    public int min_finder(ArrayList<Integer> sof,int loc){
        ArrayList<Integer> min_array= new ArrayList<>();
        int k=loc;
        int l=loc;
        int t=0;
        int r;
        while(l<40 && k<40){
            k=3*k+1;
            l=3*l+3;
            t++;
        }

        k=(k-1)/3;
        l=(l-3)/3;
        t--;
        r=t;
        System.out.println(k+" "+l+" "+t);
        int min=Integer.MAX_VALUE;
        for(int i=k;i<=l;i++){
            if(sof.get(i)<min){
                min=sof.get(i);
            }
        }
        System.out.println(min);
        int z=(3*loc)+1;
        for(int i=k;i<=l;i++){
            if(sof.get(i)==min){
                min_array.add(i);
                z=i;
                t=r;
                while(t>1) {
                    t--;
                    z = (z - 1) / 3;
                    System.out.println(z);
                }
                System.out.println(z);
                return z;
            }
        }


        return z;

    }
    public boolean start_fight(int o,Scanner s) {
        int n,times=0,p;
        float k;
        sidekick sik=null;
        String ans,ans1;
        boolean sk=false,special;
        monsters m = game_map.getGame_layout_graph().get(o);
        System.out.println("write yes if you want to use a sidekick else no");
        ans=s.next();
        if (ans.equals("yes")){
            sik=hero.findsidekick();
            if (sik==null){
                System.out.println("no sidekick found");
            }
            else if (sik instanceof minions){
                System.out.println("you have a sidekick minions with you "+"\n");
                if(((minions) sik).isAbility()){
                    System.out.println("do you want to use cloning ability press c if you want to");
                    ans1=s.next();
                    if (ans1.equals("c")){
                        sik.special_attack(hero,m);
                    }
                }
                else{
                    System.out.println("ability used up");
                }
                sk=true;
            }
            else if(sik instanceof knight){
                System.out.println("you have a sidekick knight with you "+"\n");
                sik.special_attack(hero,m);
                sk=true;
            }

        }
        while (hero.getHP() > 0 && m.getHP() > 0) {
            special=false;
            System.out.println("Fight Started. Your fighting a level " + m.getLevel() + " Monster.\n" +
                    "Choose move:\n" +
                    "1) Attack\n" +
                    "2) Defense");
            if (hero.getNo_of_moves()>=3){
                System.out.println("3) special move");
            }
            if (times!=0){
                if (hero.getSpecial_move()==2 || hero.getSpecial_move()==4 ||hero.getSpecial_move()==1){
                    p=hero.special_attack(m);
                    special=true;
                    times--;
                }
            }
            n=s.nextInt();
            if (n==1){
                System.out.println("you choose to attack");
                k=hero.attack();
                m.lose_HP(k);
                System.out.println("you inflicted "+k+"damage to the monster");
            }
            else if(n==2){
                hero.acivateDefense();
            }
            else if(hero.getNo_of_moves()>=3 && n==3){
                times=hero.special_attack(m);
                special=true;
                hero.setNo_of_moves(-1);
            }
            else{
                System.out.println("invalid input now monster will attack");
            }
            hero.setNo_of_moves(hero.getNo_of_moves()+1);
            if (special && hero.getSpecial_move()==1){
                hero.setAttack_damage(hero.getAttack_damage()-5);
            }
            System.out.println("Your Hp: "+hero.getHP()+"/"+hero.getMax_HP()+" Monsters Hp: "+m.getHP()+"/"+m.getMax_HP());
            if (sik!=null) {
                k = sik.attack();
                m.lose_HP(k);
                sik.print_HP();
            }

            System.out.println("Your Hp: "+hero.getHP()+"/"+hero.getMax_HP()+" Monsters Hp: "+m.getHP()+"/"+m.getMax_HP());
            if (m.getHP()<=0){
                if (sik!=null) {
                    sik.setHP(100);
                    sik.setXP(m.getMonster_XP() / 10);
                    sik.increase_damage(m.getMonster_XP()/10);
                    sik.special_attack_ends(hero);
                    System.out.println("hp refilled and "+m.getMonster_XP()/10+" added to sidekick");
                }

                return true;
            }

            if (m.getLevel()==4){
                Random rand= new Random();
                if (rand.nextInt(10)==0){
                    System.out.println("Liofang special move attack!");

                    k=((LIOFANG)m).special_move(hero);
                    hero.lose_HP(k);
                    if (sik!=null){
                        sik.lose_HP((float)1.5*k);
                    }
                }
                else{
                    System.out.println("Liofang attack!");
                    k=m.attack();
                    hero.lose_HP(k);
                    if (sik!=null){
                        sik.lose_HP((float)1.5*k);
                    }
                }
                System.out.println("liofang inflicted "+k+" damage");
            }
            else{
                System.out.println("Monster attack!");
                k=m.attack();
                hero.lose_HP(k);
                if (sik!=null){
                    sik.lose_HP((float)1.5*k);
                }
                System.out.println("monster inflicted "+k+" damage");
            }
            hero.setDefense(0);
            System.out.println("Your Hp: "+hero.getHP()+"/"+hero.getMax_HP()+" Monsters Hp: "+m.getHP()+"/"+m.getMax_HP());
            if (sik!=null) {
                sik.print_HP();
            }
            if (special && hero.getSpecial_move()==1){
                hero.setDefense(0);
            }
            if (sik!=null) {
                if (sik.getHP() <= 0) {
                    System.out.println("sidekick lost");
                    hero.delete_sidekick(sik);
                    sik = null;
                }
            }

        }

        if(hero.getHP()<=0){
            return false;
        }
        if (sik!=null) {
            sik.setHP(100);
            sik.setXP(m.getMonster_XP() / 10);
            sik.increase_damage(m.getMonster_XP()/10);
            sik.special_attack_ends(hero);
            System.out.println("hp refilled and "+m.getMonster_XP()/10+" added to sidekick");
        }
        return true;
    }
    @Override
    public void start_game(Scanner s){
        int n=0,o,qwerty,l1,l2;
        boolean win=false;
        boolean buy=false;
        ArrayList mins = hints();
        System.out.println("do you want hints (1/0)");
        qwerty=s.nextInt();
        if (game_finished){
            System.out.println("game is already completed try to become a new user");
            return;
        }
        while(n!=-1) {
            o=game_map.getLocation();
            if (game_map.getLocation() == 0) {
                if (qwerty==1) {
                    System.out.println("hint is :" + min_finder(mins, game_map.getLocation()));
                }
                System.out.println("You are at the starting location. Choose path:" + '\n' +
                        "1) Go to Location " + (game_map.getLocation() * 3 + 1) + " \n" +
                        "2) Go to Location " + (game_map.getLocation() * 3 + 2) + " \n" +
                        "3) Go to Location " + (game_map.getLocation() * 3 + 3) + " \n" +
                        "Enter -1 to exit");
                n = s.nextInt();
                if (n>0 && n<4) {
                    game_map.setLocation(game_map.getLocation() * 3 + n);
                }
                else if(n==-1){
                    System.out.println("exiting");
                }

            } else if (game_map.getLocation() < 13) {
                System.out.println("hint is :"+min_finder(mins,game_map.getLocation()));
                System.out.println("You are at location " + game_map.getLocation() + " Choose path:\n" +
                        "1) Go to Location " + (game_map.getLocation() * 3 + 1) + " \n" +
                        "2) Go to Location " + (game_map.getLocation() * 3 + 2) + " \n" +
                        "3) Go to Location " + (game_map.getLocation() * 3 + 3) + " \n" +
                        "4) Go back\n" +
                        "Enter -1 to exit");
                n = s.nextInt();
                if (n>0 && n<4) {
                    game_map.setLocation(game_map.getLocation() * 3 + n);
                }
                else if(n==4){
                    game_map.setLocation((game_map.getLocation()-1)/3);
                }
                else if(n==-1){
                    System.out.println("exiting");
                }
            } else {
                System.out.println("You are at location " + game_map.getLocation() + " Choose path:\n" +
                        "1) next is boss level" + "\n" +
                        "2) Go back\n" +
                        "Enter -1 to exit");
                n = s.nextInt();
                if (n==1){
                    game_map.setLocation(40);
                }
                else if(n==2){
                    game_map.setLocation((int)((game_map.getLocation()-1)/3));
                }
                else if(n==-1){
                    System.out.println("exiting");
                }

            }
            if(o==game_map.getLocation() && n!=-1){
                System.out.println("wrong output");
            }
            else if(game_map.getLocation()==0){
                System.out.println("you are at starting location 01");

            }
            else if(n!=-1){
                win=start_fight(game_map.getLocation(),s);
                buy=false;
            }
            if (win && game_map.getLocation()!=40 && game_map.getLocation()!=0){
                if (!buy) {
                    buy = true;
                    System.out.println("Monster killed!\n" + game_map.getGame_layout_graph().get(game_map.getLocation()).getMonster_XP() +
                            " XP awarded");
                    int k = game_map.getGame_layout_graph().get(game_map.getLocation()).getMonster_XP();
                    System.out.println("If you would like to buy a sidekick write yes else no");
                    String inp;
                    inp = s.next();
                    if (inp.equals("yes")) {
                        System.out.println("if you want to buy minion press 1 " + '\n' + "for knight press 2");
                        l1 = s.nextInt();
                        System.out.println("XP in total you want to spend");
                        l2 = s.nextInt();
                        if (l1 == 1) {
                            if (l2 >= 5) {
                                sidekick sik = new minions(l2 - 5);
                                System.out.println("minion buyed with attack damage " + sik.getAttack_damage());
                                k -= l2;
                                hero.add_sidekick(sik);
                            } else {
                                System.out.println("insufficient_XP adding all to hero");
                            }
                        } else if (l1 == 2) {
                            if (l2 >= 8) {
                                sidekick sik = new knight(l2 - 8);
                                System.out.println("knight buyed with attack damage " + sik.getAttack_damage());
                                hero.add_sidekick(sik);
                                k -= l2;
                            } else {
                                System.out.println("insufficient_XP adding all to hero");
                            }
                        } else {
                            System.out.println("invalid choice adding all to hero");
                        }
                    }
                    hero.add_XP(k);
                    System.out.println("proceeding to next location...");
                    game_map.getGame_layout_graph().get(game_map.getLocation()).setHP(game_map.getGame_layout_graph().get(game_map.getLocation()).getMax_HP());
                }
            }
            else if(win && game_map.getLocation()==40){
                System.out.println("You have defeated the boss LIONANG");
                System.out.println("going to main menu");
                this.game_finished=true;
                return ;
            }
            else if(!win && game_map.getLocation()!=0){
                System.out.println("game lost");
                System.out.println("moving to starting location");
                game_map.setLocation(0);
                hero.setDefense(0);
                hero.sDefense(hero.getDefense()-hero.getLevel());
                hero.setNo_of_moves(0);
                hero.setAttack_damage(hero.getAttack_damage()-hero.getLevel());
                hero.setHP(100);
                hero.setHero_XP(0);
                hero.setMax_HP(100);
                ArrayList<sidekick> side=new ArrayList<>();
                hero.setSidekicks(side);
            }
        }


    }

}
class user implements game_func{
    private final String name;
    private final String hero_name;
    private final heroes hero;
    private Game game;

    public user(String name,String hero_name){
        this.name=name;
        this.hero_name=hero_name;

        if (hero_name.equals("warrior")){
            hero=new warrior();
        }
        else if (hero_name.equals("mage")){
            hero=new mage();
        }
        else if (hero_name.equals("theif")){
            hero = new theif();
        }
        else if (hero_name.equals("healer")){
            hero = new healer();
        }
        else{
            System.out.println("Wrong choice close the program");
            hero = null;
        }
        if (hero!=null){
            this.game=new Game(hero);
        }}


    public String getName() {
        return name;
    }

    public String getHero_name() {
        return hero_name;
    }

    public heroes getHero() {
        return hero;
    }

    public Game getGame() {
        return game;
    }
    @Override
    public void start_game(Scanner s){
        game.start_game(s);
    }
}
class map{
    private final ArrayList<monsters> game_layout_graph;
    private final int size;
    private int location;
    public map(){
        this.game_layout_graph=new ArrayList<>();
        this.size=41;
        this.location=0;
        int i=0;
        Random rand= new Random();
        int r;
        monsters m;
        while(i<size-1){

            if (i==0){
                m=null;
            }
            else{
                r=rand.nextInt(3);
                if (r==0){
                    m=new goblin();
                }
                else if(r==1){
                    m=new Zombies();
                }
                else{
                    m=new fiends();
                }
            }
            this.game_layout_graph.add(m);
            i++;
        }
        m=new LIOFANG();
        this.game_layout_graph.add(m);
    }

    public ArrayList<monsters> getGame_layout_graph() {
        return game_layout_graph;
    }

    public int getSize() {
        return size;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

}


public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n=0,h,i;
        String name,hero_nam="";
        user u;

        ArrayList<user> users = new ArrayList<>();
        while(n!=3) {
            System.out.println("Welcome to ArchLegends\n" +
                    "Choose your option\n" +
                    "1) New User\n" +
                    "2) Existing User\n" +
                    "3) Exit");
            n = s.nextInt();
            switch (n){
                case 1:
                    System.out.println("Enter Username");
                    name=s.next();
                    System.out.println("Choose a Hero\n" +
                            "1) Warrior\n" +
                            "2) Thief\n" +
                            "3) Mage\n" +
                            "4) Healer");
                    h=s.nextInt();
                    switch(h) {
                        case 1:
                            u = new user(name, "warrior");
                            hero_nam="warrior";
                            break;
                        case 2:
                            u = new user(name, "theif");
                            hero_nam="theif";
                            break;
                        case 3:
                            u = new user(name, "mage");
                            hero_nam="mage";
                            break;
                        case 4:
                            u = new user(name, "healer");
                            hero_nam="healer";
                            break;
                        default:
                            System.out.println("wrong choice");
                            u=null;
                            break;

                    }
                    users.add(u);
                    System.out.println("User Creation done. Username: "+name+" Hero type: "+hero_nam+" Log in to play the game . Exiting");
                    break;
                case 2:
                    boolean b=false;
                    System.out.println("Enter existing username");
                    name=s.next();
                    for (i=0;i<users.size();i++){
                        if (users.get(i).getName().equals(name)){
                            b=true;
                            System.out.println("User Found... logging in");
                            u=users.get(i);
                            u.start_game(s);
                        }
                    }
                    if (!b){
                        System.out.println("user not found");
                    }

            }
        }
    }
}

