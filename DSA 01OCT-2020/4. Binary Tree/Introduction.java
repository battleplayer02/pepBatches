import java.util.*;
public class Introduction {
    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            left = right = null;
        }
        Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static class ConstructPair{
        Node node;
        int state;
        ConstructPair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }
    public static Node construct(Integer input[]){
        Stack<ConstructPair> st = new Stack<>();
        Node root = new Node(input[0],null,null);

        st.push(new ConstructPair(root, 1));
        int idx = 1;
        while(st.size() > 0){
            ConstructPair top = st.peek();

            if(top.state == 1){
                // left child
                Integer ele = input[idx]; // next element
                if(ele != null){
                    Node leftNode = new Node(ele);
                    top.node.left = leftNode;
                    st.push(new ConstructPair(leftNode, 1));
                }
                top.state++;
                idx++;
            }else if(top.state == 2){
                // right child
                Integer ele = input[idx]; // next element
                if(ele != null){
                    Node rightNode = new Node(ele);
                    top.node.right = rightNode;
                    st.push(new ConstructPair(rightNode, 1));
                }
                top.state++;
                idx++;
            }else {
                // if(top.state == 3) : left & right both have been processed
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        // my work
        String str = "";
        str += (node.left != null) ? node.left.data : "."; // for left 
        str += " <- "+node.data+" -> "; // for self
        str += (node.right != null) ? node.right.data : "."; // for right
        System.out.println(str);
        
        display(node.left);
        display(node.right);
    }
    
    public static int size(Node node) {
        if(node == null){
            return 0;
        }
        int size = 0;
        size += size(node.left); // count of nodes from left sub-tree
        size += size(node.right); // count of nodes from right sub-tree
        size += 1; // for myself
        
        return size;
    }

    public static int sum(Node node) {
        if(node == null){
            return 0;
        }
        int sum = 0;
        sum += sum(node.left); // sum of all nodes belonging to left subtree
        sum += sum(node.right); // sum of all nodes belonging to right subtree
        sum += node.data; // add myself
        
        return sum;
    }

    public static int max(Node node) {
        if(node == null){
            return Integer.MIN_VALUE;
        }
        int lmax = max(node.left);
        int rmax = max(node.right);
        int ans = Math.max(Math.max(lmax,rmax),node.data);
        
        return ans;
    }

    public static int height(Node node) {
        if(node == null){
            // return 0; // height in terms of number of nodes
            return -1; // height in terms of number of edges
        }
        int lht = height(node.left);
        int rht = height(node.right);
        int ht = Math.max(lht,rht)+1;
        
        return ht;
    }

    public static boolean find(Node node, int data) {
        if (node == null) {
            return false;
        }

        if (node.data == data) {
            return true;
        }

        boolean resLeft = find(node.left, data);
        if (resLeft) {
            return true;
        }

        boolean resRight = find(node.right, data);
        if (resRight) {
            return true;
        }

        // implication : data not found
        return false;
    }

    public static ArrayList < Integer > nodeToRootPath(Node node, int data) {
        if (node == null) {
            // invalid pos
            return new ArrayList < > ();
        }

        if (node.data == data) {
            // element found
            ArrayList < Integer > list = new ArrayList < > ();
            list.add(node.data);
            return list;
        }

        ArrayList < Integer > resLeft = nodeToRootPath(node.left, data);
        if (resLeft.size() > 0) {
            // element has been found
            resLeft.add(node.data);
            return resLeft;
        }

        ArrayList < Integer > resRight = nodeToRootPath(node.right, data);
        if (resRight.size() > 0) {
            // element has been found
            resRight.add(node.data);
            return resRight;
        }

        // implication : data not found
        return new ArrayList < > ();
    }

    public static void levelOrder(Node root) {
        Queue<Node> mainQ = new ArrayDeque<>();
        Queue<Node> helperQ = new ArrayDeque<>();
        
        // initial step
        mainQ.add(root);
        
        while(mainQ.size() > 0){
            // remove
            Node tmp = mainQ.remove();
            
            // print
            System.out.print(tmp.data+" ");
            
            // add
            if(tmp.left != null){
                helperQ.add(tmp.left);
            }
            if(tmp.right != null){
                helperQ.add(tmp.right);
            }
            
            if(mainQ.size() == 0){
                System.out.println();
                
                // ref. swap
                Queue<Node> tempQ = mainQ;
                mainQ = helperQ;
                helperQ = tempQ;
            }
        }
    }

    public static void preTraversal(Node node){
        if(node == null){
            return;
        }
        // pre
        System.out.print(node.data+" ");
        preTraversal(node.left);
        preTraversal(node.right);
    }

    public static void postTraversal(Node node){
        if(node == null){
            return;
        }
        
        postTraversal(node.left);
        postTraversal(node.right);
        System.out.print(node.data+" ");
    }

    public static void inTraversal(Node node){
        if(node == null){
            return;
        }
        
        inTraversal(node.left);
        System.out.print(node.data+" ");
        inTraversal(node.right);
    }

    public static void main(String[] args) {
        Integer input[] = {10 , 20 , 40 , null , null , 50 , 60 , null , null , 70 , null ,null, 30 , null , 80 , null , null};
        System.out.println(input[3]);
        Node root = construct(input);

        System.out.print("in:");
        inTraversal(root);
        System.out.println(".");

        System.out.print("pre:");
        preTraversal(root);
        System.out.println(".");

        System.out.print("post:");
        postTraversal(root);
        System.out.println(".");
    }
}
