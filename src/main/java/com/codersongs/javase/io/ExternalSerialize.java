package com.codersongs.javase.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ExternalSerialize implements Externalizable {
    //版本号的作用和serailize是一样的
    private static final long serialVersionUID = 2L;

    public static void main(String[] args) throws Exception{
        String path = "/Users/song/Desktop/test/javase/externalize.txt";
        ExternalSerialize externalSerialize = new ExternalSerialize();
        externalSerialize.setName("A");
        externalSerialize.setIsbn("I");
        externalSerialize.setAuthors(new ArrayList<>(
        ));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(externalSerialize);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        ExternalSerialize externalizableA = (ExternalSerialize) ois.readObject();
        System.out.println(externalizableA);
    }

    @Test
    public void serialize() throws Exception{
        String path = "/Users/song/Desktop/test/javase/externalize.txt";
        ExternalSerialize externalSerialize = new ExternalSerialize();
        externalSerialize.setName("A");
        externalSerialize.setIsbn("I");
        externalSerialize.setAuthors(new ArrayList<>(
        ));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(externalSerialize);
    }

    @Test
    public void deserialize() throws  Exception{
        String path = "/Users/song/Desktop/test/javase/externalize.txt";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        ExternalSerialize externalizableA = (ExternalSerialize) ois.readObject();
        System.out.println(externalizableA);
    }
    /** 书名 */
    private String name;

    /** ISBN */
    private String isbn;

    /** 作者 */
    private List<String> authors;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(isbn);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        isbn = (String) in.readObject();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @param authors
     *            the authors to set
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Book [name=" + name + ", isbn=" + isbn + ", authors=" + authors + "]";
    }
}
