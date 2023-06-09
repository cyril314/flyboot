/**
 * 初始化报销管理详情对话框
 */
var ExpenseInfoDlg = {
    expenseInfoData: {}
};

/**
 * 清除数据
 */
ExpenseInfoDlg.clearData = function () {
    this.expenseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpenseInfoDlg.set = function (key, val) {
    this.expenseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExpenseInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ExpenseInfoDlg.close = function () {
    parent.layer.close(window.parent.Expense.layerIndex);
}

/**
 * 收集数据
 */
ExpenseInfoDlg.collectData = function () {
    this.set('id').set('money').set('desc');
}

/**
 * 提交添加/修改
 */
ExpenseInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    var subUrl = "/process/update";
    var subAct = "修改";
    if ($.isEmptyObject($("#id").val())) {
        subUrl = "/process/add";
        subAct = "添加";
    }

    //提交信息
    var ajax = new $ax(subUrl, function (data) {
        Feng.success(subAct + "成功!");
        window.parent.Expense.table.refresh();
        ExpenseInfoDlg.close();
    }, function (data) {
        Feng.error(subAct + "失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.expenseInfoData);
    ajax.start();
}

$(function () {

});
