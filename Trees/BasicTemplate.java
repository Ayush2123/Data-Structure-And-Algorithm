import java.util.*;
public class Main {

    public static class TreeNode{
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static void display(TreeNode root){
        if(root==null){
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(root.left!=null? root.left.val: ".");
        sb.append(" -> ").append(root.val).append(" -> ");
        sb.append(root.right!=null? root.right.val: ".");

        System.out.println(sb.toString());

        display(root.left);
        display(root.right);
    }

    public static TreeNode constructTree(int []arr, int si, int ei){
        if(si>ei){
            return null;
        }

        int mid = (si + ei) / 2;

        TreeNode root = new TreeNode(arr[mid]);

        root.left = constructTree(arr,si,mid-1);
        root.right = constructTree(arr,mid+1,ei);

        return root;
    }

    public static TreeNode constructTree(int []arr){
        return constructTree(arr,0, arr.length-1);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int []arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = scn.nextInt();
        }

        TreeNode root = constructTree(arr);
        display(root);
    }
}
