import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{

        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
          this.contents = input;
          this.count = new int[NUMBER_OF_CHARACTERS];
          this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            for (int i = 0; i < contents.length(); i++) {
                char c = contents.charAt(i);
                count[(int)c]++;
            }
            // Print the frequency table with a header
            System.out.println("Frequency Table:");
            StringBuilder freqTable = new StringBuilder();
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                if (count[i] > 0) {
                    freqTable.append("<").append((char) i).append(", ").append(count[i]).append("> ");
                }
            }
            System.out.println(freqTable.toString());
        }
        

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        public void frequenciesToTree() {
            BinaryHeap<HuffmanNode> heap = new BinaryHeap<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                heap.insert(new HuffmanNode(Character.toString((char)i), (double)count[i]));
            }
        }
        huffmanTree = HuffmanTree.createFromHeap(heap);
        }
        

        // Construct code from huffmanTree.
        public void treeToCode() {
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                code[i] = ""; 
            }
            treeToCode(huffmanTree.root, "");
            huffmanTree.printLegend();
        }

        private void treeToCode(HuffmanNode t, String encoding) {
            if (t.letter.length() == 1) {
                code[t.letter.charAt(0)] = encoding;
            } else {
                treeToCode(t.left, encoding + "1");
                treeToCode(t.right, encoding + "0");
            }
            huffmanTree.printLegend();
        }

        // Encode content using code.
        public String encodeMessage() {
            StringBuilder encoded = new StringBuilder();
            for (char c : contents.toCharArray()) {
                encoded.append(code[c]);
            }
            String encodeMessage = encoded.toString();
            
            /*Print could be written in the method, or main. Here I comment out because they are in main method 
            System.out.println("Huffman Encoding: " + encodeMessage);

            int asciiSize = contents.length() * 8;
            System.out.println("Message size in ASCII encoding: " + asciiSize + " bits");

            int huffmanSize = encodeMessage.length(); 
            System.out.println("Message size in Huffman encoding: " + huffmanSize + " bits");*/
            return encodeMessage;
        }
        
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
            StringBuilder decoded = new StringBuilder();
            HuffmanNode node = huffmanTree.root;
            for (int i = 0; i < encodedStr.length(); i++) {
                node = encodedStr.charAt(i) == '1' ? node.left : node.right;
                if (node.letter.length() == 1) {
                    decoded.append(node.letter);
                    node = huffmanTree.root;
                }
             }
            String decodedMessage = decoded.toString();
            //for (int i = 0; i < decodedMessage.length(); i++) {
                    //System.out.println(decodedMessage.charAt(i));
        //''}
            System.out.println("Decoded Message: " + decodedMessage);

            return decodedMessage;

        }

        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        public static void main(String args[]) {

                String input = HuffmanConverter.readContents(args[0]);
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                h.frequenciesToTree();
                h.treeToCode();
                // Print the Huffman encoding here!
                String encoded = h.encodeMessage();
                System.out.println("Huffman encoding: \n"+ encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }

}
