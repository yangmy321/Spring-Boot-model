<template>
  <div class="login-container">
    <form @submit.prevent="login" class="login-form">
      <div class="form-group">
        <input type="text" id="username" v-model="name" placeholder="用户名" required>
      </div>
      <div class="form-group">
        <input type="password" id="password" v-model="password" placeholder="密码" required>
      </div>
      <button type="submit">登录</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: '',
      password: ''
    };
  },
  methods: {
    login() {
      const user = {
        name: this.name,
        password: this.password
      };

      axios.post('http://localhost:8080/user/login', user)
          .then(response => {
            if (response.data.code === 1){
              alert("登录成功")
              this.$router.push('/home'); // 替换成你想要跳转的页面路径
            }else if (response.data.code === 0){
              alert("登录失败,账号或密码错误")
            }
          });
    }
  }
};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  background-color: #B63F3FFF;
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form {
  width: 300px;
  padding: 20px;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 15px;
  font-size: 16px;
  border: none;
  border-bottom: 1px solid #ccc;
  background-color: transparent;
  transition: border-bottom-color 0.3s ease-in-out;
}

input[type="text"]:focus,
input[type="password"]:focus {
  border-bottom-color: #4caf8c;
  outline: none;
}

button {
  width: 100%;
  padding: 12px;
  font-size: 18px;
  color: #fff;
  background-color: #4caf8c;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

button:hover {
  background-color: #45a049;
}
</style>
