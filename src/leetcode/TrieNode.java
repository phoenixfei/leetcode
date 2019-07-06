package leetcode;

/**
 * TrieNode
 * 数据结构Trie(prefix tree)
 */
// 题1：https://leetcode.com/problems/implement-trie-prefix-tree/
/** 方法1 
class TrieNode {

    private TrieNode[] links; // 指向子节点

    private final int R = 26; // 26个英文字母

    private boolean isEnd = false; // 是否为结束标志

    // 构造方法，创建一个包含26个字母的一层节点
    public TrieNode() {
        links = new TrieNode[R]; // [0-25]==a-z
    }
    
    // 该层trie树是否包含字符ch
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    // 得到包含ch的树
    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    // 将字符ch放入到trie中
    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    // 设置为结束标志
    public void setEnd() {
        isEnd = true;
    }

    // 判断是否为结束标志
    public boolean isEnd() {
        return isEnd;
    }

}

class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if( !node.containsKey(currentChar) ) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (node.containsKey(currentChar)) {
                node = node.get(currentChar);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
    
}

*/

// 利用简单的关联数组实现Trie树
/*
class TrieNode {
    TrieNode[] links = new TrieNode[26];
    boolean isleaf  = false;
}

class Trie {

    TrieNode root;

    Trie () {
        root = new TrieNode();
    }

    // 实现插入方法
    public void insert(String word) {
        insert(word, root);
    }

    public void insert(String word, TrieNode node) {
        if (node == null) {
            return;
        }
        if (word.length() == 0) {
            node.isleaf = true;
            return;
        }
        int index = word.charAt(0) - 'a';
        if (node.links[index] == null) {
            node.links[index] = new TrieNode();
        }
        insert(word.substring(1), node.links[index]);
    }

    // 实现 查找 功能
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (word.length() == 0) return node.isleaf;
        int index = word.charAt(0) - 'a';
        return search(word.substring(1), node.links[index]);
    }

    // Returns if there is any word in the trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return startsWith(prefix, root);
    }

    private boolean startsWith(String prefix, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (prefix.length() == 0) return true;
        int index = prefix.charAt(0) - 'a';
        return startsWith(prefix.substring(1), node.links[index]);
    }
}
*/

// 题2：https://leetcode-cn.com/problems/map-sum-pairs/
// 关联数组
class TrieNode {
    TrieNode[] links = new TrieNode[26]; 
    int val;
}

class MapSum {

    private TrieNode root;

    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        insert(key, val, root);
    }

    private void insert(String key, int val, TrieNode node) {
        if (node == null) {
            return;
        }
        if (key.length() == 0) {
            node.val = val;
            return;
        }
        int index = key.charAt(0) - 'a';
        if (node.links[index] == null) {
            node.links[index] = new TrieNode();
        }
        insert(key.substring(1), val, node.links[index]);
    }
    
    public int sum(String prefix) {
        return sum(prefix, root);
    }

    private int sum(String prefix, TrieNode node) {
        if (node == null) {
            return 0;
        }
        if (prefix.length() != 0) {
            int index = prefix.charAt(0) - 'a';
            return sum(prefix.substring(1), node.links[index]);
        }
        int sum = node.val;
        for (TrieNode child : node.links) {
            sum += sum(prefix, child);
        }
        return sum;
    }
}