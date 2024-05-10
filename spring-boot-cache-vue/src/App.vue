<template>
  <div id="app">
    <div v-if="!loggedIn">
      <h1>登陆</h1>
      <input type="text" v-model="phone" placeholder="phone" /><br />
      <button @click="sendCode" :disabled="countdown > 0">{{ countdown > 0 ? countdown + 's后可再次发送' : '发送验证码' }}</button><br />
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
      loggedIn: false,
      codeSent: false, // 添加一个标志来跟踪是否已发送验证码
      codeTimestamp: null, // 保存验证码生成的时间戳
      countdown: 0 // 倒计时计数
    };
  },
  methods: {
    async sendCode() {
      try {
        if (this.loggedIn || this.codeSent) {
          // 如果用户已经登录或者验证码已发送，不需要重新发送验证码
          return;
        }

        if (!this.codeTimestamp || this.isCodeExpired()) {
          // 如果没有生成验证码或者验证码已过期，则生成新的验证码
          const response = await axios.post('http://localhost:8080/sms/send', {
            phone: this.phone
          }, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          });
          console.log(response.data);
          this.codeSent = true; // 标记验证码已发送
          this.codeTimestamp = Date.now(); // 保存生成验证码的时间戳

          // 开始倒计时
          this.startCountdown();
        }
      } catch (error) {
        console.error(error);
      }
    },
    isCodeExpired() {
      const expirationTime = 60 * 1000; // 1分钟的有效期
      return (Date.now() - this.codeTimestamp) > expirationTime;
    },
    startCountdown() {
      this.countdown = 60; // 设置倒计时为60秒

      // 每秒减少1秒，直到倒计时结束
      const timer = setInterval(() => {
        if (this.countdown > 0) {
          this.countdown--;
        } else {
          clearInterval(timer); // 停止计时器
        }
      }, 1000);
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
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 让容器铺满整个视口高度 */
  font-family: Arial, sans-serif;
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
  margin-bottom: 10px; /* 增加按钮之间的间距 */
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
  margin-bottom: 10px; /* 增加输入框之间的间距 */
}

/* 提示信息样式 */
.info {
  color: #28a745;
}

.error {
  color: #dc3545;
}

/* 倒计时文本框样式 */
.countdown {
  color: #666;
  font-size: 14px;
  margin-top: 5px; /* 调整倒计时文本框与按钮的间距 */
}

</style>
