import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import svu.csc213.Dialog;

import java.awt.*;

public class Hangman extends GraphicsProgram{

    Man man = new Man();
    Word word = new Word();

    String usedWord = word.getWord();

    GLabel wordLabel;

    


    @Override
    public void run() {
    }

    @Override
    public void init() {
        wordLabel = new GLabel(usedWord);
        add(man, 100, 100);

        add(wordLabel, 200, 200);
    }

    public static void main(String[] args) {
        new Hangman().start();
    }
}
