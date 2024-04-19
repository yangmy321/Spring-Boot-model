// main.js

import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // 引入路由配置文件

const app = createApp(App);

// 将路由器实例挂载到 Vue 实例中
app.use(router);

app.mount('#app');
