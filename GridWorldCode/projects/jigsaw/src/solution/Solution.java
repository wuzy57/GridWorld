package solution;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

	private List<JigsawNode> solutionPath; 
    private int searchedNodesNum;           
    private Set<JigsawNode> visitedList;    
    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	this.visitedList = new HashSet<JigsawNode>(1000);
        Queue<JigsawNode> exploreList = new LinkedList<JigsawNode>();
        //开始、结束、当前的节点
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        
        // 最大节点数为29000
        final int MAX_NODE_NUM = 29000;
        // 重置节点数和解决方法
        this.searchedNodesNum = 0;
        this.solutionPath = null;
        
        // 将开始节点放入List
        this.visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);
        
        // 如果数字小于29000，并且exploreList不为空，则循环，否则则是超过了节点数为失败
        while (this.searchedNodesNum < MAX_NODE_NUM && !exploreList.isEmpty()) {
        	//更新搜索时间
        	this.searchedNodesNum++;
        	
        	// 将当前节点加入exploreList
        	this.currentJNode = exploreList.poll();
        	// 当前节点等于endJNode,找到一条路径
          // 搜索成功
        	if(this.currentJNode.equals(eNode)) {
        		// 从开始到结束的路径
        		this.getPath();
        		break;
        	}
        	
        	// 从当前节点建立4个孩子节点
        	JigsawNode[] nextNodes = new JigsawNode[] {
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)};
        	
        	//获取四个方向
        	for (int i = 0; i < 4; i++) {
        		// 确认当前状态没有被访问
        		if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) {
        			// 添加下一个节点
        			this.visitedList.add(nextNodes[i]);
        			// 添加到exploreList
        			exploreList.add(nextNodes[i]);
        		}
        	}
        }
        
        // 打印结果
        System.out.println("Jigsaw BFSearch Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        //完成
        return this.isCompleted();
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
    	int s = 0; 
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        
        int t = 0; //从错误位置到正确位置的距离
        for (int i = 1; i <= dimension * dimension; i++) {
        	// 如果是错的，计算距离
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		int rightRow = (i-1) / dimension;
        		int rightColumn = (i-1) % dimension;
        		int presentRow = (jNode.getNodesState()[i] - 1) / dimension;
        		int presentColumn = (jNode.getNodesState()[i]-1) % dimension;
        		//距离的计算
        		int distance = (int) Math.sqrt((Math.pow((rightRow-presentRow), 2) + Math.pow(rightColumn-presentColumn, 2)));
        		t += distance;
        	}
        }
        
        int u = 0; // 错误位置的计数
        for (int i = 1; i <= dimension * dimension; i++) {
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		u++;
        	}
        }
        // Give the weighs of three value
        int []weight = {3, 10, 3};
        
        // 计算EstimatedValue
        int value = weight[0] * s + weight[1] * t + weight[2] * u;
        jNode.setEstimatedValue(value);
    }
}
