import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class linkedlist{
	public static void main(String[] args) {
		//DSA 450 Sheet Link: https://drive.google.com/file/d/1FMdN_OCfOI0iAeDlqswCiC2DZzD4nPsb/view
		//This Solution Set is provided by Sparsh Sharma, GitHub: https://github.com/sparshsharma2510
		//If you find this useful in any way, Don't forget to star this repository
		//Also, if you find any errors/bugs, try rasing an issue and feel free to contribute
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

		//Time Complexity: O(n)
        //Space Complexity: O(1)
	}

	public static ListNode reverseLinkedListRecursively(ListNode head){
		//Make a call to the recursive function
		return revList(null, head);

		//Time Complexity: O(n)
        //Space Complexity: O(n){call stack space will be O(n) as at max, we make 'n' recursive calls}
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
	public static boolean detectLoop(ListNode head){
        ListNode slow = head, fast = head;	//Initialise the slow and the fast pointer
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;	//move slow by one node
            fast = fast.next.next;	//move fast by two nodes
            if(fast == slow){	
	            //if at any instance, our fast became equal to slow, this means, we can
	            //gurantee that there must be a loop in the linkedist, as we were moving
	            //fast by 2 nodes and slow by one and the only way the two pointers could become
	            //equal was if the list had a loop
                return true;
            }
        }
        //If we reach here, this means that fast never became equal to slow, thus we return false
        return false;

        //Time Complexity: O(n)
        //Space Complexity: O(1)
    }

    /***********Q4. Program for deleting a loop in the linked list*****************/
    public static void removeLoop(ListNode head){
    	//This is also known as loop detection algo
        ListNode slow = head, fast = head;
        ListNode prev = slow;

        while(fast.next != null && fast.next.next != null){	//iterate over the list till over fast pointer reaches the end or becomes equal to slow
            prev = slow;	//keep a track of prev node reference of slow
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        if(fast != slow)
            return;
        if(slow == head){	
        //this means its a circular linkedlist, so directly do prev.next = null
        //as prev pointed to the last node of the list
            prev.next = null;
            return;
        }

        //Otherwise place slow pointer in the start and move both pointers by one until they meet
        prev = fast;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        //It is guranteed that they will always meet at the starting point of the loop if any in all circumstances
        //To know more about this algo and why will they meet at the starting point
        //Refer to this youtube link
        //https://www.youtube.com/watch?v=-YiQZi3mLq0
        prev.next = null;

        //Time Complexity : O(N)
        //Space Complexity: O(1)
    }

    /***********Q5. Program for finding the start of a loop in the linked list*****************/
    public static ListNode findStartNode(ListNode head){
    	ListNode slow = head, fast = head;

    	while(fast.next != null && fast.next.next != null){	//iterate over the list till over fast pointer reaches the end or becomes equal to slow
            prev = slow;	//keep a track of prev node reference of slow
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        //It is guranteed that they will always meet at the starting point of the loop if any in all circumstances
        //To know more about this algo and why will they meet at the starting point
        //Refer to this youtube link
        //https://www.youtube.com/watch?v=-YiQZi3mLq0
        if(fast != slow)
            return;
        return slow;

        //Time Complexity : O(N)
        //Space Complexity: O(1)
    }
}