<template>
    <div id="loginbox">
        <h2>短信登陆</h2>
        <form class="form" action="#" method="get">
            <div class="item">
                <input type="text" placeholder="姓名" id="fullname" maxlength="4" required>
            </div>
            <div class="item">
                <input type="text" placeholder="手机号" id="phonenumber" maxlength="11" required>
            </div>
            <div class="item">
                <input type="text" placeholder="邮箱" id="mailaddress" required>
            </div>
            <div>
                <button id="btn">登陆</button>
            </div>
        </form>
    </div>
</template>
  
<script>
  export default {
    name: 'smsLogin',
    components: {
    }
  }
</script>