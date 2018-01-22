package com.ftao.paths.utils;

import com.ftao.paths.domain.Path;

import java.util.List;
import java.util.Random;

public class ToolUitl {

    public static Integer integerRandom(Integer number)
    {
        //生成一个随机数字,在[0,number)之间
        Random random=new Random();
        Integer result=random.nextInt(number);
        return result;
    }
}
