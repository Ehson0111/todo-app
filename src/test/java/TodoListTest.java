import org.example.TodoList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TodoListTest {
    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");
        assertEquals(0, t.size());
    }

    @Test
    void clear() {
        TodoList t = new TodoList();
        t.add("1");
        t.add("2");
        assertEquals(2, t.size());
        t.clear();
        assertEquals(0, t.size());
    }

    @Test
    void markDone() {
        TodoList t = new TodoList();
        t.add("task");
        assertTrue(t.markDone(0));
        assertTrue(t.isCompleted(0));
        assertFalse(t.markDone(10));
    }

    @Test
    void search() {
        TodoList t = new TodoList();
        t.add("buy milk");
        t.add("read book");
        t.add("milk shake");

        List<String> results = t.search("milk");
        assertEquals(2, results.size());
        assertTrue(results.get(0).contains("buy milk"));
        assertTrue(results.get(1).contains("milk shake"));

        List<String> emptyResults = t.search("nonexistent");
        assertTrue(emptyResults.isEmpty());
    }
}