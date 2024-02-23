package com.ssyt.tqserver.framework.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanTransferUtils<T> {

    public static <T> T transfer(Object obj, Class<T> clazz){
        if(obj == null ||  clazz == null){
            return null;
        }
        T o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(obj, o);
        return o;
    }

    public static <T> List<T> transferList(List<?> objectList, Class<T> clazz){
        if(objectList == null || objectList.size() == 0 || clazz == null){
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>();
        objectList.forEach(item -> {
            arrayList.add(transfer(item, clazz));
        });
        return arrayList;
    }

}

