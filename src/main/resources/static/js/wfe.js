var datagrid;
	$(function() {
		$('#datagrid').datagrid({
			url: '/datagrid',
			methd: 'get',
			title: '标题',
			iconCls: 'icon-save',
			pagination: true,
			pageSize: 10,
			pageList: [10, 20, 30, 40, 50],
			fit: true,
			fitColumns: true, //没有横向滚动条
			nowarp: false, //false 自动换行；true 不自动换行
			border: false,
			idField: 'id', //选中，然后翻页，会自动记住所有的ID，否则只会选中当前页的
			columns: [ //两个中括号，可以生成多级表头（合并单元格）
				[{
					title: '编号',
					field: 'id',
					width: 300
				}, {
					title: '姓名',
					field: 'name',
					width: 300
				}, {
					title: '密码',
					field: 'password',
					width: 300
				}]
			]
		});
	});