import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.ActionEvent;

import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;

public class Hangman extends GraphicsProgram{

    Man man = new Man();
    Word word = new Word();

    int life = 6;

    String usedWord = word.getWord();

    Letter letter = new Letter(usedWord, 0);

    Letter[] letArray = new Letter[usedWord.length()];

    public JButton guessLetterBtn;


    GRect dash;

    GLabel wordLabel;


    @Override
    public void run() {
        genWord();
        genLett();
        addActionListeners();
    }

    @Override
    public void init() {
        wordLabel = new GLabel(usedWord);
        dash = new GRect(10, 1);

        wordLabel.setFont("Calibri-50");

        add(letter, 500, 500);
        add(man, 100, 100);
        guessLetterBtn = new JButton("GuessLetter");
        add(guessLetterBtn, SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "GuessLetter":
                guessLetter();
                break;
        }
    }

    private void guessLetter() {
        String guess = Dialog.getString("Guess A Letter");

        guessLet(guess.charAt(0));
    }




    private void genLett(){
        for (int i = 0; i < usedWord.length(); i++) {

            letArray[i] = new Letter(usedWord, i);

            add(letArray[i], getWidth()/2+ 20*(i+2), getHeight()/2);
        }
    }

    private void guessLet(char a){
        for (int i = 0; i < usedWord.length(); i++) {
            if (letArray[i].getLetter() == a){
                letArray[i].setVis(true);
            }
        }
    }

    private void loseLife(){

    }


    private void genWord(){
        add(wordLabel, getWidth()/2 - wordLabel.getWidth(), getHeight()/2- wordLabel.getHeight());
    }

    public static void main(String[] args) {
        new Hangman().start();
    }
}
