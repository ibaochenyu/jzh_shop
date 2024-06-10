export const tableOption = {
  // https://www.jianshu.com/p/a64ef0c4c4e7
  // labelWidth:10,
  // searchMenuSpan: 4,
  // searchSpan:6,
  // menuWidth: 350,
  columnBtn: false,
  border: true,
  index: false,
  selection: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  addBtn: false,
  editBtn: false,
  delBtn: false,

  //AVUE 隐藏table上的小按钮 https://www.cnblogs.com/guxingy/p/13964872.html


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
      //label: '真实item的id',
      label: '产品id',
      prop: 'truthStylerId',
      search: true
    },
    {
      //label: '真实worker的id',
      label: '工人id',
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
