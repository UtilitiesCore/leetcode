package leetcode.arraylist;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class N086Partition {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(2);
        listNode1.next.next.next.next = new ListNode(5);
        listNode1.next.next.next.next.next = new ListNode(2);
        System.out.println(new N086Partition().partition(listNode1,3));
    }
    /*
    申请两个新链表more和less。more用来存储>=界定值的值。less用来存储小于界定值的值。
    然后将less的next节点指向more的头部。

     */

    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode more = new ListNode(-1);
        ListNode moreHead = more;
        ListNode less = new ListNode(-1);
        ListNode lessHead = less;

        while (head != null){
            if(head.val < x){
                less.next = head;
                less = less.next;
            }else {
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        more.next = null;  //这步不要忘记，不然链表就出现环了。为啥会出现环？
        //比如1->4->3->2->5->2  以3为界限。最后到5的时候，将5->2加入moreHead 4->3，moreHead变为4->3->5->2。此时more为5->2
        //如果不将more的next置位null。这个2将连接到less上，形成环。可以打断点验证一下。


        less.next = moreHead.next;
        return lessHead.next;

    }
}
