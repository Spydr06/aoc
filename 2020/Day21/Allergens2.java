import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.List;
import java.io.IOException;

class Allergens2 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("input.txt"));
        HashMap<String, String> translations;

        HashSet<String> uniqueAllergens = new HashSet<>();
        HashSet<String> uniqueIngredients = new HashSet<>();
        for (String in : input) {
            String[] ingredients = in.substring(0, in.indexOf("(") - 1).split(" ");
            uniqueIngredients.addAll(Arrays.asList(ingredients));
            String[] allergens = in.substring(in.indexOf("contains") + 9, in.length() - 1).replace(",", "").split(" ");
            uniqueAllergens.addAll(Arrays.asList(allergens));
        }

        HashMap<String, ArrayList<String>> possibleMatches = new HashMap<>();
        for (String ua : uniqueAllergens) {
            ArrayList<String> temp = new ArrayList<>(uniqueIngredients);
            for (int i = 0; i < input.size(); i++) {
                String line = input.get(i);
                if (line.contains(" " + ua)) {
                    String[] ingreds = line.substring(0, line.indexOf("(") - 1).split(" ");
                    ArrayList<String> other = new ArrayList<>(Arrays.asList(ingreds));
                    temp.retainAll(other);
                }
            }
            possibleMatches.put(ua, temp);
        }

        translations = new HashMap<>();
        while (!possibleMatches.isEmpty()) {
            Iterator<Entry<String, ArrayList<String>>> itr = possibleMatches.entrySet().iterator();
            while (itr.hasNext()) {
                Entry<String, ArrayList<String>> entry = itr.next();
                ArrayList<String> ingredients = entry.getValue();
                if (ingredients.size() == 1) {
                    String allergen = entry.getKey();
                    String ingredient = ingredients.get(0);
                    translations.put(allergen, ingredient);
                    itr.remove();

                    for (ArrayList<String> list : possibleMatches.values()) {
                        list.remove(ingredient);
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String[] ingredients = line.substring(0, line.indexOf("(") - 1).split(" ");
            for (String ingredient : ingredients) {
                if (!translations.values().contains(ingredient)) {
                    count++;
                }
            }
        }

		String[] catalogue = new String[translations.size()];
		int i = 0;
		for (Entry<String, String> entry : translations.entrySet()) {
			catalogue[i] = entry.getKey() + "/" + entry.getValue();
			i++;
		}

		Arrays.sort(catalogue);

		String cdil = "";
		for (String str : catalogue) {
			cdil += str.substring(str.indexOf("/") + 1) + ",";
		}
		cdil = cdil.substring(0, cdil.length() - 1);

		System.out.println("Canonical Dangerous Ingredients List: " + cdil);
    }
}