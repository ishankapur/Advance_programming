package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Student{
    private final int student_id;
    private String student_branch;
    private final float CGPA;
    private String placement_status;
    private String Company_placed;
    Student(String branch,float CGPA, int counter){
        this.student_id=counter;
        this.student_branch=branch;
        this.CGPA=CGPA;
        this.placement_status="Not Placed";
    }

    public int getStudent_id() {
        return student_id;
    }


    public String getStudent_branch() {
        return student_branch;
    }

    public float getCGPA() {
        return CGPA;
    }

    public String getPlacement_status() {
        return placement_status;
    }

    public void setCompany_placed(String company_placed) {
        Company_placed = company_placed;
        this.placement_status="Placed";
    }

    public void setPlacement_status(String placement_status) {
        this.placement_status = placement_status;
    }
    public void Display_student(){
        System.out.println(student_id);
        System.out.println(CGPA);
        System.out.println(student_branch);
        System.out.println("Placement status: "+placement_status);
        if (placement_status.equals("Placed")){
            System.out.println(Company_placed);
        }

    }
}
class Company{
    private String Company_name;
    private int no_of_required_students;
    private String[] courses;
    private String application_status;
    private int no_of_courses;
    Company(String company_name,int required_students,int course,String[] courses,String application_status){
        this.Company_name=company_name;
        this.application_status="OPEN";
        this.courses=courses;
        this.no_of_courses=course;
        this.no_of_required_students=required_students;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public int getNo_of_required_students() {
        return no_of_required_students;
    }

    public String[] getCourses() {
        return courses;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }
    public void Display_Company(){
        System.out.println(Company_name);
        System.out.println("course criteria: ");
        for(int i=0;i<no_of_courses;i++){
            System.out.println(courses[i]);
        }
        System.out.println("Number of required students = "+no_of_required_students);
        System.out.println("Application Status = "+application_status);
    }
}
class company_placement_office{
    private Company company;
    private ArrayList<Student> student_applied;
    private ArrayList<Integer> marks_scored;
    private ArrayList<Student> Selected_students;
    company_placement_office(Company c){
        this.company=c;
        this.student_applied=new ArrayList<>();
        this.marks_scored=new ArrayList<>();
        this.Selected_students=new ArrayList<>();
    }

    public Company getCompany() {
        return company;
    }
    public Boolean display_score(int roll){
        Boolean k= false;
        for (int i=0;i<student_applied.size();i++){
            if (student_applied.get(i).getStudent_id()==roll){
                System.out.println(company.getCompany_name()+" "+ marks_scored.get(i));
                k=true;
            }
        }
        return k;

    }

    public void add_student(Student s, int m){
        student_applied.add(s);
        marks_scored.add(m);
    }
    public int select_student(int stp){
        int k;
        if (stp<company.getNo_of_required_students()){
            k=stp;
        }
        else{
            k=company.getNo_of_required_students();
        }
        for (int i=0;i<k;i++){
            System.out.println(find_max());
        }
        company.setApplication_status("Closed");
        return k;
    }
    private int find_max(){
        int max_marks=0;
        int index=0;
        float cgpa =0;
        for (int i=0;i<student_applied.size();i++){
            if (student_applied.get(i).getPlacement_status().equals("Not Placed")){
                if (max_marks<marks_scored.get(i)){
                    index=i;
                    max_marks=marks_scored.get(i);
                    cgpa= student_applied.get(i).getCGPA();
                }
                else if (max_marks==marks_scored.get(i)){
                    if (student_applied.get(i).getCGPA()>cgpa){
                        index=i;
                        max_marks=marks_scored.get(i);
                        cgpa= student_applied.get(i).getCGPA();
                    }
                }
            }
        }
        student_applied.get(index).setPlacement_status("Placed");
        Selected_students.add(student_applied.get(index));
        return student_applied.get(index).getStudent_id();
    }

}
class Placement_Office{
    private ArrayList<company_placement_office> companies;
    private Student students[];
    private int student_notplaced;
    Placement_Office(Student[] s,int n){
        this.students=s;
        companies=new ArrayList<>();
        this.student_notplaced=n;
    }

    public ArrayList<company_placement_office> getCompanies() {
        return companies;
    }

