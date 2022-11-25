import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.*;

public class Combat1 {
    public static void main(String[] args) throws IOException {
        List<Integer> deck1 = new ArrayList<>();
        List<Integer> deck2 = new ArrayList<>();
        int current = -1;

        for (String line : (Files.readAllLines(Paths.get("input.txt")))) {
            if (line.startsWith("Player")) {
                current = Integer.parseInt(line.substring(7, 8)) - 1;
            } else if (!line.equals("")) {
                (current == 0 ? deck1 : deck2).add(Integer.parseInt(line));
            }
        }

        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            int a = deck1.get(0);
            int b = deck2.get(0);
            List<Integer> higher = (a > b ? deck1 : deck2);

            deck1.remove(0);
            deck2.remove(0);
            higher.add(a > b ? a : b);
            higher.add(a > b ? b : a);
        }

        List<Integer> winner = (deck1.isEmpty() ? deck2 : deck1);
        int score = 0;
        for (int i = 0; i < winner.size(); i++) {
            score += (winner.get(i) * (-i + winner.size()));
        }
        
        System.out.println(score);
    }
}