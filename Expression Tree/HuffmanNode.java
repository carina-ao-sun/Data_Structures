public class HuffmanNode implements Comparable<HuffmanNode>{
    public String letter;
    public Double frequency; 
    public HuffmanNode left, right;

    public HuffmanNode(String letter, double frequency){
        this.letter = letter; 
        this.frequency = frequency; 
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.letter = left.letter + right.letter; 
        this.frequency = left.frequency + right.frequency;
        this.left = left; 
        this.right = right; 
    }

@Override
    public int compareTo(HuffmanNode huff){
        return Double.compare(this.frequency, huff.frequency);
    }

@Override
    public String toString(){
        return "<" + letter + ", " + frequency + ">";
    }
}
