package org.peace.allinone.ui;

import java.util.LinkedList;

public class Node {
  private Node left;
  private Node right;

  private String value;

  public Node(String value) {
    this.value = value;
  }

  public static void main(String[] args) {
    // 构造一个二叉树
    Node root = new Node("root");
    Node l1 = new Node("l1");
    Node r1 = new Node("r1");
    root.left = l1;
    root.right = r1;
    Node l2 = new Node("r2");
    l1.left = l2;

    // demo
    Node result = findTargetNode(root);
    System.out.println(result.value);
  }

  static Node findTargetNode(Node root) {
    Node result = root;
    LinkedList<Node> queue = new LinkedList<>(); // 队列
    queue.add(root);
    // 从右子节点开始按层遍历二叉树
    while (queue.size() > 0) {
      Node head = queue.pollFirst();
      if (isLeaf(head)) {
        result = head;
        break;
      }
      if (head.right != null) {
        queue.add(head.right);
      }
      if (head.left != null) {
        queue.add(head.left);
      }
    }
    return result;
  }

  static boolean isLeaf(Node node) {
    return node.left == null && node.right == null;
  }
}
