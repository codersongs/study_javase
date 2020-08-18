package com.codersongs.javase.concurrent;

import com.codersongs.javase.network.HttpUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatch {
    public static void main(String[] args) throws Exception{
        HttpRunnable httpRunnable1 = new HttpRunnable("http://localhost:9999/api/get_batch_user_imid_by_phones", "{\"phones\":[\"17328569916\"]}");
        HttpRunnable httpRunnable2 = new HttpRunnable("http://localhost:9999/api/get_batch_user_imid_by_phones", "{\"phones\":[\"18820475237\"]}");
        HttpRunnable httpRunnable3 = new HttpRunnable("http://localhost:9999/api/get_batch_user_imid_by_phones", "{\"phones\":[\"13530540284\"]}");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.execute(httpRunnable1);
        threadPool.execute(httpRunnable2);
        threadPool.execute(httpRunnable3);

        threadPool.shutdown();
    }
}
