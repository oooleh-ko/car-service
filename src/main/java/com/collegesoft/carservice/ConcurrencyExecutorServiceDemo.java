package com.collegesoft.carservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MyCallable implements Callable<String> {
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        // Simulating some task
        Thread.sleep(1000);
        return name + " completed!";
    }
}

public class ConcurrencyExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();

        // Submit tasks to the executor service
        for (int i = 1; i <= 5; i++) {
            Callable<String> callable = new MyCallable("Task " + i);
            Future<String> future = executorService.submit(callable);
            futures.add(future);
        }

        // Retrieve results when tasks are completed
        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        executorService.shutdown();
    }
}
