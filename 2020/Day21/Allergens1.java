import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.*;

class Allergens1 {
    public static void main(String[] args) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("input.txt")));
        Map<String, List<String>> table = new HashMap<>();
        List<String> allIngredients = new ArrayList<>();

        for (String item : input.split("\\)")) {
            List<String> ingredients = Arrays.asList(item.split(" \\(")[0].replaceAll("(\\r|\\n)", "").split(" "));
            String[] allergens = item.split("\\(contains ")[1].split(", ");

            System.out.println(
                    Arrays.toString(ingredients.toArray()) + " contain " + Arrays.toString(allergens) + " Allergens");

            for (String alg : allergens) {
                if (table.containsKey(alg)) {
                    List<String> common = new ArrayList<>(table.get(alg));
                    common.retainAll(ingredients);
                    table.put(alg, common);

                } else {
                    table.put(alg, ingredients);
                }
            }

            allIngredients.addAll(ingredients);
        }
        
        List<String> allTableValues = new ArrayList<>();
        for (List<String> ings : table.values()) {
            allTableValues.addAll(ings);
        }

        int count = 0;
        List<String> allTableValuesOnce = new ArrayList<>(new HashSet<>(allTableValues));
        for (String ing : allIngredients) {
            if (!allTableValuesOnce.contains(ing)) {
                count++;
            }
        }

        System.out.println(count);
    }

}