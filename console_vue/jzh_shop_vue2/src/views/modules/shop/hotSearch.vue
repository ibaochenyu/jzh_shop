<template>
  <div class="mod-hotSearcch">

    <avue-crud ref="crud"
               :page="page"
               :data="dataList"
               :table-loading="dataListLoading"
               :option="tableOption"
               @search-change="searchChange"
               @on-load="getDataList"
               @refresh-change="refreshChange"
               >
<!--               @selection-change="selectionChange">-->
      <template slot="menuLeft">
<!--        <el-button v-if="isAuth('admin:hotSearch:save')"-->
        <el-button v-if="true"
                   type="primary"
                   size="small"
                   icon="el-icon-plus"
                   @click="addOrUpdateHandle()">新增</el-button>

        <el-button type="danger"
                   size="small"
                   @click.stop="deleteHandle"
                   :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </template>

<!--      <template slot-scope="scope"-->
<!--                slot="status">-->
<!--        <el-tag v-if="scope.row.status === 0"-->
<!--                size="small"-->
<!--                type="danger">未启用</el-tag>-->
<!--        <el-tag v-else-->
<!--                size="small">启用</el-tag>-->
<!--      </template>-->

      <template slot-scope="scope"
                slot="menu">
<!--        <el-button v-if="isAuth('admin:hotSearch:update')"-->
        <el-button v-if="true"
                   type="primary"
                   size="small"
                   icon="el-icon-edit"
                   @click="addOrUpdateHandle(scope.row.hotSearchId)">修改</el-button>
<!--        <el-button v-if="isAuth('admin:hotSearch:delete')"-->
        <el-button v-if="true"
                   type="danger"
                   icon="el-icon-delete"
                   size="small"
                   @click.stop="deleteHandle(scope.row,scope.index)">删除</el-button>
      </template>

    </avue-crud>

<!--    &lt;!&ndash; 弹窗, 新增 / 修改 &ndash;&gt;-->
<!--    <add-or-update v-if="addOrUpdateVisible"-->
<!--                   ref="addOrUpdate"-->
<!--                   @refreshDataList="getDataList"></add-or-update>-->
  </div>
</template>

<script>
//import { tableOption } from '@/crud/shop/hotSearch'
import { tableOption } from '../../../crud/shop/hotSearch'
import AddOrUpdate from './hotSearch-add-or-update'
export default {
  data () {
    return {
      dataForm: {//???

      },
      dataList: [],
      page: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 10 // 每页显示多少条
      },
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      tableOption: tableOption
    }
  },
  components: {
    AddOrUpdate
  },
  methods: {
    // 获取数据列表
    getDataList (page, params, done) {
      this.dataListLoading = true
      this.$http({
        //url: this.$http.adornUrl('/admin/hotSearch/page'),
        //url: this.$http.adornUrl('/getOneProduce'),
        url: this.$http.adornUrl('/getOneProduceRtResult'),

        method: 'get',
        params: this.$http.adornParams(Object.assign({
          // current: page ? page.currentPage : 1,
          // size: page ? page.pageSize : 20
        }, params))
      }).then(({ data }) => {
        // this.page.total = data.total
        // this.page.pageSize = data.size
        // this.page.currentPage = data.current
        this.page.total = 2
        this.page.pageSize = 10
        this.page.currentPage = 1

        //this.dataList = data.records
        this.dataList = data.data

        this.dataListLoading = false
        if (done) {
          done()
        }
      })
    },

    // 点击查询
    searchChange (params, done) {
      this.getDataList(this.page, params, done)
    },
    refreshChange () {
      this.getDataList(this.page)
    }
  }
}
</script>
