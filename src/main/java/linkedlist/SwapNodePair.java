package linkedlist;

/*
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the
 values in the list's nodes (i.e., only nodes themselves may be changed.)

 Input: head = [1,2,3,4]

Output: [2,1,4,3]

    https://leetcode.com/problems/swap-nodes-in-pairs/description/
 */



public class SwapNodePair {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0, head);
        ListNode curr = head;
        ListNode prev = node;
        while(curr != null && curr.next != null){
            ListNode nxt = curr.next.next;
            ListNode second = curr.next;
            second.next = curr;
            curr.next = nxt;
            prev.next = second;
            prev = curr;
            curr = nxt;
        }
        return node.next;
    }
}
