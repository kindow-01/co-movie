// import store from '@/store'

function setRem() {
    var whdef = 100 / 1920;
    var bodyWidth = document.body.clientWidth;
    var rem = bodyWidth * whdef;
	if(rem<=60){
		rem = 60
	}
    document.getElementsByTagName('html')[0].style.fontSize = rem + 'px';
	// store.state.user.rem = rem
}

setRem()
window.onresize = function () {
    setRem()
}
