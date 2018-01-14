package com.ftao.paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class PathsController {
    @Autowired
    private PathRepository pathRepository;

    /***
     * 获取所有路程
     * @return
     */
    @GetMapping("/paths")
    public List<Path> findAllPath()
    {
        return pathRepository.findAll();
    }

    @PostMapping(value="/paths/add")
    public Path pathAdd(@RequestParam("length") Integer length,@RequestParam("type") Integer type,@RequestParam("ran") Integer ran,@RequestParam("route") String route,
                        @RequestParam("remark") String remark)
    {
        Path path=new Path();
        path.setLength(length);
        path.setRan(ran);
        path.setRemark(remark);
        path.setRoute(route);
        path.setType(type);
        return pathRepository.save(path);
    }

    /***
     *做哈夫曼树，并选出符合total值的树
     * @param paths
     * @param total
     * @return
     */
    public List<HuffmanTree.Node> pathsSort(List<Path> paths,Integer total)
    {
        List<HuffmanTree.Node> nodes=new ArrayList<HuffmanTree.Node>();
        for(int i=0;i<paths.size();i++)
        {
            nodes.add(new HuffmanTree.Node(paths.get(i).getId(),paths.get(i).getLength(),paths.get(i).getRan()));
        }
        HuffmanTree.Node root=HuffmanTree.createTree(nodes);
        return HuffmanTree.findNode2(root,total);


    }

    @GetMapping(value="/paths/testNode")
    public List<HuffmanTree.Node> testSort()
    {
        List<Path> paths=new ArrayList<Path>();
        paths=this.findAllPath();
        Integer total=154;
        return this.pathsSort(paths,total);

    }

    /***
     * 获取计算出的路径，并对路径进行判断，如果路径总的spend大于或者等于指定数值，则重新计算路径。
     * @param total
     * @return
     */
    @PostMapping(value="/paths/getPaths")
    public List<Path> getPaths(@RequestParam("total") Integer total)
    {
        List<Path> paths=new ArrayList<Path>();
        do {
            paths = this.pathSort(total);
        }
        //检查是否花费的天数大于44(假定每天2个spend,22个工作日为44个spend)
        while(this.checkSpend(paths,44)==false);


        return paths;


    }

    /***
     * 根据给出的值计算路径。
     * @param total
     * @return
     */
    public List<Path> pathSort( Integer total)
    {
        try {
            List<Path> paths = new ArrayList<Path>();
            paths = this.findAllPath();
            //生成一个随机数字,在1-paths.size()之间
            paths = this.pathsRandom(paths);
            List<HuffmanTree.Node> nodes = this.pathsSort(paths, total);
            //System.out.println(nodes.size());
            //System.out.println(nodes.get(0));
            //随机选一颗树作为解
            Random random = new Random();
            int result = random.nextInt(nodes.size());
            return rebuildPath(toPaths(nodes.get(result)), total);
            //return nodes;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /***
     * 随机删除path列表中的一个值,用于构建有随机性的huffman树
     * @param paths
     * @return
     */
    private List<Path> pathsRandom(List<Path> paths)
    {
        //生成一个随机数字,在1-paths.size()之间
        Random random=new Random();
        int result=random.nextInt(paths.size());
        paths.remove(result);

        return paths;
    }

    /***
     * 将nodes转成paths,还未作完
     * @param node
     * @return
     */
    private List<Path> toPaths(HuffmanTree.Node node)
    {
        List<HuffmanTree.Node> nodes=new ArrayList<HuffmanTree.Node>();
        nodes =HuffmanTree.breadthFirst(node);
        //打印node的个数
        List<Path> paths=new ArrayList<Path>();
        Path path=new Path();
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getData()!=null) {
                path=pathRepository.findOne(Integer.parseInt(nodes.get(i).getData().toString()));
                //System.out.println(path.getId()+"        "+path.getLength());
                paths.add(path);
            }
        }
        return paths;

    }

    /***
     * 从将获取到的paths里的length调整为指定的total值
     * @param paths
     * @param total
     * @return
     */
    private List<Path> rebuildPath(List<Path> paths,Integer total)
    {
        Integer sum=0;
        Integer dif=0;
        Integer j=0;
        Random random=new Random();
        Integer re=0;
        for(int i=0;i<paths.size();i++)
        {
            sum+=paths.get(i).getLength();
        }
        dif=total-sum;
        while(dif>paths.get(j).getRan())
        {
            //随机获得一个在ran范围中的值
            re=random.nextInt(paths.get(j).getRan()+1);
            //总值=总值-ran
            dif=dif-re;
            //更新路径上路径值+随机数和ran值-随机数
            paths.get(j).setRan(paths.get(j).getRan()-re);
            paths.get(j).setLength(paths.get(j).getLength()+re);
            //移到下一个数
            j=(j+1)%paths.size();

        }
        //更新最后一个路径上路径值+随机数和ran值-随机数
        paths.get(j).setRan(paths.get(j).getRan()-dif);
        paths.get(j).setLength(paths.get(j).getLength()+dif);
        return paths;


    }

    /***
     * 检查paths中行程的花费时间是否大于指定时间(每个月可以用的总时间)
     * 如果大于则返回false,小于等于返回false
     * @param paths
     * @param totalSpend
     * @return
     */
    private boolean checkSpend(List<Path> paths,Integer totalSpend )
    {
        Integer sum=0;
        for(int i=0;i<paths.size();i++)
        {
            sum=sum+paths.get(i).getSpend();
        }
        if(sum>totalSpend)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
