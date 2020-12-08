package com.zq.export;

import com.alibaba.fastjson.util.IOUtils;
import com.zq.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExportUtil {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setPassword("123");
        user.setRoles("ROLE_USER");
        user.setUsername("zs");

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("out"));
            outputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(outputStream);
        }

        /*File file = new File("out");
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            User user1 = (User)inputStream.readObject();
            System.out.println(user1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                //FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
