<script setup>
import { ref } from 'vue'
import axios from 'axios'

const email = ref('test@aiart.com')
const password = ref('1234')
const message = ref('')

const login = async () => {
  try {
    const res = await axios.post('http://localhost:8080/api/auth/login', {
      email: email.value,
      password: password.value,
    })

    if (res.data.success) {
      message.value = '로그인 성공'
    } else {
      message.value = '이메일 또는 비밀번호 확인'
    }
  } catch (e) {
    console.log('error: ', e);
    message.value = '서버 연결 실패'
  }
}
</script>

<template>
  <main style="max-width: 380px; margin: 40px auto; font-family: sans-serif;">
    <h2>로그인</h2>
    <input v-model="email" placeholder="email" style="width:100%;padding:8px;margin-bottom:8px" />
    <input v-model="password" type="password" placeholder="password" style="width:100%;padding:8px;margin-bottom:8px" />
    <button @click="login" style="width:100%;padding:10px">로그인</button>
    <p style="margin-top:12px">{{ message }}</p>
  </main>
</template>