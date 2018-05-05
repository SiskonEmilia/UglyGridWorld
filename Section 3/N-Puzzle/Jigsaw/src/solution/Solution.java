package solution;

import java.util.LinkedList;
import java.util.Queue;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

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

	int tempratuer = 4; // 模拟退火-温度计

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		Queue<JigsawNode> open = new LinkedList<JigsawNode>(), close = new LinkedList<JigsawNode>();
		open.offer(bNode);
		while (!open.isEmpty()) {
			JigsawNode node = open.poll();
			
			if (node.equals(eNode)) {
				this.currentJNode = node;
				getPath();
				return true;
			}

			close.add(node);

			for (int i = 0; i < 4; ++i) {
				JigsawNode newNode = new JigsawNode(node);
				if (newNode.move(i)) {
					if (!close.contains(newNode) && !open.contains(newNode)) {
						open.add(newNode);
					}
				}
			}
		}
		return false;
	}


	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
		int s = 0; // 后续节点不正确的数码个数
		int mDistance = 0;
		double oDistance = 0;
		int dimension = JigsawNode.getDimension();
		int nodes[] = new int[dimension * dimension + 1];

		int curCol, curRow, tarCol, tarRow;

		for (int index = 1; index <= dimension * dimension; ++index) {
			nodes[index] = jNode.getNodesState()[index];
		}

		for (int index = 1; index < dimension * dimension; index++) {
			if (index % dimension != 0 &&
				nodes[index] != 0 &&
				nodes[index + 1] != 0 &&
				nodes[index] + 1 !=
				nodes[index + 1]) {
				++s;
			}

			if (index / dimension != dimension - 1 &&
				nodes[index] != 0 &&
				nodes[index + dimension] != 0 &&
				nodes[index] + dimension !=
				nodes[index + dimension]) {
					++s;
			}
		}

		for (int i = 1; i <= dimension * dimension; ++i) {
			if (nodes[i] != 0) {
				curCol = (nodes[i] - 1) % dimension;
				curRow = (nodes[i] - 1) / dimension;

				tarCol = (i - 1) % dimension;
				tarRow = (i - 1) / dimension;

				mDistance += Math.abs(tarCol - curCol) + Math.abs(tarRow - curRow);
				oDistance += Math.pow(tarCol - curCol, 2) + Math.pow(tarRow - curRow, 2);
			}
		}

		++tempratuer;
		jNode.setEstimatedValue((int)(7 * s + 6 * mDistance + 3 * oDistance + Math.random() * 5000 / (500 + tempratuer)));
	}
}
