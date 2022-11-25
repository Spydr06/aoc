import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.*;

import java.io.*;

public class Passport2 {

    private static final String[] fields = { "ecl", "pid", "eyr", "hcl", "byr", "iyr", "cid", "hgt" };
    private static final String[] eyeColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

    public static void main(String[] args) {
        String file = readFile("./src/4/input.txt");
        String[] passports = Pattern.compile("\\r\\n[\\r\\n]+").split(file);

        int valid = 0;

        for (String s : passports) {
            String[] passport = s.split(":|\\ |\\r\\n");
            int fieldsMatched = 0;
            boolean cidMissing = true;

            if (passport.length % 2 != 0) {
                continue;
            }

            for (int i = 0; i < passport.length; i += 2) {
                String field = passport[i];
                String value = passport[i + 1];

                switch (field) {
                    case "ecl":
                        if (checkEyeCol(value)) {
                            fieldsMatched++;
                        }
                        break;
                    case "pid":
                        if (checkPassID(value)) {
                            fieldsMatched++;
                        }
                        break;
                    case "eyr":
                        if (checkYear(value, 2020, 2030)) {
                            fieldsMatched++;
                        }
                        break;
                    case "hcl":
                        if (checkHairCol(value)) {
                            fieldsMatched++;
                        }
                        break;
                    case "byr":
                        if (checkYear(value, 1920, 2002)) {
                            fieldsMatched++;
                        }
                        break;
                    case "iyr":
                        if (checkYear(value, 2010, 2020)) {
                            fieldsMatched++;
                        }
                        break;
                    case "hgt":
                        if (checkHeight(value)) {
                            fieldsMatched++;
                        }
                        break;
                    case "cid":
                        fieldsMatched++;
                        cidMissing = false;
                        break;
                    default:
                        break;
                }
            }

            if (cidMissing)
                fieldsMatched++;

            if (fieldsMatched == fields.length) {
                valid++;
            }
        }

        System.out.println(valid + " passports are valid.");
    }

    private static boolean checkYear(String value, int min, int max) {
        if (isNum(value)) {
            int val = Integer.parseInt(value);
            return val >= min && val <= max;
        }
        return false;
    }

    private static boolean checkEyeCol(String value) {
        return Arrays.asList(eyeColors).contains(value);
    }

    private static boolean checkPassID(String value) {
        return value.length() == 9 && isNum(value);
    }

    private static boolean checkHairCol(String value) {
        return value.charAt(0) == '#' && value.length() == 7 && value.substring(1).matches("[0-9a-fA-F]+");
    }

    private static boolean checkHeight(String value) {
        if (value.length() < 4) {
            return false;
        }

        String unit = value.substring(value.length() - 2);
        String height = value.substring(0, value.length() - 2);

        if (isNum(value)) {
            return false;
        }
        int num = Integer.parseInt(height);

        if (unit.equals("cm")) {
            return num >= 150 && num <= 193;
        } else if (unit.equals("in")) {
            return num >= 59 && num <= 76;
        }
        return false;
    }

    private static boolean isNum(String val) {
        return val.matches("[0-9]+");
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