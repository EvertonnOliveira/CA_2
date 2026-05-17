/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTree.java
 * Represents the Bank Organisation hierarchy as a Binary Tree.
 *
 * INSERTION: Level-Order (breadth-first)
 *   - Fills left child first, then right child
 *   - When both children are taken, moves to next node in level order
 *   - Guarantees a balanced tree structure
 *
 * Example with 7 nodes:
 *         Alex          ← level 0 (root)
 *        /    \
 *      Ben    Carol     ← level 1
 *     / \    /   \
 *  David Elena Finn George  ← level 2
 *
 * Each node stores: Name, Manager Type, Department
 */
public class BinaryTree {

    // ================================================================
    //  NODE — inner class representing each employee in the tree
    // ================================================================
    private static class Node {
        String name;         // employee full name
        String managerType;  // Senior Manager, Manager, Junior Manager
        String department;   // Finance, HR, IT, Marketing, Sales

        Node left;           // left child
        Node right;          // right child

        // Node constructor
        Node(String name, String managerType, String department) {
            this.name        = name;
            this.managerType = managerType;
            this.department  = department;
            this.left        = null;
            this.right       = null;
        }
    }

    // ================================================================
    //  FIELDS
    // ================================================================
    private Node root;  // top of the tree (first employee inserted)

    // ================================================================
    //  CONSTRUCTOR
    // ================================================================
    public BinaryTree() {
        this.root = null; // empty tree at start
    }

    // ================================================================
    //  INSERT — Level-Order (breadth-first) insertion
    //
    //  HOW IT WORKS:
    //  - Use a Queue to track nodes that still have empty children
    //  - For each new employee:
    //    1. If tree is empty → becomes root
    //    2. Otherwise → find first node with empty left or right child
    //    3. Fill left first, then right
    //    4. When both filled → remove from queue, move to next node
    // ================================================================

    /**
     * Inserts a new employee into the tree using Level-Order insertion.
     *
     * @param name        employee full name
     * @param managerType manager type label
     * @param department  department label
     */
    public void insert(String name, String managerType, String department) {

        // Create the new node
        Node newNode = new Node(name, managerType, department);

        // If tree is empty, new node becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        // Use a Queue for level-order traversal to find insertion point
        // Queue processes nodes left to right, level by level
        Queue<Node> queue = new LinkedList<>();
        queue.add(root); // start from root

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // get next node in queue

            // Try to insert as LEFT child first
            if (current.left == null) {
                current.left = newNode; // insert here
                return;
            } else {
                queue.add(current.left); // left is taken, add to queue
            }

            // Try to insert as RIGHT child
            if (current.right == null) {
                current.right = newNode; // insert here
                return;
            } else {
                queue.add(current.right); // right is taken, add to queue
            }
        }
    }

    /**
     * Builds the tree from the employee list loaded from CSV.
     * Uses the first 20+ employees automatically.
     *
     * @param employees the list of employees from the CSV file
     */
    public void buildFromList(List<Employee> employees) {
        // Insert all employees from the list
        for (Employee e : employees) {
            insert(e.getFullName(), e.getPosition(), e.getDepartment());
        }
    }

    // ================================================================
    //  DISPLAY — Level-Order Traversal
    //
    //  Shows the tree hierarchy level by level, left to right.
    //  Same Queue approach as insertion.
    // ================================================================

    /**
     * Displays the full tree hierarchy using Level-Order traversal.
     * Shows level number, name, manager type and department for each node.
     */
    public void displayLevelOrder() {

        if (root == null) {
            System.out.println("[EMPTY] The tree has no employees.");
            return;
        }

        System.out.println("\n========= Employee Hierarchy (Binary Tree) =========");
        System.out.printf("%-6s %-24s %-22s %-15s%n",
                "Level", "Full Name", "Manager Type", "Department");
        System.out.println("-".repeat(70));

        // Queue stores pairs of (node, level)
        Queue<Node> nodeQueue  = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();

        nodeQueue.add(root);
        levelQueue.add(0); // root is at level 0

        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            int level    = levelQueue.poll();

            // Print current node
            System.out.printf("%-6d %-24s %-22s %-15s%n",
                    level,
                    current.name,
                    current.managerType,
                    current.department);

            // Add children to queue with incremented level
            if (current.left != null) {
                nodeQueue.add(current.left);
                levelQueue.add(level + 1);
            }
            if (current.right != null) {
                nodeQueue.add(current.right);
                levelQueue.add(level + 1);
            }
        }

        System.out.println("-".repeat(70));
    }

    // ================================================================
    //  HEIGHT — recursive calculation
    //
    //  Height = number of edges on longest path from root to a leaf
    //  Base case: null node → height = -1
    //  Recursive: 1 + max(leftHeight, rightHeight)
    // ================================================================

    /**
     * Returns the height of the tree.
     * Called publicly to display the result.
     */
    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * PRIVATE recursive method to calculate tree height.
     *
     * @param node current node being evaluated
     * @return     height from this node downwards
     */
    private int calculateHeight(Node node) {

        // Base case: empty node has height -1
        if (node == null) {
            return -1;
        }

        // Recursively get height of left and right subtrees
        int leftHeight  = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Height of current node = 1 + tallest subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // ================================================================
    //  NODE COUNT — recursive calculation
    //
    //  Base case: null node → count = 0
    //  Recursive: 1 + leftCount + rightCount
    // ================================================================

    /**
     * Returns the total number of nodes in the tree.
     */
    public int getNodeCount() {
        return countNodes(root);
    }

    /**
     * PRIVATE recursive method to count all nodes.
     *
     * @param node current node being counted
     * @return     total nodes from this node downwards
     */
    private int countNodes(Node node) {

        // Base case: empty node contributes 0
        if (node == null) {
            return 0;
        }

        // Count this node (1) + all left nodes + all right nodes
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // ================================================================
    //  DISPLAY SUMMARY — called from Main
    // ================================================================

    /**
     * Displays the full tree, height and node count.
     * This is the main method called from Main.java.
     *
     * @param employees the employee list from CSV
     */
    public static void displayHierarchy(List<Employee> employees) {

        System.out.println("\nBuilding Binary Tree from " + employees.size() + " employees...");

        // Create and build the tree
        BinaryTree tree = new BinaryTree();
        tree.buildFromList(employees);

        // Display level-order traversal
        tree.displayLevelOrder();

        // Display summary stats
        System.out.println("\n========= Tree Statistics =========");
        System.out.println("Tree Height : " + tree.getHeight() + " levels");
        System.out.println("Total Nodes : " + tree.getNodeCount() + " employees");
        System.out.println("===================================");
    }
}
