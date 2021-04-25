package api;

import model.Products;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ProductService extends Remote {

    ArrayList<Products> getProduct () throws RemoteException;

}
