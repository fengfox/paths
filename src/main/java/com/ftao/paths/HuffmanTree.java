package com.ftao.paths;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree {
    public static class Node<E>
    {
        E data;
        Integer weight;
        Node leftChild;
        Node rightChild;
        //数字的浮动范围
        Integer range;
        public Node(E data,Integer weight,Integer range) {
            super();
            this.data = data;
            this.weight = weight;
            this.range=range;

        }
        public Node(){}
        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Integer getRange() {
            return range;
        }

        public void setRange(Integer range) {
            this.range = range;
        }
    }

    public static Node createTree(List<Node> nodes)
    {
        while(nodes.size()>1)
        {
            quickSort(nodes);
            Node left=nodes.get(nodes.size()-1);
            Node right=nodes.get(nodes.size()-2);
            Node parent = new Node(null, left.weight + right.weight, left.range+right.range);
            //让新节点作为两个权值最小节点的父节点
            parent.leftChild = left;
            parent.rightChild = right;

            //删除权值最小的两个节点
            nodes.remove(nodes.size()-1);
            nodes.remove(nodes.size()-1);

            //将新节点加入到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 将指定集合中的i和j索引处的元素交换
     *
     * @param nodes
     * @param i
     * @param j
     */
    private static void swap(List<Node> nodes, int i, int j) {
        Node tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }

    /**
     * 实现快速排序算法，用于对节点进行排序
     *
     * @param nodes
     * @param start
     * @param end
     */
    private static void subSort(List<Node> nodes, int start, int end) {
        if (start < end) {
            // 以第一个元素作为分界值
            Node base = nodes.get(start);
            // i从左边搜索，搜索大于分界值的元素的索引
            int i = start;
            // j从右边开始搜索，搜索小于分界值的元素的索引
            int j = end + 1;
            while (true) {
                // 找到大于分界值的元素的索引，或者i已经到了end处
                while (i < end && nodes.get(++i).weight >= base.weight)
                    ;
                // 找到小于分界值的元素的索引，或者j已经到了start处
                while (j > start && nodes.get(--j).weight <= base.weight)
                    ;

                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }

            swap(nodes, start, j);

            //递归左边子序列
            subSort(nodes, start, j - 1);
            //递归右边子序列
            subSort(nodes, j + 1, end);
        }
    }

    public static void quickSort(List<Node> nodes){
        subSort(nodes, 0, nodes.size()-1);
    }

    //广度优先遍历
    public static List<Node> breadthFirst(Node root){
        Queue<Node> queue = new ArrayDeque<Node>();
        List<Node> list = new ArrayList<Node>();

        if(root!=null){
            //将根元素加入“队列”
            queue.offer(root);
        }

        while(!queue.isEmpty()){
            //将该队列的“队尾”元素加入到list中
            list.add(queue.peek());
            Node p = queue.poll();

            //如果左子节点不为null，将它加入到队列
            if(p.leftChild != null){
                queue.offer(p.leftChild);
            }

            //如果右子节点不为null，将它加入到队列
            if(p.rightChild != null){
                queue.offer(p.rightChild);
            }
        }

        return list;
    }

    /***
     *获取符合条件的node列表
     * @param root 根节点
     * @param total 总里程数
     * @return
     */
    public static List<Node> findNode(Node root,Integer total)
    {
        List<Node> list=new ArrayList<Node>();
        list=breadthFirst(root);
        //符合total在节点的范围空间内的节点列表
        List<Node> result=new ArrayList<Node>();
        for(Integer count=0;count<list.size();count++)
        {
            if(total>=list.get(count).weight-list.get(count).range&&total<list.get(count).weight+list.get(count).range)
            {
                result.add(list.get(count));
            }

        }
        return result;
    }
    public static List<Node> findNode2(Node root,Integer total)
    {
        List<Node> list=new ArrayList<Node>();
        list=breadthFirst(root);
        //符合total在节点的范围空间内的节点列表
        List<Node> result=new ArrayList<Node>();
        for(Integer count=0;count<list.size();count++)
        {
            if((total>=(list.get(count).weight))&&(total<=(list.get(count).weight+list.get(count).range)))
            {
                result.add(list.get(count));
            }

        }
        return result;

    }
}
