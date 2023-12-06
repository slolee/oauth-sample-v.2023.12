<template>
  <h1>Hello</h1>
</template>

<script>
import axios from 'axios'

export default {
    created: function() {
        const urlParams = new URLSearchParams(window.location.search);
        const code = urlParams.get('code');
        const state = urlParams.get('state');

        axios.get("http://223.130.129.170:8080/oauth2/kakao/callback", {
            params: {
                code: code,
                state: state
            },
            withCredentials: true
        }).then((data) => {
            alert("LOGIN SUCCESS!!");
            console.log(data);
        })
    }
}
</script>