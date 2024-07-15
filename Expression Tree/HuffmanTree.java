public class HuffmanTree 
{
    HuffmanNode root;
// public HuffmanTree(HuffmanNode huff)
// public void printLegend()
// private void printLegend(HuffmanNode node, String code)
// public static BinaryHeap<HuffmanNode> legendToHeap(String legend)
// public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> heap)
// public static void main(String[] args)

    public HuffmanTree(HuffmanNode huff){
        this.root = huff;
    }
    public void printLegend(){
        printLegend(root, "");
    }
    
    private void printLegend(HuffmanNode node, String code){
        if (node.letter.length()>1){
            printLegend(node.left, code + "0");
            printLegend(node.right, code + "1");
        }
        else{
            System.out.println(node.letter + "=" + code);
        }
    }

    public static BinaryHeap<HuffmanNode> legendToHeap(String argsString){
        String[] split = argsString.split(" ");
        BinaryHeap<HuffmanNode> legend_heap = new BinaryHeap<HuffmanNode>( );

        for (int i = 0; i < split.length; i += 2) {
            String letter = split[i];
            Double frequency = Double.parseDouble(split[i + 1]);


            HuffmanNode node_original = new HuffmanNode(letter, frequency);
            legend_heap.insert(node_original);
        }

        return legend_heap;

    }
    

    public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b){
        while (b.getSize()>1){
            HuffmanNode min1 = b.deleteMin();
            HuffmanNode min2 = b.deleteMin();

            HuffmanNode parent = new HuffmanNode(min1, min2);
            b.insert(parent);

        }
        return new HuffmanTree(b.findMin());
    }


    public static void main(String[] args){
        String argsString = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap<HuffmanNode> heap = legendToHeap(argsString);
        heap.printHeap();
        HuffmanTree huffmanTree = createFromHeap(heap);
        huffmanTree.printLegend();
    }

}



