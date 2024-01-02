package blind75;

import datastructures.linkedlist.ListNode;

public class MergeSortedLinkedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode i = list1;
        ListNode j = list2;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(i != null && j != null){
            System.out.println("ival" + i.val +" and jval: " + j.val);
            if(i.val <= j.val){
                current.next = new ListNode(i.val);
                i = i.next;
                current = current.next;
            } else {
                current.next = new ListNode(j.val);
                j = j.next;
                current = current.next;
            }
        }
        while(i != null){
            current.next = new ListNode(i.val);
            i = i.next;
            current = current.next;
        }

        while(j != null){
            current.next = new ListNode(j.val);
            j = j.next;
            current = current.next;
        }
        return dummy.next;
    }
}
