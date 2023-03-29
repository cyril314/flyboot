@/*
    上传excel参数的说明:
    name : 名称
    id : excel的id
@*/
@var spaceCss = "";
@var btnType = "";
@var faIcon = "";
@if(isEmpty(space)){
@   spaceCss = "";
@}else{
@   spaceCss = "button-margin";
@}
@if(isEmpty(btnCss)){
@   btnType = "primary";
@}else{
@   btnType = btnCss;
@}
@if(isEmpty(icon)){
@   faIcon = "fa-upload";
@}else{
@   faIcon = icon;
@}
<a type="button" class="btn btn-${btnType} ${spaceCss}" id="${id}BtnId">
    <i class="fa ${faIcon}"></i>&nbsp;${name}
</a>


