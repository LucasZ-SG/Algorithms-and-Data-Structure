package AVL;

public class AVLTest {
    public static void main(String[] args){
        AVLNode<Integer>[] nodes = new AVLNode[11] ;
        for(int i = 1;i<= 10;i++){
            nodes[i] = new AVLNode<Integer>(i);
        }
        nodes[5].setLeft(nodes[4]);
        nodes[5].setRight(nodes[7]);
        nodes[4].setLeft(nodes[2]);
        nodes[2].setLeft(nodes[1]);
        nodes[2].setRight(nodes[3]);
        nodes[7].setLeft(nodes[6]);
        nodes[7].setRight(nodes[9]);
        nodes[9].setLeft(nodes[8]);
        nodes[9].setRight(nodes[10]);
        AVL<Integer> avl = new AVL<>();
        // avl.balance(nodes[5]);

    }

}
