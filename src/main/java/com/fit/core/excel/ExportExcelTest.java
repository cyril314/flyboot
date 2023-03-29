package com.fit.core.excel;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportExcelTest {

    private String projectPath = System.getProperty("user.dir");

    public void main() throws Exception {
        List<WebDto> list = new ArrayList<WebDto>();
        list.add(new WebDto("知识林", "http://www.zslin.com", "admin", "111111", 555));
        list.add(new WebDto("权限系统", "http://basic.zslin.com", "admin", "111111", 111));
        list.add(new WebDto("校园网", "http://school.zslin.com", "admin", "222222", 333));

        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "网站信息表");
        map.put("total", list.size() + " 条");
        map.put("date", getDate());
        FileOutputStream out = new FileOutputStream(projectPath + "/log/out.xls");
        ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "web-info-template.xls",
                out, list, WebDto.class, true, false, false);
    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date());
    }
}