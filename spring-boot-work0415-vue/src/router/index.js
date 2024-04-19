import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/components/Profile.vue';
import LoginForm from "@/components/Login.vue";
import EditPage from "@/components/EditPage.vue";

const routes = [
    { path: '/', redirect: '/login' }, // 默认重定向到登录页面
    { path: '/login', component: LoginForm },
    { path: '/home', component: HomePage },
    {
        path: '/edit/:id',
        name: 'EditPage',
        component: EditPage
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});


export default router;