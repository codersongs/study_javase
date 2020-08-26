package com.codersongs.javase.io;

/**
 * 深拷贝的实现，无非是人为的添加逻辑，对不能进行深拷贝的属性执行拷贝并赋值
 */
public class DeepCopy implements Cloneable{
    public static void main(String[] args) throws Exception{
        DeepCopy obj = new DeepCopy();
        obj.setAge(12);
        obj.setEmail("test");
        obj.setId(18);
        DeepSub deepSub = new DeepSub();
        deepSub.setSubName("sub");
        deepSub.setTopic("new topic");
        deepSub.setUid(123);
        obj.setSubject(deepSub);

        DeepCopy clone = (DeepCopy) obj.clone();
        System.out.println(clone);
        deepSub.setTopic("rename topic");
        System.out.println(obj.getSubject().getTopic() + ";" + clone.getSubject().getTopic());
    }

    @Override
    public Object clone() {
        //浅拷贝
        try {
            // 直接调用父类的clone()方法
            DeepCopy clone = (DeepCopy) super.clone();
            //对于无法进行深拷贝的引用数据类型（非String，非基本数据类型，要手动执行clone，因此这些引用数据类型必须实现克隆接口）
            clone.setSubject((DeepSub) this.getSubject().clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    private int age;
    private Integer id;
    private String email;
    private DeepSub subject;

    public DeepSub getSubject() {
        return subject;
    }

    public void setSubject(DeepSub subject) {
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
class DeepSub implements Cloneable{
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
    protected Object clone() throws CloneNotSupportedException {
        //Subject 如果也有引用类型的成员属性，也应该和 Student 类一样实现
        return super.clone();
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
