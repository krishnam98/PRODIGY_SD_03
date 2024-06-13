import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class contactManager {
    class Info{
        String name;
        String phNum;
        String email;
        LocalDateTime dt;

        public Info(String n,String ph,String email){
            this.name=n;
            this.phNum=ph;
            this.email=email;
        }
    }
    // creating a book
    HashMap <String,Info> book;
    public contactManager(){
       this.book=new HashMap<>();
       System.out.println("contact manager has been created!");
    }
    // prints Main Menu
    public static void printMm(){
        System.out.println("1.Add contact \n2.Delete contact \n3.Update contact \n4.Display contact \n0.exit");

    
    }
    // add contact
    public void add(String name,String ph,String id){
        Info i=new Info(name, ph, id);
        this.book.put(name,i);
    }
    // delete contact
    public void delete(String name){
        if(this.book.containsKey(name)){
            book.remove(name);
            System.out.println("Contact deleted Successfully!");
        }
        else{
            System.out.println("Contact Does not exist!");
        }
    }
    // updates the contact
    public void update(String name,String newName,String ph,String id){
        if(this.book.containsKey(name)){
            this.book.remove(name);
            this.book.put(newName,new Info(newName, ph, id));
            System.out.println("Contact updated Successfully!");
        }
        else{
            System.out.println("Contact Does not exist!");
        }
    }
//    1.Add contact \n2.Delete contact \n3.Update contact \n4.Display contact \n0.exit
    public void input(int i){
        Scanner scinput=new Scanner(System.in);
        while(i==1){
           
            System.out.print("Enter Name: ");
            String name=scinput.nextLine();
            System.out.println();
            System.out.println();


            while(book.containsKey(name)){
                System.out.print("Name already Exist!\n try another: ");
                name=scinput.nextLine();
                System.out.println();
            }

            System.out.print("Enter Contact Number: ");
            String phno=scinput.next();
            System.out.print("Enter Email Id: ");
            String email=scinput.next();

            add(name, phno, email);
            System.out.println("contact Added!");
            System.out.println("add more contacts (Press 1 or press 0 for Main Menu) : ");
            i=scinput.nextInt();

            scinput.nextLine();
            
        }

        if(i==2){
            String name=scinput.nextLine();
            System.out.println();
            delete(name);
            return;
        }

        if(i==3){
            System.out.print("enter existing name of contact: ");
            String name=scinput.nextLine();
            System.out.println();
            System.out.print("***enter new details to update or existing one***");
            System.out.print("enter name: ");
            String newname=scinput.nextLine();
            System.out.println();
            System.out.print("Contact number: ");
            String ph=scinput.next();
            System.out.print("Email id: ");
            String id=scinput.next();
            update(name, newname, ph, id);
            return;
        }

        if(i==4){
            
               Set<String> s=book.keySet();
               for (String key: s){
                System.out.println(key+":"+"Number:"+book.get(key).phNum+"Email Id: "+book.get(key).email);
               }

        }

    }

    public static void main(String args[]){
       contactManager cont=new contactManager();
    //    Name of file
       System.out.print("Give a Name to your Contact Manager: ");
       Scanner scF=new Scanner(System.in);
       String file1=scF.next();
       String file=file1.concat(".txt");
    // creating a file
       try {
        File obj=new File(file);
       
        if(obj.createNewFile()){
         System.out.println("file created!");
        }
        else{System.out.println("File already Exist!");}

       } catch (IOException e) {
        // TODO: handle exception
        System.out.println("error");
       }
      
       
       int i=-1;
    // driver code
       while(i!=0){
        cont.printMm();
        Scanner sc=new Scanner(System.in);
        System.out.print("enter Serial number: ");
        i=sc.nextInt();
        cont.input(i);
       }
       try{
        FileWriter writer= new FileWriter(file);
        Set<String> set=cont.book.keySet();
        for(String key:set){
        writer.write("Name: "+key+" Contact No: "+cont.book.get(key).phNum+" Email Id: "+cont.book.get(key).email);
        writer.write("\n");
        }
        writer.close();
       }
       catch(IOException e){
        System.out.println("error");
       }
       


        
        

    }
    
}
