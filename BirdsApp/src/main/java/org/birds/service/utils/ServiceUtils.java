package org.birds.service.utils;

import java.util.*;

/*
    Service Utils
 */
public class ServiceUtils {
    private static void replace(List<String> strings)
    {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase().trim());
        }
    }

    public static boolean isListEmpty(List<String> src){
        if(null == src || src.size() == 0){
            return true;
        }
        boolean isEmpty = false;
        for(String  elem : src){
            isEmpty = false;
            if(elem == null  || elem.trim().length() == 0){
                isEmpty = true;
            }
        }

        return isEmpty;

    }
    public static boolean isListUnique(List<String> srcList){
        if(null == srcList || srcList.isEmpty()){
            return true;
        }
        List<String> lowerList = new ArrayList<String>(srcList);
        replace(lowerList);
        Set<String> strSet  = new HashSet<String>(lowerList);

        return (strSet.size() == lowerList.size());
    }
}
