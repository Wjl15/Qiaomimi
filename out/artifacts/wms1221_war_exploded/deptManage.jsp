<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>仓库信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>	<script type="text/javascript">
    function searchDept(){
        $('#dg').datagrid('load',{
            deptName:$('#s_deptName').val()
        });
    }

    function deleteDept(){
        var selectedRows=$("#dg").datagrid('getSelections');
        if(selectedRows.length==0){
            $.messager.alert("系统提示","请选择要删除的数据！");
            return;
        }
        var strIds=[];
        for(var i=0;i<selectedRows.length;i++){
            strIds.push(selectedRows[i].deptno);
        }
        var ids=strIds.join(",");
        $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
            if(r){
                $.post("deptDelete",{delIds:ids},function(result){
                    if(result.success){
                        $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                        $("#dg").datagrid("reload");
                    }else{
                        $.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].deptName+'</font>'+result.errorMsg);
                    }
                },"json");
            }
        });
    }

    function openDeptModifyDialog(){
        var selectedRows=$("#dg").datagrid('getSelections');
        if(selectedRows.length!=1){
            $.messager.alert("系统提示","请选择一条要编辑的数据！");
            return;
        }
        var row=selectedRows[0];
        $("#dlg").dialog("open").dialog("setTitle","编辑仓库信息");
        $("#fm").form("load",row);
        url="deptSave?deptno="+row.deptno;
    }


    function closeDeptDialog(){
        $("#dlg").dialog("close");
        resetValue();
    }

    function resetValue(){
        $("#deptName").val("");
        $("#deptDesc").val("");
    }


    function saveDept(){
        $("#fm").form("submit",{
            url:url,
            onSubmit:function(){
                return $(this).form("validate");
            },
            success:function(result){
                if(result.errorMsg){
                    $.messager.alert("系统提示",result.errorMsg);
                    return;
                }else{
                    $.messager.alert("系统提示","保存成功");
                    resetValue();
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                }
            }
        });
    }

    function openDeptAddDialog(){
        $("#dlg").dialog("open").dialog("setTitle","添加仓库信息");
        url="deptSave";
    }
</script>

</head>
<body>
<table id="dg" title="仓库信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="deptList" toolbar="#tb">
    <thead>
    <tr>
        <th field="deptno" width="50">编号</th>
        <th field="deptname" width="100">仓库名称</th>
        <th field="deptdesc" width="250">仓库描述</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openDeptAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openDeptModifyDialog()"  class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteDept()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;仓库名称：&nbsp;<input type="text" name="s_deptName" id="s_deptName"/><a href="javascript:searchDept()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr>
                <td>仓库名称：</td>
                <td><input type="text" name="deptName" id="deptName" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td valign="top">仓库描述：</td>
                <td><textarea rows="7" cols="30" name="deptDesc" id="deptDesc"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveDept()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDeptDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>

