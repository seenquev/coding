import java.util.LinkedList;
import java.util.Queue;

public class BTSerializer {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return new String();
        }

        String serialized = new String();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        serialized += "[";
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                serialized += "null";
            } else {
                serialized += Integer.toString(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (!queue.isEmpty()) {
                serialized += ",";
            }
        }
        serialized += "]";

        System.out.println(serialized);
        return serialized;
    }

    private TreeNode deserializeQueue(String data) {
        TreeNode root = null;
        Queue<TreeNode> toSerialize = new LinkedList<>();
        toSerialize.add(root);

        while(!toSerialize.isEmpty()) {
            TreeNode node = toSerialize.poll();
            String tmp = new String();

            boolean numberAdj = false;
            int count = 0;

            for (int i = 0; i < data.length(); i++) {
                Character ch = data.charAt(i);
                if (ch == '-' || Character.isDigit(ch)) {
                    tmp += Character.toString(ch);
                    numberAdj = true;
                } else if (ch == ',') {
                    if (numberAdj) {
                        int num = Integer.parseInt(tmp);
                        if (node == null) {
                            node = new TreeNode(num);
                            if (root == null) {
                                root = node;
                            }
                            toSerialize.add(node);

                            data = data.substring(i + 1);
                            break;
                        } else if (count == 0) {

                            node.left = new TreeNode(num);
                            toSerialize.add(node.left);
                            numberAdj = false;
                            tmp = new String();

                        } else if (count == 1) {

                            node.right = new TreeNode(num);
                            toSerialize.add(node.right);

                            data = data.substring(i + 1);
                            break;
                        }
                    } else {
                        if (count == 1) {
                            data = data.substring(i + 1);
                            break;
                        }
                    }
                    count++;
                } else if (ch == ']') {
                    toSerialize.clear();
                    break;
                }
            }
        }
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() <= 3) {
            return null;
        }

        data = data.substring(1); // removing '['

        TreeNode root = deserializeQueue(data);
        return root;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
