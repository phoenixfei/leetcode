package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Graph
 */
public class Graph {

    /** 图的表示 https://blog.csdn.net/zhoucheng05_13/article/details/79403505
     * 简单实现方式—— 邻接矩阵
     * 表示图的一种简单方法是
     */

    /**
     * 弗洛伊德Floyd-warshall算法，专门用于寻找 带权图中多源点之间的最短路劲。
     * 核心思想是，利用中继顶点，一步一步推导出图中所有顶点之间的最短距离。
     */

    /**
     * 迪杰斯特拉Dijkstra算法，著名的单源最短路径算法。
     */
    // https://leetcode-cn.com/problems/course-schedule/
    // BFS
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        // 利用队列实现BFS
        Queue<Integer> queue = new LinkedList<>();
        // 每个结点的入度
        int[] indegrees = new int[numCourses];
        for(int[] cp : prerequisites){
            indegrees[cp[0]]++;
        }
        // 将入度为0的结点初始化到队列中
        for (int i = 0; i < numCourses; i++) {
            if(indegrees[i] == 0) queue.offer(i);
        }
        while(!queue.isEmpty()){
            int pre = queue.poll();
            numCourses --;
            for(int[] req : prerequisites){
                if(req[1] != pre) continue;
                if(--indegrees[req[0]] == 0) queue.offer(req[0]);
            }
        }
        return numCourses == 0;
    }
    // DFS
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 构建邻接矩阵
        int[][] adjacency = new int[numCourses][numCourses];
        for(int[] cp : prerequisites){
            adjacency[cp[1]][cp[0]] = 1;
        }
        // 未被 DFS 访问：i == 0；
        // 已被其他节点启动的DFS访问：i == -1；
        // 已被当前节点启动的DFS访问：i == 1。
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!dfs(adjacency, flags, i)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;
        flags[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if(adjacency[i][j] == 1 && !dfs(adjacency, flags, j)) return false;
        }
        flags[i] = -1;
        return true;
    }

}