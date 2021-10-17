import java.util.*;

public class Backtracking{
	public static void main(String[] args) {
		
	}

	/******************** Question 1: Rat in a maze Problem ***********************/
	private static void getPaths(int i, int j, int[][] mat, int n, ArrayList<String> ans, String ds, boolean[][] visited){
        // If we reach the bottom right, we can add out string in our ans
        if(i == n-1 && j == n-1 && mat[i][j] == 1){
            ans.add(ds);
            return;
        }
        else if(i < 0 || j < 0 || i >= n || j >= n || visited[i][j] || mat[i][j] == 0)
            return;
        // Mark the current index as visited
        visited[i][j] = true;
        // go DOWN
        getPaths(i+1, j, mat, n, ans, ds+"D", visited);
        // go LEFT
        getPaths(i, j-1, mat, n, ans, ds+"L", visited);
        // go RIGHT
        getPaths(i, j+1, mat, n, ans, ds+"R", visited);
        // go UP
        getPaths(i-1, j, mat, n, ans, ds+"U", visited);
        // BACKTRACK
        visited[i][j] = false;
        return;
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> ans = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        getPaths(0,0,m,n,ans,"",visited);
        return ans;

        // Time : Since at each index, we try out all the possible 4 ways, Thus, the worst case time complexity will be O((n^2)^4)
        // Space: As at max, there will be n^2 recursive calls in the call stack thus, the worst case space complexity will be O(n^2)

        /*
        	Worst case space complexity example: 
			1 1 1 1 1
			1 1 1 1 1
			1 1 1 1 1
			1 1 1 1 1
			1 1 1 1 0
        */
    }
}