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

               @selection-change="selectionChange">
    >
<!--      selectionChange自定义的函数，当选择的条目改变时候，会触发这个函数-->
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
<!--        明白了，原来参考的数据库的第一列就叫id。而我这里传过来的是id，和他们不一样-->
        <el-button v-if="true"
                   type="primary"
                   size="small"
                   icon="el-icon-edit"
                   @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
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
        // currentPage: 1, // 当前页数
        // pageSize: 10 // 每页显示多少条
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
        url: this.$http.adornUrl('/produceHandle/searchPageResult'),
//https://blog.csdn.net/weixin_43299180/article/details/112882498
//         const target = { a: 1, b: 2 };
//         const source1 = { b: 4, c: 5 };
//         const source2 = { b: 6, c: 7 };
//         const obj = Object.assign(target,source1,source2);
//         console.log(obj); // (a: 1, b: 6, c: 7)
//         源对象属性与目标对象属性不同，则会被拷贝到目标对象中；

        method: 'get',
        params: this.$http.adornParams(Object.assign({  //{"current": 3,"size": 10,"truthItemId": "82002"}
          current: page ? page.currentPage : 1,
          size: page ? page.pageSize : 10
        }, params))
      }).then(({ data }) => {
        this.page.total = data.total
        this.page.pageSize = data.size
        this.page.currentPage = data.current
        // this.page.total = 2
        // this.page.pageSize = 10
        // this.page.currentPage = 1

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
      this.$nextTick(() => {//this.$nextTick() 解释为在下次DOM更新循环结束之后执行延迟回调。在修改数据之后立即使用这个方法，获取更新后的DOM.  也就是说，当dom元素发生改变，重新渲染dom树后，再执行vue.$nextTick()里面的内容。
        this.$refs.addOrUpdate.init(id)
      })
    },

    // 点击查询
    searchChange (params, done) {
      this.getDataList(this.page, params, done)
    },
    // 删除
    deleteHandle (row, index) {
      var ids = row.id ? [row.id] : this.dataListSelections.map(item => {
        return item.id
      })
      // ids=ids[0]
      // console.log("可能要删除的id是："+ids[0]);
      this.$confirm(`确定进行[${row.id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/produceHandle/testDelete'),

          //url: this.$http.adornUrl('/produceHandle/'+ids[0]),

          method: 'PUT',
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

<!--let和var区别-->
<!--//////-->
<!--{-->
<!--let name= 'lulu',age= 18;-->
<!--console.log(name); //lulu-->
<!--}-->
<!--console.log(name); //name is not defined-->
<!--let存在块作用域特性，变量只在块域中有效-->

<!--//////-->
<!--使用 var 定义的代码，声明会被提升到前面，赋值还在原位置-->

<!--代码A-->
<!--console.log(a); //undefined-->
<!--var a = 1;-->
<!--console.log(a);  //1-->

<!--代码B（等价于代码A，因为变量提升：解析器会先解析代码，然后把声明的变量的声明提升到最前-->
<!--var a;-->
<!--console.log(a); //undefined-->
<!--a = 1;-->
<!--console.log(a);  //1-->

<!--////-->
<!--var name = "Trump";-->
<!--function Chairman() {-->
<!--var name;-->
<!--if (false) {-->
<!--name = "Biden";-->
<!--}-->
<!--console.log(name);-->
<!--}-->
<!--Chairman();-->
<!--这样一来，即使没有走到赋值语句处，在函数内部依然开辟了新的内存地址来保存局部变量name，所以最终输出的是undefined。-->
<!--这其实算是js语言早期发展的一个bug。-->
<!--https://segmentfault.com/a/1190000044673994-->
