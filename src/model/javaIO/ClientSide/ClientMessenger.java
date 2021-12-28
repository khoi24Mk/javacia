package model.javaIO.ClientSide;

public class ClientMessenger extends Thread{

    public static String _msg = "";
    public static Boolean _flag = false;

    public ClientMessenger(Boolean flag){
        _flag =flag;
    }

    synchronized public String get_msg() throws InterruptedException {
        while (!_flag){
            wait();
        }
        _flag = false;
        notify();
        return _msg;
    }

    synchronized public void set_msg(String _msg) throws InterruptedException {
        while (_flag){
            wait();
        }
        _flag = true;
        ClientMessenger._msg = _msg;
        notify();
    }
}
