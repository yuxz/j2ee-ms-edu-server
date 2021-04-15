<template>
  <el-row :gutter="40" class="panel-group">
	   <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('campuses')">
        <div class="card-panel-icon-wrapper icon-campus">
          <!-- <svg-icon icon-class="shopping" class-name="card-panel-icon" /> -->
		   <icon-svg icon-class="campus" name="campus" class-name="card-panel-icon"></icon-svg>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Campus
          </div>
          <count-to :start-val="0" :end-val="campus" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('teachers')">
        <div class="card-panel-icon-wrapper icon-teacher">
          <!-- <svg-icon icon-class="message" class-name="card-panel-icon" /> -->
		  <icon-svg icon-class="teacher" name="teacher" class-name="card-panel-icon"></icon-svg>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Teachers
          </div>
          <count-to :start-val="0" :end-val="teacher" :duration="3000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
	<el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('students')">
        <div class="card-panel-icon-wrapper icon-student">
          <!-- <svg-icon icon-class="peoples" class-name="card-panel-icon" /> -->
		   <icon-svg icon-class="student" name="student"  class-name="card-panel-icon"></icon-svg>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Students
          </div>
          <count-to :start-val="0" :end-val="student" :duration="2600" class="card-panel-num" />
        </div>
      </div>
    </el-col>    
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('classes')">
        <div class="card-panel-icon-wrapper icon-classes">
          <!-- <svg-icon icon-class="money" class-name="card-panel-icon" /> -->
		  <icon-svg icon-class="classes" name="classes" class-name="card-panel-icon"></icon-svg>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Classes
          </div>
          <count-to :start-val="0" :end-val="classes" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
   
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
// import { isURL } from '@/utils/validate'
export default {
  components: {
    CountTo
  },
  data() {
    return {
      campus: 0,
	  teacher: 0,
	  classes: 0,
	  student: 0,
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.fetchCampusData();
	  this.fetchTeacherData();
	  this.fetchClassData();
	  this.fetchStudentData();
    });
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
	//fetch campus
	fetchCampusData() {
      //get data from server
      this.$http({
        url: this.$http.adornUrl("/ims/statistics/campus/count"),
        method: "get",
      }).then(({ data }) => {
        this.campus = data.data;       
      });	  
    },
	fetchTeacherData() {
      //get data from server
      this.$http({
        url: this.$http.adornUrl("/tms/statistics/teacher/count"),
        method: "get",
      }).then(({ data }) => {
        this.teacher = data.data;       
      });	  
    },
	fetchClassData() {
      //get data from server
      this.$http({
        url: this.$http.adornUrl("/ims/statistics/class/count"),
        method: "get",
      }).then(({ data }) => {
        this.classes = data.data;       
      });	  
    },
	fetchStudentData() {
      //get data from server
      this.$http({
        url: this.$http.adornUrl("/sms/statistics/student/count"),
        method: "get",
      }).then(({ data }) => {
        this.student = data.data;       
      });	  
    },
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-student {
        background: #40c9c6;
      }

      .icon-teacher {
        background: #36a3f7;
      }

      .icon-classes {
        background: #f4516c;
      }

      .icon-campus {
        background: #34bfa3
      }
    }

    .icon-student {
      color: #40c9c6;
    }

    .icon-teacher {
      color: #36a3f7;
    }

    .icon-classes {
      color: #f4516c;
    }

    .icon-campus {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
