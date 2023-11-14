import http from '../util/http'
// import configService from '@/common/service/config.service.js';
const apiService = {
	/**
	 * 图片上传
	 */
	uploadFile(params) {
		return http.httpRequest('/upload/file', 'POSTFILE', params)
	},

	/**
 * 获取短信验证码--目前是模拟的
 */
	code(params) {
		return http.httpRequest('/api/user/code', 'post', params)
	},
	/**
	 * 登录
	 */
	login(params) {
		return http.httpRequest('/api/user/login', 'post', params)
	},
	/**
 * 注册
 */
	register(params) {
		return http.httpRequest('/api/user/register', 'post', params)
	},
	/**
	  * 获取当前登录用户信息
	  */
	getUserInfoById() {
		return http.httpRequest('/api/user/getUserInfoById', 'get')
	},
	/**
  * 编辑用户信息
  */
	updateUser(params) {
		return http.httpRequest('/api/user/update', 'postjson', params)
	},

	/**
	  * 视频专辑全查询
	  */
	videoSetAll(params) {
		return http.httpRequest('/api/videoSetDO/videoSetAll', 'get', params)
	},
	/**
  * 视频专辑列表
  */
	videoSetPage(params) {
		return http.httpRequest('/api/videoSetDO/list', 'get', params)
	},

	/**
  * 专辑详情
  */
	getVideoSetById(params) {
		return http.httpRequest('/api/videoSetDO/queryById', 'get', params)
	},
	/**
	  * 增加点击量
	  */
	videoSetAddClick(params) {
		return http.httpRequest('/api/videoSetDO/addClick', 'get', params)
	},

	/**
		* 今日热门查询
		*/
	videoSetHostList(params) {
		return http.httpRequest('/api/videoSetDO/videoSetHostList', 'get', params)
	},
	/**
	* 今日热门查询
	*/
	videoSetHostListByWeek(params) {
		return http.httpRequest('/api/videoSetDO/videoSetHostListByWeek', 'get', params)
	},
	/**
	* 推荐
	*/
	recommend(params) {
		return http.httpRequest('/api/videoSetDO/recommend', 'get', params)
	},
	/**
	* 推荐 返回用户是否追剧收藏等信息
	*/
	recommendAuth(params) {
		return http.httpRequest('/api/videoSetDO/recommendAuth', 'get', params)
	},

	/**
	* 推荐
	*/
	queryVideoList(params) {
		return http.httpRequest('/api/videoDO/queryList', 'get', params)
	},
	/**
	* 专辑标签列表
	*/
	videoSetTagPage(params) {
		return http.httpRequest('/api/categoryTagsDO/list', 'get', params)
	},
	/**
	* 记录观看历史
	*/
	recordHistory(params) {
		return http.httpRequest('/api/viewHistoryDO/recordHistory', 'postjson', params)
	},
	/**
* 获取当前视频合集观看到哪个视频了
*/
	getHistoryVideoByVideoSetId(params) {
		return http.httpRequest('/api/viewHistoryDO/getHistoryVideoByVideoSetId', 'post', params)
	},
	/**
	* 追剧或者取消追剧
	*/
	chasingDramaAddOrDel(params) {
		return http.httpRequest('	/api/chasingDramaDO/addOrDel', 'post', params)
	},
	/**
	* 追剧列表
	*/
	chasingDramaList(params) {
		return http.httpRequest('/api/chasingDramaDO/list', 'get', params)
	},
	/**
	* 收藏或取消收藏
	*/
	saveOrDelCollect(params) {
		return http.httpRequest('/api/collectDO/saveOrDel', 'postjson', params)
	},
	/**
	* 检查是否收藏
	*/
	checkCollect(params) {
		return http.httpRequest('/api/collectDO/check', 'get', params)
	},
	/**
	* 历史播放列表
	*/
	historyList(params) {
		return http.httpRequest('/api/viewHistoryDO/list', 'get', params)
	},
	/**
	* 查询历史播放时长
	*/
	historyQueryTime(params) {
		return http.httpRequest('/api/viewHistoryDO/queryTime', 'get', params)
	},

	/**
	* 收藏分页列表查询
	*/
	collectList(params) {
		return http.httpRequest('/api/collectDO/list', 'get', params)
	},


	/**
	* 花絮列表
	*/
	gagsList(params) {
		return http.httpRequest('/api/gagsDO/list', 'get', params)
	},

	/**
	  * 新增评论
	  */
	addComment(params) {
		return http.httpRequest('/api/comment/add', 'postjson', params)
	},
	/**
	 * 评列表论
	 */
	commentList(params) {
		return http.httpRequest('/api/comment/list', 'get', params)
	},
	/**
	 * 评列总数
	 */
	commentTotal(params) {
		return http.httpRequest('/api/comment/commentTotal', 'post', params)
	},
	/**
	* 评列表论 不需要认证
	*/
	commentListUnAuth(params) {
		return http.httpRequest('/api/comment/listUnAuth', 'get', params)
	},
	/**
	* 评论点赞
	*/
	commentLike(params) {
		return http.httpRequest('/api/comment/like', 'post', params)
	},

	/**
	  * 通过id查询文章
	  */
	getArticle(params) {
		return http.httpRequest('/api/article/queryById', 'get', params)
	},
	/**
	  * 新增文章
	  */
	addArticle(params) {
		return http.httpRequest('/api/article/add', 'POSTJSON', params)
	},
	/**
	  * 文章列表
	  */
	ArticleList(params) {
		return http.httpRequest('/api/article/list', 'get', params)
	},
	/**
	  * 手机号码登录
	  */
	phoneNoLogin(params) {
		return http.post('/sys/phoneLogin', params);
	},
	/**
	  * 退出
	  */
	logout(params) {
		return http.post('/sys/logout', params);
	}
	//,
	// /**
	//  * 获取文件访问路径
	//  * @param avatar
	//  * @param subStr
	//  * @returns {*}
	//  */
	// getFileAccessHttpUrl(avatar,subStr){
	//     if(!subStr) subStr = 'http'
	//     if(avatar && avatar.startsWith(subStr)){
	//         return avatar;
	//     }else{
	//         return configService.staticDomainURL + "/" + avatar;
	//     }
	// }
};

export default apiService;
