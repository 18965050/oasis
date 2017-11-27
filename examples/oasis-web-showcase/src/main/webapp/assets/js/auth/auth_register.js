/**
 * 注册页面js
 */
define(function(require){
   $("#form").validate({
      rules:{
          email:{
              required:true,
              email:true
          },
          password:{
              required:true
          },
          rptPassword:{
              required:true,
              equalTo: "#password"
          },
          type : {
              required:true
          }
      } ,
      messages:{
          email:{
              required:"请输入email",
              email:"请输入正确格式的email"
          },
          password:{
              required:"请输入密码"
          },
          rptPassword:{
              required:"请确认密码",
              equalTo: "两次输入的密码不一致"
          },
          type : {
              required:"请选择注册类型"
          }
      }
   });
   
});