/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project.GanttChart.ExtraData;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.Background;
import javax.swing.JFrame;
import sun.plugin.javascript.navig.Anchor;


/**
 *
 * @author ahmed
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    public Button button_ADD;
    @FXML
    public Button button_clear;
    @FXML
    public Button button_run;
    @FXML
    public TextField txt_priority;
    @FXML
    public TextField txt_arrivaltime;
    @FXML
    public TextField waitingtime;
    @FXML
    public TextField name;
    @FXML
    public TextField brust;
    @FXML
    public TextField turnaroundtime;
    @FXML
    public TextField quantum_time;
    @FXML
    public CheckBox priority;
    @FXML
    public ComboBox combobox_schedulers;
    @FXML
    TableView<Process> table;
    @FXML
    TableColumn<Process, String> name_id;
    @FXML
    TableColumn<Process, Integer> CPUbrust_id;
    @FXML
    TableColumn<Process, Integer> arrival_id;
    @FXML
    TableColumn<Process, Integer> priority_id;
    @FXML
    public Button clear_all;
    @FXML
    public TextArea from_to;
    @FXML
    public Label quantum_lbl;
    @FXML
    public BorderPane borderPane;
    //public Anchor parent;
    final ObservableList<Process> processs = FXCollections.observableArrayList();
    String fromto="";
   // public VBox vboxParent;
    public GanttChart<Number, String> chart;
    public NumberAxis xAxis;
    public CategoryAxis yAxis;
    public HBox hboxChart;
    
     String[] colors = new String[]{"status-red", "status-green", "status-blue", "status-tomato",
            "status-violet", "status-purple", "status-yellow", "status-brown", "status-black"};
    String[] colorsHex = new String[]{"#800000", "#008000", "#000080", "#ff6347", "#ee82ee", "#6a5acd", "#ffff47"
            , "#996600", "#000000"};

  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
        
        name_id.setCellValueFactory(new PropertyValueFactory<>("Name"));
        CPUbrust_id.setCellValueFactory(new PropertyValueFactory<>("Brust"));
        arrival_id.setCellValueFactory(new PropertyValueFactory<>("Arrive"));
        priority_id.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        table.setItems(processs);
        //table.getColumns().addAll(name_id, CPUbrust_id, arrival_id,priority_id);
        txt_priority.setVisible(false);
        quantum_time.setVisible(false);
        quantum_lbl.setVisible(false);
        from_to.setVisible(false);
        priority.setOnAction(e -> handleOptions(txt_priority));
        combobox_schedulers.getItems().addAll("FCFS","SJF(Preemptive)","SJF(Non Preemptive)","Priority (Preemptive)","Priority (NonPreemptive)","Round Robin");
        combobox_schedulers.setOnAction( e ->quantumOption( quantum_time) );
        button_ADD.setOnAction(e -> Add());
        button_clear.setOnAction(e -> clear());
        clear_all.setOnAction(e -> ClearAll());
        button_run.setOnAction(e -> Run());
        initializeganttChart();
    }
       private void handleOptions(TextField txt ){

        if(priority.isSelected())
          txt_priority.setVisible(true);
        else
          txt_priority.setVisible(false);  
        
        }  
       private void quantumOption(TextField text){
           
         if((combobox_schedulers.getValue()=="Round Robin"))  
         {                  
            quantum_time.setVisible(true);
            quantum_lbl.setVisible(true);
         }
         else
         {
            quantum_time.setVisible(false);
            quantum_lbl.setVisible(false);
         }
         if((combobox_schedulers.getValue()=="Priority (Preemptive)")||(combobox_schedulers.getValue()=="Priority (NonPreemptive)")) 
         {
             priority.setSelected(true);
             txt_priority.setVisible(true);   
         }
         else
         {
             priority.setSelected(false);
             txt_priority.setVisible(false);  
         }
        
         
       }
      
       private void Add(){
           // Process p = new Process (name.getText(), Integer.parseInt(brust.getText()), Integer.parseInt(txt_arrivaltime.getText()), Integer.parseInt(txt_priority.getText()));
//        Algorithms.Processes.add(p);
        if (txt_priority.getText() == null || txt_priority.getText().trim().isEmpty()) {
            
     // your code here
//        Process p = new Process();
//        p.setName(name.getText());
//        p.setBrust(Integer.parseInt(brust.getText()));       
//        p.setArrive(Integer.parseInt(txt_arrivaltime.getText()));
//        p.setPriority(0);
        Process p = new Process (name.getText(), Integer.parseInt(brust.getText()), Integer.parseInt(txt_arrivaltime.getText()));
        Algorithms.OriginalProcesses.add(p);
        priority_id.setVisible(false);
        table.getItems().add(p);
     //   table.setBackground(Background.EMPTY);
        name.clear();
        brust.clear();
        txt_arrivaltime.clear();
        //txt_priority.clear();
}
        else{
          Process p = new Process (name.getText(), Integer.parseInt(brust.getText()), Integer.parseInt(txt_arrivaltime.getText()), Integer.parseInt(txt_priority.getText()));
//        Process p = new Process();
//        p.setName(name.getText());
//        p.setBrust(Integer.parseInt(brust.getText()));
//        p.setPriority(Integer.parseInt(txt_priority.getText()));
//        p.setArrive(Integer.parseInt(txt_arrivaltime.getText()));
        Algorithms.OriginalProcesses.add(p);
        priority_id.setVisible(true);
        table.getItems().add(p);
        name.clear();
        brust.clear();
        txt_arrivaltime.clear();
        txt_priority.clear();
        
        }   
       }
       public void clear(){
        ObservableList<Process>productSelected,allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        
        for(int i=0;i<productSelected.size();i++){
        Algorithms.OriginalProcesses.remove(productSelected.get(i));
        }
        productSelected.forEach(allProducts::remove);
    }
      public void ClearAll(){
         from_to.setVisible(false);
         from_to.clear();
         from_to.setText("");
         
        ObservableList<Process>allProducts;
        allProducts = table.getItems();
        allProducts.clear();
        Algorithms.OriginalProcesses.clear();
        combobox_schedulers.setValue(null);
        waitingtime.setText(null);
        turnaroundtime.setText(null);
        priority.setSelected(false);
        txt_priority.setVisible(false);  
        quantum_time.clear(); 
        chart.getData().clear();
    }  
       void Run(){
        
        for(int i=0; i<Algorithms.OriginalProcesses.size(); i++){
            System.out.println(Algorithms.OriginalProcesses.get(i).Name+" RT "+Algorithms.OriginalProcesses.get(i).remainingTime);
        }
        switch(combobox_schedulers.getSelectionModel().getSelectedIndex()){
            case 0:
                Algorithms.FCFS();
                Algorithms.Nonpreem_table();
                buildGanttChart();
                Print2();
                break;
            case 1:
                Algorithms.SJF_preemptive();
                buildGanttChart();
                Print2();
//                Algorithms.Processes.clear();
                break;
            case 2:
                Algorithms.SJF_nonpreemptive();
                Algorithms.Nonpreem_table();
                buildGanttChart();
                Print2();
                break;
            case 3:
                Algorithms.Priority_preemptive();
                buildGanttChart();
                Print2();
                break;
            case 4:
                Algorithms.Priority_nonpreemptive();
                Algorithms.Nonpreem_table();
                buildGanttChart();
                Print2();
                break;
            case 5:
                Algorithms.qt = Integer.parseInt(quantum_time.getText());
                Algorithms.RR();
                buildGanttChart();
                Print2();
                break;
            default:
                Algorithms.FCFS();
                Algorithms.Nonpreem_table();
                buildGanttChart();
                Print2();
                break;
                
        }
        
//        GanttWindow GW = new GanttWindow(Algorithms.Processes);
//        GW.setVisible(true);
//        GW.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
    }
       void Print2(){
           from_to.setVisible(true);
        for(int i=0; i<Algorithms.Processes.size(); i++){
            System.out.println(Algorithms.Processes.get(i).Name+" from "+Algorithms.Processes.get(i).start+" to "+Algorithms.Processes.get(i).finish+"  RT "+Algorithms.Processes.get(i).remainingTime);
        }
        for(int i=0; i<Algorithms.Processes.size(); i++){
            fromto=fromto+Algorithms.Processes.get(i).Name+" from "+Algorithms.Processes.get(i).start+" to "+Algorithms.Processes.get(i).finish+"\n";
        }
        fromto=fromto+"---------------------------------\n";
        from_to.setText(fromto);
        waitingtime.setText(Double.toString(Algorithms.avgWaiting));
        turnaroundtime.setText(Double.toString(Algorithms.avgTurnAround));
    }
    private void initializeganttChart() {


        xAxis = new NumberAxis();
        yAxis = new CategoryAxis();

        chart = new GanttChart<Number, String>(xAxis, yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.BLACK);
        xAxis.setMinorTickCount(1);

        xAxis.setAutoRanging(false);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.BLACK);
        yAxis.setTickLabelGap(10);

    //    chart.setTitle("Machine Monitoring");
        chart.setLegendVisible(false);
        chart.setBlockHeight(50);


        URL url = this.getClass().getResource("ganttchart.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();

        chart.setBlockHeight(040);
        chart.getStylesheets().add(css);
        chart.setAnimated(true);


      //  hboxChart = new HBox(chart);
        hboxChart = new HBox(chart);

        borderPane.setCenter(hboxChart);
        hboxChart.setMaxWidth(500);
        //hboxChart.setMaxWidth(parent.getMaxWidth() );
        chart.setMinWidth(500);
       // chart.setMaxWidth(vboxParent.getMaxWidth());
        chart.setLegendVisible(false);
       // borderPane.setCenter(hboxChart);
      // vboxParent.getChildren().add(hboxChart);
//        hboxChart.setMaxWidth(700);
//        hboxChart.setMaxWidth(vboxParent.getMaxWidth() );
//        chart.setMinWidth(700);
//        chart.setMaxWidth(vboxParent.getMaxWidth());
//        chart.setLegendVisible(false);


    }
        private void buildGanttChart() {
        chart.getData().clear();
//        hboxChart.setMaxWidth(vboxParent.getWidth()-300);
//        hboxChart.setMinWidth(vboxParent.getWidth()-300 );
//        chart.setMinWidth(vboxParent.getWidth()-300);
//        chart.setMaxWidth(vboxParent.getWidth());
//
//
        XYChart.Series series = new XYChart.Series();
        chart.getData().add(series);
//
        double lastFinish = Algorithms.Processes.getLast().finish;
        xAxis.setUpperBound(lastFinish);
        if (lastFinish < 20) {
            xAxis.setTickUnit(1);

        } else if (lastFinish < 30) {
            xAxis.setTickUnit(2);
        } else {
            xAxis.setTickUnit(5);
        }

       
              for (int i = 0; i <Algorithms.Processes.size(); i++) {
                 double currentStart = Algorithms.Processes.get(i).start;
              double currentFinish = Algorithms.Processes.get(i).finish;
              String col  =  colors[Integer.valueOf(Algorithms.Processes.get(i).Name)];
              
  
            series.getData().add(new XYChart.Data(currentStart, "",new ExtraData( (long) (currentFinish - currentStart),col)));
              }
              VBox vBoxLegend = new VBox();
        HBox hBox = new HBox();
        int i = 0;
        while (i < Algorithms.Processes.size()) {
            //CpuProcess process = processesList.get(i);
            Rectangle rectangle = new Rectangle(20, 20);
            rectangle.setFill(Paint.valueOf(colorsHex[Integer.valueOf(Algorithms.Processes.get(i).Name)]));
            Label label = new Label("P" + Algorithms.Processes.get(i).Name);
            label.setFont(new Font("Arial", 11));
            label.setPadding(new Insets(0, 10, 0, 10));

            if (i % 2 == 0) {
                hBox = new HBox(label, rectangle);
                hBox.setPadding(new Insets(2));
            } else {
                hBox.getChildren().addAll(label, rectangle);
                vBoxLegend.getChildren().addAll(hBox);
            }


            i++;
        }
        if (vBoxLegend.getChildren().indexOf(hBox) == -1)
            vBoxLegend.getChildren().add(hBox);

        borderPane.setLeft(vBoxLegend);

        /*if (hboxChart.getChildren().size() == 2) {
            hboxChart.getChildren().set(1, vBoxLegend);
        } else {
            hboxChart.getChildren().add(vBoxLegend);

        }*/
              
        }
