import acm.graphics.GLabel;

public class Letter extends GLabel {


    public Letter(String word, int char1){
        super(null);

        setLabel(String.valueOf(toArray(word, char1)));
        setFont("Calibri-30");


    }

    public char toArray(String word, int char1){
        char[] char2 = word.toCharArray();
        return char2[char1];
    }


}
