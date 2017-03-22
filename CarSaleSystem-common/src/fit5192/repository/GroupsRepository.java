/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Groups;
import javax.ejb.Remote;

/**
 *
 * @author Administrator
 */
@Remote
public interface GroupsRepository {
    public void addGroup(Groups group) throws Exception;
}
