package practice.a;

/**
 * @author shixy
 */
public class LinkUtil {

    public static <T> Link<T> reverse(Link<T> link) {
        if (link == null || !link.hasNext()) {
            return link;
        }
        Link<T> prev = null;
        Link<T> current = link;
        Link<T> next = link.next;
//      1 -> 2 -> 3 -> 4
//      1    2 -> 3 -> 4
//      1 <- 2    3 -> 4
//      1 <- 2 <- 3    4
//      1 <- 2 <- 3 <- 4
        do {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        } while (next != null);
        current.next = prev;
        return current;
    }

    public static <T> Link<T> delete(Link link, int lastNum) {
        if (link == null || (!link.hasNext() && lastNum > 1)) {
            return null;
        }
        Link s0 = link;
        Link s1 = link;
        Link s2 = link;
        int len0 = 1;
        int len1 = 1;
        int len2 = 1;
        for (; (s2.next != null) && s2.next.next != null; s2 = s2.next.next) {
            s1 = s1.next;
        }
        return link;
    }
}

class Link<T> {
    public Link<T> next;
    public T value;

    public Link() {
    }

    public Link(Link<T> next) {
        this.next = next;
    }

    public Link(T value) {
        this.value = value;
    }

    public Link(Link<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public boolean hasNext() {
        return next != null;
    }
}
