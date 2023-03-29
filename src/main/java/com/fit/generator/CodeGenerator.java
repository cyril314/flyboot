package com.fit.generator;


import com.fit.core.util.ToolUtil;
import com.fit.generator.config.GeneratorConfig;

/**
 * 代码生成器,可以生成实体,dao,service,controller,html,js
 *
 * @author Aim
 * @Date 2017/5/21 12:38
 */
public class CodeGenerator {

    public static void main(String[] args) {

        /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
         */
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.doMpGeneration();

        /**
         * 生成器:
         *      代码生成器可以生成controller,html页面,页面对应的js
         */
        generatorConfig.doGeneration();
    }
}