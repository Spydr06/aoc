import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.io.*;

public class Passwd2 {
    public static void main(String[] args) {
        String[] input = readFile("./src/2/input.txt");

        int matched = 0;

        for (String inputString : input) {
            String[] identifier = translateIdentifier(inputString.split(":")[0].trim());
            String passwd = inputString.split(":")[1].trim();
            int index1 = Integer.parseInt(identifier[0]);
            int index2 = Integer.parseInt(identifier[1]);
            char character = identifier[2].charAt(0);

            if (passwd.length() < index1 || passwd.length() < index2) {
                continue;
            }
            if (passwd.charAt(index1 - 1) == character ^ passwd.charAt(index2 - 1) == character) {
                matched++;
                System.out.println("The password \"" + passwd + "\" matches and has \"" + character + "\" at letter " + (index1 + 1) + " xor " + (index2 + 1) + ".");
            }
        }

        System.out.println(matched + " of " + input.length + " passwords matched.");
    }

    private static String[] translateIdentifier(String identifier) {
        String from = identifier.split("-")[0].trim();
        String character = identifier.split(" ")[1].trim();
        String to = identifier.substring(identifier.indexOf("-") + 1, identifier.indexOf(" ")).trim();

        String[] output = { from, to, character };
        return output;
    }

    private static String[] readFile(String path) {
        String[] input = null;

        try {
            File file = new File(path);
            List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            input = lines.toArray(new String[lines.size()]);

        } catch (IOException e) {
            System.out.println("Can't read input.txt");
            System.exit(-1);
        }

        if (input == null) {
            System.out.println("Can't read input string");
            System.exit(-1);
        }

        return input;
    }
}
