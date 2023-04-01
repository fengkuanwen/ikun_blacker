<template>
    <div id="loginbox">
        <h2>密码登陆</h2>
        <form class="form" action="#" method="get">
            <div class="item">
                <input type="text" placeholder="手机号" id="username" maxlength="11" required>
            </div>
            <div class="item">
                <input type="text" placeholder="邮箱" id="password" required>
            </div>
            <div>
                <button id="btn">登陆</button>
            </div>
        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'pwdLogin',
    components: {
    }
  }
</script>