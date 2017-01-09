package org.peace.allinone.ui;

public class Node {

  static Node HEAD = new Node();
  static Node TAIL = new Node();

  Node next;
  int value;

  public static Node getReverseN(int N) {
    Node head = HEAD;
    Node tail = TAIL;

    int l = 0;
    Node tmp = head;
    while (tmp != tail) {
      tmp = head.next.next;
      l += 2;
      if (tmp == null) {
        --l; // go back
      }
    }

    int M = l - N;
    Node result = head;
    for (int i = 0; i < M; ++i) {
      result = result.next;
    }
    return result;
  }

  public static void sort(Node head, Node tail) {
    Node tmpTail = tail;
    while (tmpTail != head) {
      tmpTail = doSort(head, tmpTail);
    }
  }

  static Node doSort(Node head, Node tail) {
    Node tmp = head;
    Node end = tail;
    while (tmp != tail && tmp != null) {
      Node r = tmp.next;
      if (r == tail) {
        end = tmp;
      }
      if (r != null && tmp.value > r.value) {
        swap(tmp, r);
      }
      tmp = tmp.next;
    }
    return end;
  }

  static void swap(Node left, Node right) {
    int t = left.value;
    left.value = right.value;
    right.value = t;
  }
}
