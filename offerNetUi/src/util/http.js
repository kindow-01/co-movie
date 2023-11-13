import { ACCESS_TOKEN, USER_NAME, USER_INFO, BASE_URL } from "./constants"
import axios from "axios";
import Vue from 'vue'
import store from "../main"
const baseUrl = BASE_URL  //请求接口地址
const httpRequest = (url, method, data) => {
	let meth = method.toUpperCase();//小写改为大写
	if (!data) {
		data = {}
	}

	let httpDefaultOpts = {
		url: baseUrl + url,
		method: meth == 'GET' ? 'GET' : 'POST',
		headers: {
			'X-Requested-With': 'XMLHttpRequest',
			'Content-type': meth == 'POSTJSON' ? 'application/json' : 'application/x-www-form-urlencoded',
			'X-Access-Token': window.localStorage.getItem(ACCESS_TOKEN)
		},
		dataType: 'json',
	}
	if (meth == 'POSTJSON' || meth == 'POSTFILE') {
		httpDefaultOpts.data = data;
	} else {
		httpDefaultOpts.params = data;
	}

	console.log(httpDefaultOpts);
	return new Promise(function (resolve, reject) {
		axios.request(httpDefaultOpts).then(
			(res) => {
				if (res.data.code == 200) {//成功返回
					resolve(res.data)
				} else {//错误信息
					if (res.data.code == 401) {
						store.dispatch('setShowLoginBoxData', true)
					} else {
						Vue.prototype.$notify.error({
							title: '错误',
							message: res.data.message,
							offset: 50
						});
					}
					resolve(res.data) //错误信息返不返回 看个人情况
				}
			}
		).catch(
			(error) => {
				if (error.response.status === 401) {
					//如果存在notOpenLoginBox就说明出现401不需要弹出登陆窗口
					if (data.notOpenLoginBox) {

					} else {
						// 执行跳转到登录页或者其他操作
						store.dispatch('setShowLoginBoxData', true)
					}
					//最终清除一下缓存
					store.dispatch('setUser', '{}')
					localStorage.removeItem(USER_INFO);
				} else {
					Vue.prototype.$notify.error({
						title: '错误',
						message: '服务器异常，请联系管理员',
						offset: 50
					});
				}
				reject(error)
			}
		)
	})
};

export default {
	baseUrl,
	httpRequest
}
