package Tree;
import java.util.List;


import java.util.ArrayList;

public class Traversals<T extends Comparable<? super T>> {


    public List<T> preorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if(root != null){
            result.add(root.getData());
            result.addAll(preorder(root.getLeft()));
            result.addAll(preorder(root.getRight()));
        }
        return result;
    }
    public List<T> inorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if(root != null){
            result.addAll(inorder(root.getLeft()));
            result.add(root.getData());
            result.addAll(inorder(root.getRight()));
        }
        return result;
    }

    public List<T> postorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if(root != null){
            result.addAll(postorder(root.getLeft()));
            result.addAll(postorder(root.getRight()));
            result.add(root.getData());
        }
        return result;
    }

}