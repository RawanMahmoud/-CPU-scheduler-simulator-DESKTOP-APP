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
import java.util.*;

public class Algorithms {
    public static LinkedList <Process> Processes;
    public static LinkedList <Process> OriginalProcesses = new LinkedList ();
    public static int qt;
    public static double avgWaiting;
    public static double avgTurnAround;
    
    static void FCFS(){
        Processes = new LinkedList(OriginalProcesses);
        for (int i=0; i<(Processes.size()-1); i++){
            for(int j=1; j<(Processes.size()-i); j++){
                if (Processes.get(j-1).Arrive > Processes.get(j).Arrive){
                    Collections.swap (Processes , j, j-1 );
                }
            }
        }
    }
    
    static void Nonpreem_table(){
        int clock = Processes.get(0).Arrive;
        int totalWaiting = 0;
        int totalTurn= 0;
        System.out.println("Clock start "+clock);
        for(int i=0; i<Processes.size(); i++){
            Processes.get(i).start = clock;
            Processes.get(i).finish = clock + Processes.get(i).Brust;
            clock += Processes.get(i).Brust;
            Processes.get(i).waiting = Processes.get(i).start - Processes.get(i).Arrive;
            Processes.get(i).turn = Processes.get(i).finish - Processes.get(i).Arrive;
            totalWaiting += Processes.get(i).waiting;
            totalTurn += Processes.get(i).turn;
        }
        avgWaiting = ((double)totalWaiting)/Processes.size();
        avgTurnAround=((double)totalTurn)/Processes.size();
        System.out.println("Clock end "+clock+" average waiting "+avgWaiting);
        
    }
    
    static void SJF_preemptive(){
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList(OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        
        int clock = Processes.get(0).getArrive();
        int count = Processes.size();

        int totalBurst = 0 + Processes.get(0).getArrive();
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).Brust;
        }
        System.out.println("Clock starts at "+clock+" and ends at "+totalBurst);
        while ( clock <= totalBurst ){
            for(int i=0; i<Processes.size(); i++){
                if (Processes.get(i).getArrive() <= clock){
                    System.out.println(Processes.get(i).Name);
                    String Name = Processes.get(i).Name;
                    int Brust = Processes.get(i).Brust;
                    int Arrive = Processes.get(i).Arrive;
                    int Priority = Processes.get(i).Priority;
                    Sorting.add(new Process(Name,Brust,Arrive,Priority));
                    Processes.remove(i);
                    i--;
                }
            }
            System.out.println("Sorting size before : "+Sorting.size());
            System.out.println("clock "+clock);
            for (int i=0; i<(Sorting.size()-1); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).remainingTime > Sorting.get(j).remainingTime){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0){
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }

                Sorting.get(0).remainingTime -= 1;
                String Name = Sorting.get(0).Name;
                int Brust = Sorting.get(0).Brust;
                int Arrive = Sorting.get(0).Arrive;
                int Priority = Sorting.get(0).Priority;
                int remaining = Sorting.get(0).remainingTime;
                int start = clock;
                int finish = clock+1;
//                int waiting = start - Arrive;
//                totalWaiting += waiting;
                Sorted.add(new Process(Name,Brust,Arrive,Priority,remaining,start,finish));
                
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }
            }
            clock++;
        }
        Processes = new LinkedList (Sorted);
        MergeProcesses();
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).Name.equals(Processes.get(j).Name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).Arrive - OriginalList.get(i).Brust;
                    OriginalList.get(i).turn = OriginalList.get(i).waiting + OriginalList.get(i).Brust;
                }
            }
        }
        int totalWaiting = 0;
        int totalturn=0;
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).Name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
            totalturn += OriginalList.get(i).turn;
            
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
        avgTurnAround = ((double)totalturn)/count;
    }
    
    static void SJF_nonpreemptive(){
        LinkedList <Process> Sorting = new LinkedList();
        LinkedList <Process> Sorted = new LinkedList ();
        FCFS();
        
        int clock = Processes.get(0).Arrive;
        int totalBurst = 0;
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).Brust;
        }
//      int k=1;
//      int min;
//    for(int j=0;j<Processes.size();j++)
//    {
//    //    bt=bt+B_T[j];
//        min=Processes.get(k).Brust;
//    for(int i=k;i<Processes.size();i++)
//    {
//        if ( totalBurst>=Processes.get(i).Arrive && Processes.get(i).Brust<min)
//            {
////                temp=p[k];
////                p[k]=p[i];
////                p[i]=temp;
////                temp=A_V_T[k];
////                A_V_T[k]=A_V_T[i];
////                A_V_T[i]=temp;
////                temp=B_T[k];
////                B_T[k]=B_T[i];
////                B_T[i]=temp;
//                 Collections.swap (Processes, k, i );
//            }
//    }
//    k++;
//    }
        while (clock <= totalBurst){
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).Arrive <= clock){
                    Sorting.add(Processes.get(i));
                    Processes.remove(i);
                    i--;
                }
            }
            for(int i=0; i<Sorting.size(); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).getBrust() > Sorting.get(j).getBrust()){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0)
            {
            Sorted.add(Sorting.get(0));
            clock += Sorting.get(0).Brust;
            Sorting.remove(0);
            }
            else
                break;
        }
        Processes = Sorted;
    }
   
    static void Priority_preemptive(){
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList(OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        
        int clock = Processes.get(0).getArrive();
        int count = Processes.size();

        int totalBurst = 0 + Processes.get(0).getArrive();
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).Brust;
        }
        System.out.println("Clock starts at "+clock+" and ends at "+totalBurst);
        while ( clock <= totalBurst ){
            for(int i=0; i<Processes.size(); i++){
                if (Processes.get(i).getArrive() <= clock){
                    System.out.println(Processes.get(i).Name);
                    String Name = Processes.get(i).Name;
                    int Brust = Processes.get(i).Brust;
                    int Arrive = Processes.get(i).Arrive;
                    int Priority = Processes.get(i).Priority;
                    Sorting.add(new Process(Name,Brust,Arrive,Priority));
                    Processes.remove(i);
                    i--;
                }
            }
            System.out.println("Sorting size before : "+Sorting.size());
            System.out.println("clock "+clock);
            for (int i=0; i<(Sorting.size()-1); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).Priority > Sorting.get(j).Priority){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0){
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }

                Sorting.get(0).remainingTime -= 1;
                String Name = Sorting.get(0).Name;
                int Brust = Sorting.get(0).Brust;
                int Arrive = Sorting.get(0).Arrive;
                int Priority = Sorting.get(0).Priority;
                int remaining = Sorting.get(0).remainingTime;
                int start = clock;
                int finish = clock+1;
