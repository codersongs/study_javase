package com.codersongs.javase.keyword;

/**
 * -server -Xmx4g -Xms4g
 * 注册到Finalizer 中的ReferenceQueue<Object> queue，由Finalizer的静态内部类FinalizerThread处理
 * Finalizer类可以理解JVM为JAVA语言提供的全局性对象释放资源类（类似C语言的析构方法）。
 *
 * Finalizer类有一个全局储存Finalizer对象的ReferenceQueue和一个优先级较低的执行线程FinalizerThread。
 *
 * Finalizer类以链表的形式将需要释放资源的对象维护成Finalizer对象链。
 *
 * 实现了finalize方法的类被JVM标记为f类，这种f类实例化对象时，JVM会调用Finalizer类的register方法，将f类对象包裹成Finalizer对象加入Finalizer链。
 *
 * JVM会在GC时，判断Finalizer对象包裹的f类对象是不是只被Finalizer类引用，如果是，则将这个Finalizer对象加入ReferenceQueue。
 *
 * FinalizerThread线程会从ReferenceQueue中取出Finalizer对象，将它从Finalizer对象链中移除，JVM执行f类对象的finalize方法。这之后，f类对象就可以在下次GC时被回收。
 */
public class Finallize {
    private String flag;

    public Finallize(String flag) {
        this.flag = flag;
    }

    /**
     * 只有重写了finalize才会注册到 referenceQueue，且注册在初始化(new)之前
     * -XX:-RegisterFinalizersAtInit，默认为True，即先注册再实例化，设置后先初始化再注册
     * 初始化时加入Finalizer链表，GC时判断是否有除了Finalizer(使用referent属性)外还有别的引用，没有的话加入ReferenceQueue，意味着可以被FinalizerThread执行，执行的过程中，先删除队列元素，再删除链表结点，
     * 然后执行finalize()，执行完成后将finalizer的referent设置为null，执行完毕
     */
    @Override
    public void finalize(){
        System.out.println("execute finalize");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Finallize finallize = new Finallize("flag:" + i);
            System.out.println(finallize);
        }
    }
}
