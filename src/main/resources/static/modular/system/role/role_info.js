/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
    roleInfoData: {},
    deptZtree: null,
    pNameZtree: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        },
        tips: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RolInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function (key, val) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};
RolInfoDlg.onDblClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
    $("#deptContent").fadeOut("fast");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#pName").attr("value", RolInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
RolInfoDlg.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};

/**
 * 显示父级菜单的树
 *
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function () {
    Feng.showInputTree("pName", "pNameContent");
};

/**
 * 收集数据
 */
RolInfoDlg.collectData = function () {
    this.set('id').set('name').set('pid').set('deptid').set('tips').set('num');
};

/**
 * 验证数据是否为空
 */
RolInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交修改
 */
RolInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    var subUrl = "/role/edit";
    var subAct = "修改";
    if ($.isEmptyObject($("#id").val())) {
        subUrl = "/role/add";
        subAct = "添加";
    }

    //提交信息
    var ajax = new $ax(subUrl, function (data) {
        Feng.success(subAct + "成功!");
        window.parent.Role.table.refresh();
        RolInfoDlg.close();
    }, function (data) {
        Feng.error(subAct + "失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

$(function () {
    Feng.initValidator("roleInfoForm", RolInfoDlg.validateFields);

    var deptTree = new $ZTree("deptTree", "/dept/tree");
    deptTree.bindOnClick(RolInfoDlg.onClickDept);
    deptTree.bindOnDblClick(RolInfoDlg.onDblClickDept)
    deptTree.init();
    RolInfoDlg.deptZtree = deptTree;

    var pNameTree = new $ZTree("pNameTree", "/role/roleTreeList");
    pNameTree.bindOnClick(RolInfoDlg.onClickPName);
    pNameTree.init();
    RolInfoDlg.pNameZtree = pNameTree;
});
