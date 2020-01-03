package linked;

/**
 * 单链表的 CRUD
 */
public class SinglyLinkedList {

    private Node head = null;//头节点

    /**
     * 链表尾部添加元素
     */
    public void addToTail(int data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            Node p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(new Node(data, null));
        }
    }
    /**
     * 链表添加尾节点
     */
    public void addNodeToTail(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(node);
        }
    }

    /**
     * 链表头部添加元素
     */
    public void addToHead(int data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            Node p = head;
            head = new Node(data, p);
        }
    }

    /**
     * 链表指定位置添加元素
     */
    public void addToIndex(int index, int data) {
        if (index < 0) return;
        if (head == null || index == 0) {
            addToHead(data);
        } else {
            Node p = head;
            for (int i = 1; i < index; i++) {
                if (p != null && p.getNext() != null) {
                    p = p.getNext();
                }
            }
            Node q = p.getNext();
            p.setNext(new Node(data, q));
        }
    }

    /**
     * 链表删除指定节点
     */
    public void delete(Node p) {
        //当删除的节点为空或者链表为空时
        if (p == null || head == null) {
            return;
        }
        //当删除的是头节点时
        if (p == head) {
            head = head.getNext();
        }
        Node q = head;
        while (q != null && q.getNext() != p) {
            q = q.getNext();
        }
        q.setNext(p.getNext());
    }

    /**
     * 根据节点存储的数据获取某个节点
     */
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.getData() != value) {
            p = p.getNext();
        }
        return p;
    }

    /**
     * 根据节点的所在的索引获取某个节点
     */
    public Node findByIndex(int index) {
        Node p = head;
        for (int i = 1; i < index; i++) {
            if (p != null) {
                p = p.getNext();
            }
        }
        return p;
    }

    /**
     * 遍历链表
     */
    public void findAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p);
            p = p.getNext();
        }
    }

    /**
     * 删除头节点
     *
     */
    public Node deleteHead() {
        //当删除的节点为空或者链表为空时
        if (head == null) {
            return null;
        }
        //临时变量,存储头节点的下一个节点
        Node temp=head.getNext();
        //将头节点的下一个节点置空
        head.setNext(null);
        //将当前头节点作为返回值
        Node deleteNode =head;
        //将头节点指针指向下一个节点
        head = temp;
        //返回被删除的头节点
        return deleteNode;
    }

    /**
     * 添加头节点
     * @return
     */
    public void addHead(Node node) {
        if(head==null){
            head=node;
        }else {
            node.setNext(head);
            head=node;
        }

    }
    /**
     * 单链表反转
     * 新建链表,头节点插入法,对原链表做头删除,对新链表做头插入
     * 1->2->3->4->null
     * 4->3->2->1->null
     */
    public SinglyLinkedList reverse1() {
        SinglyLinkedList newSinList=new SinglyLinkedList();
        while (this.head!=null){
            Node newHead = this.deleteHead();
            newSinList.addHead(newHead);
        }
        return  newSinList;
    }


    /**
     * 单链表反转
     * 就地反转法
     * 把当前链表头节点的下一个节点的next设置为当前头节点,指针向后移动
     * ,先将头节点的next置空,不然会有环
     */
    public void reverse2() {
        Node q=head.getNext();
        head.setNext(null);
        Node t;
        while (q!=null){
            t=q.getNext();
            q.setNext(head);
            head=q;
            q=t;
        }
    }

    /**
     * 链表中环的检测
     * true 有环
     * false 无环
     */
    public boolean checkCircle() {
        Node slow=head;
        Node fast=head;
        while (fast!=null){
            slow=slow.getNext();
            fast=fast.getNext().getNext();
            if(slow==fast){
               return true;
            }
        }
        return  false;
    }

    /**
     * 两个有序的链表合并
     * 新建链表,使用尾插法将原两链表中较小的插入到新的链表中
     */
    public SinglyLinkedList merge(SinglyLinkedList list) {
        SinglyLinkedList newList=new SinglyLinkedList();
        Node  p;
        Node  q;
        while (this.head!=null&&list.head!=null){
            p=this.head;
            q=list.head;
            if(p.getData()<q.getData()){
                newList.addNodeToTail(this.deleteHead());
            }else {
                newList.addNodeToTail(list.deleteHead());
            }
        }
        while (this.head!=null){
            newList.addNodeToTail(this.deleteHead());
        }
        while (list.head!=null){
            newList.addNodeToTail(list.deleteHead());
        }
        return newList;
    }

    /**
     * 删除链表倒数第 n 个结点
     *  单链表要想一次遍历定位某个节点,使用快慢指针,这里快指针先走n+1步,然后快慢指针一起走,当快指针为空时,慢指针始终比快指针少n+1步
     *  刚好指向待删除节点的前一个节点,考虑到可能头节点没有前一个节点,这里需要新建一个节点指向头节点,快慢指针从新节点开始走,
     *  这样就避免了删除头节点时无前一个节点的问题,直接返回头节点 preNode.next
     *  如没有返回值需要改变头节点指针 head=preNode.next.next
     */
    public void deleteNode(int index) {
        Node preNode=new Node(-1,head);
        Node slow=preNode;
        Node fast=preNode;
        for(int i=0;i<=index;i++){
            fast=fast.getNext();
        }
        if(fast==null){
            head=slow.getNext().getNext();
        }
        while (fast!=null){
            fast=fast.getNext();
            slow=slow.getNext();
        }
        slow.setNext(slow.getNext().getNext());
    }

}
