<!--<script setup>-->
<!--import HelloWorld from './components/HelloWorld.vue'-->
<!--import TheWelcome from './components/TheWelcome.vue'-->
<!--</script>-->

<!--<template>-->
<!--  <header>-->
<!--    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />-->

<!--    <div class="wrapper">-->
<!--      <HelloWorld msg="You did it!" />-->
<!--    </div>-->
<!--  </header>-->

<!--  <main>-->
<!--    <TheWelcome />-->
<!--  </main>-->
<!--</template>-->

<!--<style scoped>-->
<!--header {-->
<!--  line-height: 1.5;-->
<!--}-->

<!--.logo {-->
<!--  display: block;-->
<!--  margin: 0 auto 2rem;-->
<!--}-->

<!--@media (min-width: 1024px) {-->
<!--  header {-->
<!--    display: flex;-->
<!--    place-items: center;-->
<!--    padding-right: calc(var(&#45;&#45;section-gap) / 2);-->
<!--  }-->

<!--  .logo {-->
<!--    margin: 0 2rem 0 0;-->
<!--  }-->

<!--  header .wrapper {-->
<!--    display: flex;-->
<!--    place-items: flex-start;-->
<!--    flex-wrap: wrap;-->
<!--  }-->
<!--}-->
<!--</style>-->

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const username = ref('');
const password = ref('');
const age = ref(null);
const phone = ref('');
const userID = ref(''); // 假设userID是由后端生成的，前端不需要填写
const message = ref(''); // 用来保存错误信息

const login = async message => {

  // 检查用户名是否已填写
  if (!username.value || username.value.trim() === '' || password.value.trim() === '') {
    // 用户名为空或只包含空格，提示用户并结束函数执行
    alert('用户名或密码为空');
    return; // 退出函数
  }

  try {
    const response = await axios.post('http://localhost:8080/user/add', {
      username: username.value,
      password: password.value,
      age: age.value,
      phone: phone.value,
      userID: userID.value,
    });

    // 如果登录成功，通常后端会返回一个token或其他成功信息
    if (response.data.msg ==="success") {
      // 在这里处理登录成功后的逻辑，比如保存token到localStorage等
      alert("code"+response.data.code+" msg:"+response.data.msg);
      console.log("登录成功",response.data)
      // 可以跳转到其他页面或显示登录成功的信息
    } else {
      alert("code"+response.data.code+" msg:"+response.data.msg);
      // 登录失败的处理逻辑
      console.log('登录失败', response.data);
      // 可以显示错误信息给用户
    }
  } catch (error) {
    // 请求错误处理
    console.error('登录请求发生错误', error);
    // 可以显示请求错误的信息给用户
  }
};
</script>

<template>
  <div>
    <label>用户名:</label>
    <input type="text" v-model="username" required><br>
    <label>密码:</label>
    <input type="password" v-model="password" required><br>
    <label>年龄:</label>
    <input type="number" v-model.number="age" required><br>
    <label>电话:</label>
    <input type="text" v-model="phone" required><br>
    <label>身份证号:</label>
    <input type="text" v-model="userID" required><br>
    <button @click="login">添加用户</button>
    <p v-if="message">{{ message }}</p> <!-- 显示错误信息 -->
  </div>
</template>

<style scoped>
.message {
  margin-top: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>