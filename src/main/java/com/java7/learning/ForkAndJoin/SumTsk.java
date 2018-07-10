package com.java7.learning.ForkAndJoin;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author xzk
 * @version 1.0
 * since JDK 1.8
 * @date 2018年06月21日
 **/
public class SumTsk extends RecursiveTask<Long> {
    private final int threshold = 15000000;

    private int[] arr;

    private int start;

    private int end;

    public SumTsk(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start <= this.threshold){
            Long sum = 0L;
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }
            System.out.println(String.
                    format(Thread.currentThread().getName() + ", start:%d, end:%d, size:%d, result:%d", start, end, (end - start), sum));
            return sum;
        }

        int mid = (end + start) / 2;

        SumTsk s1 = new SumTsk(arr, start, mid);
        SumTsk s2 = new SumTsk(arr, mid + 1, end);

        invokeAll(s1, s2);
//        s1.fork();

        Long r2 = s2.join();
        Long r1 = s1.join();

        return r1 + r2;
    }

    public static void main(String[] args) {
        int size = 300000000;
        int[] arr = new int[size];
        for (int i=0; i<size; i++){
            arr[i] = i+1;
        }
        long start = System.currentTimeMillis();
        long end;
        ForkJoinPool fjp = new ForkJoinPool();
        SumTsk fjt = new SumTsk(arr, 0, arr.length -1);

        ForkJoinTask<Long> submit = fjp.submit(fjt);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Long result = fjp.invoke(fjt);
        System.out.println(String.format("总和:%d, takes:%d, start:%d, end:%d", result,
                (end = System.currentTimeMillis()) - start, start, end)
        );

        start = end;
        long sum = 0L;
        for (int i = 0; i<size; i++){
            sum += arr[i];
        }
        System.out.println(String.format("总和:%d, takes:%d, start:%d, end:%d",
                sum, (end = System.currentTimeMillis()) - start, start, end));
    }
}
