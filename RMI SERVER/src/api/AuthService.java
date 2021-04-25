package api;

import model.Person;
import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AuthService extends Remote {

   Person login (String email, String password) throws RemoteException;

   String register (String name, String email, String password, String address, String phone) throws RemoteException;

   ArrayList<Student> getUsers () throws RemoteException;

}
