package com.fit.modular.code.controller;

import com.fit.core.base.controller.BaseController;
import com.fit.core.datasource.DruidProperties;
import com.fit.generator.config.WebGeneratorConfig;
import com.fit.generator.model.GenBean;
import com.fit.modular.code.factory.DefaultTemplateFactory;
import com.fit.modular.code.service.TableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成控制器
 *
 * @author Aim
 * @Date 2017年11月30日16:39:19
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

    private static String PREFIX = "/code";

    @Autowired
    private TableService tableService;

    @Autowired
    private DruidProperties druidProperties;

    /**
     * 跳转到代码生成主页
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("params", DefaultTemplateFactory.getDefaultParams());
        model.addAttribute("templates", DefaultTemplateFactory.getDefaultTemplates());
        return PREFIX + "/code.html";
    }

    /**
     * 生成代码
     */
    @ApiOperation("生成代码")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public Object generate(GenBean GenBean) {
        GenBean.setUrl(druidProperties.getUrl());
        GenBean.setUserName(druidProperties.getUsername());
        GenBean.setPassword(druidProperties.getPassword());
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(GenBean);
        webGeneratorConfig.doMpGeneration();
        webGeneratorConfig.doGeneration();
        return SUCCESS_TIP;
    }
}