    public void add_company(company_placement_office c){
        companies.add(c);
    }
    public void Display_company(String name){
        for(int i=0;i<companies.size();i++){
            if (companies.get(i).getCompany().getCompany_name().equals(name)) {
                companies.get(i).getCompany().Display_Company();
            }
        }
    }
    public void remove_companies(ArrayList<Integer> arr){
        int count=0;
        System.out.println("accounts removed:");
        for (int i=0;i<arr.size();i++){
            System.out.println(companies.get(arr.get(i)-count).getCompany().getCompany_name());
            companies.remove(arr.get(i)-count);
            count+=1;
        }

    }
    public void select(String name){
        int o=0;
        System.out.println(" ROLL no. of selected students:");
        for(int i=0;i<companies.size();i++){
            if (companies.get(i).getCompany().getCompany_name().equals(name)){
                o=companies.get(i).select_student(student_notplaced);
            }
        }
        setStudent_notplaced(o);

    }
    public void print_left_companies(){
        for(int i=0;i<companies.size();i++){
            if (companies.get(i).getCompany().getApplication_status().equals("OPEN")){
                System.out.println(companies.get(i).getCompany().getCompany_name());
            }
        }
    }
    public void print_all_scores(int rollno){
        Boolean k=false;
        for(int i=0;i<companies.size();i++){
            k=k||companies.get(i).display_score(rollno);
        }
        if (!k){
            System.out.println("No student with the given roll number has an account.");
        }
    }

    public int getStudent_notplaced() {
        return student_notplaced;
    }

    public void setStudent_notplaced(int student_notplaced) {
        this.student_notplaced-= student_notplaced;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        int n,j,k,i,l,i2,score;
        float m;
        String stream,name,course;
        n=s.nextInt();
        Student[] st= new Student[n];
        for(i=0;i<n;i++){
            m=s.nextFloat();
            stream=s.next();
            Student stud = new Student(stream,m,i+1);
            st[i]=stud;
        }
        System.out.println(" --Students registered--");
        Placement_Office po=new Placement_Office(st,n);
        while(po.getStudent_notplaced()!=0){
            j=s.nextInt();
            switch (j) {
                case 1:
                    name = s.next();
                    System.out.print("Number Of Eligible Courses = ");
                    k = s.nextInt();
                    String[] coursed = new String[k];
                    for (i = 0; i < k; i++) {
                        coursed[i] = s.next();
                    }
                    System.out.print("Number of student required = ");
                    l = s.nextInt();
                    Company comp = new Company(name, l, k, coursed, "OPEN");
                    company_placement_office cpo = new company_placement_office(comp);
                    System.out.println(name);
                    System.out.println("Course Criteria");
                    for (i = 0; i < k; i++) {
                        System.out.println(coursed[i]);
                    }
                    System.out.println("Number of student required = " + l);
                    System.out.println("Application status = OPEN");
                    System.out.println("Enter scores for the technical round");
                    for (i = 0; i < n; i++) {
                        if (st[i]!=null) {

                            if (st[i].getPlacement_status().equals("Not Placed")) {
                                for (i2 = 0; i2 < k; i2++) {
                                    if (st[i].getStudent_branch().equals(coursed[i2])) {
                                        System.out.println("Enter score for Roll no. " + st[i].getStudent_id());
                                        score = s.nextInt();
                                        cpo.add_student(st[i], score);
                                    }

                                }
                            }
                        }
                    }
                    po.add_company(cpo);
                    break;
                case 2:
                    System.out.println("Accounts removed for:");
                    for (i = 0; i < n; i++) {

                        if (st[i] != null && st[i].getPlacement_status().equals("Placed")) {
                            System.out.println(st[i].getStudent_id());
                            st[i] = null;
                        }
                    }
                    break;
                case 3:
                    ArrayList<Integer> indexes = new ArrayList<>();
                    for (i = 0; i < po.getCompanies().size(); i++) {
                        if (po.getCompanies().get(i).getCompany().getApplication_status().equals("Closed")) {
                            indexes.add(i);
                        }
                    }
                    po.remove_companies(indexes);
                    break;
                case 4:
                    System.out.println(po.getStudent_notplaced() + " students left");
                    break;
                case 5:
                    po.print_left_companies();
                    break;
                case 6:
                    name = s.next();
                    po.select(name);
                    break;
                case 7:
                    name = s.next();
                    po.Display_company(name);
                    break;
                case 8:
                    k = s.nextInt();
                    for (i = 0; i < n; i++) {
                        if (st[i] != null && st[i].getStudent_id() == k) {
                            st[i].Display_student();
                        }
                    }
                    break;
                case 9:
                    k = s.nextInt();
                    po.print_all_scores(k);
                    break;
                default:
                    System.out.println("wrong input");
                    break;
            }

        }
        System.out.println("Code end");

    }
}
