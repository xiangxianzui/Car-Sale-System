/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.mbeans;

import fit5192.repository.CarRepository;
import fit5192.repository.SaleRepository;
import fit5192.repository.UsersRepository;
import fit5192.repository.entities.Car;
import fit5192.repository.entities.Sale;
import fit5192.repository.entities.Users;
import fit5192.util.SuperBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Administrator
 */
@Named(value = "saleBean")
@SessionScoped
public class SaleBean extends SuperBean implements Serializable {

    /**
     * Creates a new instance of SaleBean
     */
    @EJB
    private SaleRepository saleRepository;
    @EJB
    private UsersRepository usersRepository;
    @EJB
    private CarRepository carRepository;
            
    private Sale sale;
    
    private String buyCarErrMsg;
    
    private String buyCarSelectedSalespersonName;
    
    private String saleSearchCriterionSelectedType;
    private String saleSearchErrMsg;
    
    private String payErrMsg;
    
    private String saleSearchCriterionSelectedType_Salesperson;
    private String saleSearchErrMsg_Salesperson;
    public SaleBean() {
        sale = new Sale();
        
        buyCarErrMsg = "";
        
        buyCarSelectedSalespersonName = "";
        
        saleSearchCriterionSelectedType = "";
        saleSearchErrMsg = "";
        
        payErrMsg = "";
        
        saleSearchCriterionSelectedType_Salesperson = "";
        saleSearchErrMsg_Salesperson = "";
    }

    public SaleRepository getSaleRepository() {
        return saleRepository;
    }

    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getBuyCarErrMsg() {
        return buyCarErrMsg;
    }

    public void setBuyCarErrMsg(String buyCarErrMsg) {
        this.buyCarErrMsg = buyCarErrMsg;
    }

    public String getBuyCarSelectedSalespersonName() {
        return buyCarSelectedSalespersonName;
    }

    public void setBuyCarSelectedSalespersonName(String buyCarSelectedSalespersonName) {
        this.buyCarSelectedSalespersonName = buyCarSelectedSalespersonName;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getSaleSearchCriterionSelectedType() {
        return saleSearchCriterionSelectedType;
    }

    public void setSaleSearchCriterionSelectedType(String saleSearchCriterionSelectedType) {
        this.saleSearchCriterionSelectedType = saleSearchCriterionSelectedType;
    }

    public String getSaleSearchErrMsg() {
        return saleSearchErrMsg;
    }

    public void setSaleSearchErrMsg(String saleSearchErrMsg) {
        this.saleSearchErrMsg = saleSearchErrMsg;
    }

    public String getPayErrMsg() {
        return payErrMsg;
    }

    public void setPayErrMsg(String payErrMsg) {
        this.payErrMsg = payErrMsg;
    }

    public String getSaleSearchCriterionSelectedType_Salesperson() {
        return saleSearchCriterionSelectedType_Salesperson;
    }

    public void setSaleSearchCriterionSelectedType_Salesperson(String saleSearchCriterionSelectedType_Salesperson) {
        this.saleSearchCriterionSelectedType_Salesperson = saleSearchCriterionSelectedType_Salesperson;
    }

    public String getSaleSearchErrMsg_Salesperson() {
        return saleSearchErrMsg_Salesperson;
    }

    public void setSaleSearchErrMsg_Salesperson(String saleSearchErrMsg_Salesperson) {
        this.saleSearchErrMsg_Salesperson = saleSearchErrMsg_Salesperson;
    }

    
    public String buyCar(Car car) throws Exception{
        System.out.println("buy a car" + car.getCarId());
        Users currentUser = (Users) getSessionValue("currentUser");
        List<Sale> currentUserSales = saleRepository.getSalesByCustomerId(currentUser.getUserId());
        if(currentUserSales!=null){
            if(currentUserSales.isEmpty()){//currentUser can buy a new car
                setSessionValue("currentCar", car);
                return "/buyCarPage?faces-redirect=true";
            } else{
                for (Sale currentUserSale : currentUserSales) {
                    if(currentUserSale.getStatus().equals("unpaid")){//currentUser has a sale record which is not paid yet, can not buy a new car
                        buyCarErrMsg = "Sorry, you have a sale record which has not been paid yet. Can not buy a new car!";
                        return null;
                    }
                }
                //currentUser doesn't have a sale record which is not paid yet, can buy a new car
                setSessionValue("currentCar", car);
                return "/buyCarPage?faces-redirect=true";
            }
        }
        return null;    
    }
    
    public String buyCarInBuyCarPage() throws Exception{
        //System.out.println("buyCarInBuyCarPage" + buyCarSelectedSalespersonName.split(" ")[0]);
        Users salesperson = usersRepository.getUserByFirstNameAndLastName(buyCarSelectedSalespersonName.split(" ")[0], buyCarSelectedSalespersonName.split(" ")[1]);
        Users customer = (Users)getSessionValue("currentUser");
        String sellDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()); 
        Car car = (Car) getSessionValue("currentCar");
        carRepository.updateSellStateById(car.getCarId(), "sold");
        String status = "unpaid";
        
        Car thisCar = carRepository.searchCarById(car.getCarId());
        
        System.out.println(salesperson.toString() +"\n" + customer.toString() +"\n"+ sellDate +"\n"+ thisCar.toString());   
        Sale newSale = new Sale();
        newSale.setCustomer(customer);
        newSale.setSalesperson(salesperson);
        newSale.setSaleDate(sellDate);
        newSale.setCar(thisCar);
        newSale.setStatus(status);
        saleRepository.addSale(newSale);
        return "/customerPage?faces-redirect=true";
    }
    
