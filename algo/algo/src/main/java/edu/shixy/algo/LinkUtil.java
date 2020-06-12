package edu.shixy.algo;

/**
 * Hello world!
 */
public class LinkUtil {
    public static Link reverse(Link link) {
        if (link == null || !link.hasNext()) {
            return link;
        }
        Link temp;
        Link prev = link;
        Link current = link.next;
        prev.next = null;
        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        Link link = new Link(1);
        Link first = link;
        link.next = new Link(2);
        link = link.next;
        link.next = new Link(3);
        link = link.next;
        link.next = new Link(4);
        for (Link i = first; i != null; i = i.next) {
            System.out.println(i.value);
        }
        System.out.println("-------------------------");
        Link reverse = reverse(first);
        for (Link i = reverse; i != null; i = i.next) {
            System.out.println(i.value);
        }
    }
}

class Link {
    public Link next;
    public Integer value;

    public Link() {

    }

    public Link(Link link, Integer value) {
        this.next = link;
        this.value = value;
    }

    public Link(Integer value) {
        this.value = value;
    }

    public boolean hasNext() {
        return next != null;
    }

}
