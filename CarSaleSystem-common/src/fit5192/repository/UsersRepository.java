/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Administrator
 */
@Remote
public interface UsersRepository {
    public void addUser(Users user) throws Exception;
    
    public void removeUser(Long userId) throws Exception;
    
    public Users searchUserById(Long id) throws Exception;
    
    public List<Users> getAllUsers() throws Exception;
    
    public Users getUserByFirstNameAndLastName(String firstName, String lastName) throws Exception;
    
    public List<Users> getUsersByLastName(String lastName) throws Exception;
    
    public List<Users> getUsersByFirstName(String firstName) throws Exception;
    
    public Users getUserByEmail(String email) throws Exception;
    
    public List<Users> getAllSalespersons() throws Exception;
    

    
    /**
     * Close all resources in the repository
     */
    public void close();
    
}
