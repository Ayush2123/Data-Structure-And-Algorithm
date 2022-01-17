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

    public static void displayDLL(TreeNode head){
        if(head==null){
            return;
        }

        TreeNode curr = head;
        while(curr!=null){
            System.out.print(curr.val + " ");
            curr = curr.right;
        }

        System.out.println();
    }

    public static TreeNode getRightMost(TreeNode head, TreeNode node){
        while(node.right!=null && node.right!=head){
            node = node.right;
        }

        return node;
    }

    public static TreeNode bstToDLL(TreeNode root){
        if(root==null || (root.left==null && root.right==null)){
            return root;
        }

        TreeNode dummy = new TreeNode(-1), curr = root, prev = dummy;

        while(curr!=null){
            TreeNode left = curr.left;
            if(left==null){
                prev.right = curr;
                curr.left = prev;

                prev = prev.right;
                curr = curr.right;
            }else{
                TreeNode rightMost = getRightMost(curr,left);
                if(rightMost.right==null){
                    rightMost.right = curr;
                    curr = curr.left;
                }else{
                    rightMost.right = null;
                    prev.right = curr;
                    curr.left = prev;

                    prev = prev.right;
                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        return head;
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
        TreeNode head = bstToDLL(root);
        displayDLL(head);
    }
}
