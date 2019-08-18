package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class item{
    private final int unique_code;
    private final String item_name;
    private float item_price;
    private int quantity;
    private String offer;
    private final String category;
    private merchant m;

    public item(int unique_code, String item_name, float item_price, int quantity, String category,merchant m) {
        this.unique_code = unique_code;
        this.item_name = item_name;
        this.item_price = item_price;
        this.quantity = quantity;
        this.category = category;
        this.m=m;
        this.offer="none";
    }

    public merchant getM() {
        return m;
    }

    public int getUnique_code() {
        return unique_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCategory() {
        return category;
    }
    public void display(){
        System.out.println(unique_code+" "+item_name+" "+item_price+" "+quantity+" "+offer+" "+category);
    }

}
class customer implements reward{
    private final int id;
    private final String name;
    private String Address;
    private int no_of_orders;
    private ArrayList<item> Past_orders;
    private ArrayList<Integer> qty;
    private float reward_money;
    private float money;
    private ArrayList<item> cart;
    private ArrayList<Integer> qty_of_cart;

    public customer(int id,String name, String address, int no_of_orders) {
        this.id=id;
        this.money=100;
        this.name = name;
        Address = address;
        this.no_of_orders = no_of_orders;
        this.reward_money=0;
        this.Past_orders=new ArrayList<>();
        this.qty=new ArrayList<>();
        this.cart=new ArrayList<>();
        this.qty_of_cart= new ArrayList<>();

    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public ArrayList<item> getCart() {
        return cart;
    }

    public void setCart(ArrayList<item> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public ArrayList<item> getPast_orders() {
        return Past_orders;
    }

    public ArrayList<Integer> getQty() {
        return qty;
    }

    public void setQty(ArrayList<Integer> qty) {
        this.qty = qty;
    }

    public void setPast_orders(ArrayList<item> past_orders) {
        Past_orders = past_orders;
    }

    public float getReward_money() {
        return reward_money;
    }

    public void setReward_money(float reward_money) {
        this.reward_money = reward_money;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getNo_of_orders() {
        return no_of_orders;
    }

    public void setNo_of_orders(int no_of_orders) {
        this.no_of_orders = no_of_orders;
    }
    @Override
    public void Print_reward(){
        System.out.println("total reward earned ="+((int)(no_of_orders/5))*2);
    }
    public Boolean buy_item(item i,int qty){
        float total_money;
        total_money=i.getItem_price()*qty;
        if( i.getOffer()!=null){
            if ((i.getOffer()).equals("Buy one get one")){
                total_money=total_money-(i.getItem_price()*(int)(qty/2));
            }
            else if(i.getOffer().equals("25% off")){
                total_money=total_money*(3/4);
            }
        }
        if (money+reward_money>=total_money){
            if (money>=total_money){
                money=money-total_money;
            }
            else{
                reward_money=reward_money+money-total_money;
                money=0;
            }
            no_of_orders+=1;
            Past_orders.add(0,i);
            this.qty.add(0,qty);
            if (no_of_orders%5==0){
                reward_money+=10;
            }
            return true;
        }
        else{
            System.out.println("insufficient money");
            return false;
        }
    }
    public void add_to_cart(item i, int qty){
        cart.add(i);
        qty_of_cart.add(qty);
        System.out.println("added to cart");
    }
    public void print_past_orders(){
        int a,i;
        if (no_of_orders>=10){
            a=10;
        }
        else{
            a=no_of_orders;
        }
        for(i=0;i<a;i++){
            Past_orders.get(i).display();
            System.out.println("quantity: "+ qty.get(i));
        }
    }
    public float checkout(){
        Boolean b;
        merchant m;
        float mon,total_mon=0;
        for (int i=0;i<cart.size();i++){
            b=buy_item(cart.get(i),qty_of_cart.get(i));
            if (b){
                m=cart.get(i).getM();
                mon=m.add_contribution_from_orders(cart.get(i),qty_of_cart.get(i));
                total_mon+=(2*mon/100);
            }
            else{
                System.out.println("can't buy all stuff clearing cart...");
                break;
            }
        }
        cart=new ArrayList<>();
        qty_of_cart=new ArrayList<>();
        return total_mon;
    }
    @Override
    public void print_details(){
        System.out.println(name+" "+Address+" "+no_of_orders);
    }
}
interface func{
    public void add_item(merchant m,String str,float f,int i,String s);
    public void edit_item(item i,float p, int qty);
    public void edit_item(item i,int offer);
}
interface reward{
    public void Print_reward();
    public void print_details();
}
class merchant implements func,reward{
    private final int id;
    private final String name;
    private String address;
    private float contribution;
    private int slots;
    private ArrayList<item> items;
    private int no_of_items;
    private ArrayList<String> categories;
    private int no_of_category;

    public merchant(int id,String name, String address, float contribution) {
        this.items=new ArrayList<>();
        this.categories=new ArrayList<>();
        this.no_of_category=0;
        this.id=id;
        this.slots=0;
        this.name = name;
        this.address = address;
        this.contribution = contribution;
        this.no_of_items=0;
    }

    public int getSlots() {
        return slots;
    }

    public int getNo_of_items() {
        return no_of_items;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public int getNo_of_category() {
        return no_of_category;
    }

    public int getId() {
        return id;
    }

    public ArrayList<item> getItems() {
        return items;
    }

    public void setItems(ArrayList<item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getContribution() {
        return contribution;
    }

    public void setContribution(float contribution) {
        this.contribution = this.contribution+contribution;
    }
    @Override
    public void print_details(){
        System.out.println(name+" "+address+" "+contribution);
    }
    @Override
    public  void Print_reward(){
        System.out.println("total reward = "+(int)(contribution/100));
    }
    @Override
    public void add_item(merchant m,String name,float price,int qty, String category){
        if (no_of_items>=10){
            System.out.println("items limit exceeded");
            return;
        }
        item i =new item(this.no_of_items+1,name,price,qty,category,m);
        items.add(i);
        no_of_items+=1;
        if (!categories.contains(category)){
            categories.add(category);
            no_of_category+=1;
        }

    }
    @Override
    public void edit_item(item i,float price, int qty){
        i.setItem_price(price);
        i.setQuantity(qty);
        i.display();
    }
    @Override
    public void edit_item(item i, int offer){
        if (offer==1) {

            i.setOffer("Buy one get one");

        }
        else if (offer==2){
            i.setOffer("25% off");
        }
        else{
            System.out.println("wrong offer");
            return;
        }
        i.display();
    }
    public float add_contribution_from_orders(item i,int qty){
        float total_money;
        total_money=i.getItem_price()*qty;
        if( i.getOffer()!=""){
            if (i.getOffer().equals("Buy one get one")){
                total_money=total_money-(i.getItem_price()*(int)(qty/2));
            }
            else if(i.getOffer().equals("25% off")){
                total_money=total_money*(3/4);
            }
        }
        contribution+=total_money;
        return total_money;

    }
}
class Company implements func{
    private ArrayList<merchant> Merchant;
    private ArrayList<customer> Customer;
    private ArrayList<item> All_items;
    private int no_of_items;
    private int no_of_categories;
    private float company_balance;
    private ArrayList<String> categories;
    public Company(){
        this.Merchant=new ArrayList<>();
        this.Customer=new ArrayList<>();
        this.All_items=new ArrayList<>();
        this.company_balance=0;
        this.no_of_items=0;
        this.no_of_categories=0;
        this.categories=new ArrayList<>();
    }

    public ArrayList<merchant> getMerchant() {
        return Merchant;
    }

    public void setMerchant(ArrayList<merchant> merchant) {
        Merchant = merchant;
    }

    public ArrayList<customer> getCustomer() {
        return Customer;
    }

    public void setCustomer(ArrayList<customer> customer) {
        Customer = customer;
    }

    public ArrayList<item> getAll_items() {
        return All_items;
    }

    public void setAll_items(ArrayList<item> all_items) {
        All_items = all_items;
    }

    public int getNo_of_items() {
        return no_of_items;
    }

    public void setNo_of_items(int no_of_items) {
        this.no_of_items = no_of_items;
    }

    public int getNo_of_categories() {
        return no_of_categories;
    }

    public void setNo_of_categories(int no_of_categories) {
        this.no_of_categories = no_of_categories;
    }

    public float getCompany_balance() {
        return company_balance;
    }

    public void setCompany_balance(float company_balance) {
        this.company_balance = company_balance;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
    public void add_customer(String name,String address){
        customer c =new customer(Customer.size()+1,name,address,0);
        Customer.add(c);
    }
    public void add_merchant(String name,String address){
        merchant m =new merchant(Merchant.size()+1,name,address,0);
        Merchant.add(m);
    }

    @Override
    public void add_item(merchant m,String name,float price,int qty, String category){
        if (m.getNo_of_items()>=10){
            System.out.println("limit of unique items exceeded");
            return;
        }
        item i= new item(no_of_items+1,name,price,qty,category,m);
        All_items.add(i);
        no_of_items+=1;
        if( !categories.contains(category)){
            categories.add(category);
            no_of_categories+=1;
        }
        m.add_item(m,name,price,qty,category);
        i.display();

    }
    @Override
    public void edit_item(item i,float price, int qty){
        i.setItem_price(price);
        i.setQuantity(qty);
    }
    @Override
    public void edit_item(item i, int offer){
        if (offer==1) {

            i.setOffer("Buy one get one");

        }
        else{
            i.setOffer("25% off");
        }
    }
    public void Input_manager(){
        Scanner s= new Scanner(System.in);
        int a=0,b,i;
        String x;
        char d;
        merchant m;
        customer c;
        while(a!=5) {
            System.out.println("Welcome to Mercury \n 1) Enter as Merchant\n" +
                    "2) Enter as Customer\n" +
                    "3) See user details\n" +
                    "4) Company account balance\n" +
                    "5) Exit");
            a = s.nextInt();
            switch (a) {
                case 1:
                    System.out.println("choose merchant");
                    for (i = 0; i < Merchant.size(); i++) {
                        System.out.println(i+1+") "+Merchant.get(i).getName());
                    }
                    b = s.nextInt();
                    m=Merchant.get(b-1);
                    b=0;
                    merchant_menu(m,s);
                    break;
                case 2:
                    System.out.println("choose customer");
                    for (i=0;i<Customer.size();i++){
                        System.out.println(i+1+") "+Customer.get(i).getName());
                    }
                    b=s.nextInt();
                    c=Customer.get(b-1);
                    b=0;
                    customer_menu(c,s);
                    break;
                case 3:
                    System.out.println("merchants");
                    for (i=0;i<5;i++){
                        System.out.println(Merchant.get(i).getId()+" "+Merchant.get(i).getName());
                    }
                    for (i=0;i<5;i++){
                        System.out.println(Customer.get(i).getId()+" "+Customer.get(i).getName());
                    }

                    x=s.next();
                    System.out.println(x);
                    d=x.charAt(0);
                    System.out.println(d);
                    b=s.nextInt();
                    System.out.println(b);
                    if (d=='M'){
                        Merchant.get(b-1).print_details();
                    }
                    else if(d=='C'){
                        Customer.get(b-1).print_details();
                    }
                    break;
                case 4:
                    System.out.println(company_balance);
                    break;
                case 5:
                    System.out.println("exiting...");
                    break;
                default:
                    System.out.println("wrong choice");
                    break;


            }
        }

    }
    public void display(String category){
        for (int i=0;i<All_items.size();i++){
            if (All_items.get(i).getCategory().equals(category)){
                All_items.get(i).display();
            }
        }
    }
    public void display(customer c, String category,Scanner s){
        ArrayList<item> customer_items = new ArrayList<>();
        int i,a,qty,choice;
        merchant m;
        float mon;
        Boolean suc;
        for (i=0;i<All_items.size();i++){
            if (All_items.get(i).getCategory().equals(category)){
                customer_items.add(All_items.get(i));
            }
        }
        System.out.println("choose item by code");
        for (i=0;i<customer_items.size();i++){
            System.out.print(i+1+") ");
            customer_items.get(i).display();
        }
        System.out.println("enter item code");
        a=s.nextInt();
        System.out.println("enter quantity");
        qty=s.nextInt();
        System.out.println("Choose method of transaction\n" +
                "1) Buy item\n" +
                "2) Add item to cart\n" +
                "3) Exit");
        choice=s.nextInt();
        if (choice==1) {
            suc=c.buy_item(customer_items.get(a - 1), qty);
            if (suc){
                m=customer_items.get(a-1).getM();
                mon=m.add_contribution_from_orders(customer_items.get(a-1),qty);
                company_balance+=(2*mon/100);
                System.out.println("item successfully bought");
            }

        }
        else if (choice==2){
            c.add_to_cart(customer_items.get(a-1),qty);
        }
        else if (choice!=3){
            System.out.println("wrong choice");
        }

    }
    public void customer_menu(customer c,Scanner s){
        int b=0,i,n;
        float mon=0;
        String category;
        while(b!=5){
            System.out.println("Welcome "+c.getName()+"\n" +
                    "Customer Menu\n" +
                    "1) Search item\n" +
                    "2) checkout cart\n" +
                    "3) Reward won\n" +
                    "4) print latest orders\n" +
                    "5) Exit");
            b=s.nextInt();
            switch (b){
                case 1:
                    System.out.println("Choose category");
                    for (i=0;i<no_of_categories;i++){
                        System.out.println(i+1+") "+categories.get(i));
                    }
                    n=s.nextInt();
                    category=categories.get(n-1);
                    display(c,category,s);
                    break;
                case 2:
                    mon=c.checkout();
                    company_balance+=mon;
                    break;
                case 3:
                    c.Print_reward();
                    break;
                case 4:
                    c.print_past_orders();
                    break;
                case 5:
                    System.out.println("exiting customer...");
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }
    }
    public void merchant_menu(merchant m,Scanner s){
        int b=0,n,i,qty,off;
        ArrayList<item> merchant_items= m.getItems();
        String name,category;
        float price;
        while(b!=6){
            System.out.println("Welcome "+m.getName()+"\n" +
                    "Merchant Menu\n" +
                    "1) Add item\n" +
                    "2) Edit item\n" +
                    "3) Search by category\n" +
                    "4) Add offer\n" +
                    "5) Rewards won\n" +
                    "6) Exit");
            b = s.nextInt();
            switch (b){
                case 1:
                    System.out.println("Enter item details");
                    System.out.println("Enter item name:");
                    name=s.next();
                    System.out.println("Enter item price:");
                    price=s.nextFloat();
                    System.out.println("Enter item quantity");
                    qty=s.nextInt();
                    System.out.println("Enter item category");
                    category=s.next();
                    add_item(m,name,price,qty,category);
                    break;

                case 2:
                    System.out.println("choose item by code");
                    for (i=0;i<m.getNo_of_items();i++){
                        merchant_items.get(i).display();
                    }
                    n=s.nextInt();
                    System.out.println("enter edit details:");
                    System.out.println("enter price");
                    price=s.nextFloat();
                    System.out.println("enter qty");
                    qty=s.nextInt();
                    edit_item(merchant_items.get(n-1),price,qty);
                    m.edit_item(merchant_items.get(n-1),price,qty);
                    for (i=0;i<no_of_items;i++){
                        if (merchant_items.get(n-1).getUnique_code()==All_items.get(i).getUnique_code()){
                            edit_item(All_items.get(i),price,qty);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Choose category");
                    for (i=0;i<no_of_categories;i++){
                        System.out.println(i+1+") "+categories.get(i));
                    }
                    n=s.nextInt();
                    category=categories.get(n-1);
                    display(category);
                    break;
                case 4:
                    System.out.println("Choose item by code");
                    for (i=0;i<m.getNo_of_items();i++){
                        merchant_items.get(i).display();
                    }
                    n=s.nextInt();
                    System.out.println("choose offer\n" +
                            "1) buy one get one\n" +
                            "2) 25% off");
                    off=s.nextInt();
                    edit_item(merchant_items.get(n-1),off);
                    m.edit_item(merchant_items.get(n-1),off);
                    for (i=0;i<no_of_items;i++){
                        if (merchant_items.get(n-1).getUnique_code()==All_items.get(i).getUnique_code()){
                            edit_item(All_items.get(i),off);
                        }
                    }
                    break;
                case 5:
                    m.Print_reward();
                    break;
                case 6:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("wrong output chosen");
                    break;

            }

        }
    }
}
public class Main {

    public static void main(String[] args) {
        Company mercury = new Company();
        mercury.add_customer("ishan","F-174");
        mercury.add_customer("ishan2","F-1741");
        mercury.add_customer("ishan3","F-1742");
        mercury.add_customer("ishan4","F-1743");
        mercury.add_customer("ishan5","F-1744");
        mercury.add_merchant("A", "op");
        mercury.add_merchant("B", "opq");
        mercury.add_merchant("C", "opr");
        mercury.add_merchant("D", "ops");
        mercury.add_merchant("E", "opt");
        mercury.Input_manager();
    }
}
