package blind75;

import datastructures.linkedlist.ListNode;

public class RemoveNthCharFromEndOfLinkedList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /* create a dummy node */
        ListNode dummy = new ListNode(0, head);

        /* initialize left and right ptrs */
        ListNode leftPtr = dummy;
        ListNode rightPtr = head;
        int moveRightBy = n;
        while(moveRightBy > 0){
            rightPtr = rightPtr.next;
            moveRightBy--;
        }

        while(rightPtr != null){
            System.out.println(rightPtr.val);
            leftPtr = leftPtr.next;
            rightPtr = rightPtr.next;
        }

        /* delete */
        leftPtr.next = leftPtr.next.next;

        return dummy.next;
    }
}
