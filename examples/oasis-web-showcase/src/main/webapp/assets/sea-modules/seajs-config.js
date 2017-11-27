var baseUrl = '';         //网站的根目录地址，发布到线上时使用
var jsBase = '/assets/js';
var __ctx = baseUrl;

seajs.config({

  // 别名配置
/*  alias: {

  },*/

  // 路径配置
/*  paths: {
    'gallery': 'https://a.alipayobjects.com/gallery'
  },
*/
  // 变量配置
  vars: {
    'ctx': baseUrl + jsBase
  },

  // 映射配置
  map: [
    ['http://example.com/js/app/', 'http://localhost/js/app/']
  ],

  // 预加载项
/*  preload: [
    Function.prototype.bind ? '' : 'es5-safe',
    this.JSON ? '' : 'json'
  ],
*/	
  // 调试模式
/*  debug: true,
*/
  // Sea.js 的基础路径
  //base: '/assets/sea-modules',

  // 文件编码
  charset: 'utf-8'
});