//
//
//
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//                System.out.println("david");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }*/
//        final ScheduledExecutorService scheduler
//                = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(
//                new Runnable() {
//
//                    int counter = -1;
//
//                    @Override
//                    public void run() {
//                        runsimulation();
//                        counter++;
//                        if (counter < schedule.startTimes.size()) {
//
//                            Platform.runLater(new Runnable() {
//                                @Override
//                                public void run() {
//                                    buildGrandChartHpFn(schedule, counter, series);
//
//                                }
//                            });
//
//
//                        } else {
//                            endSimulation();
//                            setAverageWaitingAndTurnAroundTime(schedule);
//                            scheduler.shutdown();
//
//                         }
//
//                    }
//                },
//                1,
//                1,
//                TimeUnit.SECONDS);
//
//
//        VBox vBoxLegend = new VBox();
//        HBox hBox = new HBox();
//        int i = 0;
//        while (i < processesList.size()) {
//            CpuProcess process = processesList.get(i);
//            Rectangle rectangle = new Rectangle(20, 20);
//            rectangle.setFill(Paint.valueOf(colorsHex[process.getProcessId()]));
//            Label label = new Label("P" + process.getProcessId());
//            label.setFont(new Font("Arial", 11));
//            label.setPadding(new Insets(0, 10, 0, 10));
//
//            if (i % 2 == 0) {
//                hBox = new HBox(label, rectangle);
//                hBox.setPadding(new Insets(2));
//            } else {
//                hBox.getChildren().addAll(label, rectangle);
//                vBoxLegend.getChildren().addAll(hBox);
//            }
//
//
//            i++;
//        }
//        if (vBoxLegend.getChildren().indexOf(hBox) == -1)
//            vBoxLegend.getChildren().add(hBox);
//
//        borderPane.setRight(vBoxLegend);
//
//        /*if (hboxChart.getChildren().size() == 2) {
//            hboxChart.getChildren().set(1, vBoxLegend);
//        } else {
//            hboxChart.getChildren().add(vBoxLegend);
//
//        }*/
//
//
//    }
//     private void buildGrandChartHpFn(CpuSchedule schedule, int i, XYChart.Series series) {
//        int currentStart = schedule.startTimes.get(i);
//        int currentfinish = schedule.finishTimes.get(i);
//        textCounter.setText(String.valueOf(currentfinish));
//        textCounter.setFill(Paint.valueOf(colorsHex[schedule.processIDs.get(i)]));
//
//
//        series.getData().add(new XYChart.Data(currentStart, "",
//                new ExtraData((int) (currentfinish - currentStart), colors[schedule.processIDs.get(i)])));
//    }  
    }    