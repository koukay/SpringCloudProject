package com.houkai;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

/**
 * 1到100万以内，如何打印99万个不重复的随机数？
 */
public class BloomFilterTest {
    private static int total = 1000000;
    private static int i=0;
    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total);
//    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total, 0.001);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int BloomFilterSize = useBloomFilter();
        long end = System.currentTimeMillis();
        int setSize = useSet();
        long endSet = System.currentTimeMillis();
        long bloomTime = end - start;
        long setTime = endSet - end;
        System.out.println("布隆过滤器BloomFilterSize: "+BloomFilterSize+" 执行用时为: "+bloomTime);
        System.out.println("set集合setSize: "+setSize+" 执行用时为: "+setTime);

    }
    public static  int useSet(){
        Set set= new HashSet();
        while (true){
            if (set.size()<990000){
                int random = (int) (Math.random() * total);
                System.out.println(random);
                set.add(random);
            }else {
                return set.size();
            }
        }

    }
    public static  int useBloomFilter(){
        // 匹配已在过滤器中的值，是否有匹配上的

        while (true){
            int random = (int) (Math.random() * total);
            if (!bf.mightContain(random)) {
                bf.put(random);
                System.out.println(random);
                i++;
                if (i>=990000) return i;
            }
        }

    }

// 初始化1000000条数据到过滤器中
//        for (int i = 0; i < total; i++) {
//            bf.put(i);
//        }
//        // 匹配已在过滤器中的值，是否有匹配不上的
//        for (int i = 0; i < total; i++) {
//            if (!bf.mightContain(i)) {
//                System.out.println("有坏人逃脱了~~~"+i);
//            }
//        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
//        int count = 0;
//        for (int i = total; i < total + 10000; i++) {
//            if (bf.mightContain(i)) {
//                count++;
//            }
//        }
//        System.out.println("误伤的数量：" + count);


}