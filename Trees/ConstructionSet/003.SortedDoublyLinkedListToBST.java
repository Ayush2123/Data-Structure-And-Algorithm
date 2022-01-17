import java.util.*;
public class Main {

    static Scanner scn = new Scanner(System.in);

    public static class TreeNode{
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode getMidNode(TreeNode head){
        if(head==null || head.right==null){
            return head;
        }

        TreeNode fast = head, slow = head;
        while(fast.right!=null && fast.right.right!=null){
            fast = fast.right.right;
            slow = slow.right;
        }

        return slow;
    }

    public static TreeNode DllToBST(TreeNode head){
        if(head==null || head.right==null){
            return head;
        }

        TreeNode mid = getMidNode(head);
        TreeNode prev = mid.left, forw = mid.right;

        mid.right = forw.left = mid.left = null;
        if(prev!=null){
            prev.right = null;
        }

        TreeNode lHead = prev!=null? head: null, rHead = forw, root = new TreeNode(mid.val);
        root.left = DllToBST(lHead);
        root.right = DllToBST(rHead);

        return root;
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

    public static TreeNode makeList(int n) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        while (n-- > 0) {
            TreeNode node = new TreeNode(scn.nextInt());
            prev.right = node;
            node.left = prev;
            prev = prev.right;
        }

        TreeNode head = dummy.right;
        head.left = dummy.right = null;

        return head;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        TreeNode head = makeList(n);

        TreeNode root = DllToBST(head);
        display(root);
    }
}
