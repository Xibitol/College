package dev.pimous.l2s3sdn.td4;

import java.util.ArrayList;


public class ArrayListEtud<E> extends ArrayList<E>{

    @Override
    public boolean add(E p) {
        this.addFirst(p);
        return true;
    }
}
