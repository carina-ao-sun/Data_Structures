package hw7;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class MultiChildTreeNode<AnyType> extends DoublyLinkedList{
    AnyType element;
    DoublyLinkedList<MultiChildTreeNode<AnyType>> children;
    int distance;

    MultiChildTreeNode(AnyType theElement, int dist) {
        element = theElement;
        children = new DoublyLinkedList<>();
        distance = dist;
    }

    void addChild(MultiChildTreeNode<AnyType> child) {
        children.addLast(child);
    }


    public static int findClosest(MultiChildTreeNode<String> root, String target) {
        if (root == null) return 0;
    
        PriorityQueue<MultiChildTreeNode<String>> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
        root.distance = 0;
        heap.add(root);
    
        while (!heap.isEmpty()) {
            MultiChildTreeNode<String> current = heap.poll();
    
            if (current.element.equals(target)) {
                return current.distance;
            }
    
            for (MultiChildTreeNode<String> child : current.children) {
                child.distance = current.distance + child.distance;
                heap.add(child);
                
            }
        
        }
        return 0; 
    }

    /*public static MultiChildTreeNode<String> buildTree(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        ArrayStack<MultiChildTreeNode<String>> stack = new ArrayStack<>();
        MultiChildTreeNode<String> currentParent = null;

        while (scanner.hasNext()) {
            String token = scanner.next();

            if (token.equals("(")) {
                stack.push(currentParent);
            } else if (token.equals(")")) {
                currentParent = stack.pop();
            } else {
                String label = token;
                int distance = scanner.nextInt();
                MultiChildTreeNode<String> newNode = new MultiChildTreeNode<>(label, distance);
                if (currentParent != null) {
                    currentParent.addChild(newNode);
                }
                currentParent = newNode;
            }
        }

        scanner.close();
        return stack.pop(); 
    }*/

    public static MultiChildTreeNode<String> buildTree(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        ArrayStack<MultiChildTreeNode<String>> stack = new ArrayStack<>();
        MultiChildTreeNode<String> currentParent = null;
        MultiChildTreeNode<String> root = null;
    
        while (scanner.hasNext()) {
            String token = scanner.next();
            System.out.println("Processing token: " + token); 
    
            if (token.equals("(")) {
                stack.push(currentParent);
                currentParent = null;
            } else if (token.equals(")")) {
                if (!stack.isEmpty()) {
                    MultiChildTreeNode<String> parent = stack.pop();
                    if (parent != null) {
                        parent.addChild(currentParent);
                        currentParent = parent;
                    }
                }
            } else {
                String label = token;
                int distance = scanner.nextInt();
                MultiChildTreeNode<String> newNode = new MultiChildTreeNode<>(label, distance);
                System.out.println("Created node: " + label + " with distance " + distance); 
                if (currentParent != null) {
                    currentParent.addChild(newNode);
                    System.out.println("Adding " + label + " as a child of " + currentParent.element); 
                    root = newNode; // Set root if currentParent is null
                }
                currentParent = newNode;
            }
        }
    
        scanner.close();
        System.out.println("Root of the tree: " + (root != null ? root.element : "null")); 
        return root;
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        String target = "*";
        
        MultiChildTreeNode<String> root = buildTree(filename);
        int closestDistance = findClosest((MultiChildTreeNode<String>) root, target);
        if (closestDistance != -1) {
            System.out.println("Found \"*\" at distance " + closestDistance + ".");
        } else {
            System.out.println("\"*\" not foundddd.");
        }
        
    }





    
}


