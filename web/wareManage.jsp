<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>商品信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        function dateFormate(val) {
            var time = '';
            var flag = true;
            for (var i in val) {
                if (null !=val[i] && "" != val[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return "";
            }
            var year = parseInt(val.year)+1900;
            var month = (parseInt(val.month)+1);
            month= month > 9 ? month : ('0'+month);
            var date = parseInt(val.date);
            date = date > 9 ? date : ('0'+date);
            time = year + '-' + month + '-'+ date;
            return time;
        }


        function searchGood(){
            $('#dg').datagrid('load',{
                pN:$('#s_pN').val(),
                gName:$('#s_gName').val(),
                isInBound:$('#s_isInBound').combobox("getValue"),
                inBoundDate:$('#s_inBoundDate').datebox("getValue"),
                inBoundDate1:$('#s_inBoundDate_1').datebox("getValue"),
                deptno:$('#s_deptno').combobox("getValue")
            });
        }


        function deleteGood(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i]. gId);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("goodDelete",{delIds:ids},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示',result.errorMsg);
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="商品信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="goodList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="gId" width="50" align="center">编号</th>
        <th field="pN" width="100" align="center">商品编号</th>
        <th field="gName" width="100" align="center">商品名称</th>
        <th field="isInBound" width="100" align="center">是否在库</th>
        <th field="inBoundDate" width="100" align="center" formatter="dateFormate">入库日期</th>
        <th field="deptno" width="100" align="center" >仓库编号</th>
        <th field="madeIn" width="150" align="center">出产地</th>
        <th field="gDesc" width="250" align="center">商品详情</th>
    </tr>
    </thead>
</table>

<div id="tb">

    <div>
        <a href="" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteGood()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>

    <div>&nbsp;商品编号：&nbsp;<input type="text" name="s_pN" id="s_pN" size="10"/>
        &nbsp;商品名称：&nbsp;<input type="text" name="s_gName" id="s_gName" size="10"/>
        &nbsp;是否在库：&nbsp;<select class="easyui-combobox" id="s_isInBound" name="s_isInBound" editable="false" panelHeight="auto">
            <option value="">请选择...</option>
            <option value="0">在库</option>
            <option value="1">不在库</option>
        </select>
        &nbsp;入库日期： <input class="easyui-datebox" name="s_inBoundDate" id="s_inBoundDate" editable="false" size="10"/> <input class="easyui-datebox" name="s_inBoundDate_1" id="s_inBoundDate_1" editable="false" size="10"/>
        &nbsp;仓库编号：&nbsp;<input class="easyui-combobox" id="s_deptno" name="s_deptno" size="10" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/>

        <a href="javascript:searchGood()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

</body>
</html>
