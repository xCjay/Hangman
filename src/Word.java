import acm.util.RandomGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class Word {

    File file = new File("C:\\Users\\cd14674\\Documents\\Programming\\Hangman\\Res\\words.txt");
    Scanner sc = null;
    Random ran = new Random();

    public Word(){
    }

    public String getWord() {

        String word = null;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            for (int i = 0; i < ran.nextInt(1000); i++) {
                word = sc.nextLine();
                word.toLowerCase();
            }
            break;
        }
        return word;
    }
}
