package solutions;



import com.sun.source.tree.Tree;
import model.Pair;


import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {

    private int key;
    private BinaryTree first;
    private BinaryTree second;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.first = first;
        this.second = second;

    }

    public Integer findLowestCommonAncestor(int first, int second) {
       List<Integer> firstPath = findPath(first);
         List<Integer> secondPath = findPath(second);


         int smallerSize = Math.min(firstPath.size(), secondPath.size());

            int i = 0;
            for (; i < smallerSize; i++) {
                if (!firstPath.get(i).equals(secondPath.get(i))) {
                   break;
                }
            }
            return firstPath.get(i - 1);
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();
        findNodePath(this, element, result);
        return result;
    }

    private  boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> result) {

        if (binaryTree == null) {
             return false;
        }

        if (binaryTree.key == element) {

            return true;
        }

        result.add(binaryTree.key);

       boolean leftResult = findNodePath(binaryTree.first, element, result);

if (leftResult) {
    return true;
}

        boolean rightResult = findNodePath(binaryTree.second, element, result);
    if (rightResult) {
    return true;
    }
return false;
    }

    public List<Integer> topView() {

          Map<Integer, Pair<Integer,Integer>> map = new TreeMap<>();

          traverseTree(this, 0, 1, map);

         return  map.values().stream().map(Pair::getKey).collect(Collectors.toList());


    }

    private void traverseTree(BinaryTree binaryTree, int offset, int level, Map<Integer, Pair<Integer, Integer>> map) {
        if (binaryTree == null) {
            return;
        }

        Pair<Integer, Integer> current = map.get(offset);
       if (current == null || current.getValue() > level) {
           map.put(offset, new Pair<>(binaryTree.key, level));
       }

     traverseTree(binaryTree.first, offset - 1, level + 1, map);
        traverseTree(binaryTree.second, offset + 1, level + 1, map);
    }


}
