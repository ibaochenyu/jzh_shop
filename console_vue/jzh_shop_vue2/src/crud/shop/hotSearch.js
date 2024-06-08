export const tableOption = {
  searchMenuSpan: 6,
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
  column: [
    {
      label: '生产时间',
      prop: 'produce_date',
      search: true
    },
    {
      label: '真实item的id',
      prop: 'truth_item_id',
      search: true
    },
    {
      label: '真实worker的id',
      prop: 'truth_worker_id',
      search: true
    },
    {
      label: '生产数量',
      prop: 'produce_count',
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