    public void payCarInBuyCarPage(Long saleId) throws Exception{
        System.out.println("payCarInBuyCarPage");
        saleRepository.updateStatusById(saleId, "paid");
        payErrMsg = "Pay Success!";
    }
    
    public List<Sale> searchOnclick() throws Exception{
        List<Sale> resultSales = null;
        Users currentCustomer = (Users) getSessionValue("currentUser");
        switch(saleSearchCriterionSelectedType){
            case "My Sales":
                resultSales = saleRepository.getSalesByCustomerId(currentCustomer.getUserId());
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg = "No sales found!";}
                else    {saleSearchErrMsg = "";}
                return resultSales;
            case "paid":
                resultSales = saleRepository.getSalesByStatusAndCustomerId("paid", currentCustomer.getUserId());
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg = "No sales found!";}
                else    {saleSearchErrMsg = "";}
                return resultSales;
            case "unpaid":
                resultSales = saleRepository.getSalesByStatusAndCustomerId("unpaid", currentCustomer.getUserId());
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg = "No sales found!";}
                else    {saleSearchErrMsg = "";}
                return resultSales;
            default:
                return null;
        }
    }
    
    public boolean isPaid(String status){
        if(status!=null){
            switch(status){
                case "unpaid":
                    return false;
                case "paid":
                    return true;
            }
        }
        return false;
    }
    
    public List<Sale> searchOnclick_Salesperson() throws Exception{
        List<Sale> resultSales = null;
        switch(saleSearchCriterionSelectedType_Salesperson){
            case "All Sales":
                resultSales = saleRepository.getAllSales();
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg_Salesperson = "No sales found!";}
                else    {saleSearchErrMsg_Salesperson = "";}
                return resultSales;
            case "My Sales":
                Users currentUser = (Users) getSessionValue("currentUser");
                resultSales = saleRepository.getSalesBySalespersonId(currentUser.getUserId());
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg_Salesperson = "No sales found!";}
                else    {saleSearchErrMsg_Salesperson = "";}
                return resultSales;
            case "Unpaid Sales":
                resultSales = saleRepository.getSalesByStatus("unpaid");
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg_Salesperson = "No sales found!";}
                else    {saleSearchErrMsg_Salesperson = "";}
                return resultSales;
            case "Paid Sales":
                resultSales = saleRepository.getSalesByStatus("paid");
                if(resultSales == null || resultSales.isEmpty())  {saleSearchErrMsg_Salesperson = "No sales found!";}
                else    {saleSearchErrMsg_Salesperson = "";}
                return resultSales;
            default:
                return null;
        }
    }
    
}
