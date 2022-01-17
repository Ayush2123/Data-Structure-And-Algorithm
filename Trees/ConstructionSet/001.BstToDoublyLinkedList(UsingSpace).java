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
        TreeNode curr = head;
        while(curr!=null){
            System.out.print(curr.val + " ");
            curr = curr.right;
        }

        System.out.println();
    }

    public static void insertAllLeft(TreeNode node, LinkedList<TreeNode>st){
        while(node!=null){
            st.addFirst(node);
            node = node.left;
        }
    }

    public static TreeNode bstToDll(TreeNode root){
        if(root==null || (root.left==null && root.right==null)){
            return root;
        }

        TreeNode dummy = new TreeNode(-1), prev = dummy;
        LinkedList<TreeNode> st = new LinkedList<>();

        insertAllLeft(root,st);

        while(st.size()>0){
            TreeNode curr = st.removeFirst();
            prev.right = curr;
            curr.left = prev;

            prev = prev.right;

            insertAllLeft(curr.right,st);
        }

        TreeNode head = dummy.right;
        head.left = dummy.right = null;
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
        TreeNode head = bstToDll(root);
        displayDLL(head);
    }
}
