package hw7;
import java.io.File;
import java.io.FileNotFoundException;
// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of package DataStructures
import java.util.PriorityQueue;
import java.util.Scanner;
class TreeNode<AnyType> implements Comparable<TreeNode<AnyType>> {
    AnyType element; // The data in the node
    TreeNode<AnyType> left;      // Left child
    TreeNode<AnyType> right;     // Right child
    int distance;                


    TreeNode(AnyType theElement) {
        this(theElement, null, null, 0);
    }

    TreeNode(AnyType theElement, TreeNode<AnyType> lt, TreeNode<AnyType> rt, int dist) {
        element = theElement;
        left = lt;
        right = rt;
        distance = dist;
    }

  
    @Override
    public int compareTo(TreeNode<AnyType> other) {
        return Integer.compare(this.distance, other.distance);
    }



    public static int findClosest(TreeNode<String> root, Object target) {
        if (root == null) return 0;

        PriorityQueue<TreeNode<String>> heap = new PriorityQueue<>((a, b) -> Double.compare(a.distance, b.distance));
        root.distance = 0;
        heap.add(root);

        while (!heap.isEmpty()) {
            TreeNode<String>current = heap.poll();

            if (current.element.equals(target)) {
                return current.distance;
            }

            if (current.left != null) {
                current.left.distance = current.distance + current.left.distance;
                heap.add(current.left);
            }

            if (current.right != null) {
                current.right.distance = current.distance + current.right.distance;
                heap.add(current.right);
            }
        }
        return -1; 
    }

    public static TreeNode<String> buildTree(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        ArrayStack<TreeNode<String>> stack = new ArrayStack<>();
    
        while (scanner.hasNext()) {
            String temp = scanner.next();

    
            if (temp.equals("(")) {
                continue;
            } else if (temp.equals(")")) {
                TreeNode<String> rightChild = stack.pop();
                TreeNode<String> leftChild = stack.pop();
                TreeNode<String> parent = stack.pop();
                parent.left = leftChild;
                parent.right = rightChild;
                stack.push(parent);
            } else {
                String label = temp;
                int distance = scanner.nextInt();
                stack.push(new TreeNode<>(label, null, null, distance));
            }
        }
    
        scanner.close();
        return stack.pop();
    }
     
    

    public static void main(String[] args) throws FileNotFoundException {
        
        String filename = args[0];
        String target = "*";

        TreeNode<String> root = buildTree(filename);
        int closestDistance = findClosest((TreeNode<String>) root, target);
        if (closestDistance != -1) {
            System.out.println("Found \"*\" at distance " + closestDistance + ".");
        } else {
            System.out.println("\"*\" not found.");
        }
    }


}

