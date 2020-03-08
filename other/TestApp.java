package other;

import java.util.Map;
import java.util.concurrent.*;


public class TestApp {
    public static void main(String[] args) {
        new ParallelProcessor().parallelProcess(3);
    }
}

class ParallelProcessor {
    Map<String, String> resultMap = new ConcurrentHashMap<>();

    public void parallelProcess(int threadCount) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.submit(() -> {
                try {
                    parallelProcessing(threadNum);
                } catch (Exception e) {
                    ////??? 求教山哥  这里面应该怎么操作能在线程被中断的情况下 能合理的停掉被创建出来的线程
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished1");
        executorService.shutdown();
        //iterate over resultMap to do further processing.
        System.out.println("finished2");
    }

    //??? 求教山哥  这里面应该怎么操作能在线程被中断的情况下 能合理的停掉被创建出来的线程
    private void parallelProcessing(int threadNum) {
        System.out.println("{" + threadNum + "}");
        // mock the IO operation  make downstream rest service call
        restCall(threadNum);
        //someotherprocessing
    }

    //??? 求教山哥  这里面应该怎么操作能在线程被中断的情况下 能合理的停掉被创建出来的线程
    private void restCall(int threadNum) {
        System.out.println("downstream call start");
        if (threadNum == 2) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resultMap.putIfAbsent("downsteam" + threadNum, "mock response");
        System.out.println("downstream call end");
    }
}
