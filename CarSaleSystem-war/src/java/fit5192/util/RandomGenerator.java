/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.util;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class RandomGenerator {
    
    public int getRandomModelNo(){
        Random random = new Random();
        return random.nextInt(100)+1;
    }
    
    public String getRandomVIN(){
        String[] VINArray = {"1M2AA18Y2YW123448", "1FMHK7D82CGA81998", "JTHFF2C2XA2508899", "3GCEK23M49G154357", "2FMDK36CX9BA53472", "SALMF13426A221759", "1GNSKKKC1FR150345", "5NPEU46F19H407421", "1MELM5345PA611982"};
        int index = (int) Math.floor(9 * Math.random());
        return VINArray[index];
    }
}
