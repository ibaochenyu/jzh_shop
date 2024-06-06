<template>
  <div>
    <h2>主页333</h2>
    <!-- 使用 router-link 组件来导航. -->
    <router-link to="/about">关于</router-link>
    <br/>
  </div>

  <div class="mod-hotSearcch">
<!--    https://blog.csdn.net/huanhuan03/article/details/107831516-->
<!--    vue项目组件库Avue表格组件avue-crud-->
<!--    https://blog.csdn.net/weixin_56740218/article/details/130863129-->
<!--    npm i @smallwei/avue -S-->
    <avue-crud ref="crud"
               :page="page"
               :data="dataList"
               :table-loading="dataListLoading"
               :option="tableOption"
               @search-change="searchChange"
               @on-load="getDataList"
               @refresh-change="refreshChange"
               @selection-change="selectionChange">
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

      <template slot-scope="scope"
                slot="status">
<!--        <el-tag v-if="scope.row.status === 0"-->
        <el-tag v-if="false"
                size="small"
                type="danger">未启用</el-tag>
        <el-tag v-else
                size="small">启用</el-tag>
      </template>

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

    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible"
                   ref="addOrUpdate"
                   @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import { tableOption } from '@/crud/shop/hotSearch'
import AddOrUpdate from './hotSearch-add-or-update.vue'
export default {
  data () {
    return {
      dataForm: {

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
        url: this.$http.adornUrl('/admin/hotSearch/page'),
        method: 'get',
        params: this.$http.adornParams(Object.assign({
          current: page ? page.currentPage : 1,
          size: page ? page.pageSize : 20
        }, params))
      }).then(({ data }) => {
        this.page.total = data.total
        this.page.pageSize = data.size
        this.page.currentPage = data.current
        this.dataList = data.records
        this.dataListLoading = false
        if (done) {
          done()
        }
      })
    },
    // 多选回调
    selectionChange (list) {
      this.dataListSelections = list
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 点击查询
    searchChange (params, done) {
      this.getDataList(this.page, params, done)
    },
    // 删除
    deleteHandle (row, index) {
      var ids = row.hotSearchId ? [row.hotSearchId] : this.dataListSelections.map(item => {
        return item.hotSearchId
      })
      this.$confirm(`确定进行[${row.hotSearchId ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/hotSearch'),
          method: 'delete',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.getDataList()
            }
          })
        })
      }).catch(() => { })
    },
    refreshChange () {
      this.getDataList(this.page)
    }
  }
}
</script>
