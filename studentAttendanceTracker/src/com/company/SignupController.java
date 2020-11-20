package com.company;

import com.opencsv.*;
import java.io.*;
import java.util.*;

class UsernameAlreadyExistsException extends Exception{
    private String username;
    UsernameAlreadyExistsException(String username){
        this.username = username;
    }
    public String toString(){
        return "The username "+username+" already exists";
    }
}

class UsernameNotFoundException extends Exception{
    private String username;
    UsernameNotFoundException(String username){
        this.username = username;
    }
    public String toString(){
        return "The username "+username+" is not found";
    }
}

class TypeMismatchException extends Exception{
    private char a,b;
    TypeMismatchException(char a,char b){//Gets the characters thst mismatch as inputs
        this.a = a;
        this.b = b;
    }
    private String Type(char c){//Returns the full name of a character
        String out = "";
        switch(c){
            case 'S':{
                out =  "Student";
                break;
            }
            case 'T':{
                out = "Tutor";
                break;
            }
            case 'F':{
                out = "Faculty";
                break;
            }
        }
        return out;
    }

    public String toString(){
        return Type(a)+" cannot access "+Type(b)+"'s portal";
    }
}

public class SignupController{
    private String username,password,rollnoOrEmail,subject;
    private char choice;
    SignupController(String un,String pwd,String rn,char c){//Char c is the type(Student(S) or Faculty(F) or Teacher(T))
        username = un;
        password = pwd;
        rollnoOrEmail=rn;
        choice = c;
    }

    void setSubject(String sub){//Additional method to get subject as input if it is a Faculty
        if (choice=='F'){
            subject = sub;
        }
        else{
            subject=null;
        }
    }

    void storeData() throws IOException,Exception{
        Scanner file;
        CSVWriter database;
        boolean flag=false;
        try{
            file = new Scanner(new File("SignupDetails.csv"));
            ArrayList<String[]> oldDetails = new ArrayList<String[]>();
            file.useDelimiter("\n");
            while(file.hasNext()){
                String[] temp = file.next().split(",");
                oldDetails.add(temp);
            }
            flag = true;
            database = new CSVWriter(new FileWriter("SignupDetails.csv"));
            for(String[] x:oldDetails){
                database.writeNext(x,false);
                if(x[0].equals(username)){
                    flag = false;
                }
            }

        }
        catch(FileNotFoundException e){
            database = new CSVWriter(new FileWriter("SignupDetails.csv"));
        }
        if(!flag){
            database.close();
            throw new UsernameAlreadyExistsException(username);
        }
        String[] details = {username,password,rollnoOrEmail,String.valueOf(choice),subject};
        database.writeNext(details,false);
        //System.out.println("Stored");
        database.close();
    }
}
class Login{
    private String username,password;
    private char choice;
    Login(String un,String pwd,char c) {
        username = un;
        password = pwd;
        choice = c;
    }

    String signin()  throws Exception{
        Scanner file = new Scanner(new FileReader("SignupDetails.csv"));
        file.useDelimiter("\n");
        String pwd = "";
        boolean flag = false;
        while(file.hasNext()){
            String fulldata = file.next();
            String[] data = fulldata.split(",");
            if(data[0].equals(username)){
                flag =  true;
                char c = data[3].charAt(0);
                if (choice == 'T' || choice == 'F') {
                    if ( c != 'T' && c != 'F') {
                        throw new TypeMismatchException(data[3].charAt(0), choice);
                    }
                } else {
                    if(c != choice) throw new TypeMismatchException(c, choice);
                }
                if(data[1].equals(password)){
                    return fulldata;
                }
                else{
                    return null;
                }
            }
        }
        if(!flag){
            throw new UsernameNotFoundException(username);
        }
        return null;
    }
}

