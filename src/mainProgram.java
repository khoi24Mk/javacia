
public class mainProgram {


    static class Entity{
        int count = 0;
        boolean flag = false;



        public synchronized void put() throws InterruptedException {

            Thread.sleep(1111);
            while (flag){
                System.out.println("PUT");
                wait();
            }
            flag = true;
            count++;
            System.out.println(count+"-------P--------");
            notify();
        }

        public synchronized void get() throws InterruptedException {

            while (!flag){
                System.out.println("GET");
                wait();
                System.out.println("CONTINUE");
            }
            flag = false;

            System.out.println(count+"------G---------");
            notify();
        }
    }

    static class Product implements Runnable {
        Entity _x;

        Product(Entity x){
            _x = x;
            new Thread(this).start();
        }

        @Override
        public void run() {
            while(true){
                try {
                    _x.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Customer implements Runnable {
        Entity _x;

        Customer(Entity x){
            _x = x;
            new Thread(this).start();
        }
        @Override
        public void run() {
            while(true){
                try {
                    _x.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    // Main driver method
    public static void main(String[] args){
        Entity x = new Entity();
        new Product(x);
        new Customer(x);


    }
}