//                int waiting = start - Arrive;
//                totalWaiting += waiting;
                Sorted.add(new Process(Name,Brust,Arrive,Priority,remaining,start,finish));
                
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }
            }
            clock++;
        }
        Processes = new LinkedList (Sorted);
        MergeProcesses();
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).Name.equals(Processes.get(j).Name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).Arrive - OriginalList.get(i).Brust;
                    OriginalList.get(i).turn = OriginalList.get(i).waiting + OriginalList.get(i).Brust;
                }
            }
        }
        int totalWaiting = 0;
        int totalturn=0;
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).Name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
            totalturn += OriginalList.get(i).turn;
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
        avgTurnAround = ((double)totalturn)/count;
    }
    
    static void Priority_nonpreemptive(){
        System.out.println("Priority nonpreemtive called");
        
        LinkedList <Process> Sorting = new LinkedList();
        LinkedList <Process> Sorted = new LinkedList ();
        FCFS();
        
        int clock = Processes.get(0).Arrive;
        int totalBurst = 0;
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).Brust;
        }
        totalBurst += clock;
        System.out.println(totalBurst);
        while (clock <= totalBurst){
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).Arrive <= clock){
                    Sorting.add(Processes.get(i));
                    System.out.println("p= "+Processes.get(i).Name);
                    Processes.remove(i);
                    i--;
                }
            }
            for(int i=0; i<Sorting.size(); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).getPriority()> Sorting.get(j).getPriority()){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0)
            {
            Sorted.add(Sorting.get(0));
            System.out.println("sort= "+Sorting.get(0).Name);
            clock += Sorting.get(0).Brust;
            Sorting.remove(0);
            }
            else
                break;
        }
        Processes = Sorted;
    }
    
    static void RR(){
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList (OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        int clock = Processes.get(0).Arrive;
        int totalBurst = 0 + Processes.get(0).Arrive;
        
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).Brust;
        }
        int loop = 0;
        System.out.println("clock start "+clock+" and end "+totalBurst);
        while (clock <= totalBurst){
            loop++;
            System.out.println("clock "+clock+"  loop "+loop);
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).Arrive <= clock){
                    String Name = Processes.get(i).Name;
                    int Brust = Processes.get(i).Brust;
                    int Arrive = Processes.get(i).Arrive;
                    int Priority = Processes.get(i).Priority;
                    int remaining = Processes.get(i).remainingTime;
                    Sorting.add(new Process (Name,Brust,Arrive,Priority,remaining));
                    Processes.remove(i);
                    i--;
                }
            }
            if (Sorting.size() > 0){
                if (Sorting.get(0).remainingTime > qt){
                    Sorting.get(0).remainingTime -= qt;
                    
                    String Name = Sorting.get(0).Name;
                    int Brust = Sorting.get(0).Brust;
                    int Arrive = Sorting.get(0).Arrive;
                    int Priority = Sorting.get(0).Priority;
                    int remaining = Sorting.get(0).remainingTime;
                    int start = clock;
                    int finish = clock+qt;
                    
                    clock += qt;
                    
                    Sorted.add(new Process (Name,Brust,Arrive,Priority,remaining,start,finish));
                    Sorting.remove(0);
                    Processes.add(new Process(Name,Brust,Arrive,Priority,remaining));
                }
                else if (Sorting.get(0).remainingTime > 0){
                    int clockIncrease = Sorting.get(0).remainingTime;
                    Sorting.get(0).remainingTime = 0;
                    
                    String Name = Sorting.get(0).Name;
                    int Brust = Sorting.get(0).Brust;
                    int Arrive = Sorting.get(0).Arrive;
                    int Priority = Sorting.get(0).Priority;
                    int remaining = Sorting.get(0).remainingTime;
                    int start = clock;
                    int finish = clock+clockIncrease;
                    
                    clock += clockIncrease;
                    
                    Sorted.add(new Process (Name,Brust,Arrive,Priority,remaining,start,finish));
                    Sorting.remove(0);
                }
            }
            else clock++;
        }
        Processes = new LinkedList(Sorted);
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).Name.equals(Processes.get(j).Name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).Arrive - OriginalList.get(i).Brust;
                    OriginalList.get(i).turn = Processes.get(j).finish - OriginalList.get(i).Arrive ;
                }
            }
        }
        int totalWaiting = 0;
        int totalturn=0;
        int count = OriginalProcesses.size();
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).Name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
            totalturn += OriginalList.get(i).turn;
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
        avgTurnAround = ((double)totalturn)/count;
    }
    
    static void MergeProcesses(){
        int i = 0;
        for(int j=1; j<Processes.size(); ){
            if(Processes.get(i).Name.equals(Processes.get(j).Name)){
                Processes.get(i).finish = Processes.get(j).finish;
                Processes.remove(j);
            }
            else{
                i++;
                j++;
            }
        }
    }
    
}
