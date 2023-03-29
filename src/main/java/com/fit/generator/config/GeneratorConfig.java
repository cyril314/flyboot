package com.fit.generator.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;

/**
 * 默认的代码生成的配置
 *
 * @author Aim
 * @date 2017-10-28-下午8:27
 */
public class GeneratorConfig extends AbstractGeneratorConfig {

    @Value("${spring.datasource.driverClassName}")
    private String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private String projectPath = System.getProperty("user.dir");

    protected void globalConfig() {
        globalConfig.setOutputDir(projectPath + "/src/main/java");//写自己项目的绝对路径,注意具体到java目录
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("Aim");
    }

    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/fly?characterEncoding=utf8");
    }

    protected void strategyConfig() {
        strategyConfig.setTablePrefix(new String[]{"sys_"});// 此处可以修改为您的表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
    }

    protected void packageConfig() {
        packageConfig.setParent(null);
        packageConfig.setEntity("com.fit.admin.common.persistence.model");
        packageConfig.setMapper("com.fit.admin.common.persistence.dao");
        packageConfig.setXml("com.fit.admin.common.persistence.dao.mapping");
    }

    protected void contextConfig() {
        contextConfig.setProPackage("com.fit.admin");
        contextConfig.setCoreBasePackage("com.fit.core");
        contextConfig.setBizChName("字典管理");
        contextConfig.setBizEnName("sysDict");
        contextConfig.setModuleName("system");
        contextConfig.setProjectPath(projectPath);//写自己项目的绝对路径
        contextConfig.setEntityName("SysDict");
        sqlConfig.setParentMenuName(null);//这里写已有菜单的名称,当做父节点

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(true);
        contextConfig.setDaoSwitch(true);
        contextConfig.setServiceSwitch(true);

        /**
         * 生成器开关
         */
        contextConfig.setControllerSwitch(true);
        contextConfig.setIndexPageSwitch(true);
        contextConfig.setAddPageSwitch(true);
        contextConfig.setEditPageSwitch(true);
        contextConfig.setJsSwitch(true);
        contextConfig.setInfoJsSwitch(true);
        contextConfig.setSqlSwitch(true);
    }

    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        contextConfig();
    }
}
