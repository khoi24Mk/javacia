package model.javaIO.ServerSide;

public class ServerMessenger extends Thread{

    public static String _msg = "";
    public static Boolean _flag = false;

    public ServerMessenger(Boolean flag){
        System.out.println("START IO");
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
        ServerMessenger._msg = _msg;
        notify();
    }
}
