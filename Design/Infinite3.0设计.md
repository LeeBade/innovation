# 需求分析
## 美化

### 进入小程序的加载页动画

### 减少文字增加icon

### 为icon赋予动画效果作为提示信息

## 一级页面

### 导航

- 底部导航栏
  - 生效范围：仅在一级页面：首页+我的页面
  - 准备两个icon
### 首页：

- 职责：
  - 长列表拉取所有项目
    - 项目名+owner名
    - 封面
    - 跳转到preview的入口
      - 演示文件入口
      - 视频入口
    - 跳转到detail的入口
  - 返回顶部的按钮
- 数据要求
  - items：array
    - itemInfo
      - id
      - name
      - ownerInfo
        - id
        - name
        - phone
        - identify
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口
- 搜索入口
  - 收集关键词然后跳转到搜索页
- 创建项目按钮



### 我的页面

- 职责
  - 未登录显示登录按钮
  - 已登陆
    - 显示用户信息
    - 点击修改个人信息
      - 显示修改昵称和手机号的表单
      - 点击提交后显示提示信息，确认后隐藏表单
    - 长列表显示用户拥有的项目
    - 创建项目入口，跳转到create页面
- 数据要求
  - userInfo
    - id
    - phone
    - name
  - userItems:array
    - itemInfo
      - id
      - name
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口
## 二级页面

### 搜索页

- 长列表显示所有项目
  - 没有搜索到提示用户
- 数据要求
  - searchItems：array
    - itemInfo
      - id
      - name
      - ownerInfo
        - id
        - name
        - phone
        - identify
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口

### preview页

- 中间页面作为容纳webview的容器
- 数据要求
  - 浏览入口的path


### create页

- 表单+icon+提示信息+进度条
- 名字
  - 提示信息：长度1-30字符
- 演示文件
  - icon：方框+上传图标作为按钮
  - 提示信息：
    - 建议使用pdf得到最好的演示效果，最多只能上传一个文件
    - 免费的格式转换网站：https://convertio.co/zh/
    - 支持大部分主流文件格式，若发现不支持请删除原项目并重新上传其它格式；
- 演示视频
  - icon：方框+上传图标作为按钮
  - 提示信息：支持大部分主流视频格式，若发现不支持请删除原项目并重新上传其它格式；
- 提交按钮
  - 提交userId+itemName->并行提交文件

### detail页

- 已登录
  - 显示项目负责人和老师的信息
- 是本人的项目：增加删除按钮
  - 删除使用弹窗确定
- 一级标题显示项目名
- 二级标题显示用户名
- 列表显示所有文件的下载入口

# 前端设计

## api

### common

- 封装uni.request暴露给其它api
  - return Promise 封装成功和错误报文
### item
- getItems
  - 获取全部项目
  - method：GET
  - params：none
  - return：
    - items：array
      - itemInfo
        - id
        - name
        - ownerInfo
          - id
          - name
          - phone
          - identify
        - fileInfo
          - coverPath：本地
          - pptPath：服务端浏览入口
          - videoPath：服务端浏览入口
    - err：无
- searchItems
  - 搜索项目
  - method：GET
  - params：keywords
  - return：
    - items：array
      - itemInfo
        - id
        - name
        - ownerInfo
          - id
          - name
          - phone
          - identify
        - fileInfo
          - coverPath：本地
          - pptPath：服务端浏览入口
          - videoPath：服务端浏览入口
    - err：无
- getUserItems
  - 获取用户的全部项目
  - params：userId
  - method：GET
  - return：
    - items：array
      - itemInfo
        - id
        - name
        - ownerInfo
          - id
          - name
          - phone
          - identify
        - fileInfo
          - coverPath：本地
          - pptPath：服务端浏览入口
          - videoPath：服务端浏览入口
    - err：无
- deleteItem：删除项目及其文件
  - params:itemId
  - method:DELETE
  - return:null
  - err:null
- ceateItem
  - params:userId(从全局存储获取)、itemName
  - method:POST
  - return:
    - itemInfo
        - id
        - name
        - ownerInfo
          - id
          - name
          - phone
          - identify
        - fileInfo
          - coverPath：本地
          - pptPath：服务端浏览入口
          - videoPath：服务端浏览入口
  - err
    - 10001:最多只能创建三个项目哦，请进入项目详情页进行删除再返回创建
    - 10002：抱歉，您暂时不能创建项目，请联系管理员或老师申请权限哦！
### files

- uploadDemoFile
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false
  - err:
    - 20001：对不起，文件上传失败，请重新上传
    - 20002：请选择主流格式的演示文件（建议pdf），请重新上传
