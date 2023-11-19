package edu.hw6.task1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Diskmap implements Map<String, String> {

    private int size;

    private final Map<String, String> map;

    public Diskmap(){
        size = 0;
        map = new HashMap<>();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    //
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        //т.е.в этом методе нужно создать такой файл, имя которого будет key, содержимое - value
        //потом добавить это в hashmap
        return null;
    }

    @Override
    public String remove(Object key) {
        //удалить этот файл физически
        //удалить его из hashmap
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {
        //удалить все файлы, которые есть уже в hashmap
        //а потом очистить всю hashmap
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
