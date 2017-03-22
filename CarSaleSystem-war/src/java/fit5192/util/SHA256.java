/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Administrator
 */
public class SHA256 {

    public SHA256() {
    }
    
    public String SHA256Encode(String password){
        return Hex.encodeHexString(DigestUtils.sha256(password));
    }
    
}
