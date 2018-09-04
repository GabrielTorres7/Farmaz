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
        byte[] b = null;
                
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        b = out.toByteArray();
        
        out.close();
        os.close();
        
        return b;
    }
    
    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{
        Object obj = null;
        
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        obj = is.readObject();
        
        in.close();
        is.close();
        
        return obj;
    }
}
