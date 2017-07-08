/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;

public class Message implements Serializable {

    static final int SEARCH = 0, LOGIN = 1, LOGOUT = 2, REGISTER = 3, ADD = 4, GETKEYWORDS = 5;
    private int type;
    private String message;
    Object messageObject ;

    // constructor
    Message(int type, String message) {
        this.type = type;
        this.message = message;
    }
     Message(int type, Object message) {
        this.type = type;
        this.messageObject = message;
    }


    // getters
    int getType() {
        return type;
    }
    String getMessage() {
        return message;
    }
}