- uploadDemoVideo
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false
  - err:
    - 20001：对不起，文件上传失败，请重新上传
    - 20003：请选择主流格式的演示视频，请重新上传
- uploadCoverImage
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false
  - err:
    - 20001：对不起，文件上传失败，请重新上传
    - 20004：请选择主流格式的图片，请重新上传

### user

- getUserInfo
  - params：userId
  - method：GET
  - return：
    - userInfo
      - id
      - name
      - phone
      - identify
- login
  - params：null
  - method：GET
  - return：
    - userInfo
      - id
      - name
      - phone
      - identify
- updateUserInfo
  - params:name-false,phone-false
  - method：PATCH
  - return:null
  - err:null
## common

### errorPopup

- params：错误信息
- 弹窗显示三秒的错误

## globalData

## 组件

### ProfilesEditor
https://blog.csdn.net/weixin_59733368/article/details/126263649
- 数据
  - 父组件传入数据
    - Profiles：array（可能需要更改）
      - name：表单名
      - ruleform和rule
      - value：现有值
      - placeholder：提示语
      - api引用
      - 提交所调用的方法
  - 方法：
    - 确认的方法、取消的方法
    - 

### showItems
https://blog.csdn.net/qq_41968486/article/details/109304386
- 功能：长列表显示项目
  - 项目名+owner名
    - 封面
    - 跳转到preview的入口
      - 演示文件入口
      - 视频入口
    - 跳转到detail的入口
  - 返回顶部的按钮
  - 分享按钮https://www.bing.com/search?q=微信小程序分享功能&qs=LT&pq=微信小程序分&sk=LT2&sc=10-6&cvid=F3143566D1FE476086223719CAA9FB85&FORM=QBRE&sp=3&ghc=1&lq=0
- 数据：
  - 父组件传入需要显示的items
  - items：array
    - itemInfo
      - id
      - name
      - ownerInfo
        - id
        - name
        - phone
        - identify
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口
    - 
### uploadFile
- 验证子组件是否能正常调用父组件中的方法，然后将返回值存储到父组件的数据中
- https://www.cnblogs.com/jin-zhe/p/9523782.html


- 数据
  - 需要父组件传入
    - 项目名字：“演示视频、文件、封面”
    - 提示信息
    - 支持的类型列表
    - 不支持的类型列表
    - 触碰图标时调用的方法
  - 三个icon
    - upload
    - √
    - ×
- method
  - 这里思考如何显示已选择的文件名，或者项目已经绑定的文件名
    - 目前的想法是在子组件绑定父组件的filePath三个属性中的一个   

## 一级页面

### 首页-index

- 用户进入到准备好显示加载动画：loading效果即可，底色绿色
- template
  - 搜索框+跳转到搜索页的confirm
  - 组件showItems+传入items
  - 登录按钮+icon：加号
    - 检查hasLogin，若未登录则弹窗显示登录或取消
- data
  - userInfo
  - hasLogin
  - items：array
    - itemInfo
      - id
      - name
      - ownerInfo
        - id
        - name
        - phone
        - identify
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口
- onload
  - 不使用本地存储，加载时自动登录用code换取openId，获取到openId后将userInfo存储到全局数据
  - 调用getItems装载到items
  - 

### 我的主页-user

- onload
  - 获取全局数据userInfo
- 数据
  - hasLogin
  - userInfo
  - userItems：array
    - itemInfo
      - id
      - name
      - ownerInfo
        - id
        - name
        - phone
        - identify
      - fileInfo
        - coverPath：本地
        - pptPath：服务端浏览入口
        - videoPath：服务端浏览入口
  - getlogin()
    - 调用login（api）->调用getUserItems，设置hasLogin=true、将全局数据赋值给userInfo
- template
  - v-if="hasLogin"
    - 组件profilesEditor：
      - 名字
      - 手机号
    - 黄老师的信息
    - 创建项目按钮-跳转到create页面
    - 组件showItems
  - v-else
    - 登录按钮
### 底部导航栏
https://juejin.cn/post/7139720358805897230
## 二级页面

### create

- 页面生命周期
  - onLoad（多次测验试探onLoad是否会重复加载）
    - item!==undefined
      - isIdReady=true
      - 深克隆
    - else item=undefined
- 数据
  - isIdReady：false
