import java.util.*;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class linkedlist{
	public static void main(String[] args) {
		//This entire Solutions Set is provided by Sparsh Sharma, GitHub: https://github.com/sparshsharma2510
		//If you find this useful in any way, Don't forget to star this repository
		//Also, if you find any errors, try rasing an issue and feel free to contribute
	}
	/***********Q1. Program for reversing the linked list*****************/
	public static ListNode reverseLinkedListIteratively(ListNode head){
		ListNode prev = null;	//this will be the reference to previous node
		ListNode curr = head;	//this is the current node pointer

		while(curr != null){	//iterate till the end of the list
			//store the next reference, because this will be broken for the current node
			//and will be attached to the prev reference
			ListNode next = curr.next;

			curr.next = prev;	//attach the next pointer to the previous one
			prev = curr;	//set the prev to curr node
			curr = next;	//set the curr node to next
			//(NOTE: Here, curr.next is not equal to next, beacuse we changed the reference and setted to prev)
		}

		return prev;
	}

	public static ListNode reverseLinkedListRecursively(ListNode head){
		//Make a call to the recursive function
		return revList(null, head);
	}

	public static ListNode revList(ListNode prev, ListNode curr){
		//Once you understand the iterative version, recursive will be cakewalk
		if(curr == null)
			return prev;
		//firstly store the next pointer
		ListNode next = curr.next;
		//Now since we have the access to the next pointer, we can now break it
		//and attach it to the prev node
		curr.next = prev;
		//now recur over the remaning list and pass curr node as prev and next as curr
		return revList(curr,next);
	}

	/***********Q3. Program for detecting a loop in the linked list*****************/
	public static boolean detectLoop(Node head){
        // Add code here
        Node slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow)
                return true;
        }
        return false;
    }
}