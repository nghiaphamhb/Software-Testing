package itmo.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SkewHeapTest {
    private Tracer tr;

    @BeforeEach
    public void setUp(){
        tr = new Tracer();
    }

    @Test
    @DisplayName("merge(null, node) (hits P1) 🧪 ")
    void mergeNullLeft(){
        SkewHeap sh = new SkewHeap(10);
        Node b = sh.root();
        Node result = SkewHeap.merge(null, b, tr);

        assertSame(b, result);
        assertEquals(List.of("P1"), tr.steps());
    }

    @Test 
    @DisplayName("merge(node, null) (hits P2) 🧪 ")
    void mergeNullRight(){
        SkewHeap sh = new SkewHeap(10);
        Node b = sh.root();
        Node result = SkewHeap.merge(b, null, tr);

        assertSame(b, result);
        assertEquals(List.of("P2"), tr.steps());
    } 

    @Test
    @DisplayName("merge keeps root when a.key <= b.key (hits P3N) 🧪 ")
    void mergeNoSwap(){
        Node a = new Node(5);
        Node b = new Node(9);

        Node result = SkewHeap.merge(a, b, tr);

        assertEquals(5, result.key);
        assertTrue(tr.steps().contains("P3N"));
        assertFalse(tr.steps().contains("P3"));

        assertEquals(List.of("P3N", "P4", "P1", "P5", "P6"), tr.steps());
    }

    @Test
    @DisplayName("merge swaps root when a.key > b.key (hits P3) 🧪 ")
    void mergeWithSwap(){
        Node a = new Node(9);
        Node b = new Node(5);

        Node result = SkewHeap.merge(a, b, tr);

        assertEquals(5, result.key);
        assertTrue(tr.steps().contains("P3"));

        assertEquals(List.of("P3", "P4", "P1", "P5", "P6"), tr.steps());
    }

    @Test
    @DisplayName("merge deeper recursion (hits P4 twice) 🧪 ")
    void mergeDeeperRecursion() {
        // a = 3 with right = 8
        Node a = new Node(3);
        a.right = new Node(8);

        // b = 5
        Node b = new Node(5);

        Node result = SkewHeap.merge(a, b, tr);

        assertEquals(3, result.key);
        assertEquals(
            List.of("P3N","P4", "P3","P4","P1","P5","P6", "P5","P6"),
            tr.steps()
        );
    }

    @Test
    @DisplayName("remove min node from heap 🧪 ")
    void removeMinNode(){
        SkewHeap sh = new SkewHeap(0);
        Node root = sh.root();
        root.right = new Node(10);

        int min = sh.removeMin(tr);

        assertEquals(0, min);
        assertEquals(List.of("P1"), tr.steps());
    }
}
