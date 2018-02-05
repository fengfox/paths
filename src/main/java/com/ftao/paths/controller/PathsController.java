package com.ftao.paths.controller;

import com.ftao.paths.service.PathsService;
import com.ftao.paths.utils.HuffmanTree;
import com.ftao.paths.domain.Path;
import com.ftao.paths.repository.PathRepository;
import com.ftao.paths.utils.ToolUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//偷懒没写service,发现还是不行,以后新的方法再写...
@RestController
public class PathsController {
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private PathsService pathsService;

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
    public List<HuffmanTree.Node> pathsSort(List<Path> paths, Integer total)
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

    @PostMapping(value="/paths/getPaths")
    public List<Path> pathSort(@RequestParam("total") Integer total)
    {
        List<Path> paths=new ArrayList<Path>();
        paths=this.findAllPath();
        //生成一个随机数字,在1-paths.size()之间
        paths=this.pathsRandom(paths);
        List<HuffmanTree.Node> nodes=this.pathsSort(paths,total);
        //System.out.println(nodes.size());
        //System.out.println(nodes.get(0));
        //随机选一颗树作为解
        Random random=new Random();
        int result=random.nextInt(nodes.size());
        return rebuildPath(toPaths(nodes.get(result)),total);
        //return nodes;

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
    private List<Path> rebuildPath(List<Path> paths,int total)
    {
        int sum=0;
        int dif=0;
        int j=0;
        Random random=new Random();
        int re=0;
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
    @GetMapping(value="/paths/test")
    public List<HuffmanTree.Node> testFunction()
    {
        List<HuffmanTree.Node> mainNodes=new ArrayList<HuffmanTree.Node>();
        List<HuffmanTree.Node> subNodes=new ArrayList<HuffmanTree.Node>();
        mainNodes.add(new HuffmanTree.Node("1",9,3));
        mainNodes.add(new HuffmanTree.Node("2",8,2));
        mainNodes.add(new HuffmanTree.Node("3",7,1));
        mainNodes.add(new HuffmanTree.Node("4",6,1));
        mainNodes.add(new HuffmanTree.Node("1",9,3));
        subNodes.add(new HuffmanTree.Node("1",9,3));
        subNodes.add(new HuffmanTree.Node("1",9,3));
        subNodes.add(new HuffmanTree.Node("2",8,2));
        return HuffmanTree.removeList(mainNodes, subNodes);

    }

    /***
     *根据全部里程和领导里程,计算出来相对应的路程,第一个路径组合是是(全部-领导)的路程,第二个是领导路程
     * @param totalLength 全部里程
     * @param leaderLength 领导里程
     * @return
     */
    @PostMapping(value="/paths/calculate")
    public List<Path> pathsCalculate(@PathParam("totalLength") int totalLength,@PathParam("leaderLength") int leaderLength)
    {
        List<Integer> totals=new ArrayList<Integer>();
        totals.add(totalLength-leaderLength);
        totals.add(leaderLength);
        List<Path> tmppaths=new ArrayList<Path>();
        List<Path> paths=new ArrayList<Path>();
        tmppaths=pathsService.pathsFindAll();
        int count=0;
        for(int i=0;i<tmppaths.size();i++)
        {

            for(int j=0;j<tmppaths.get(i).getTimes();j++)
            {
                Path path=new Path();
                path.setId(tmppaths.get(i).getId());
                path.setRemark( tmppaths.get(i).getRemark());
                path.setTimes( tmppaths.get(i).getTimes());
                path.setLength( tmppaths.get(i).getLength());
                path.setRan( tmppaths.get(i).getRan());
                path.setRoute( tmppaths.get(i).getRoute());
                path.setType( tmppaths.get(i).getType());
                paths.add(path);
            }
        }
        //随机删几个path
        paths.remove(ToolUitl.integerRandom(paths.size()));
        paths.remove(ToolUitl.integerRandom(paths.size()));
        List<HuffmanTree.Node> nodes=new ArrayList<HuffmanTree.Node>();
        //将paths转换成nodes
        for(int i=0;i<paths.size();i++)
        {
            HuffmanTree.Node node=new HuffmanTree.Node();
            node.setData(paths.get(i));
            node.setRange(paths.get(i).getRan());
            node.setWeight(paths.get(i).getLength());
            nodes.add(node);
        }

        return rebuildPath(pathsService.pathsCalculate(nodes,totals).get(0),totalLength-leaderLength);


    }



}