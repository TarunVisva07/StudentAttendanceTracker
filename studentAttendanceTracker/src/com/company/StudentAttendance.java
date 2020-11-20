package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


class RollNumberNotFoundException extends Exception{//Exception thrown when given roll number is not present in the file
    private String rollno;
    RollNumberNotFoundException(String rollno){
        this.rollno=rollno;
    }
    public String toString(){
        return "RollNumberNotFoundException: Roll number "+rollno+" not found";
    }

}
class SubjectNotFoundException extends Exception{
    private String subject;
    SubjectNotFoundException(String subject){this.subject=subject;}
    public String toString(){
        return "SubjectNotFoundException: Subject \""+subject+"\" not found";
    }

}
public class StudentAttendance {
    // some constants for the utility depending on the file data
    static final String[] subjects = {
            "19Z301 - Linear Algebra",
            "19Z302 - Data Structures",
            "19Z303 - Computer Architecture",
            "19Z304 - Discrete Structures",
            "19Z305 - Object Oriented Programming",
            "19Z306 - Economics for Engineers",
            "19Z310 - Data Structures Laboratory",
            "19Z311 - Object Oriented Programming Laboratory",
            "19K312 - Environmental Science"};

    static final String[] fileNames = {"Attendance - October","Attendance - September","Attendance - August"};

    private Scanner scan;
    protected String rollno;
    private final Scanner details;
    String filename;

    StudentAttendance(String rollno,String fna) throws Exception{
        this.rollno=rollno;
        filename=fna;
        scan = new Scanner(new File(filename+".csv"));
        details = new Scanner(new File(filename+".csv"));
    }
    String[] getAttendance() {
        scan = details;//Initialize file pointer to the beginning of the file
        scan.useDelimiter("\n");
        while (scan.hasNext()) {
            String[] data = scan.next().split(",");//Fetches the attendance data for all subjects as an array of Strings
            if(data.length == 0) continue;
            if (rollno.equals(data[0])) {
                return data;//If the given roll number is found, it returns the data
            }
        }
        String[] s = {"Roll number not found"};//This array of length 1 is used by later methods to throw RollNumberNotFoundException
        return s;
    }
    String[] getSubjects () {
        scan = details;//Initialize file pointer to the beginning of the file
        scan.useDelimiter("\n");
        int m=4;
        if(filename.equals("Attendance - August"))
        {
            m=5;
        }
        String[] subjects = scan.next().split(","), output = new String[(subjects.length - 2) / m];
        int count;
        for (count = 2; count < subjects.length - 2; count += m) {
            output[count / m] = subjects[count];
        }
        return output;//Filters the extras in the file and extracts the subjects.Returns an array of Strings

    }

}


class Student extends StudentAttendance{
    Student(String rollno,String filename) throws Exception{
        super(rollno,filename);
    }
    String[] subjects=getSubjects(),attendance=getAttendance();//Attendance detials and subjects
    String getName()throws Exception{//Returns name of the student
        try {
            return attendance[1];//If roll number is not found,array of length 1 is returned by getAttendance()
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new RollNumberNotFoundException(rollno);//Exception is thrown
        }
    }
    AttendanceData getAttendance(String subject)throws  Exception{//Returns the attendance data of the student for the given subject
        int count=2,m=4;
        if(filename.equals("Attendance - August"))
        {
            m=5;
        }
        if(attendance.length==1){//If roll number is not found,array of length 1 is returned by getAttendance()
            throw new RollNumberNotFoundException(rollno);//Exception is thrown
        }
        for(String x:subjects){
            if(x.equals(subject)){
                break;
            }
            count+=m;//Incremented by 4 as each subject has four columns(P,L,E,UE)
        }
        if(m==4 && count>=38||m==5 &&count>=47){
            return new AttendanceData(subject);//count>=38 if the given subject is not found
        }
        int[] temp = new int[m];
        for(int i=count;i<count+m;i++){
            try {
                temp[i - count] = Integer.parseInt(attendance[i]);//Stores the values of P,L,E,UE
            }
            catch(NumberFormatException e){
                temp[i-count] = 0;
            }
        }
        if(filename.equals("Attendance - August"))
        {
            return  new AttendanceData(temp[0],temp[2],temp[3],temp[4],subject);//removing percentage field
        }
        return  new AttendanceData(temp[0],temp[1],temp[2],temp[3],subject);//AttendanceData object for the particular subject is returned
    }
    AttendanceData[] getTotalAttendance()throws Exception{
        if(attendance.length==1){//If roll number is not found,array of length 1 is returned by getAttendance()
            throw new RollNumberNotFoundException(rollno);//Exception thrown
        }
        AttendanceData[] out = new AttendanceData[subjects.length];
        int i=0;
        for(String x:subjects){
            out[i]=getAttendance(x);
            i++;
        }
        return out;//returns attendance for all subjects of the student as an array of AttendanceData object
    }
}
class AttendanceData{
    // getters
    public int getPresent() {
        return present;
    }

