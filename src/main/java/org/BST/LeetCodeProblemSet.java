package org.BST;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class LeetCodeProblemSet {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
      if (p == null && q == null) return true;

      if (p == null || q == null) return false;

      return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public int maxDepth(TreeNode root) {
      return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
      if (root1 == null) return root2;
      if (root2 == null) return root1;
      return new TreeNode(
          root1.val + root2.val,
          mergeTrees(root1.left, root2.left),
          mergeTrees(root1.right, root2.right));
    }

    // does not work map.entry is stupid
    public boolean isSymmetric(TreeNode root) {
      Deque<Map.Entry<Optional<TreeNode>, Optional<TreeNode>>> stack = new ArrayDeque<>();

      stack.push(Map.entry(Optional.ofNullable(root.left), Optional.ofNullable(root.right)));

      while (!stack.isEmpty()) {
        final Map.Entry<Optional<TreeNode>, Optional<TreeNode>> oppositeTrees = stack.pop();
        final Optional<TreeNode> leftTree = oppositeTrees.getKey();
        final Optional<TreeNode> rightTree = oppositeTrees.getValue();

        final boolean isSymmetric =
            Stream.of(leftTree, rightTree).map(opt -> opt.map(node -> node.val)).distinct().count()
                == 1;
        if (!isSymmetric) return false;

        final Map.Entry<Optional<TreeNode>, Optional<TreeNode>> outer =
            Map.entry(leftTree.map(tree -> tree.left), rightTree.map(tree -> tree.right));

        final Map.Entry<Optional<TreeNode>, Optional<TreeNode>> inner =
            Map.entry(leftTree.map(tree -> tree.right), rightTree.map(tree -> tree.left));

        if (!(outer.getKey().isEmpty() && outer.getValue().isEmpty()))
          stack.push(Map.entry(outer.getKey(), outer.getValue()));
        if (!(inner.getKey().isEmpty() && inner.getValue().isEmpty()))
          stack.push(Map.entry(inner.getKey(), inner.getValue()));
      }

      return true;
    }
  }
}
