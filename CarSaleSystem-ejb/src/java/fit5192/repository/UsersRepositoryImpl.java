/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
@Stateless
public class UsersRepositoryImpl implements UsersRepository{
    private static final String PERSISTENCE_UNIT = "CarSaleSystemPU";
    
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public UsersRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addUser(Users user) throws Exception {
        //System.out.println("************************************addUser excuted");
        entityManager.persist(user);
        //System.out.println(user.getLastName() + " " + user.getFirstName() + " " + user.getType() + " " + user.getAddress() + " ");
//        EntityTransaction transaction = entityManager.getTransaction();
//        try{
//            transaction.begin();
//            entityManager.persist(user);
//            transaction.commit();
//        } catch(Exception e){
//            transaction.rollback();
//        }
    }

    @Override
    public void removeUser(Long userId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users searchUserById(Long id) throws Exception {
        return entityManager.find(Users.class, id);
    }

    @Override
    public List<Users> getAllUsers() throws Exception {
        return entityManager.createNamedQuery("Users.findAll").getResultList();
    }

    @Override
    public Users getUserByFirstNameAndLastName(String firstName, String lastName) throws Exception {
        TypedQuery<Users> q = entityManager.createQuery("SELECT u FROM Users u WHERE u.firstName='"+firstName+"' AND u.lastName='"+lastName+"'", Users.class);
        if(q.getResultList()!=null){
            if(!q.getResultList().isEmpty())   return q.getResultList().get(0); 
        }
        return null;
    }

    @Override
    public List<Users> getUsersByLastName(String lastName) throws Exception {
        TypedQuery<Users> q = entityManager.createQuery("SELECT u FROM Users u WHERE u.lastName='"+lastName+"'", Users.class);
        return q.getResultList();     
    }

    @Override
    public List<Users> getUsersByFirstName(String firstName) throws Exception {
        TypedQuery<Users> q = entityManager.createQuery("SELECT u FROM Users u WHERE u.firstName='"+firstName+"'", Users.class);
        return q.getResultList(); 
    }

    @Override
    public Users getUserByEmail(String email) throws Exception {
        TypedQuery<Users> q = entityManager.createQuery("SELECT u FROM Users u WHERE u.email='"+email+"'", Users.class);
        if(q.getResultList()!=null){
            if(!q.getResultList().isEmpty())   return q.getResultList().get(0); 
        }
        return null;
    }
    
    
    
    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }

        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Override
    public List<Users> getAllSalespersons() throws Exception {
        String type = "SalesPerson";
        TypedQuery<Users> q = entityManager.createQuery("SELECT u FROM Users u WHERE u.type='"+type+"'", Users.class);
        return q.getResultList(); 
    }

}
