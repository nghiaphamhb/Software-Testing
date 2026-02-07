package itmo.lab1;

/**
 * Skew Heap implementation
 * Min-heap
 * Methods: merge, insert, removeMin
 */
public class SkewHeap 
{
    private Node root;

    public SkewHeap() {
        this.root = null;
    }

    public SkewHeap(int key){
        this.root = new Node(key);
    }

    public Node root(){
        return root;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public static Node merge(Node a, Node b, Tracer t){
        // P1: a == null
        if(a == null) {
            if(t != null) t.hit("P1");
            return b;
        }

        // P2: b == null
        if (b == null) {
            if(t != null) t.hit("P2");
            return a;
        }

        // P3: root swap
        if (a.key > b.key) {
            if(t != null) t.hit("P3");
            Node temp = a;
            a = b;
            b = temp;
        } else {
            if(t != null) t.hit("P3N");
        }

        // P4: recursive merge to the RIGHT
        if(t != null) t.hit("P4");
        a.right = merge(a.right, b, t);

        // P5: swap children (the "skew" step)
        if(t != null) t.hit("P5");
        Node temp = a.left;
        a.left = a.right;
        a.right = temp;

        // P6: return
        if (t != null) t.hit("P6");
        return a;
    }

    public void insert(int key, Tracer t){
        Node x = new Node(key);
        root = merge(root, x, t);
    }

    public int removeMin(Tracer t) {
        if (root == null) throw new IllegalStateException("heap is empty");
        int min = root.key;
        root = merge(root.left, root.right, t);
        return min;
    }
}
