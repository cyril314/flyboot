@/*
    时间查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    isTime : 日期是否带有小时和分钟(true/false)
    pattern : 日期的正则表达式(例如:"YYYY-MM-DD")
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button" th:text="${name}">按钮名</button>
    </div>
    <input type="text" class="form-control layer-date" onclick="laydate({istime: ${isTime}, format: '${pattern}'})" th:id="${id}"/>
</div>