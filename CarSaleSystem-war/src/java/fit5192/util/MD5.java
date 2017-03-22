/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.util;

import java.security.MessageDigest;

/**
 *
 * @author Administrator
 */
public class MD5 {
    //Encode the password using MD5
    public MD5(){
        
    }
    
    public String md5Encode(String password) throws Exception{
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = password.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        System.out.println("password: " + hexValue.toString());
        return hexValue.toString();

    }
}
