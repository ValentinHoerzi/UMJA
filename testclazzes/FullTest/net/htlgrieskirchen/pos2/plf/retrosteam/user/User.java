package net.htlgrieskirchen.pos2.plf.retrosteam.user;
import net.htlgrieskirchen.pos2.plf.retrosteam.store.Game;
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
public interface User{
public final static int CAPACITY = 2 ;
public Game[] getWishlist();
public void addToWishlist(Game game);
}
