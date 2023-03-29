/**
 * 初始化字典详情对话框
 */
var DictInfoDlg = {
    count: $("#itemSize").val(),
    dictName: '',			//字典的名称
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
DictInfoDlg.newId = function () {
    if (this.count == undefined) {
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
DictInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#dictItem").attr("id", this.newId());
};

/**
 * 删除item
 */
DictInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DictInfoDlg.clearNullDom = function () {
    $("[name='dictItem']").each(function () {
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        if (num == '' || name == '') {
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
DictInfoDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function () {
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        mutiString = mutiString + (num + ":" + name + ";");
    });
    this.dictName = $("#dictName").val();
    this.mutiString = mutiString;
};

/**
 * 提交添加/修改字典
 */
DictInfoDlg.editSubmit = function () {
    this.collectData();

    var subUrl = "/dict/update";
    var subAct = "修改";
    if ($.isEmptyObject($("#id").val())) {
        subUrl = "/dict/add";
        subAct = "添加";
    }
    var ajax = new $ax(subUrl, function (data) {
        Feng.success(subAct + "成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    }, function (data) {
        Feng.error(subAct + "失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictId', $("#dictId").val());
    ajax.set('dictName', this.dictName);
    ajax.set('dictValues', this.mutiString);
    ajax.start();
};
