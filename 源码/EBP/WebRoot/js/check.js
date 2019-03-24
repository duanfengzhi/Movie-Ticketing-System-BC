
function $(elementId){
  return document.getElementById(elementId).value;
    }
function divId(elementId){
  return document.getElementById(elementId);
    }
/*用户名验证*/    
function checkUser(){
  var user=$("user");
  var userId=divId("user_prompt");
   userId.innerHTML="";    
  var reg=/^[a-zA-Z][a-zA-Z0-9]{3,15}$/;    
    if(reg.test(user)==false){
       userId.innerHTML="用户名不正确";
    return false;
      }
      return true;
    }
/*密码验证*/
function checkPwd(){
  var pwd=$("pwd");
  var pwdId=divId("pwd_prompt");
   pwdId.innerHTML="";    
  var reg=/^[a-zA-Z0-9]{4,10}$/;    
    if(reg.test(pwd)==false){
       pwdId.innerHTML="密码不能含有非法字符，长度在4-10之间";
    return false;
      }
      return true;
    }
    
function checkRepwd(){
  var repwd=$("repwd");
  var pwd=$("pwd");
  var repwdId=divId("repwd_prompt");
   repwdId.innerHTML="";
    if(pwd!=repwd){
       repwdId.innerHTML="两次输入的密码不一致";
    return false;
      }
      return true;
    }

/*验证邮箱*/
function checkEmail(){
  var email=$("email");
  var email_prompt=divId("email_prompt");
  email_prompt.innerHTML="";
  var reg=/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;    
    if(reg.test(email)==false){
      email_prompt.innerHTML="Email格式不正确，例如web@sohu.com";
    return false;
      }
      return true;
}
/*验证手机号码*/
function checkMobile(){
    var mobile=$("mobile");
    var mobileId=divId("mobile_prompt");
    var regMobile=/^1\d{10}$/;
    if(regMobile.test(mobile)==false){
        mobileId.innerHTML="手机号码不正确，请重新输入";
        return false;
        }
        mobileId.innerHTML="";
        return true;
    }

function checkPwd(){
	  var pwd=$("pwd");
	  var pwdId=divId("pwd_prompt");
	   pwdId.innerHTML="";    
	  var reg=/^[a-zA-Z0-9]{4,10}$/;    
	    if(reg.test(pwd)==false){
	       pwdId.innerHTML="密码不能含有非法字符，长度在4-10之间";
	    return false;
	      }
	      return true;
	    }
	    
function checkIdcardnumber(){
	var idcardnumber=$("idcardnumber");
	var idcardnumberId=divId("idcardnumber_prompt");
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	idcardnumberId.innerHTML="";
	if(reg.test(idcardnumber) == false)
	{
		idcardnumberId.innerHTML="身份证输入不合法";
		return false;
	}
}
function checkTicketnumber(){
	var ticketnumber =$("ticketnumber");
	var ticketnumberId=divId("ticketnumber_prompt");
	var reg =/^[1-9]\d*$|^0$/;
	ticketnumberId.innerHTML="";
	if(reg.test(ticketnumber)==false){
		alert("购票张数不合理");
		return false;
	}
	var balance=$("bce");
	if(ticketnumber>balance){
		alert("购票张数大于上限");
		return false;
	}	
	return true;
}