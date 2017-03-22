/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.main;

import fit5192.gui.MyFrame;
import fit5192.repository.CarRepositoryImpl;
import fit5192.repository.entities.Car;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class CarSearchClient implements ActionListener, ListSelectionListener{

    private List<Car> carListResult;//hold the searching results
    
    private CarRepositoryImpl carRepositoryImpl;
    private MyFrame frame;
    
    public CarSearchClient(){
        this.carRepositoryImpl = new CarRepositoryImpl();
        this.carListResult = new ArrayList<Car>();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.getModelNoCheck()){
            if(frame.getModelNoCheck().isSelected()){
                frame.getModelNoTextField().setEnabled(true);
            } else{
                frame.getModelNoTextField().setEnabled(false);
            }
        } else if(e.getSource() == frame.getModelNameCheck()){
            if(frame.getModelNameCheck().isSelected()){
                frame.getModelNameTextField().setEnabled(true);
            } else{
                frame.getModelNameTextField().setEnabled(false);
            }
        } else if(e.getSource() == frame.getMakerCheck()){
            if(frame.getMakerCheck().isSelected()){
                frame.getMakerTextField().setEnabled(true);
            } else{
                frame.getMakerTextField().setEnabled(false);
            }
        } else if(e.getSource() == frame.getTypeCheck()){
            if(frame.getTypeCheck().isSelected()){
                frame.getTypeTextField().setEnabled(true);
            } else{
                frame.getTypeTextField().setEnabled(false);
            }
        } else if(e.getSource() == frame.getSearchButton()){
            if(frame.getModelNoCheck().isSelected() && !frame.getModelNameCheck().isSelected() && !frame.getMakerCheck().isSelected() && !frame.getTypeCheck().isSelected()){
                if(frame.getModelNoTextField().isEnabled() && !frame.getModelNoTextField().getText().equals("")){
                    try {
                        this.getCarsByModelNo();
                    } catch (Exception ex) {
                        Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.displayResultsInTable();
                } else{
                    System.out.println("input first!");
                    JOptionPane.showMessageDialog(frame, "input Model No. first!");
                }
            }
            if(!frame.getModelNoCheck().isSelected() && frame.getModelNameCheck().isSelected() && !frame.getMakerCheck().isSelected() && !frame.getTypeCheck().isSelected()){
                if(frame.getModelNameTextField().isEnabled() && !frame.getModelNameTextField().getText().equals("")){
                    try {
                        this.getCarsByModelName();
                    } catch (Exception ex) {
                        Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.displayResultsInTable();
                } else{
                    System.out.println("input first!");
                    JOptionPane.showMessageDialog(frame, "input Model Name first!");
                }
            }
            if(!frame.getModelNoCheck().isSelected() && !frame.getModelNameCheck().isSelected() && frame.getMakerCheck().isSelected() && !frame.getTypeCheck().isSelected()){
                if(frame.getMakerTextField().isEnabled() && !frame.getMakerTextField().getText().equals("")){
                    try {
                        this.getCarsByMaker();
                    } catch (Exception ex) {
                        Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.displayResultsInTable();
                } else{
                    System.out.println("input first!");
                    JOptionPane.showMessageDialog(frame, "input Maker first!");
                }
            }
            if(!frame.getModelNoCheck().isSelected() && !frame.getModelNameCheck().isSelected() && !frame.getMakerCheck().isSelected() && frame.getTypeCheck().isSelected()){
                if(frame.getTypeTextField().isEnabled() && !frame.getTypeTextField().getText().equals("")){
                    if(frame.getTypeTextField().getText().equals("Sedan") || frame.getTypeTextField().getText().equals("4 wheel drive") || frame.getTypeTextField().getText().equals("Truck")){
                        try {
                            this.getCarsByType();
                        } catch (Exception ex) {
                            Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        this.displayResultsInTable();
                    } else{
                        JOptionPane.showMessageDialog(frame, "Type should be one of " + "Sedan, 4 wheel drive and Truck");
                    }
                    
                } else{
                    System.out.println("input first!");
                    JOptionPane.showMessageDialog(frame, "input Type first!");
                }
            }
            if(frame.getModelNoCheck().isSelected() && frame.getModelNameCheck().isSelected() && frame.getMakerCheck().isSelected() && frame.getTypeCheck().isSelected()){
                if(!frame.getModelNoTextField().getText().equals("") && !frame.getModelNameTextField().getText().equals("") && !frame.getMakerTextField().getText().equals("") && !frame.getTypeTextField().getText().equals("")){
                    try {
                        this.getCarsByModelNoAndModelNameAndMakerAndType();
                    } catch (Exception ex) {
                        Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.displayResultsInTable();
                } else{
                    System.out.println("input first!");
                    JOptionPane.showMessageDialog(frame, "input first!");
                }
            }
            
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if((e.getSource() == frame.getResultTable().getSelectionModel()) && !e.getValueIsAdjusting()){
            if(frame.getResultTable().getSelectedRow() >= 0){
                int selectedRowIndex = frame.getResultTable().getSelectedRow();
                String selectedCarId_str = frame.getResultTable().getValueAt(selectedRowIndex, 0).toString();
                Long selectedCarId = Long.parseLong(selectedCarId_str);
                JOptionPane.showMessageDialog(frame, "selected: "+selectedCarId);
                try {
                    Car car = this.getCarById(selectedCarId);
                    displayResultInDetail(car);
                } catch (Exception ex) {
                    Logger.getLogger(CarSearchClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }                      
            
        }     
    }
    
    public void createCars(){
        Car car1 = new Car(1L, 1, "VIN1", "modelname1", "maker1", "type1", "thumb1", "desc1", "preview1", "sold");
        Car car2 = new Car(2L, 2, "VIN2", "modelname2", "maker2", "type2", "thumb2", "desc2", "preview2", "unsold");
        Car car3 = new Car(3L, 3, "VIN3", "modelname3", "maker3", "type3", "thumb3", "desc3", "preview3", "unsold");
        Car car4 = new Car(4L, 4, "VIN4", "modelname4", "maker4", "type4", "thumb4", "desc4", "preview4", "unsold");
        Car car5 = new Car(5L, 5, "VIN5", "modelname5", "maker5", "type5", "thumb5", "desc5", "preview5", "sold");
        try{
            this.carRepositoryImpl.addCar(car1);
            this.carRepositoryImpl.addCar(car2);
            this.carRepositoryImpl.addCar(car3);
            this.carRepositoryImpl.addCar(car4);
            this.carRepositoryImpl.addCar(car5);
            System.out.println("5 cars added successfully");
        } catch(Exception ex){
            System.out.println("Failed to create cars: " + ex.getMessage());
        }
    }
    
    public void searchById(){
        Long id = 6L;
        try{
            Car car = this.carRepositoryImpl.searchCarById(id);
            if(car != null){
                System.out.println(car.toString());
            } else{
                System.out.println("Car not found");
            }
        } catch(Exception ex){
            System.out.println("Failed to search car by ID: " + ex.getMessage());
        }
    }
    
    public void run(){
        this.initView();
        //this.createCars();
        //this.getCarsByModelNoAndModelNameAndMakerAndType();
    }
    
    public void initView(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            e.printStackTrace();
        }
        
        frame = new MyFrame();
        frame.getModelNoCheck().addActionListener(this);
        frame.getModelNameCheck().addActionListener(this);
        frame.getMakerCheck().addActionListener(this);
        frame.getTypeCheck().addActionListener(this);
        frame.getSearchButton().addActionListener(this);
        
        frame.getResultTable().getSelectionModel().addListSelectionListener(this);
    }
    
    private Car getCarById(Long id) throws Exception{
        return this.carRepositoryImpl.searchCarById(id);
    }
    
    private void getCarsByModelNoAndModelNameAndMakerAndType() throws Exception{
        int modelNo = Integer.parseInt(frame.getModelNoTextField().getText());
        String modelName = frame.getModelNameTextField().getText();
        String maker = frame.getMakerTextField().getText();
        String type = frame.getTypeTextField().getText();
        
        carListResult = this.carRepositoryImpl.getCarsByModelNoAndModelNameAndMakerAndType(modelNo, modelName, maker, type);
        
//        List<Car> carList = this.carRepositoryImpl.getCarsByModelNoAndModelNameAndMakerAndType(modelNo, modelName, maker, type);
//        if(carList!=null && !carList.isEmpty()){
//            int size = carList.size();
//            for(int i=0; i<size; i++){
//                System.out.println(carList.get(i).toString());
//            }  
//        } else{
//            System.out.println("cars not found by combination");
//        }
    }
    
    private void getCarsByModelNo() throws Exception {
        int modelNo = Integer.parseInt(frame.getModelNoTextField().getText());
        carListResult = this.carRepositoryImpl.getCarsByModelNo(modelNo);
    }

    private void getCarsByModelName() throws Exception {
        String modelName = frame.getModelNameTextField().getText();
        carListResult = this.carRepositoryImpl.getCarsByModelName(modelName);
    }

    private void getCarsByMaker() throws Exception {
        String maker = frame.getMakerTextField().getText();
        carListResult = this.carRepositoryImpl.getCarsByMaker(maker);
    }

    private void getCarsByType() throws Exception {
        String type = frame.getTypeTextField().getText();
        carListResult = this.carRepositoryImpl.getCarsByType(type);
    }
    
    public void displayResultsInTable(){
        this.clearResultTable();
        if(carListResult==null || carListResult.isEmpty()){
            //pop up an alert
            JOptionPane.showMessageDialog(frame, "Sorry, no record found!");
        }
        for(Car car: carListResult){
            Object[] rowData = new Object[]{car.getCarId(), car.getModelNo(), car.getModelName(), car.getMaker()};
            ((DefaultTableModel)frame.getResultTable().getModel()).addRow(rowData);
        }
    }
    
    public void displayResultInDetail(Car car){
        frame.getModelNoTextField_detail().setText(String.valueOf(car.getModelNo()));
        frame.getVINTextField_detail().setText(car.getVIN());
        frame.getModelNameTextField_detail().setText(car.getModelName());
        frame.getMakerTextField_detail().setText(car.getMaker());
        frame.getTypeTextField_detail().setText(car.getType());
        frame.getDescTextField_detail().setText(car.getDescription());
        frame.getPreviewTextField_detail().setText(car.getPreviewUrl());
        frame.getThumbnailLabel().setIcon(new ImageIcon(car.getThumbnail()));
        
    }
    
    private void clearResultTable(){
        int numberOfRow = frame.getResultTable().getModel().getRowCount();
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) frame.getResultTable().getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    public static void main(String[] args) {  
        try {
            new CarSearchClient().run();
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }
    
}

