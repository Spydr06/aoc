import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.*;
import java.io.*;

public class Passport1 {

    private static String[] fields = {
        "ecl", "pid", "eyr", "hcl", "byr", "iyr", "cid", "hgt"
    };

    public static void main(String[] args) {
        String file = readFile("./src/4/input.txt");
        String[] passports = Pattern.compile("\\r\\n[\\r\\n]+").split(file);

        int valid = 0;

        for (String s : passports) {
            String[] passport = s.split(":|\\ |\\r\\n");
            int fieldsMatched = 0;
            boolean cidMissing = true;

            for (String arg : passport) {
                if (!Arrays.asList(fields).contains(arg)) {
                    continue;
                } else if (arg.equals(fields[6])) {
                    cidMissing = false;
                }
                fieldsMatched++;
            }
            if (cidMissing)
                fieldsMatched++;

            if (fieldsMatched == fields.length) {
                valid++;
            }
        }

        System.out.println(valid + " passports are valid.");
    }

    private static String readFile(String path) {
        try {
            return Files.readString(new File(path).toPath(), Charset.defaultCharset());

        } catch (IOException e) {
            System.out.println("Can't read input.txt");
            System.exit(-1);
        }
        return null;
    }
}