    public int getLate() {
        return late;
    }

    public int getExcused() {
        return excused;
    }

    public int getUnexcused() {
        return unexcused;
    }

    public String getStudname() {
        return studname;
    }

    public String getStudrno() {
        return studrno;
    }

    private int present,late,excused,unexcused;
    private String subject,studname,studrno;
    AttendanceData(String subject)throws Exception{
        throw new SubjectNotFoundException(subject);//Default constructor(Called when attendance data is not found)
    }

    AttendanceData(int p,int l,int e,int u,String subject){
        present=p;
        late=l;
        excused=e;
        unexcused=u;
        this.subject=subject;
    }

    void getparticularName_rno(String na,String rno)
    {
        studname=na;
        studrno=rno;
    }

    double getPercentage(){//returns the percentage of attendance of the particular student for the subject
        try {
            return (double) present * 100 / (present + late + excused + unexcused);
        }
        catch(ArithmeticException e){
            return 0;
        }
    }

    int[] getData(){//Returns attendance data as an array of integers
        int[] out = {present,late+excused+unexcused};
        return out;
    }

    String getSubject(){
        return subject;
    }

    // to update the values for all
    int[] updateall(int incdec,char subch,int ch)
    {
        if(subch=='P')
        {
            if(ch==1) present+=incdec;
            else if(ch==2)
            {
                present-=incdec;
                if(present<0) present=0;
            }
        }
        else if(subch=='L')
        {
            if(ch==1) late+=incdec;
            else if(ch==2)
            {
                late-=incdec;
                if(late<0) late=0;
            }
        }
        else if(subch=='E')
        {
            if(ch==1) excused+=incdec;
            else if(ch==2)
            {
                excused-=incdec;
                if(excused<0) excused=0;
            }
        }
        else if(subch=='U')
        {
            if(ch==1) unexcused+=incdec;
            else if(ch==2)
            {
                unexcused-=incdec;
                if(unexcused<0) unexcused=0;
            }
        }
        int[] itemp={present,late,excused,unexcused};
        return itemp;
    }

    // to update for a single student
    int[] update(int newVal,char subch)
    {
        if(subch=='P') present = newVal;
        else if(subch=='L') late = newVal;
        else if(subch=='E') excused = newVal;
        else if(subch=='U') unexcused = newVal;
        int[] itemp={present,late,excused,unexcused};
        return itemp;
    }

    String[] convert(int[] itemp,String subject,String filename,String[] data,String[] subs) throws Exception{
        int count = 2,m=4,k;
        if(filename.equals("Attendance - August"))
        {
            m=5;
        }
        for (String x : subs) {
            if (x.equals(subject)) {
                break;
            }
            count += m;//Incremented by 4 as each subject has four columns(P,L,E,UE)
        }
        k = count;
        for (int i = count; i < count + 4; i++) {
            try {
                if(filename.equals(StudentAttendance.fileNames[2]) && i == count+1) k++;
                data[k] = Integer.toString(itemp[i - count]);//Stores the values of P,L,E,UE
                k++;
            } catch (NumberFormatException e) {
                data[i]="0";
            }
        }
        return data;
    }

