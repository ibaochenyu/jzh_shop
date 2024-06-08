export const tableOption = {
  searchMenuSpan: 6, // 设置搜索表单项的栅格宽度，表示该项在布局中占据1/4的宽度（24格中的6格）。
  columnBtn: false,  // 控制是否显示列操作按钮，一般用于列的显隐控制。
  border: true,      // 控制表格是否显示边框，使表格更加醒目和易于区分。
  index: false,      // 控制是否显示索引列（行号），默认不显示。
  selection: true,   // 控制是否显示多选框列，允许用户选择多行数据。
  indexLabel: '序号', // 当索引列（行号）启用时，设置其列头显示的文字标签。
  stripe: true,      // 控制表格是否显示斑马纹效果（隔行变色），提高表格的可读性。
  menuAlign: 'center', // 设置操作列中按钮的对齐方式，可以是 'left'（左对齐）、'right'（右对齐）或 'center'（居中对齐）。
  align: 'center',   // 设置表格内容的对齐方式，通常为 'left'（左对齐）、'right'（右对齐）或 'center'（居中对齐）。
  addBtn: false,     // 控制是否显示添加按钮，默认不显示。
  editBtn: false,    // 控制是否显示编辑按钮，默认不显示。
  delBtn: false,     // 控制是否显示删除按钮，默认不显示。

  //Element UI 是一个基于 Vue.js 的 UI 组件库，为开发者提供了一套高质量的、易用的前端组件，旨在快速构建现代化的 Web 应用。
  //Element UI提供丰富的 UI 组件（如表单、表格、对话框等）。
  //Avue 是基于 Vue.js 和 Element UI 的高级框架，进一步封装和扩展了 Element UI 的组件，专注于后台管理系统的快速开发。
  column: [
    {//后端传回什么，这里就写什么。
      label: '生产时间',
      prop: 'produceDate',
      search: true
    },
    {
      label: '真实item的id',
      prop: 'truthItemId',
      search: true
    },
    {
      label: '真实worker的id',
      prop: 'truthWorkerId',
      search: true
    },
    {
      label: '生产数量',
      prop: 'produceCount',
      search: true
    }
    // {
    //   label: '录入时间',
    //   prop: 'recDate',
    //   sortable: true
    // }
    // {
    //   label: '顺序',
    //   prop: 'seq',
    //   sortable: true
    // },
    // {
    //   label: '启用状态',
    //   prop: 'status',
    //   type: 'select',
    //   slot: true,
    //   search: true,
    //   dicData: [
    //     {
    //       label: '未启用',
    //       value: 0
    //     }, {
    //       label: '启用',
    //       value: 1
    //     }
    //   ]
    // }
  ]
}
