layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
    form = layui.form,
    table = layui.table;

    /**
     * 歌单列表展示
     */
    table.render({
    elem: '#musicList',
    url: ctx+'/list/query',
    toolbar: '#toolbarDemo',
    defaultToolbar: ['filter', 'exports', 'print', {
    title: '提示',
    layEvent: 'LAYTABLE_TIPS',
    icon: 'layui-icon-tips'
}],
    cols: [[
{type: "checkbox", width: 50},
{field: 'listName', width: 80, title: '歌单名称', sort: true},
{field: 'listInfo', width: 150, title: '歌单介绍'},
{field: 'type', width: 80, title: '音乐类型', sort: true},
{title: '操作', minWidth: 150, toolbar: '#musicList', align: "center"}
    ]],
    limits: [10, 15, 20, 25, 50, 100],
    limit: 15,
    page: true,
    skin: 'line'
});

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
    var result = JSON.stringify(data.field);
    layer.alert(result, {
    title: '最终的搜索信息'
});

    //执行搜索重载
    table.reload('currentTableId', {
    page: {
    curr: 1
}
    , where: {
    searchParams: result
}
}, 'data');

    return false;
});

    /**
     * toolbar监听事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
    if (obj.event === 'add') {  // 监听添加操作
    var index = layer.open({
    title: '添加用户',
    type: 2,
    shade: 0.2,
    maxmin:true,
    shadeClose: true,
    area: ['100%', '100%'],
    content: '../page/table/add.html',
});
    $(window).on("resize", function () {
    layer.full(index);
});
} else if (obj.event === 'delete') {  // 监听删除操作
    var checkStatus = table.checkStatus('currentTableId')
    , data = checkStatus.data;
    layer.alert(JSON.stringify(data));
}
});

    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (obj) {
    console.log(obj)
});

    table.on('tool(currentTableFilter)', function (obj) {
    var data = obj.data;
    if (obj.event === 'edit') {

    var index = layer.open({
    title: '编辑用户',
    type: 2,
    shade: 0.2,
    maxmin:true,
    shadeClose: true,
    area: ['100%', '100%'],
    content: '../page/table/edit.html',
});
    $(window).on("resize", function () {
    layer.full(index);
});
    return false;
} else if (obj.event === 'delete') {
    layer.confirm('真的删除行么', function (index) {
    obj.del();
    layer.close(index);
});
}
});

});