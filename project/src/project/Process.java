/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author ahmed
 */
public class Process {
    
    String Name;
    int Brust;
    int Arrive;
    int Priority;
    int remainingTime;
    int turn;
    int start;
    int finish;
    int waiting;
//    int turnaround;
    Process (){ 
        this.Name = "";
        this.Brust = 0;
        this.Arrive = 0;
        this.Priority = 0;
        this.remainingTime = 0;
    }
    //arrival and priorty
    Process (String n, int b, int a, int p){ 
        this.Name = n;
        this.Brust = b;
        this.Arrive = a;
        this.Priority = p;
        this.remainingTime = b;
    }
    //no arrival ana no Priority
    Process (String n, int b){
        this.Name = n;
        this.Brust = b;
        this.remainingTime = b;
    }
    //arrival and no priorty
    Process (String n, int b, int a){
        this.Name = n;
        this.Brust = b;
        this.Arrive = a;
        this.Priority = 0;
        this.remainingTime = b;
    }
  
    Process (String n, int b, int a, int p, int r){
        this.Name = n;
        this.Brust = b;
        this.Arrive = a;
        this.Priority = p;
        this.remainingTime = r;
    }
    Process (String n, int b, int a, int p, int r, int start, int finish){
        this.Name = n;
        this.Brust = b;
        this.Arrive = a;
        this.Priority = p;
        this.remainingTime = r;
        this.start = start;
        this.finish = finish;
    }
    Process (String n, int b, int a, int p, int r, int start, int finish, int waiting){
        this.Name = n;
        this.Brust = b;
        this.Arrive = a;
        this.Priority = p;
        this.remainingTime = r;
        this.start = start;
        this.finish = finish;
        this.waiting = waiting;
    }
    public int getArrive(){
        return this.Arrive;
    }
    public int getBrust(){
        return this.Brust;
    }
    public int getPriority(){
        return this.Priority;
    }

    public String getName() {
        return Name;
    }
    void setName(String name) {
        this.Name = name;
    }
    void setPriority(int p){
        this.Priority = p;
    }
    void setBrust(int a){
        this.Brust= a;
    }
    void setArrive(int b){
        this.Arrive=b;
    }
}
