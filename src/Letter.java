import acm.graphics.GLabel;

public class Letter extends GLabel {

    private char letter;

    public Letter(String word, int char1){
        super(null);

        letter = toArray(word, char1);

        setLabel(String.valueOf(toArray(word, char1)));
        setFont("Calibri-30");
        setVisible(false);

    }

    public char toArray(String word, int char1){
        char[] char2 = word.toCharArray();

        return char2[char1];
    }

    public void setVis(boolean set){
        setVisible(set);
    }

    public char getLetter(){
        return letter;
    }


}
