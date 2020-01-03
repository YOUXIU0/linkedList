package linked;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 单链表节点
 */
@Getter
@Setter
public class Node {
    private int data;//存储数据
    private Node next;//存储下一个节点

    public Node() {
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data+"}";
    }
}
