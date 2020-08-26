package com.codersongs.javase.io;

/**
 * 对象拷贝
 * 浅拷贝：
 *  1.必须要实现克隆接口
 */
public class ShallowCopy implements Cloneable{
    public static void main(String[] args) throws Exception{
        ShallowCopy objCopy = new ShallowCopy();
        objCopy.setAge(12);
        objCopy.setEmail("123@qq.com");
        objCopy.setId(1);
        Subject subject = new Subject();
        subject.setSubName("test sub");
        subject.setTopic("test topic");
        subject.setUid(3);
        objCopy.setSubject(subject);

        ShallowCopy clone = (ShallowCopy) objCopy.clone();
        System.out.println(clone);
        //对于基本数据类型的修改，由于对数据进行了拷贝，因此不会影响到拷贝对象
        objCopy.setAge(23);
        System.out.println(objCopy.getAge() + ";" + clone.getAge());
        //String 是不可变的对象，赋值后未String重新生成了地址，因此也不会影响到拷贝对象，相当于执行了new String
        objCopy.setEmail("fakfa");
        System.out.println(objCopy.getEmail() + ";" + clone.getEmail());
        //对引用数据类型的修改会影响到拷贝对象
        subject.setTopic("new Topic");
        System.out.println(objCopy.getSubject().getTopic() + ";" + clone.getSubject().getTopic());

        //拷贝的对象，地址是不同的
        System.out.println(objCopy == clone);
    }

//    @Override
//    public Object clone() {
//        //浅拷贝
//        try {
//            // 直接调用父类的clone()方法
//            return super.clone();
//        } catch (CloneNotSupportedException e) {
//            return null;
//        }
//    }

    private int age;
    private Integer id;
    private String email;
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ObjCopy{" +
                "age=" + age +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", subject=" + subject +
                '}';
    }
}

class Subject{
    private String subName;
    private String topic;
    private int uid;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subName='" + subName + '\'' +
                ", topic='" + topic + '\'' +
                ", uid=" + uid +
                '}';
    }
}
