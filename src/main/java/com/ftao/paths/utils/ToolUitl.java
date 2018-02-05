package com.ftao.paths.utils;

import com.ftao.paths.domain.Path;

import java.util.List;
import java.util.Random;

public class ToolUitl {

    public static int integerRandom(Integer number)
    {
        //生成一个随机数字,在[0,number)之间
        Random random=new Random();
        int result=random.nextInt(number);
        return result;
    }
}
