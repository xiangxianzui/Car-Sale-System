/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Sale;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
@Stateless
public class SaleRepositoryImpl implements SaleRepository{
    
    private static final String PERSISTENCE_UNIT = "CarSaleSystemPU";
    
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public SaleRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addSale(Sale sale) throws Exception {
        System.out.println("**************************addSale excuted");
        entityManager.persist(sale);
    }

    @Override
    public void deleteSale(Long saleId) throws Exception {
        Sale sale = searchSaleById(saleId);
        if(sale != null){
            entityManager.remove(sale);
        }
    }

    @Override
    public Sale searchSaleById(Long id) throws Exception {
        return entityManager.find(Sale.class, id);
    }

    @Override
    public List<Sale> getAllSales() throws Exception {
        return entityManager.createNamedQuery("Sale.findAll").getResultList();
    }

    @Override
    public List<Sale> getSalesByCustomerId(Long customerId) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("SELECT s FROM Sale s WHERE s.customer.userId='"+customerId+"'", Sale.class);
        return q.getResultList();
    }

    @Override
    public List<Sale> getSalesBySalespersonId(Long salespersonId) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("SELECT s FROM Sale s WHERE s.salesperson.userId='"+salespersonId+"'", Sale.class);
        return q.getResultList();
    }

    @Override
    public List<Sale> getSalesByDate(String date) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("SELECT s FROM Sale s WHERE s.saleDate='"+date+"'", Sale.class);
        return q.getResultList();
    }

    @Override
    public List<Sale> getSalesByStatus(String status) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("SELECT s FROM Sale s WHERE s.status='"+status+"'", Sale.class);
        return q.getResultList();
    }

    @Override
    public void updateStatusById(Long id, String status) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("UPDATE Sale s SET s.status='"+status+"' WHERE s.saleId='"+id+"'", Sale.class);
        q.executeUpdate();
    }

    @Override
    public List<Sale> getSalesByStatusAndCustomerId(String status, Long customerId) throws Exception {
        TypedQuery<Sale> q = entityManager.createQuery("SELECT s FROM Sale s WHERE s.status='"+status+"' AND s.customer.userId='"+customerId+"'", Sale.class);
        return q.getResultList();
    }
    
    
    
}
