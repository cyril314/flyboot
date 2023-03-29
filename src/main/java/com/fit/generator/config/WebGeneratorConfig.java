package com.fit.generator.config;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.fit.core.support.StrKit;
import com.fit.core.util.ToolUtil;
import com.fit.generator.model.GenBean;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * 默认的代码生成的配置
 *
 * @author Aim
 * @date 2017-10-28-下午8:27
 */
public class WebGeneratorConfig extends AbstractGeneratorConfig {

    private GenBean GenBean;

    @Value("${spring.datasource.driverClassName}")
    private String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public WebGeneratorConfig(GenBean GenBean) {
        this.GenBean = GenBean;
    }

    @Override
    protected void config() {
        /**
         * 数据库配置
         */
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(GenBean.getUserName());
        dataSourceConfig.setPassword(GenBean.getPassword());
        dataSourceConfig.setUrl(GenBean.getUrl());

        /**
         * 全局配置
         */
        globalConfig.setOutputDir(GenBean.getProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor(GenBean.getAuthor());
        contextConfig.setProPackage(GenBean.getProjectPackage());
        contextConfig.setCoreBasePackage(GenBean.getCorePackage());

        /**
         * 生成策略
         */
        if (GenBean.getIgnoreTabelPrefix() != null) {
            strategyConfig.setTablePrefix(new String[]{GenBean.getIgnoreTabelPrefix()});
        }
        strategyConfig.setInclude(new String[]{GenBean.getTableName()});
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        packageConfig.setParent(null);
        packageConfig.setEntity(GenBean.getProjectPackage() + ".modular.system.model");
        packageConfig.setMapper(GenBean.getProjectPackage() + ".modular.system.dao");
        packageConfig.setXml(GenBean.getProjectPackage() + ".modular.system.dao.mapping");

        /**
         * 业务代码配置
         */
        contextConfig.setBizChName(GenBean.getBizName());
        contextConfig.setModuleName(GenBean.getModuleName());
        contextConfig.setProjectPath(GenBean.getProjectPath());//写自己项目的绝对路径
        if (ToolUtil.isEmpty(GenBean.getIgnoreTabelPrefix())) {
            String entityName = StrKit.toCamelCase(GenBean.getTableName());
            contextConfig.setEntityName(StrKit.firstCharToUpperCase(entityName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entityName));
        } else {
            String entiyName = StrKit.toCamelCase(StrKit.removePrefix(GenBean.getTableName(), GenBean.getIgnoreTabelPrefix()));
            contextConfig.setEntityName(StrKit.firstCharToUpperCase(entiyName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entiyName));
        }
        sqlConfig.setParentMenuName(GenBean.getParentMenuName());//这里写已有菜单的名称,当做父节点

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(GenBean.getEntitySwitch());
        contextConfig.setDaoSwitch(GenBean.getDaoSwitch());
        contextConfig.setServiceSwitch(GenBean.getServiceSwitch());

        /**
         * 生成器开关
         */
        contextConfig.setControllerSwitch(GenBean.getControllerSwitch());
        contextConfig.setIndexPageSwitch(GenBean.getIndexPageSwitch());
        contextConfig.setAddPageSwitch(GenBean.getAddPageSwitch());
        contextConfig.setEditPageSwitch(GenBean.getEditPageSwitch());
        contextConfig.setJsSwitch(GenBean.getJsSwitch());
        contextConfig.setInfoJsSwitch(GenBean.getInfoJsSwitch());
        contextConfig.setSqlSwitch(GenBean.getSqlSwitch());
    }
}
