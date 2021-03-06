package com.ftao.paths.utils;

import javax.tools.Tool;
import javax.validation.constraints.Null;
import java.util.*;

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

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", range=" + range +
                    '}';
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
     *获取符合条件的node列表,已不使用.
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

    /***
     * 查询出所有符合条件的root节点。
     * @param root
     * @param total
     * @return
     */
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

    public static Node rootFindByWeigth (Node root,Integer weight)
    {

        List<Node> nodes=breadthFirst(root);
        /*
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getLeftChild().getWeight()==weight||nodes.get(i).getRightChild().getWeight()==weight)
            {
                return nodes.get(i);
            }
        }
        */
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getWeight()==weight)
            {
                return nodes.get(i);
            }
        }
        return null;

    }
    /***
     * 分配range中的值,让weight的总值能够=total的值得
     * @param nodes
     * @param total
     * @return
     */
    public static List<Node> distributionRange(List<Node> nodes,Integer total)
    {

        Integer sum=0;
        Integer dif=0;
        Integer j=0;
        Random random=new Random();
        Integer re=0;
        for(int i=0;i<nodes.size();i++)
        {
            sum+=nodes.get(i).getWeight();
        }
        dif=total-sum;
        while(dif>nodes.get(j).getRange())
        {
            //随机获得一个在ran范围中的值
            re=random.nextInt(nodes.get(j).getRange()+1);
            //总值=总值-ran
            dif=dif-re;
            //更新路径上路径值+随机数和ran值-随机数
            nodes.get(j).setRange(nodes.get(j).getRange()-re);
            nodes.get(j).setWeight(nodes.get(j).getWeight()+re);
            //移到下一个数
            j=(j+1)%nodes.size();

        }
        //更新最后一个路径上路径值+随机数和ran值-随机数
        nodes.get(j).setRange(nodes.get(j).getRange()-dif);
        nodes.get(j).setWeight(nodes.get(j).getWeight()+dif);
        return nodes;

    }
    public static List<Node> removeList(List<Node> mainNodes,List<Node>subNodes)
    {
        Integer mainSize=mainNodes.size();
        Integer subSize=subNodes.size();

        for(int j=0;j<subSize;j++)
        {
            for(int i=0;i<mainNodes.size();i++)
            {
                if(mainNodes.get(i).getData()==subNodes.get(j).getData())
                {
                    mainNodes.remove(i);
                    break;
                }
            }
        }
        return mainNodes;
    }


    /***
     * 找出叶子节点.
     * @param nodes
     * @return
     */
    public static List<Node> leafNodes(List<Node> nodes)
    {
        List<Node> nodeList=new ArrayList<Node>();
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getData()!= null)
            {
                //nodes.get(i).setLeftChild(null);
                //nodes.get(i).setRightChild(null);
                nodeList.add(nodes.get(i));
            }
        }
        return nodeList;
    }
    /***
     * 获取和多个totals匹配的子树,还未完成
     * @param nodes
     * @param totals
     * @return
     */
    public static List<List<Node>> nodesSplit(List<Node> nodes,List<Integer> totals)
    {
        //先将nodes转换成主树
        //查询符合条件的节点,随机选一个
        //将此节点作为子树root,在主树中删除此子树,并返回子树root
        //将主树初始化,重新设置成nodes
        //返回重新开始第一步
        List<Node> tmpNodes= nodes;
        List<List<Node>> nodesList = new ArrayList<List<Node>>();
        for(int i=0;i<totals.size();i++) {

            Node root = createTree(tmpNodes);
            List<Node> results=findNode2(root,totals.get(i));
            Node result= results.get(ToolUitl.integerRandom(results.size()));
            //Node treeRoot=rootFindByWeigth(root,result.getWeight());
            List<Node> rootList=new ArrayList<Node>();
            List<Node> rootTree=new ArrayList<Node>();
            rootList=breadthFirst(root);
            rootTree=breadthFirst(result);
            rootList=leafNodes(rootList);
            rootTree=leafNodes(rootTree);
            nodesList.add(rootTree);
            tmpNodes=removeList(rootList,rootTree);
        }
        return nodesList;
    }

}
