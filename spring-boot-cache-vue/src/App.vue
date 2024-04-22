<template>
  <div id="app">
    <div v-if="!loggedIn">
      <h1>登陆</h1>
      <input type="text" v-model="phone" placeholder="phone" /><br />
      <button @click="sendCode">发送验证码</button><br />
      <input type="text" v-model="code" placeholder="code" /><br />
      <button @click="login">登陆</button>
    </div>
    <div v-else>
      <h1>Welcome, {{ phone }}!</h1>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      phone: '',
      code: '',
      loggedIn: false
    };
  },
  methods: {
    async sendCode() {
      try {
        const response = await axios.post('http://localhost:8080/sms/send', {
          phone: this.phone
        }, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            });
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    },
    async login() {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          phone: this.phone,
          code: this.code
        });
        console.log(response.data);
        this.loggedIn = true;
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

<style>
/* 主要容器样式 */
#app {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 让容器铺满整个视口高度 */
  font-family: Arial, sans-serif;
  text-align: center;
}

/* 标题样式 */
h1 {
  color: #333;
  font-size: 24px;
  margin-bottom: 20px;
}

/* 段落样式 */
p {
  color: #666;
  font-size: 16px;
  line-height: 1.5;
}

/* 链接样式 */
a {
  color: #007bff;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

/* 按钮样式 */
.button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.button:hover {
  background-color: #0056b3;
}

/* 输入框样式 */
input[type="text"] {
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 200px;
}

/* 提示信息样式 */
.info {
  color: #28a745;
}

.error {
  color: #dc3545;
}

</style>