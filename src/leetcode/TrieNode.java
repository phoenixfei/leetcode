package leetcode;

/**
 * TrieNode
 * 数据结构Trie(prefix tree)
 */
public class TrieNode {

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