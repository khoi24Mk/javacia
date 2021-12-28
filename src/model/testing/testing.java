package model.testing;








class Dosth{
    int total = 0;
    testModel _itsNumber;
    Boolean _flag  = false;

    public Dosth(testModel itsNumber){
        _itsNumber = itsNumber;
    }

    public void resetNumber(){
        total = 0;
    }



    synchronized public void setTotal() throws InterruptedException {
        while (_flag){
            wait();
        }
        _flag = true;

        total+=1;
        Thread.sleep(1000);

        notify();
    }

    synchronized public void getTotal() throws InterruptedException {
        while (!_flag){
            wait();
        }
        _flag = false;
        System.out.println(total);
        _itsNumber.setLabel(String.valueOf(total));
        notify();
    }
}

public class testing{
    static class Thread1 extends Thread{
        Dosth _entity;

        Boolean _flag = false;

        Thread1(Dosth entity){
            _entity = entity;
        }

        synchronized public void notifyThread(){
            _flag =false;
            notify();

        }
        public void setPause(){
            _flag =true;
        }

        @Override
        synchronized  public void run() {
            try {

                while (true){
                    while (_flag){
                        wait();
                    }
                    _entity.setTotal();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread{
        Dosth _entity;

        Boolean _flag = false;

        Thread2(Dosth entity){
            _entity = entity;
        }

        synchronized public void notifyThread(){
            _flag =false;
            notify();
        }
        public void setPause(){
            _flag =true;
        }

        @Override
        synchronized  public void run() {
            try {
                while (true){
                    while (_flag){
                        wait();
                    }
                    _entity.getTotal();

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        /*Dosth x = new Dosth();

        Thread a = new Thread1(x);
        Thread b = new Thread2(x);
        a.start();
        b.start();*/



    }

}