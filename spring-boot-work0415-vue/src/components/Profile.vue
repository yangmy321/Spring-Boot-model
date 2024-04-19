<template>
  <div class="user-table-container">
    <h1 class="title">用户信息</h1>
    <div class="user-table-wrapper">
      <table class="user-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>头像</th>
          <th>姓名</th>
          <th>性别</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>
            <form @submit.prevent="saveChanges">
            <label class="avatar-upload">
<!--              <img :src="user.img" alt="用户图片" class="user-image">-->
              <input
                  type="file"
                  id="imageUpload"
                  ref="imageUpload"
                  accept="image/*"
                  @change="handleFileChange(user.id, $event)"
                  style="display: none;"
              >
              <span class="upload-text"><img :src="user.img" alt="Avatar" class="avatar"></span>
            </label>
            </form>
          </td>

          <td>{{ user.name }}</td>
          <td>{{ user.sex }}</td>
          <td>
            <button @click="editUser(user.id)" class="edit-button">修改</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      users: [],
      user: {
        id:'',
        name: '',
        password:'',
        sex: '',
        img: ''
      }
    };
  },
  methods: {
    editUser(id) {
      this.$router.push({ path: '/edit/' + id });
    },
    fetchUsers() {
      axios.get('http://localhost:8080/user/getUsers')
          .then(response => {
            this.users = response.data.data;
          });
    },
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


    handleFileChange(id,e){
      const file = e.target.files[0];
      if (file) {
        const formData = new FormData();
        formData.append('file', file);

        axios.post('http://localhost:8080/user/oss/'+id, formData)
            .then(response => {
              if(response.data.code === 1){//如果成功了
               this.fetchUsers();
              }
            })
            .catch(error => {
              // 处理错误
              console.error('上传失败:', error);
            });

      }
    },
  },
  created() {
    this.fetchUsers();
    this.user.id = this.$route.params.id;
  }
};
</script>

<style scoped>
/* 容器样式 */
.user-table-container {
  margin: 150px auto 0;
}

/* 标题样式 */
.title {
  font-size: 24px;
  margin-bottom: 10px;
}

/* 表格外包装样式 */
.user-table-wrapper {
  overflow-x: auto;
}

/* 表格样式 */
.user-table {
  width: 600px;
  margin: 0 auto;
  border-collapse: collapse;
}

/* 表头样式 */
.user-table th {
  background-color: #f2f2f2;
  padding: 10px;
  text-align: center;
}

/* 表格单元格样式 */
.user-table td {
  padding: 10px;
}

/* 头像样式 */
.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

/* 修改按钮样式 */
.edit-button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
}

/* 修改按钮鼠标悬停效果 */
.edit-button:hover {
  background-color: #45a049;
}
</style>
