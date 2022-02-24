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

    GRect[] dashArray = new GRect[usedWord.length()];

    public JButton guessLetterBtn;

    int charShown = usedWord.length();


    GRect dash;

    GLabel wordLabel;


    @Override
    public void run() {
        usedWord = word.getWord();
        genDashes();
        genLet();
        addActionListeners();
    }

    @Override
    public void init() {
        dash = new GRect(10, 1);

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


    private void genDashes(){
        for (int i = 0; i < usedWord.length(); i++) {
            dashArray[i] = new GRect(20, 1);
            add(dashArray[i], getWidth()/2 + 25*(i), getHeight()/8*7);
        }
    }

    private void genLet(){
        for (int i = 0; i < usedWord.length(); i++) {

            letArray[i] = new Letter(usedWord, i);

            add(letArray[i], dashArray[i].getX(), dashArray[i].getY()-5);
        }
    }

    private void guessLet(char a){
        int check = charShown;
        for (int i = 0; i < usedWord.length(); i++) {
            if (letArray[i].getLetter() == a){
                letArray[i].setVis(true);
                charShown += 1;
                if(charShown == usedWord.length()){
                    win();
                }
            }
        }
        if(check == charShown){
            life -= 1;
            man.addParts();
            if(life == 0){
                lose();
            }
        }
    }


    private void win(){
        Dialog.showMessage("You win");
        run();
    }

    private void lose(){
        Dialog.showMessage("You lost");
        System.exit(0);
    }




    public static void main(String[] args) {
        new Hangman().start();
    }
}
