import acm.graphics.*;
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
    GRect letterBank = new GRect(300, 50);

    public JButton guessLetterBtn;
    public JButton guessWordBtn;
    public JButton quit;
    private GImage gallows;

    int charShown = usedWord.length();

    int lettersGuessed = 0;
    int lettersRight = 0;


    GRect dash;

    GLabel wordLabel;
    GLabel[] labelBank;



    @Override
    public void run() {
        System.out.println(usedWord);
        genDashes();
        genLet();
        addActionListeners();
    }

    @Override
    public void init() {
        dash = new GRect(10, 1);
        gallows = new GImage("res/gallows.png");
        gallows.setSize(200, 200);
        setBackground(Color.gray);

        add(letter, 500, 500);
        add(gallows, 100, 100);
        add(man, gallows.getX() + 76, gallows.getY()+ 53);
        add(letterBank, 10, 10);
        guessLetterBtn = new JButton("GuessLetter");
        add(guessLetterBtn, SOUTH);
        guessWordBtn = new JButton("GuessWord");
        add(guessWordBtn, SOUTH);
        quit = new JButton("Quit");
        add(quit, SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "GuessLetter":
                guessLetter();
                break;
            case "GuessWord":
                guessWord();
                break;
            case "Quit":
                System.exit(0);
                break;

        }
    }


    private void guessLetter() {
        String guess = Dialog.getString("Guess A Letter");
        guessLet(guess.charAt(0));
    }

    private void guessWord(){
        String guess = Dialog.getString("Guess the word");
        if (guess.equals(usedWord)){
            win();
        }
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
                lettersRight += 1;
                if(lettersRight == usedWord.length()){
                    win();
                }
            }
        }
        if(check == charShown){
            life -= 1;
            man.addParts();
            addToBank(a);
            if(life == 0){
                lose();
            }
        }
    }

    private void addToBank(char a){
        lettersGuessed += 1;
        add(new GLabel(String.valueOf(a)), letterBank.getX() + lettersGuessed * 25, letterBank.getY() + 10);
    }


    private void win(){

        for (int i = 0; i < usedWord.length(); i++) {
            letArray[i].setVis(true);
        }
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
