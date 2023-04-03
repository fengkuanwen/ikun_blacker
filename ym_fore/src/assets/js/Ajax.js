// this.$request ({
//     url:`${this.$store.state.serverPath}`+"/order/hello",
//     methods: "GET",
//     responseType:"blob",
// }).then(res=>{
//     console.log(res)
// })

// registry(){
//     // const that = this;
//     var username = JSON.stringify(this.registryData.username);
//     var phone = JSON.stringify(this.registryData.phone);
//     var password = JSON.stringify(this.registryData.password);
//     var smsCode = JSON.stringify(this.registryData.smsCode);
//     var uuid = JSON.stringify(this.registryData.smsUUID);
//     this.$request ({
//         url:`${this.$store.state.serverPath}`+"/auth/registry",
//         method: "post",
//         headers: {"Authorization": "this.$store.state.token"},
//         data:{
//             username,phone,password,smsCode,uuid
//         },
//         responseType:"json",
//     }).then((res)=>{
//         alert(res.data.data)
//     },(err) => {
    // console.log(err);
    // alert("请检查输入！验证码过期时间为俩分钟")
    // })
// }