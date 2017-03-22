/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Sale;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Administrator
 */
@Remote
public interface SaleRepository {
    public void addSale(Sale sale) throws Exception;
    
    public void deleteSale(Long saleId) throws Exception;
    
    public Sale searchSaleById(Long id) throws Exception;
    
    public List<Sale> getAllSales() throws Exception;
    
    public List<Sale> getSalesByCustomerId(Long customerId) throws Exception;
    
    public List<Sale> getSalesBySalespersonId(Long salespersonId) throws Exception;
    
    public List<Sale> getSalesByDate(String date) throws Exception;
    
    public List<Sale> getSalesByStatus(String status) throws Exception;
    
    public List<Sale> getSalesByStatusAndCustomerId(String status, Long customerId) throws Exception;
    
    public void updateStatusById(Long id, String status) throws Exception;
}
