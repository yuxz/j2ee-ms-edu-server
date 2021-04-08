import Vue from 'vue'
import App from '@/App'
import router from '@/router' // api: https://github.com/vuejs/vue-router
import store from '@/store' // api: https://github.com/vuejs/vuex
import VueCookie from 'vue-cookie' // api: https://github.com/alfhen/vue-cookie
import '@/element-ui' // api: https://github.com/ElemeFE/element
// import locale from 'element-ui/lib/locale/lang/en' //yxz 
import '@/icons' // api: http://www.iconfont.cn/
import '@/element-ui-theme'
import '@/assets/scss/index.scss'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
import { isAuth } from '@/utils'
import cloneDeep from 'lodash/cloneDeep'

// import MarkdownEditor from '@/components/MarkdownEditor'

//---summernote start
import 'bootstrap/js/dist/modal.js'
import 'bootstrap/js/dist/dropdown.js'
import 'bootstrap/js/dist/tooltip.js'
import 'bootstrap/dist/css/bootstrap.css'
import 'font-awesome/css/font-awesome.css'
import 'summernote'
import 'summernote/dist/lang/summernote-zh-CN'
import 'summernote/dist/summernote.css'
//---summernote end

// Vue.use(VueCookie, { local })
Vue.use(VueCookie)
Vue.config.productionTip = false

// 非生产环境, 适配mockjs模拟数据                 // api: https://github.com/nuysoft/Mock
if (process.env.NODE_ENV !== 'production') {
    require('@/mock')
}

// 挂载全局
Vue.prototype.$http = httpRequest // ajax请求方法
Vue.prototype.isAuth = isAuth // 权限方法

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    components: { App }
})