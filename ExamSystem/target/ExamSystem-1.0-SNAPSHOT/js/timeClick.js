function beginClick() {
    //设置时间倒计时
    setCountDown_time();
}
/*时间倒计时*/
var sec =60,min =39;
var format = function(str) {
    if(parseInt(str) < 10) {
        return "0" + str;
    }
    return str;
};
function setCountDown_time(){
    var idt = window.setInterval("ls();", 1000);
}
function ls() {
    if (sec>=0){
        sec--;
    }
    if(sec == 0&&min>0) {
        min--;
        sec = 59;
    }
    document.getElementById("countdown_time").innerHTML = format(min) + ":" + format(sec);
    if(min == 0 && sec== 0) {
        alert('考试时间已到，请提交试卷，感谢您的使用！');
        return;
    }
}