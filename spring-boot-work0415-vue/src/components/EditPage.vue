<template>
  <div class="edit-page">
    <div class="edit-form">
      <h3>修改用户信息</h3>
      <form @submit.prevent="saveChanges">
<!--        <label class="avatar-upload">-->
<!--          <img :src="user.img" alt="用户图片" class="user-image">-->
<!--          <input-->
<!--              type="file"-->
<!--              id="imageUpload"-->
<!--              ref="imageUpload"-->
<!--              accept="image/*"-->
<!--              @change="handleFileChange"-->
<!--              style="display: none;"-->
<!--          >-->
<!--          <span class="upload-text">点击上传头像</span>-->
<!--        </label>-->
        <div class="form-row">
          <div class="form-group">
            <label for="name">用户名:</label>
            <input type="text" id="name" v-model="user.name" class="form-input">
          </div>
          <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" v-model="user.password" class="form-input">
          </div>
          <div class="form-group">
            <label for="sex">性别:</label>
            <select id="sex" v-model="user.sex" class="form-select">
              <option value="male">男</option>
              <option value="female">女</option>
            </select>
          </div>
        </div>
        <div class="button-container">
          <button type="submit" class="save-button">保存</button>
          <button type="button" class="back-button" @click="goback">返回</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: {
        id:'',
        name: '',
        password:'',
        sex: '',
        img: '' // 假设这里是用户头像的 URL
      },
    };
  },
  created() {
    // 获取传递的 id 参数
    this.user.id = this.$route.params.id;
  },
  methods: {
    saveChanges() {
      axios.post('http://localhost:8080/user/updateUserById',this.user)
          .then(response => {
            if (response.data.code === 1){
              alert("修改成功")
            }else {
              alert("修改失败")
            }
          });
    },
    goback(){
      this.$router.go(-1); // 返回上一页
    },
    editUser(id) {
      this.$router.push({ path: '/edit/' + id });
    },
    fetchUsers() {
      axios.get('http://localhost:8080/user/getUsers')
          .then(response => {
            this.users = response.data.data;
          });
    },
    handleFileChange(e){
      const file = e.target.files[0];
      if (file) {
        const formData = new FormData();
        formData.append('file', file);

        axios.post('http://localhost:8080/user/oss/'+this.user.id, formData)
            .then(response => {
              if(response.data.code === 1){//如果成功了
                this.init();
              }
            })
            .catch(error => {
              // 处理错误
              console.error('上传失败:', error);
            });

      }
    },
    init(){
      axios.get('http://localhost:8080/user/getUserById/' + this.user.id)
          .then(response => {
            // 使用 Object.assign 或扩展运算符合并数据
            this.user = Object.assign({}, this.user, response.data.data);
          });
    }
  },
  mounted() {
    this.init();
  },
};

</script>


<style scoped>
.edit-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f4f4;
}

.edit-form {
  width: 400px; /* 设置表单的宽度 */
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.edit-form h3 {
  margin-bottom: 20px;
  font-size: 1.2rem;
  color: #333;
}

.avatar-upload {
  position: relative;
  overflow: hidden;
  display: inline-block;
  cursor: pointer;
}

.user-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.upload-text {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  background-color: rgba(0, 0, 0, 0.7);
  color: #fff;
  padding: 5px 10px;
  font-size: 0.8rem;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.avatar-upload:hover .upload-text {
  opacity: 1;
}

.form-row {
  flex: 1; /* 让每个表单组件占据相同的空间 */
  margin-bottom: 15px;
  display: flex;
  flex-direction: column; /* 将子元素在垂直方向上排列 */
}

.form-group {
  flex: 1; /* 让每个表单组件占据相同的空间 */
  margin-left: 100px; /* 向右移动文本框 */
  margin-bottom: 15px;
  text-align: left;
}
.label {
  display: block;
  font-weight: bold;
  color: #333;
}

.form-input,
.form-select {
  width: calc(33.33% - 10px); /* 设置三个文本输入框相同宽度，并减去间距 */
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  text-align: left; /* 将文本框内文本左对齐 */
}

.button-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.save-button,
.back-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.save-button {
  background-color: #007bff;
  color: #fff;
}

.save-button:hover {
  background-color: #0056b3;
}

.back-button {
  background-color: #ccc;
  color: #333;
}

.back-button:hover {
  background-color: #bfbfbf;
}
</style>