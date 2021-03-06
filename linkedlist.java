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
    /***********Q2. Reverse Node of the linked list in groups of size k*****************/
    public static ListNode reverseInGroups(ListNode head, int k){
        //Firstly, Calculate the original length of the linked list
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }

        //create a dummy node so as to keep a reference to new head,
        // as our main head will change after reversing the nodes in groups
        ListNode dummy = new ListNode(-1);  
        dummy.next = head;

        // We also need a prev node reference as we need references of prev nodes while reversing
        //(refer to the reverseListIteratievly code segment)
        ListNode prev = dummy;

        //Keep iterating while our length is greater than the given group size
        while(len >= k){
            ListNode curr = prev.next;  //For each group, our prev pointer's next will point to curr
            ListNode next = curr.next; 

            // Iteratively work on each group
            for(int i = 1; i < k; i++){
                //make curr pointer point to the next's next every time
                //so by the last iteration, our curr's next will point to the next group's head(original)
                curr.next = next.next;  
                //make next's next point to the prev's next
                //(our prev's next pointer points to the node which was immediately before the next node)
                next.next = prev.next;
                //now in the end we set the prev's next to next 
                prev.next = next;
                //and we update our next to the curr's next
                next = curr.next;
            }
            //after we are done with the work on the group, we set our prev to curr
            //as after each group work, curr will become the last node of that particular group
            prev = curr;
            //and at last subtract k from length
            len -= k;
        }

        if(len == 0)    //if we have exhauted the entire linkedlist length, we return the dummy's next
            return dummy.next;

        //NOTE: If you are solving this problem on leetcode, then you need not to reverse the remaining list
        // But for those who are solving it on GFG, you need to reverse the remaining linkedlist using a simple
        // reversing the list code segment
        prev.next = reverseLinkedListIteratively(prev.next);
        return dummy.next;

        //Please NOTE: If you had a hard time understanding this code segment
        //refer to this video resource: https://www.youtube.com/watch?v=Of0HPkk3JgI

        //TIME: O(n/k)*O(k) = O(n)
        //SPACE: O(1)
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
        ListNode prev = slow;
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
            return null;
        return slow;

        //Time Complexity : O(N)
        //Space Complexity: O(1)
    }

    /***********Q6. Program for Remove Duplicates in a sorted Linked List*****************/
    public static ListNode removeDuplicatesFromSortedLL(ListNode head){
        ListNode temp = head;
        while(temp != null){
            //Create a ptr which will keep moving until a new number is encountered
            ListNode dupPtr = temp.next;
            while(dupPtr != null && dupPtr.val == temp.val)
                dupPtr = dupPtr.next;
            // After the iteration is over, we set our temp's next pointer to dupPtr
            //which will be either null or will be pointing to a new number
            temp.next = dupPtr;
            temp = temp.next;
        }
        return head;
        //Please Note: It is highly advised that you should not distort the given input
        //untill and unless you are asked to do so.
        //You could create a separate linkedlist whose head you can return as your answer

        //Time Complexity : O(N)
        //Space Complexity: O(1)
    }

    /***********Q6. Program for Remove Duplicates in a unsorted Linked List*****************/
    public static ListNode removeDuplicatesFromUnSortedLL(ListNode head){
        //This question is similar to the previous one. But here the list is not sorted
        //Thus, we need to maintain some sort of a visited element space so that we are 
        //aware when we encounter a duplicate. Here, since we need fast accessing and we
        //do not want to store duplicates, A hashset seems the best fit for storing visited elem

        Set<Integer> visited = new HashSet<>();
        ListNode temp = head;
        visited.add(temp.val);
        while(temp != null){
            //Create a ptr which will keep moving until a new number is encountered
            ListNode dupPtr = temp.next;
            while(dupPtr != null && visited.contains(dupPtr.val))
                dupPtr = dupPtr.next;
            // After the iteration is over, we set our temp's next pointer to dupPtr
            //which will be either null or will be pointing to a new number
            temp.next = dupPtr;
            temp = temp.next;
            if(temp != null)
                visited.add(temp.val);
        }

        return head;
        //Please Note: It is highly advised that you should not distort the given input
        //until and unless you are asked to do so.
        //You could create a separate linkedlist whose head you can return as your answer

        //Time Complexity : O(N)
        //Space Complexity: O(N)
    }
}