package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<String> items = new ArrayList<>();
    private final List<Boolean> completed = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(item);
                completed.add(false);
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            completed.remove(index);
            return true;
        }
        return false;
    }

    public List<String> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
        completed.clear();
    }

    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            completed.set(index, true);
            return true;
        }
        return false;
    }

    public List<String> search(String query) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toLowerCase().contains(query.toLowerCase())) {
                String status = completed.get(i) ? "[DONE] " : "";
                results.add(i + ": " + status + items.get(i));
            }
        }
        return results;
    }

    public boolean isCompleted(int index) {
        if (index >= 0 && index < completed.size()) {
            return completed.get(index);
        }
        return false;
    }
}