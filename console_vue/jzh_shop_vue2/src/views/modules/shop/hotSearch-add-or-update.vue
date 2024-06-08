<template>
  <div class="mod-hotSearch-add-or-update">
    <el-dialog :title="!dataForm.hotSearchId ? '新增' : '修改'"
               :close-on-click-modal="false"
               :visible.sync="visible">
      <el-form :model="dataForm"
               :rules="dataRule"
               ref="dataForm"
               @keyup.enter.native="dataFormSubmit()"
               label-width="80px">
<!--如果这里v-model等没有修改到相应值，会出现卡顿无法输入的情况-->
        <el-form-item label="生产时间"
                      prop="produceDate">
          <el-input v-model="dataForm.produceDate"
                    controls-position="right"
                    :min="0"
                    maxlength="50"
                    show-word-limit
                    label="生产时间"></el-input>
        </el-form-item>

        <el-form-item label="产品id"
                      prop="truthItemId">
          <el-input v-model="dataForm.truthItemId"
                    controls-position="right"
                    type="textarea"
                    :min="0"
                    maxlength="255"
                    show-word-limit
                    label="产品id"></el-input>
        </el-form-item>

        <el-form-item label="工人id"
                      prop="truthWorkerId">
          <el-input v-model="dataForm.truthWorkerId"
                    controls-position="right"
                    type="textarea"
                    :min="0"
                    maxlength="255"
                    show-word-limit
                    label="工人id"></el-input>
        </el-form-item>

        <el-form-item label="生产数量"
                      prop="produceCount">
          <el-input v-model="dataForm.produceCount"
                    controls-position="right"
                    type="textarea"
                    :min="0"
                    maxlength="255"
                    show-word-limit
                    label="生产数量"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary"
                   @click="dataFormSubmit()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//import { Debounce } from '@/utils/debounce'
import { Debounce } from '../../../utils/debounce'//？？？
export default {
  data () {
    return {
      dataForm: {
        hotSearchId: 0,
        produceDate: '',
        truthItemId: '',
        truthWorkerId: '',
        produceCount: '',
        // recDate: '',
        // seq: 0,
        // status: 0
      },
      page: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 10 // 每页显示多少条
      },
      addProdVisible: false,
      visible: false,
      // resourcesUrl: process.env.VUE_APP_RESOURCES_URL,
      dataRule: {
        produceDate: [
          { required: true, message: '生产时间不能为空', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在1到50个字符内', trigger: 'blur' },
          { pattern: /\s\S+|S+\s|\S/, message: '生产时间不能为空', trigger: 'blur' }
        ],
        truthItemId: [
          { required: true, message: '产品id不能为空', trigger: 'blur' },
          { min: 1, max: 255, message: '长度在1到255个字符内', trigger: 'blur' },
          { pattern: /\s\S+|S+\s|\S/, message: '产品id不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  components: {},
  methods: {
    init (id) {
      this.dataForm.hotSearchId = id || 0
      this.visible = true
      this.$nextTick(() => {//this.$nextTick ()方法可以在下次DOM更新循环结束之后执行延迟回调，获取更新后的DOM
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.hotSearchId) {
          this.$http({
            url: this.$http.adornUrl('/produceHandle/getOneProduceInfo/' + this.dataForm.hotSearchId),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            this.dataForm = data
          })
        }
      })
    },

    // 表单提交
    dataFormSubmit: Debounce(function () {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          let param = this.dataForm
          this.$http({
            url: this.$http.adornUrl(`/produceHandle`),
            method: param.hotSearchId ? 'put' : 'post',
            data: this.$http.adornData(param)
          }).then(({ data }) => {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.visible = false
                this.$emit('refreshDataList', this.page)
              }
            })
          })
        }
      })
    })
  }
}
</script>
