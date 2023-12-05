import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import KakaoCallback from '../views/KakaoCallback.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/kakao-login',
    name: '카카오 로그인',
    component: () => import('../views/KakaoLogin.vue')
  },
  {
    path: '/oauth2/kakao/callback',
    name: '카카오 인가코드 콜백 엔드포인트',
    component: KakaoCallback
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
