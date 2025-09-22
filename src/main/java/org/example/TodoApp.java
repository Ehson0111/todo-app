package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoList list = new TodoList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple Todo CLI. Commands: add <task>, remove <index>, list, done <index>, search <query>, clear, exit");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "add":
                    if (parts.length > 1) {
                        list.add(parts[1]);
                        System.out.println("Added.");
                    } else {
                        System.out.println("Usage: add <task>");
                    }
                    break;

                case "remove":
                    if (parts.length > 1) {
                        try {
                            int idx = Integer.parseInt(parts[1]);
                            if (list.remove(idx))
                                System.out.println("Removed.");
                            else
                                System.out.println("Index out of range.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid index.");
                        }
                    } else {
                        System.out.println("Usage: remove <index>");
                    }
                    break;

                case "done":
                    if (parts.length > 1) {
                        try {
                            int idx = Integer.parseInt(parts[1]);
                            if (list.markDone(idx))
                                System.out.println("Task marked as done.");
                            else
                                System.out.println("Index out of range.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid index.");
                        }
                    } else {
                        System.out.println("Usage: done <index>");
                    }
                    break;

                case "search":
                    if (parts.length > 1) {
                        List<String> results = list.search(parts[1]);
                        if (results.isEmpty()) {
                            System.out.println("No tasks found.");
                        } else {
                            System.out.println("Found tasks:");
                            for (String result : results) {
                                System.out.println(result);
                            }
                        }
                    } else {
                        System.out.println("Usage: search <query>");
                    }
                    break;

                case "list":
                    List<String> all = list.getAll();
                    if (all.isEmpty()) {
                        System.out.println("(empty)");
                    } else {
                        for (int i = 0; i < all.size(); i++) {
                            String status = list.isCompleted(i) ? "[DONE] " : "";
                            System.out.printf("%d: %s%s%n", i, status, all.get(i));
                        }
                    }
                    break;

                case "clear":
                    list.clear();
                    System.out.println("All tasks cleared.");
                    break;

                case "exit":
                    System.out.println("Bye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Commands: add, remove, done, search, list, clear, exit");
            }
        }
    }
}