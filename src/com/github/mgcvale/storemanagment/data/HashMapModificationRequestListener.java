package com.github.mgcvale.storemanagment.data;

public interface HashMapModificationRequestListener {
    public final int DELETE_REQUESTED = 1;
    public final int SELL_REQUESTED = 2;
    public final int EDIT_REQUESTED = 3;
    public final int STOCK_UPDATE_REQUESTED = 4;
    public void modificationRequested(int type);
}
