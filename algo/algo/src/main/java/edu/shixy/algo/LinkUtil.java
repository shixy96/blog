package edu.shixy.algo;

/**
 * Hello world!
 */
public class Link {
    private Link next;
    private Integer value;

    public Link next() {
        return next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
