package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String input = readFileAsString(args[0]);

        System.out.println("The text is:\n" + input);

        String[] sentences = input.split("[.!?]");

        int words = 0;
        int syllables = 0;
        int polysyllables = 0;
        for (String sentence : sentences) {
            String[] word = sentence.trim().split("([,:;]?\\s+)");
            words += word.length;
            for (String w : word) {
                int syllableCount = countSyllables(w);
                syllables += syllableCount;
                if (syllableCount > 2) {
                    polysyllables++;
                }
            }
        }

        int characters = removeSpaces(input).length();

        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences.length);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String method = scanner.nextLine();

        System.out.println();

        switch (method) {
            case "all":
            case "ARI":
                System.out.print("Automated Readability Index: ");
                printScoreDetails(averageReadabilityIndex(characters, words, sentences.length));
                if (!"all".equals(method)) {
                    break;
                }
            case "FK":
                System.out.print("Flesch–Kincaid readability tests: ");
                printScoreDetails(fKReadabilityTest(words, sentences.length, syllables));
                if (!"all".equals(method)) {
                    break;
                }
            case "SMOG":
                System.out.print("Simple Measure of Gobbledygook: ");
                printScoreDetails(simpleMeasureOfGobbledygook(sentences.length, polysyllables));
                if (!"all".equals(method)) {
                    break;
                }
            case "CL":
                System.out.print("Coleman–Liau index: ");
                printScoreDetails(colemanLIndex(characters, words, sentences.length));
                break;
        }

    }

    private static void printScoreDetails(float score) {
        System.out.println(String.format("%.2f (about %s year olds).", score, getLevel((int)Math.ceil(score))));
    }

    private static float averageReadabilityIndex(int characters, int words, int sentences) {
        float characterPerWord = ( characters * 4.71f ) / words;
        float wordsPerSentence = ( words * 0.5f ) / sentences;

        return characterPerWord + wordsPerSentence - 21.43f;
    }

    private static float fKReadabilityTest(int words, int sentences, int syllables) {
        float wordsPerSentence = (0.39f * words / sentences);
        float syllablesPerWord = (11.8f * syllables / words);

        return wordsPerSentence + syllablesPerWord - 15.59f;
    }

    private static float simpleMeasureOfGobbledygook(int sentences, int polysyllables) {
        float polysyllablesPerSentence =  1.043f * (float)Math.sqrt(30f * polysyllables /sentences);
        return polysyllablesPerSentence + 3.1291f;
    }

    private static float colemanLIndex(int characters, int words, int sentences) {
        float L = 0.0588f * (characters * 100f / words);
        float S = 0.296f * (sentences * 100f / words);

        return L - S - 15.8f;
    }

    private static String readFileAsString(String filename) throws IOException {
        return new String( Files.readAllBytes(Paths.get(filename)));
    }

    private static String removeSpaces(String data) {
        return data.replaceAll("\\s+","");
    }

    private static String getLevel(int score) {
        switch (score) {
            case 1: return "6";
            case 2: return "7";
            case 3: return "9";
            case 4: return "10";
            case 5: return "11";
            case 6: return "12";
            case 7: return "13";
            case 8: return "14";
            case 9: return "15";
            case 10: return "16";
            case 11: return "17";
            case 12: return "18";
            case 13: return "24";
            default: return "24+";
        }
    }

    private static int countSyllables(String word) {

        word = word.toLowerCase();

        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Character prevCh = null;
            if (i != 0) {
                prevCh = word.charAt(i - 1);
            }

            if(i == word.length() - 1 && ch == 'e') {
                break;
            }

            if((prevCh == null && isVowel(ch)) || (prevCh!=null && !isVowel(prevCh) && isVowel(ch))) {
                count++;
            }
        }
        return count == 0 ? 1 : count;
    }

    private static boolean isVowel(char ch) {
        switch (ch) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'y': return true;
            default: return false;
        }
    }

}
