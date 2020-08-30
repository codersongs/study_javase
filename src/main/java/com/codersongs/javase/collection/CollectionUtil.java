package com.codersongs.javase.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionUtil {
    /**
     * 使用final和unmodifable方法表示不可修改
     */
    @Test
    public void unmodify(){
        List<String> list = new ArrayList<>();
        list.add("A");
        System.out.println(list);
        final List<String> unmodifiableList = Collections.unmodifiableList(list);
//        unmodifiableList.add("B");//抛出异常
        System.out.println(unmodifiableList);
    }

    /**
     * arrays.sort和collections.sort是最常用的排序方法，Collections.sort使用的也是arrays.sort
     */
    @Test
    public void sort(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        int[] array = new int[]{4,7,1};
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
