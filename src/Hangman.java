import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import svu.csc213.Dialog;

import java.awt.*;

public class Hangman extends GraphicsProgram{

    Man man = new Man();
    Word word = new Word();

    String usedWord = word.getWord();

    Letter letter = new Letter(usedWord, 0);



    GRect dash;

    GLabel wordLabel;


    @Override
    public void run() {
        genWord();
    }

    @Override
    public void init() {
        wordLabel = new GLabel(usedWord);
        dash = new GRect(10, 1);

        wordLabel.setFont("Calibri-50");

        add(letter, 500, 500);
        add(man, 100, 100);


    }


    private void genWord(){
        add(wordLabel, getWidth()/2 - wordLabel.getWidth(), getHeight()/2- wordLabel.getHeight());
    }

    public static void main(String[] args) {
        new Hangman().start();
    }
}
