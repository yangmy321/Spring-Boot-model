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
#app {
  font-family: Arial, sans-serif;
  text-align: center;
  margin-top: 50px;
}
</style>