/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator
 */
public class MyFrame extends JFrame{
    private static final String[] TABLE_COLUMNS = {"Car Id", "Model No.", "Model Name", "Maker"};
    
    private JPanel searchPanel;
    private JScrollPane resultPanel;
    private JPanel detailPanel;
    
    /*------------searchPanel--------------*/
    private JLabel searchCriteriaLabel;
    private JCheckBox modelNoCheck;
    private JCheckBox modelNameCheck;
    private JCheckBox makerCheck;
    private JCheckBox typeCheck;
    private JButton searchButton;   
    private JLabel modelNoLabel;
    private JLabel modelNameLabel;
    private JLabel makerLabel;
    private JLabel typeLabel;
    private JTextField modelNoTextField;
    private JTextField modelNameTextField;
    private JTextField makerTextField;
    private JTextField typeTextField;
    private JPanel searchSubPanel;
    /*------------resultPanel--------------*/
    private JTable resultTable;
    /*------------detailPanel--------------*/
    private JPanel detailLeftSubPanel;
    private JPanel detailCenterSubPanel;
    private JPanel detailRightSubPanel; 
    private JTextField modelNoTextField_detail;
    private JTextField VINTextField_detail;
    private JTextField modelNameTextField_detail;
    private JTextField makerTextField_detail;
    private JTextField typeTextField_detail;
    private JTextField descTextField_detail;
    private JTextField previewTextField_detail;
    private JLabel thumbnailLabel;
    
