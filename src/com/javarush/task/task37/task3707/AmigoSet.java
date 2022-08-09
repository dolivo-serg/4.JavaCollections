package com.javarush.task.task37.task3707;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max((int) (collection.size() / .75f) + 1, 16));
        addAll(collection);
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        try {
            AmigoSet<E> amigoSet = (AmigoSet<E>) super.clone();
            amigoSet.map = (HashMap<E, Object>) map.clone();
            return amigoSet;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        int capacity = s.readInt();
        float loadFactor = s.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        int size = s.readInt();

        for (int i = 0; i < size; i++) {
            E e = (E) s.readObject();
            map.put(e, PRESENT);
        }
    }

    private void writeObject(ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();

        @SuppressWarnings("ConstantConditions")
        int intParam = HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity");
        s.writeInt(intParam);

        @SuppressWarnings("ConstantConditions")
        float floatParam = HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor");
        s.writeFloat(floatParam);

        s.writeInt(map.size());

        for (E e : map.keySet()) {
            s.writeObject(e);
        }
    }

}
