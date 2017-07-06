import java.io.*;

public class Message implements Serializable {

    static final int SEARCH = 0, LOGIN = 1, LOGOUT = 2, REGISTER = 3;
    private int type;
    private String message;
    private Object o;


    // constructor
    Message(int type, String message) {
        this.type = type;
        this.message = message;
    }
    
    Message(int type, Object o) {
        this.type = type;
        this.o = o;
    }

    // getters
    int getType() {
        return type;
    }
    
    String getMessage() {
        return message;
    }
    
    Object getobj(){
    	return o;
    }
}

