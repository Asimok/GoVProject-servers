package com.tools;
/*
 * tojson<K,V> 将任意键值对转换成json格式
 * 
 * */

import java.util.HashMap;
import java.util.Set;

public class tojson<K,V> extends HashMap<K,V> {
    public String toString() {
        Set<K> keys = super.keySet();
        StringBuilder bases = new StringBuilder("{");
        for (K k: keys) {
            bases.append("\""+k.toString()+"\":");
            bases.append("\""+super.get(k)+"\",");
        }
        bases.setCharAt(bases.length()-1,'}');
        return new String(bases);
    }
}
