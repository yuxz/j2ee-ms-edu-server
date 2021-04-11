<template>
  <!-- <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="18">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div> -->
  <el-container style="height: 100%; border: 1px solid #eee">
    
    <el-container width="80%">
      <!-- <el-header style="text-align: right; font-size: 12px"> head </el-header> -->
      <el-main>
        <el-tabs
          v-model="activeName"         
          @tab-click="handleClick"
        >
          <el-tab-pane
            v-for="campus,index in campusAllList"
            :key="campus.id"
            :label="campus.name"
            :name="campus.name"
          >
		    <el-row :gutter="20">
			<el-col :span="6"> 
				<el-button type="primary" round>一键分配</el-button>
 				<drag-multi
				:key="2"
				:list="campus.student"
				:group="'student'"
				class="kanban todo"
				header-text="Students"
				/> 
			</el-col>
			<el-col :span="18">
				<el-collapse  
		      v-for="aclass in campus.class"
              :key="aclass.id"
			  v-model="campusAllList[index].active" >
			  
			<el-collapse-item :title="aclass.name + ' ' + aclass.student.length +'/' +aclass.teacher.length" :name="aclass.id">
				<drag-multi
                :key="3"
                :list="aclass.teacher"
                :group="'teacher'"
                class="kanban working"
                header-text="Teachers"
				@start="start"
              />
              <drag-multi
                :key="4"
                :list="aclass.student"
                :group="'student'"
                class="kanban working"
                header-text="Students"
				@start="start"
              />
			</el-collapse-item>			
			</el-collapse>    
			</el-col>
			</el-row>		         
          </el-tab-pane>          
        </el-tabs>
      </el-main>     
    </el-container>
	<el-aside width="20%" style="background-color: rgb(238, 241, 246)">
	  <el-button type="primary" round>一键分配</el-button>
      <drag-multi
        :key="1"
        :list="teacherAllList"
        :group="'teacher'"
        class="kanban todo"
        header-text="Teachers"
      />
      
    </el-aside>
  </el-container>
</template>

<script>
import CampusSelect from "../common/campus-select";

import DragList from "@/components/DragList";
import DragMulti from "@/components/DragMulti";
import { fetchList } from "@/api/article";

export default {
  components: {
    CampusSelect,
    DragList,
    DragMulti,
  },
  data() {
    return {
      activeName: "campus1",
    //   activeClassName:[
	// 	  ["1","2","3"],
	//   	  ["4","5","6"],
	// 	],
      teacherAllList: [
        { name: "T", id: 1 },
        { name: "T", id: 2 },
        { name: "T", id: 3 },
        { name: "T", id: 4 },
      ],     
      campusAllList: [
        {
          id: 1,
		  name: "campus1",
		  active:[1,2],
          class: [
            {
              id: 1,
			  name: "year1",			 
              teacher: [
                { name: "T", id: 25 },
                { name: "T", id: 26 },
                { name: "T", id: 27 },
              ],
              student: [
                { name: "S", id: 25 },
                { name: "S", id: 26 },
                { name: "S", id: 27 },
              ],
            },
            {
              id:2,
			  name: "year2",			  
              teacher: [
                { name: "T", id: 52 },
                { name: "T", id: 62 },
                { name: "T", id: 72 },
              ],
              student: [
                { name: "S", id: 52 },
                { name: "S", id: 62 },
                { name: "S", id: 72 },
              ],
            },
          ],
		   student: [
				{ name: "S", id: 1 },
				{ name: "S", id: 2 },
				{ name: "S", id: 3 },
				{ name: "S", id: 4 },
			],
        },
        {
          id:2,
		  name: "campus2",
		  active:[3,4],
          class: [
            {
              id:3,
			  name: "year5",			 
              teacher: [
                { name: "T", id: 15 },
                { name: "T", id: 16 },
                { name: "T", id: 17 },
              ],
              student: [
                { name: "S", id: 15 },
                { name: "S", id: 16 },
                { name: "S", id: 17 },
              ],
            },
            {
              id: 4,
			  name: "year6",			 
              teacher: [
                { name: "T", id: 51 },
                { name: "T", id: 61 },
                { name: "T", id: 71 },
              ],
              student: [
                { name: "S", id: 51 },
                { name: "S", id: 61 },
                { name: "S", id: 71 },
              ],
            },
          ],
		  student: [
				{ name: "S", id: 1 },
				{ name: "S", id: 2 },
				{ name: "S", id: 3 },
				{ name: "S", id: 4 },
			],
        },
      ],
    };
  },
  methods: {
	start(e){
		console.log("drag start---------" + e);
	},  
    handleClick(tab, event) {
      console.log(tab, event);
    },
    getData() {
      this.listLoading = true;
      //   fetchList().then(response => {
      //     this.list1 = response.data.items.splice(0, 5)
      //     this.list2 = response.data.items
      //   })
    },

    init(id) {
      this.dataForm.id = id || 0;

      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/ims/class/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.class.institutionId;
              this.dataForm.campusId = data.class.campusId;
              this.dataForm.classTypeId = data.class.classTypeId;
              this.dataForm.classroomId = data.class.classroomId;
              this.dataForm.name = data.class.name;
              this.dataForm.startTime = data.class.startTime;
              this.dataForm.endTime = data.class.endTime;
              this.dataForm.isFinished = data.class.isFinished;
              this.dataForm.note = data.class.note;
            }
          });
        }
      });
    },
    campusSelectChange(data) {
      this.dataForm.campusId = data;
      // update classroom data

      //更新classroom数据
      this.$refs.classroom.getClassroom(data);
    },
    classroomSelectChange(data) {
      this.dataForm.classroomId = data;
    },

    classTypeSelectChange(data) {
      this.dataForm.classTypeId = data;
    },

    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/ims/class/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
              campusId: this.dataForm.campusId,
              classTypeId: this.dataForm.classTypeId,
              classroomId: this.dataForm.classroomId,
              name: this.dataForm.name,
              startTime: this.dataForm.startTime,
              endTime: this.dataForm.endTime,
              isFinished: this.dataForm.isFinished,
              note: this.dataForm.note,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
};
</script>
<style>
</style>
