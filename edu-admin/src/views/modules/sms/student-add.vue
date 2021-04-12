<template>
  <div>
    <el-steps :active="active" finish-status="success" align-center>
      <el-step title="Base"> </el-step>
      <el-step title="Enroll"> </el-step>
    </el-steps>
    <br />
    <div ref="step2Info" v-if="step1Visible">
      <el-form
        :model="dataForm"
        :rules="dataRule"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="220px"
      >
        <!-- <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item> -->

        <!-- <el-form-item label="科目" prop="classTypeId">
        <el-input
          v-model="dataForm.classTypeId"
          placeholder="科目"
        ></el-input>
      </el-form-item> -->
        <!-- <el-form-item label="学生姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="学生姓名"></el-input>
      </el-form-item> -->
	   <el-form-item label="Email Address" prop="email">
          <el-input
            placeholder="Email address must be unique. If you have enrolled in our school, please input email address, and click the right button to get your own infomation."
            v-model="dataForm.email"
            class="input-with-select"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getStudentInfo"
            ></el-button>
          </el-input>
          
        </el-form-item>
        <el-form-item label="FamilyName" prop="firstName">
          <el-input
            v-model="dataForm.firstName"
            placeholder="FamilyName"
          ></el-input>
        </el-form-item>
        <el-form-item label="GivenName" prop="lastName">
          <el-input
            v-model="dataForm.lastName"
            placeholder="GivenName"
          ></el-input>
        </el-form-item>
        <!-- <el-form-item label="中文姓名" prop="cname">
      <el-input v-model="dataForm.cname" placeholder="中文姓名"></el-input>
    </el-form-item> -->
        <el-form-item label="Date of Birth">
          <el-date-picker
            v-model="dataForm.birth"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="Gender">
          <el-radio-group v-model="dataForm.gender">
            <el-radio :label="1">Male</el-radio>
            <el-radio :label="2">Famale</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Day School">
          <el-autocomplete
            class="inline-input"
            v-model="dataForm.school"
            :fetch-suggestions="querySchoolSearch"
            placeholder="School"
            :trigger-on-focus="false"
            @select="handleSchoolSelect"
           
          ></el-autocomplete>
        </el-form-item>
        <el-form-item
          label="Current year level in day school"
		  v-model="dataForm.currentLevel"
          prop="currentLevel"
        >
          <class-level-select
            @class-level-select-change="classLevelSelectToCurrentLevelChange"
          ></class-level-select>
        </el-form-item>
       
        <el-form-item label="Mobile" prop="mobile">
          <el-input v-model="dataForm.mobile" placeholder="Mobile"></el-input>
        </el-form-item>
        <el-form-item label="Home Suburb">
          <el-input
            v-model="dataForm.suburb"
            placeholder="Home Suburb"
          ></el-input>
        </el-form-item>
        <el-form-item label="Postcode">
          <el-input
            v-model="dataForm.postcode"
            placeholder="Postcode"
          ></el-input>
        </el-form-item>
        <el-form-item label="Contact">
          <el-input
            v-model="dataForm.contractOne"
            placeholder="Contact"
          ></el-input>
        </el-form-item>
        <!-- <el-form-item label="Contact No.2" >
        <el-input
          v-model="dataForm.contractTwo"
          placeholder="Contact No.2"
        ></el-input>
      </el-form-item> -->

        <el-form-item label="Parent’s/Guardian’s Full Name" prop="parent">
          <el-input
            v-model="dataForm.parent"
            placeholder="Parent’s/Guardian’s Full Name"
          ></el-input>
        </el-form-item>

        <el-form-item label="Memo">
          <el-input
            type="textarea"
            :rows="2"
            v-model="dataForm.note"
            placeholder="Memo"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="Whether to active student account"
          prop="isActived"
        >
          <el-switch
            v-model="dataForm.isActived"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0"
          >
          </el-switch>
        </el-form-item>
        <el-form-item>
          <!-- <el-button @click="visible = false">取消</el-button> -->
        </el-form-item>
        <el-form-item>          
          <el-button
            style="margin-top: 12px"
            v-if="nextVisible"
            @click="nextStep"
            >Next step<i class="el-icon-arrow-right el-icon--right"></i
          ></el-button>
        </el-form-item>
      </el-form>
    </div>
    <div ref="step2Info" v-if="step2Visible">
      <el-form
        :model="dataForm"
        :rules="dataRule"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="220px"
      >
        <el-form-item label="Campus" prop="campusId">
          <campus-select
            @campus-select-change="campusSelectChange"
          ></campus-select>
        </el-form-item>
        <el-form-item label="Schedule" prop="campusScheduleId">
          <schedule-select
            ref="schedule"
            @schedule-select-change="scheduleSelectChange"
          ></schedule-select>
        </el-form-item>
        <el-form-item label="Class Type" prop="classTypeId">
          <class-type-select
            @class-type-select-change="classTypeSelectChange"
          ></class-type-select>
        </el-form-item>
        <el-form-item label="Class Level" prop="classLevelId">
          <class-level-select
            @class-level-select-change="classLevelSelectChange"
          ></class-level-select>
        </el-form-item>
        <el-form-item label="Regiter Date">
          <el-date-picker
            v-model="dataForm.registered"
            type="date"
            placeholder="Please select date"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button
            style="margin-top: 12px"
            v-if="prevVisible"
            @click="previousStep"
            icon="el-icon-arrow-left"
            >Previous step</el-button
          >
          <el-button type="primary" @click="dataFormSubmit()">
            Enroll</el-button
          >          
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import CampusSelect from "../common/campus-select";
import ScheduleSelect from "../common/schedule-select";
import ClassTypeSelect from "../common/classtype-select";
import ClassLevelSelect from "../common/classlevel-select";
export default {
  components: {
    CampusSelect,
    ScheduleSelect,
    ClassTypeSelect,
    ClassLevelSelect,
  },
  data() {
    return {
      //   steps -start
      active: 0,
      step1Visible: true,
      step2Visible: false,
      prevVisible: false,
      nextVisible: true,
      //   steps -end

      //  提供输入即时检索数据
      schools: [],

      dataForm: {
        id: 0,
        institutionId: "",
        campusId: "",
        campusScheduleId: "",
        classTypeId: "",
        classLevelId: "",
        name: "",
        firstName: "",
        lastName: "",
        cname: "",
        birth: "",
        gender: 1,
        email: "",
        mobile: "",
        suburb: "",
        postcode: "",
        contractOne: "",
        contractTwo: "",
        school: "",
        currentLevel: "",
        parent: "",
        yearLevel: "",
        registered: new Date(),
        note: "",
        field1: "",
        field2: "",
        field3: "",
        field4: "",
        field5: "",
        field6: "",
        token: "",
        isActived: 1,
        logicDeleted: "",
        created: "",
        updated: "",
        userId: "",
      },
      dataRule: {
        firstName: [
          { required: true, message: "FamilyName不能为空", trigger: "blur" },
        ],
        lastName: [
          { required: true, message: "GivenName不能为空", trigger: "blur" },
        ],
        //   cname: [
        //     { required: true, message: '中文姓名不能为空', trigger: 'blur' }
        //   ],
        birth: [{ required: true, message: "生日不能为空", trigger: "blur" }],
        gender: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        email: [
          {
            required: true, //是否必填
            message: "请输入邮箱地址", //错误提示信息
            trigger: "blur", //检验方式（blur为鼠标点击其他地方，）
          },
          {
            type: "email", //要检验的类型（number，email，date等）
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        mobile: [
          {
            required: true, //是否必填
            pattern: /^\d{10}$/,
            message: "手机号不能为空",
            trigger: "blur",
          },
        ],
        suburb: [
          { required: true, message: "suburb不能为空", trigger: "blur" },
        ],
        postcode: [
          { required: true, message: "postcode不能为空", trigger: "blur" },
        ],
        contractOne: [
          { required: true, message: "contract1不能为空", trigger: "blur" },
        ],
        contractTwo: [
          { required: true, message: "contract2不能为空", trigger: "blur" },
        ],
        school: [
          { required: true, message: "day school不能为空", trigger: "blur" },
        ],
        currentLevel: [
          {
            required: true,
            message: "current year level in day school不能为空",
            trigger: "blur",
          },
        ],
        parent: [
          {
            required: true,
            message: "parent or guardian name不能为空",
            trigger: "blur",
          },
        ],
        campusId: [
          { required: true, message: "分校不能为空", trigger: "blur" },
        ],
        campusScheduleId: [
          { required: true, message: "时段不能为空", trigger: "blur" },
        ],
        classTypeId: [
          { required: true, message: "班级类型不能为空", trigger: "blur" },
        ],
        classLevelId: [
          { required: true, message: "年级类型不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" },
        ],

        note: [{ required: true, message: "备注不能为空", trigger: "blur" }],
        isActived: [
          { required: true, message: "是否激活不能为空", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //step-start
    previousStep() {
      console.log("step" + this.active);
      this.nextVisible = true;
      this.prevVisible = false;
      //   if (this.active-- == 1) {
      //     this.previousVisible = false;
      //   }
      this.active = 0;
      this.isVisible();
    },
    nextStep() {
      console.log("step" + this.active);
      this.prevVisible = true;
      this.nextVisible = false;
      //   if (this.active++ == 1) {
      //     this.nextVisible = false;
      //   }
      this.active = 1;
      this.isVisible();
    },
    isVisible() {
      this.step1Visible = false;
      this.step2Visible = false;
      if (this.active == 0) {
        this.step1Visible = true;
      } else if (this.active == 1) {
        this.step2Visible = true;
      }
    },
    //step-end

    campusSelectChange(data) {
      this.dataForm.campusId = data;
      //更新classroom数据
      this.$refs.schedule.getSchedule(data);
    },
    scheduleSelectChange(data) {
      console.log("scheduleId" + data);
      this.dataForm.campusScheduleId = data;
    },
    classTypeSelectChange(data) {
      this.dataForm.classTypeId = data;
    },
    classLevelSelectChange(data) {
      this.dataForm.classLevelId = data;
    },
    classLevelSelectToCurrentLevelChange(data) {
      this.dataForm.currentLevel = data;
    },
    // 查询学校--start
    getSchools() {
      this.$http({
        //  TODO 此处应institutionId
        url: this.$http.adornUrl(`/sms/student/schools`),
        method: "get",
      }).then(({ data }) => {
        console.log("schools" + data.list);
        this.schools = data.list;
      });
    },
    querySchoolSearch(queryString, cb) {
      var restaurants = this.schools;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    },
    handleSchoolSelect(item) {
      console.log("oooooooo" + item);
    },
    // 查询学校--end

    getStudentInfo() {
      this.$http({
        url: this.$http.adornUrl(`/sms/student/enroll/${this.dataForm.email}`),
        method: "get",
        params: this.$http.adornParams(),
      }).then(({ data }) => {
        if (data && data.code === 0) {
		  this.dataForm.id = data.student.id;	
          this.dataForm.institutionId = data.student.institutionId;
          this.dataForm.campusId = data.student.campusId;
          this.dataForm.campusScheduleId = data.student.campusScheduleId;
          this.dataForm.classTypeId = data.student.classTypeId;
          this.dataForm.classLevelId = data.student.classLevelId;
          this.dataForm.name = data.student.name;
          this.dataForm.firstName = data.student.firstName;
          this.dataForm.lastName = data.student.lastName;
          this.dataForm.cname = data.student.cname;
          this.dataForm.gender = data.student.gender;
          this.dataForm.birth = data.student.birth;
          this.dataForm.mobile = data.student.mobile;
          this.dataForm.email = data.student.email;
          this.dataForm.suburb = data.student.suburb;
          this.dataForm.postcode = data.student.postcode;
          this.dataForm.contractOne = data.student.contractOne;
          this.dataForm.contractTwo = data.student.contractTwo;
          this.dataForm.school = data.student.school;
          // this.dataForm.yearLevel: "",
		  this.dataForm.currentLevel= data.student.currentLevel;
          this.dataForm.field1 = data.student.field1;
          this.dataForm.field2 = data.student.field2;
          this.dataForm.field3 = data.student.field3;
          this.dataForm.field4 = data.student.field4;
          this.dataForm.field5 = data.student.field5;
          this.dataForm.field6 = data.student.field6;
          // this.dataForm.token: "",
          this.dataForm.note = data.student.note;
          this.dataForm.registered = data.student.registered;
          this.dataForm.isActived = data.student.isActived;
          //  this.$refs.campus.value = this.dataForm.campusId;
          //  this.$refs.schedule.value = this.dataForm.campusScheduleId;
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/sms/student/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
              campusId: this.dataForm.campusId,
              campusScheduleId: this.dataForm.campusScheduleId,
              classTypeId: this.dataForm.classTypeId,
              classLevelId: this.dataForm.classLevelId,
              name: this.dataForm.name,
              firstName: this.dataForm.firstName,
              lastName: this.dataForm.lastName,
              cname: this.dataForm.cname,
              birth: this.dataForm.birth,
              gender: this.dataForm.gender,
              email: this.dataForm.email,
              mobile: this.dataForm.mobile,
              suburb: this.dataForm.suburb,
              postcode: this.dataForm.postcode,
              contractOne: this.dataForm.contractOne,
              contractTwo: this.dataForm.contractTwo,
              school: this.dataForm.school,
              currentLevel: this.dataForm.currentLevel,
              parent: this.dataForm.parent,
              yearLevel: this.dataForm.yearLevel,
              registered: this.dataForm.registered,
              note: this.dataForm.note,
              // 'field1': this.dataForm.field1,
              // 'field2': this.dataForm.field2,
              // 'field3': this.dataForm.field3,
              // 'field4': this.dataForm.field4,
              // 'field5': this.dataForm.field5,
              // 'field6': this.dataForm.field6,
              // 'token': this.dataForm.token,
              isActived: this.dataForm.isActived,
              // 'logicDeleted': this.dataForm.logicDeleted,
              // 'created': this.dataForm.created,
              // 'updated': this.dataForm.updated,
              // 'userId': this.dataForm.userId
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  //   this.$emit("refreshDataList");
                },
              });
              this.dataForm.name = "";
              this.dataForm.firstName = "";
              this.dataForm.lastName = "";
              this.dataForm.cname = "";
              this.dataForm.birth = "";
              this.dataForm.gender = 1;

              this.dataForm.email = "";
              this.dataForm.mobile = "";
              this.dataForm.note = "";
              this.dataForm.suburb = "";
              this.dataForm.postcode = "";
              this.dataForm.contractOne = "";
              this.dataForm.contractTwo = "";
              this.dataForm.school = "";
              this.dataForm.currentLevel = "";
              this.dataForm.parent = "";
              this.dataForm.campusId = "";
              this.dataForm.campusScheduleId = "";
              this.dataForm.classTypeId = "";
              this.dataForm.classLevelId = "";
              this.dataForm.yearLevel = "";
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
  mounted() {
    this.getSchools();
  },
};
</script>
