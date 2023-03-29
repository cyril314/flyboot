@/*
    选择查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">
            ${name}
        </button>
    </div>
    <style>
        .chosen-container-single .chosen-single {
            position: relative;
            display: block;
            overflow: hidden;
            padding: 0 0 0 8px;
            border: 1px solid #e5e6e7;
            border-radius: 0px;
            background-color: #fff;
            background: none;
            background: none;
            background-clip: padding-box;
            -webkit-box-shadow: none;
            box-shadow: none;
            color: #444;
            text-decoration: none;
            white-space: nowrap;
            line-height: 32px;
        }
    </style>

    <select class="form-control" id="${id}" style="border-radius:0px" >
        ${tagBody!}
    </select>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}