    public MyFrame(){
        searchPanel = new JPanel();
        detailPanel = new JPanel();
        
        /*------------searchPanel--------------*/
        searchSubPanel = new JPanel();
        searchCriteriaLabel = new JLabel("Search Criteria: ");
        modelNoCheck = new JCheckBox("Model No");
        modelNameCheck = new JCheckBox("Model Name");
        makerCheck = new JCheckBox("Maker");
        typeCheck = new JCheckBox("Type");
        searchButton = new JButton("Search");
        searchSubPanel.setLayout(new GridLayout(1, 6));
        searchSubPanel.add(searchCriteriaLabel);
        searchSubPanel.add(modelNoCheck);
        searchSubPanel.add(modelNameCheck);
        searchSubPanel.add(makerCheck);
        searchSubPanel.add(typeCheck);
        searchSubPanel.add(searchButton);
        
        modelNoLabel = new JLabel("Model No");
        modelNameLabel = new JLabel("Model Name");
        makerLabel = new JLabel("Maker");
        typeLabel = new JLabel("Type");
        modelNoTextField = new JTextField();
        modelNameTextField = new JTextField();
        makerTextField = new JTextField();
        typeTextField = new JTextField();
        modelNoTextField.setEnabled(false);
        modelNameTextField.setEnabled(false);
        makerTextField.setEnabled(false);
        typeTextField.setEnabled(false);
        
        searchPanel.setBorder(new TitledBorder("Search"));
        searchPanel.setLayout(new GridLayout(5, 2));
        searchPanel.add(modelNoLabel);
        searchPanel.add(modelNoTextField);
        searchPanel.add(modelNameLabel);
        searchPanel.add(modelNameTextField);
        searchPanel.add(makerLabel);
        searchPanel.add(makerTextField);
        searchPanel.add(typeLabel);
        searchPanel.add(typeTextField);
        searchPanel.add(searchSubPanel);
        
        /*------------resultPanel--------------*/
        resultTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel tableColumnModel = resultTable.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(275);
        tableColumnModel.getColumn(1).setPreferredWidth(275);
        tableColumnModel.getColumn(2).setPreferredWidth(275);
        tableColumnModel.getColumn(3).setPreferredWidth(275);
        
        resultPanel = new JScrollPane(resultTable);
        resultPanel.setBorder(new TitledBorder("Result"));
        /*------------detailPanel--------------*/
        modelNoTextField_detail = new JTextField();
        modelNoTextField_detail.setBorder(new TitledBorder("Model No."));
        VINTextField_detail = new JTextField();
        VINTextField_detail.setBorder(new TitledBorder("VIN"));
        modelNameTextField_detail = new JTextField();
        modelNameTextField_detail.setBorder(new TitledBorder("Model Name"));
        makerTextField_detail = new JTextField();
        makerTextField_detail.setBorder(new TitledBorder("Maker"));
        typeTextField_detail = new JTextField();
        typeTextField_detail.setBorder(new TitledBorder("Type"));
        descTextField_detail = new JTextField();
        descTextField_detail.setBorder(new TitledBorder("Description"));
        previewTextField_detail = new JTextField();
        previewTextField_detail.setBorder(new TitledBorder("Preview URL"));
        
        detailLeftSubPanel = new JPanel();
        detailLeftSubPanel.setLayout(new GridLayout(5, 1));
        detailLeftSubPanel.add(modelNoTextField_detail);
        detailLeftSubPanel.add(VINTextField_detail);
        detailLeftSubPanel.add(modelNameTextField_detail);
        detailLeftSubPanel.add(makerTextField_detail);
        detailLeftSubPanel.add(typeTextField_detail);
        
        detailCenterSubPanel = new JPanel();
        detailCenterSubPanel.setLayout(new GridLayout(2, 1));
        detailCenterSubPanel.add(descTextField_detail);
        detailCenterSubPanel.add(previewTextField_detail);
        
       // thumbnailLabel = new JLabel(new ImageIcon("resources/image/car1.png"));
        thumbnailLabel = new JLabel();
        detailRightSubPanel = new JPanel();
        detailRightSubPanel.add(thumbnailLabel);
        
        detailLeftSubPanel.add(modelNoTextField_detail);
        detailPanel.setLayout(new GridLayout(1, 3));
        detailPanel.add(detailLeftSubPanel);
        detailPanel.add(detailCenterSubPanel);
        detailPanel.add(detailRightSubPanel);
        detailPanel.setBorder(new TitledBorder("Detail"));
        
        /*------------MyFrame--------------*/
        this.setLayout(new GridLayout(3, 1));
        this.add(searchPanel);
        this.add(resultPanel);
        this.add(detailPanel);
        
        this.setTitle("Car Search Client");
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public void setResultTable(JTable resultTable) {
        this.resultTable = resultTable;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public JScrollPane getResultPanel() {
        return resultPanel;
    }

    public void setResultPanel(JScrollPane resultPanel) {
        this.resultPanel = resultPanel;
    }

    public JPanel getDetailPanel() {
        return detailPanel;
    }

    public void setDetailPanel(JPanel detailPanel) {
        this.detailPanel = detailPanel;
    }

    public JLabel getSearchCriteriaLabel() {
        return searchCriteriaLabel;
    }

    public void setSearchCriteriaLabel(JLabel searchCriteriaLabel) {
        this.searchCriteriaLabel = searchCriteriaLabel;
    }

    public JCheckBox getModelNoCheck() {
        return modelNoCheck;
    }

    public void setModelNoCheck(JCheckBox modelNoCheck) {
        this.modelNoCheck = modelNoCheck;
    }

    public JCheckBox getModelNameCheck() {
        return modelNameCheck;
    }

    public void setModelNameCheck(JCheckBox modelNameCheck) {
        this.modelNameCheck = modelNameCheck;
    }

    public JCheckBox getMakerCheck() {
        return makerCheck;
    }

    public void setMakerCheck(JCheckBox makerCheck) {
        this.makerCheck = makerCheck;
    }

    public JCheckBox getTypeCheck() {
        return typeCheck;
    }

    public void setTypeCheck(JCheckBox typeCheck) {
        this.typeCheck = typeCheck;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JLabel getModelNoLabel() {
        return modelNoLabel;
    }

    public void setModelNoLabel(JLabel modelNoLabel) {
        this.modelNoLabel = modelNoLabel;
    }

    public JLabel getModelNameLabel() {
        return modelNameLabel;
    }

    public void setModelNameLabel(JLabel modelNameLabel) {
        this.modelNameLabel = modelNameLabel;
    }

    public JLabel getMakerLabel() {
        return makerLabel;
    }

    public void setMakerLabel(JLabel makerLabel) {
        this.makerLabel = makerLabel;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(JLabel typeLabel) {
        this.typeLabel = typeLabel;
    }

    public JPanel getDetailLeftSubPanel() {
        return detailLeftSubPanel;
    }

    public void setDetailLeftSubPanel(JPanel detailLeftSubPanel) {
        this.detailLeftSubPanel = detailLeftSubPanel;
    }

    public JPanel getDetailCenterSubPanel() {
        return detailCenterSubPanel;
    }

    public void setDetailCenterSubPanel(JPanel detailCenterSubPanel) {
        this.detailCenterSubPanel = detailCenterSubPanel;
    }

    public JPanel getDetailRightSubPanel() {
        return detailRightSubPanel;
    }

    public void setDetailRightSubPanel(JPanel detailRightSubPanel) {
        this.detailRightSubPanel = detailRightSubPanel;
    }

    public JTextField getModelNoTextField_detail() {
        return modelNoTextField_detail;
    }

    public void setModelNoTextField_detail(JTextField modelNoTextField_detail) {
        this.modelNoTextField_detail = modelNoTextField_detail;
    }

    public JTextField getVINTextField_detail() {
        return VINTextField_detail;
    }

    public void setVINTextField_detail(JTextField VINTextField_detail) {
        this.VINTextField_detail = VINTextField_detail;
    }

    public JTextField getModelNameTextField_detail() {
        return modelNameTextField_detail;
    }

    public void setModelNameTextField_detail(JTextField modelNameTextField_detail) {
        this.modelNameTextField_detail = modelNameTextField_detail;
    }

    public JTextField getMakerTextField_detail() {
        return makerTextField_detail;
    }

    public void setMakerTextField_detail(JTextField makerTextField_detail) {
        this.makerTextField_detail = makerTextField_detail;
    }

    public JTextField getTypeTextField_detail() {
        return typeTextField_detail;
    }

    public void setTypeTextField_detail(JTextField typeTextField_detail) {
        this.typeTextField_detail = typeTextField_detail;
    }

    public JTextField getDescTextField_detail() {
        return descTextField_detail;
    }

    public void setDescTextField_detail(JTextField descTextField_detail) {
        this.descTextField_detail = descTextField_detail;
    }

    public JTextField getPreviewTextField_detail() {
        return previewTextField_detail;
    }

    public void setPreviewTextField_detail(JTextField previewTextField_detail) {
        this.previewTextField_detail = previewTextField_detail;
    }

    public JLabel getThumbnailLabel() {
        return thumbnailLabel;
    }

    public void setThumbnailLabel(JLabel thumbnailLabel) {
        this.thumbnailLabel = thumbnailLabel;
    }

    public JTextField getModelNoTextField() {
        return modelNoTextField;
    }

    public void setModelNoTextField(JTextField modelNoTextField) {
        this.modelNoTextField = modelNoTextField;
    }

    public JTextField getModelNameTextField() {
        return modelNameTextField;
    }

    public void setModelNameTextField(JTextField modelNameTextField) {
        this.modelNameTextField = modelNameTextField;
    }

    public JTextField getMakerTextField() {
        return makerTextField;
    }

    public void setMakerTextField(JTextField makerTextField) {
        this.makerTextField = makerTextField;
    }

    public JTextField getTypeTextField() {
        return typeTextField;
    }

    public void setTypeTextField(JTextField typeTextField) {
        this.typeTextField = typeTextField;
    }

    public JPanel getSearchSubPanel() {
        return searchSubPanel;
    }

    public void setSearchSubPanel(JPanel searchSubPanel) {
        this.searchSubPanel = searchSubPanel;
    }
    
//    public static void main(String[] args){
//        new MyFrame();
//    }
}

