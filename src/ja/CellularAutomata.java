package ja; /**
 * Created by yangst on 2018/5/17.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author yangst
 * @Date 2018/5/17
 **/
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board mainBoard) {
        this.mainBoard = mainBoard;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        });
        this.workers = new Worker[count];
        //for(int i = 0;i<count;i++)
           // workers[i] = new Worker();

    }

    private class Worker implements Runnable{
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasconverged()){
                for(int x = 0; x< board.getMaxX();x++){
                    for(int y = 0; y< board.getMaxY();y++)
                        ;

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                    return;
                }

                }
            }
        }

        public void start(){

        }
    }


    private class Board {

        private int getMaxX(){
            return 10;
        }

        private int getMaxY(){
            return 10;
        }

        private void commitNewValues(){

        }

        private  boolean hasconverged(){
            return true;
        }
    }
}
