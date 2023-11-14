import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import concentration from '@/view/body/concentration'
import free from '@/view/body/free'
import mediumVideo from '@/view/body/mediumVideo'
import tv from '@/view/body/tv'
import film from '@/view/body/film'
import variety from '@/view/body/variety'
import comic from '@/view/body/comic'
import juvenile from '@/view/body/juvenile'
import documentary from '@/view/body/documentary'
import list from '@/view/list/list'
import detail from '@/view/detail/detail'
import index from '@/view/index/index'
import userInfo from '@/view/userInfo/userInfo'
import history from '@/view/userInfo/history'
import chasingDrama from '@/view/userInfo/chasingDrama'
import comment from '@/view/userInfo/comment'
import collect from '@/view/userInfo/collect'
import about from '@/view/about/aboutBox'
const originalPush = Router.prototype.push

Router.prototype.push = function push(location) {
  console.log("push函数被调用了")
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index,
      redirect:'concentration',
      children: [
        {
          path: '/concentration',
          name: 'concentration',
          component: concentration
        },
        {
          path: '/free',
          name: 'free',
          component: free
        },
        {
          path: '/mediumVideo',
          name: 'mediumVideo',
          component: mediumVideo
        },
        {
          path: '/tv',
          name: 'tv',
          component: tv
        },
        {
          path: '/film',
          name: 'film',
          component: film
        },
        {
          path: '/variety',
          name: 'variety',
          component: variety
        },
        {
          path: '/comic',
          name: 'comic',
          component: comic
        },
        {
          path: '/juvenile',
          name: 'juvenile',
          component: juvenile
        },
        {
          path: '/documentary',
          name: 'documentary',
          component: documentary
        },
        {
          path: '/list',
          name: 'list',
          component: list
        },
        {
          path: '/detail',
          name: 'detail',
          component: detail
        },
        {
          path: '/about',
          name: 'about',
          component: about
        },
        {
          path: '/userInfo',
          name: 'userInfo',
          component: userInfo,
          children:[
            {
              path: '/history',
              name: 'history',
              component: history,
            },
            {
              path: '/chasingDrama',
              name: 'chasingDrama',
              component: chasingDrama,
            },
            {
              path: '/collect',
              name: 'collect',
              component: collect,
            },
            {
              path: '/comment',
              name: 'comment',
              component: comment,
            }
          ]
        }
      ]
    },
  ]
})