- template
  - 步骤条：https://uniapp.dcloud.net.cn/component/uniui/uni-steps.html#%E4%BB%8B%E7%BB%8D
  - 组件ProfilesEditor
    - 项目名
    - 成功后显示上传演示文件的三个按钮
    - createItem然后将返回值存储到
  - if(isIdReady)
    - 组件uploadFile
      - uploadFile
    - 提交按钮和2秒以上的等待动画
- 数据
  - itemInfo
    - id:undefined
    - name
    - ownerInfo
      - id
      - name
      - phone
    - fileInfo
      - coverPath：本地
      - pptPath：服务端浏览入口
      - videoPath：服务端浏览入口
  - isReady：false
- methods
  - 三个选择文件的方法->将path暂存到itemInfo.fileInfo中/弹窗显示错误
  - 上传文件
    - 并行调用三个upload-api
    - return isReady===true->更新itemInfo
    - /弹窗显示错误

### preview
- onload解析浏览入口，然后装载

### search

- onload
  - 解析keywords
  - 调用searchItems-api，然后绑定数据
- template
  - 搜索框
  - 组件showItems
- 数据
  - searchItems
    - id:undefined
    - name
    - ownerInfo
      - id
      - name
      - phone
    - fileInfo
      - coverPath：本地
      - pptPath：服务端浏览入口
      - videoPath：服务端浏览入口
### detail
- 显示负责人的联系信息、老师的联系信息、各个文件的下载入口、预览的入口
- 如果用户拥有该项目则显示删除、编辑按钮
- 删除弹出确认框
- 编辑跳转到create界面，传递item参数
- onload
  - 传递item
- 
### router

```JSON
"pages": [{
		"path": "pages/index/index",
		"style": {
			"navigationBarTitleText": "首页"
		}
	},{
		"path": "pages/create/create",
		"style": {
			"navigationBarTitleText": "edit"
		}
	},{
		"path": "pages/detail/detail",
		"style": {
			"navigationBarTitleText": "detail"
		}
	},{
		"path": "pages/user/user",
		"style": {
			"navigationBarTitleText": "video"
		}
	},{
		"path": "pages/editinfo/editinfo",
		"style": {
			"navigationBarTitleText": "personalInfo"
		}
	},{
		"path": "pages/preview/preview",
		"style": {
			"navigationBarTitleText": "preview"
		}
	}],

```


# 后端设计

## Controller
- validation校验
- 胶水层
### items

- items数据格式
  - id、name、ownerInfo{id、name、phone、identify}、fileInfo{coverPath、pptPath、videoPath}
  - identify由枚举类维护，权限控制完全由后端决定，目前：
    - 1-"普通用户"
    - 2-"组长"
    - 3-"管理员"
- getItems
  - 获取全部项目
  - method：GET
  - params：none
  - return：items
  - err：无
- searchItems
  - 搜索项目
  - method：GET
  - params：keywords
  - return：items：array
  - err：无
- getUserItems
  - 获取用户的全部项目
  - params：userId
  - method：GET
  - return：items
  - err：无
- deleteItem：删除项目及其文件
  - params:itemId
  - method:DELETE
  - return:null
  - err:null
- createItem
  - params:userId、itemName
  - method:POST
  - return:itemInfo
### files

- uploadDemoFile
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false
- uploadDemoVideo
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false
- uploadCoverImage
  - params:
    - multipartFile
    - itemId
  - mathod:POST
  - return:
    - itemInfo完整的信息，缺省的信息标注null
    - 项目已完整则返回isReady：true，否则返回false

### user

- getUserInfo
  - params：userId
  - method：GET
  - return：
    - userInfo
      - id
      - name
      - phone
      - identify
- login
  - params：null
  - method：GET
  - return：
    - userInfo
      - id
      - name
      - phone
      - identify
- updateUserInfo
  - params:name-false,phone-false
  - method：PATCH
  - return:null
  - err:null

### exception
- 10001:最多只能创建三个项目哦，请进入项目详情页进行删除再返回创建
- 10002：抱歉，您暂时不能创建项目，请联系管理员或老师申请权限哦！
- 20001：对不起，文件上传失败，请重新上传
- 20002：请选择主流格式的演示文件（建议pdf），请重新上传
- 20003：请选择主流格式的演示视频，请重新上传
- 20004：请选择主流格式的图片，请重新上传
- 20005: 抱歉，目标文件已移动或删除
## Domain

### mysql
- item
  - id:auto-increament
  - name:varchar(40)
  - userId:varchar(30)
- user
  - id:varchar(30)
  - name，varchar(20)
  - phone，varchar(13)
  - accessLevel：TINYINT

### files
- 修改文件名为：项目id+"&"+源文件名
