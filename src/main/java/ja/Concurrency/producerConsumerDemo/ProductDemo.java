package ja.Concurrency.producerConsumerDemo;

/**
 * 生产者-消费者模式
 */
public class ProductDemo {

    Object du ;
    Product product = new Product(this);
    Consumer consumer = new Consumer(this);


    public static void main(String[] args) throws InterruptedException {
        ProductDemo demo = new ProductDemo();
        new Thread(demo.product).start();

        new Thread(demo.consumer).start();

    }

    class Product implements Runnable{
        ProductDemo productDemo;

        public Product(ProductDemo productDemo) {
            this.productDemo = productDemo;
        }
        @Override
        public void run() {
            while(true) {
                synchronized (this){
                    if(du!=null){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (productDemo.consumer){
                    System.out.println("生产了一个du");
                    du = new Object();
                    productDemo.consumer.notifyAll();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        ProductDemo productDemo;
        public Consumer(ProductDemo productDemo) {
            this.productDemo = productDemo;
        }
        @Override
        public void run() {
            while (true){
                synchronized (this){
                    if(du == null){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (productDemo.product){
                    System.out.println("消费了一个du");
                    du = null;
                    productDemo.product.notifyAll();
                }
            }
        }
    }
}
