package practice.a;

public class SinglyLinkListCopy {

    private Node head = null;

    public Node findByValue(int value) {
        Node r = head;
        while (r != null && r.data != value) {
            r = r.next;
        }
        return r;
    }

    public Node findByIndex(int index) {
        Node r = head;
        int pos = 0;
        while (r != null && pos != index) {
            r = r.next;
            ++pos;
        }
        return r;
    }

    public void insertToHead(int value) {
        Node node = new Node(value, null);
        insertToHead(node);
    }

    public void insertToHead(Node newNode) {
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }

    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        if (p == head) {
            insertToHead(newNode);
            return;
        }
        Node r = head;
        while (r != null && r.next != p) {
            r = r.next;
        }
        if (r == null) {
            return;
        }
        newNode.next = p;
        r.next = newNode;
    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        if (head == p) {
            head = head.next;
            return;
        }
        Node r = head;
        while (r != null && r.next != p) {
            r = r.next;
        }
        if (r == null) {
            return;
        }
        r.next = r.next.next;
    }

    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null) {
            return;
        }

        if (q == null) {
            // head
            head = head.next;
        } else {
            q.next = p.next;
        }
    }

    public void deleteAllByValue(int value) {
        if (head == null) {
            return;
        }
        Node p = head;
        Node q = null;
        while (p != null) {
            if (p.data == value) {
                if (q != null) {
                    q.next = p.next;
                } else {
                    p = p.next;
                }
                continue;
            }
            q = p;
            p = p.next;
        }
    }

    public boolean palindrome() {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Node p1 = head;
        Node p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        Node right = p1.next;
        if (p2.next == null) {
            // 奇数
            return listEqual(reverseList(p1).next, right);
        } else {
            // 偶数
            return listEqual(reverseList(p1), right);
        }
    }

    // 翻转链表到节点p
    public Node reverseList(Node p) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node cur = head;
        Node next;
        while (cur != p) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur.next = prev;
        return cur;
    }

    public boolean listEqual(Node left, Node right) {
        Node p = left;
        Node q = right;
        while (p != null && q != null && p.data == q.data) {
            p = p.next;
            q = q.next;
        }
        return p == q && p == null;
    }


    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
