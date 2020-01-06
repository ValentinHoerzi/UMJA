package net.htlgrieskirchen.pos2.plf.retrosteam.user;
import net.htlgrieskirchen.pos2.plf.retrosteam.user.User;
import net.htlgrieskirchen.pos2.plf.retrosteam.store.Game;
import net.htlgrieskirchen.pos2.plf.retrosteam.user.Customer;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.applet.*;
import java.beans.*;
import java.lang.*;
import java.math.*;
import java.net.*;
import java.nio.*;
import java.rmi.*;
import java.security.*;
public abstract class Customer implements User{ 
private String name;
private Game[] library;
private Game[] wishlist;
public  Customer(String name){}
public Game[] getLibrary(){return null;}
public void addToLibrary(Game game){}
public String toString(){return null;}
}
