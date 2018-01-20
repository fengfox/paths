package com.ftao.paths;

import com.ftao.paths.utils.HuffmanTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HuffmanTreeTest {






    @Test
    public void removeListTest()
    {
        List<HuffmanTree.Node> mainNodes=new ArrayList<HuffmanTree.Node>();
        List<HuffmanTree.Node> subNodes=new ArrayList<HuffmanTree.Node>();
        mainNodes.add(new HuffmanTree.Node("1",9,3));
        mainNodes.add(new HuffmanTree.Node("2",8,2));
        mainNodes.add(new HuffmanTree.Node("3",7,1));
        mainNodes.add(new HuffmanTree.Node("4",6,1));
        mainNodes.add(new HuffmanTree.Node("1",9,3));

        subNodes.add(new HuffmanTree.Node("1",9,3));
        //subNodes.add(new HuffmanTree.Node("1",9,3));
        subNodes.add(new HuffmanTree.Node("2",8,2));

        for(int i=0;i<HuffmanTree.removeList(mainNodes,subNodes).size();i++) {
            System.out.println(HuffmanTree.removeList(mainNodes, subNodes).get(i).toString());
        }
    }



}