    public String toString(){
        return subject+"\nPresent: "+present+" Late: "+late+" Excused: "+excused+" Unexcused: "+unexcused;
    }
}
class Professor
{
    String subject,filename;
    String[] data=new String[50],subs;
    BufferedReader br;
    BufferedReader details=null;
    BufferedWriter bw=null;

    Professor(String s,String fna) throws Exception {
        subject = s;
        filename=fna;
        subs = StudentAttendance.subjects;
    }

    AttendanceData getAttendance(String subject,String filename,String data[]) throws Exception{
        int count = 2,m=4;
        if(filename.equals("Attendance - August"))
        {
            m=5;
        }
        for (String x : subs) {
            if (x.equals(subject)) {
                break;
            }
            count += m;//Incremented by 4 as each subject has four columns(P,L,E,UE)
        }
        if (m==4 && count >= 38||m==5 && count>=47) {
            return new AttendanceData(subject);//count>=38 if the given subject is not found
        }
        int[] temp = new int[m];
        for (int i = count; i < count + m; i++) {
            try {
                temp[i - count] = Integer.parseInt(data[i]);//Stores the values of P,L,E,UE
            } catch (NumberFormatException e) {
                temp[i - count] = 0;
            }
        }
        if(filename.equals("Attendance - August"))
        {
            return  new AttendanceData(temp[0],temp[2],temp[3],temp[4],subject);//removing percentage field
        }
        return new AttendanceData(temp[0], temp[1], temp[2], temp[3], subject);//AttendanceData object for the particular subject is returned
    }

    void copy()throws Exception
    {
        boolean b=(new File(filename+".csv")).delete();
        System.out.println(b);
        b=(new File("temp.csv")).renameTo(new File(filename+".csv"));
        System.out.println(b);
    }

    LinkedList getsubjectattendance() throws Exception
    {
        String l;
        LinkedList list=new LinkedList();
        br = new BufferedReader(new FileReader(filename+".csv"));
        l=br.readLine();
        l=br.readLine();
        l=br.readLine();
        l=br.readLine();
        while((l=br.readLine())!=null)
        {
            data=l.split(",");
            AttendanceData a=getAttendance(this.subject,filename,data);
            a.getparticularName_rno(data[1],data[0]);
            list.add(a);
        }
        br.close();
        return list;
    }

    void modifyall(int incdec,char subch,int choice) throws Exception
    {
        br= new BufferedReader(new FileReader(filename+".csv"));
        bw = new BufferedWriter(new FileWriter("temp.csv"));
        String l;
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        while((l=br.readLine())!=null)
        {
            data=l.split(",");
            AttendanceData a=getAttendance(this.subject,filename,data);
            int[] itemp = a.updateall(incdec,subch,choice);
            data=a.convert(itemp,this.subject,filename,data,subs);
            String s="";
            for(String x:data)
            {
                s=s+x+",";
            }
            l=s;
            //System.out.println(l);
            bw.write(l+"\n");
        }
        br.close();
        bw.close();
        copy();
    }

    // to modify a single student
    void modify(int incdec,String rollno,char subch)throws Exception
    {
        //choice - 1 : increase ; 2 : decrease;
        //subch P - present, L - late, E - excused , U - unexcused
        br= new BufferedReader(new FileReader(filename+".csv"));
        bw = new BufferedWriter(new FileWriter("temp.csv"));
        String l;
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        l=br.readLine();
        bw.write(l+"\n");
        while((l=br.readLine())!=null)
        {
            data=l.split(",");
            if(rollno.equals(data[0])) {
                AttendanceData a = getAttendance(this.subject,filename,data);
                int[] itemp = a.update(incdec, subch);
                data = a.convert(itemp, this.subject,filename, data, subs);
                String s = "";
                for (String x : data) {
                    s = s + x + ",";
                }
                l = s;
            }
            bw.write(l+"\n");
        }
        br.close();
        bw.close();
        copy();
    }
}
//The classes end here
