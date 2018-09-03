/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Gabriel
 */
public class Conversor {
    
    public static byte[] toByteArray(Object obj) throws IOException{
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.flush();
            bytes = bos.toByteArray();
        } finally{
            if (oos != null){
                oos.close();
            }
            if (bos != null){
                bos.close();
            }
        }
        return bytes;
    }
    
    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } finally{
            if (bis != null){
                bis.close();
            }
            if (ois != null){
                ois.close();
            }
        }
        return obj;
    }
}
