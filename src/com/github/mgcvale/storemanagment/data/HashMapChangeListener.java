package com.github.mgcvale.storemanagment.data;

import java.util.HashMap;

public interface HashMapChangeListener {
    public void hashMapUpdated(HashMap<Integer, String[]> map, String source);
}
