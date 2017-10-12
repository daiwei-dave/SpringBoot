/**
* @ $.loginScan()              基于jquery扩展的扫码登录插件,实例化，参数如下 
* @ $.loginScan().switchPage() 插件的显示与隐藏
* @ param "el"                 挂在父元素,默认body,(id: "#id")，可选
* @ param "isShow"             初始化时当前是否显示（true/false），默认false，可选
* @ param "style"              当前扫码登录的样式, 字符串类型 "style.xx"，可选
* @ param "style.width"        插件宽度，字符类型（px，%），可选
* @ param "style.height"       插件高度，字符类型（px，%），可选
* @ param "style.backgroundColor"插件高度，字符类型（px，%），可选
* @ param "message"            盒子内部显示的内容，对象如下"message.xx"，可选
* @ param "message.switchBtn"  顶部切换板块的内容，字符类型，可选
* @ param "message.title"      头部标题板块的内容，字符类型，可选
* @ param "message.tip"        底部提示板块的内容，字符类型，可选
* @ param "urlData"            请求类型对象，对象如下urlData.xx"，必填
* @ param "urlData.url"        第三方应用获取二维码图片的信息-id的请求地址，必填
* @ param "urlData.imgUrl"     第三方应用获取二维码图片信息，可选择性告诉服务器指定跳转页面的地址，必填
* @ param "urlData.checkurl"   第三方应用（前端）检查是否已通过认证的请求地址，必填
* @ param "urlData.data"       请求参数{appKey:"第三方应用的标识", redirectUrl:"登录成功后的跳转页面地址"}，appKey必填
* @ param "success"            登录成功后的回调函数，参数data为标示符code，，可选
* @ param "switchCallback"     点击切换时的回调函数，可选
* 请求返回值：
*      urlData.url:
*                请求参数
*                appKey	        string					第三方应用的标识
*                redirectUrl	string					登录成功后的跳转页面地址，非必填
*                返回参数
*                code	string					反馈的状态码，成功0，不成功非0
*                msg	string					反馈的信息，成功"SUCCESS"，不成功给出相应失败信息
*                data	array
*                       ---token	string			二维码id，标识准备获取二维码
*       urlData.imgUrl:
*                请求参数
*                appKey	string					第三方应用的标识
*                token	string					已获取的二维码id
*                返回参数
*                code	string					反馈的状态码，成功0，不成功非0
*                msg	string					反馈的信息，成功"SUCCESS"，不成功给出相应失败信息
*                image	stream					二维码图片，有效时间1分钟
*                data	null					
*       urlData.checkurl:
*                请求参数
*                appKey	string					第三方应用的标识
*                token	string					已获取的二维码id
*                返回参数
*                code	string					反馈的状态码，成功0，不成功非0
*                msg	string					反馈的信息，成功"SUCCESS"，不成功给出相应失败信息        
*                data	array					当登录成功后，才会返回，否则为null
*                       ---scanned	boolean	    小虫是否已扫码。false：未扫码；true：已扫码
*                       ---code	string			临时票据，第三方应用使用这个票据来换取token，scanned为true时才返回
*                code的状态码：0----SUCCESS，0503---无权限，1003---签名参数无效，1004---签名错误，1005---参数解码KEY无效，1006---参数解码错误，5001---二维码未扫描，请扫描二维码
*                             5002---授权登录失败，5003---二维码无效，5004---二维码已过期，1111---未知错误
**/
(function($){
    function login(opts){
        /****************** 私有方法 ***********************/ 
        // 盒子出现
        function appearModel(val) {
            var $$ = typeof val == "object" ? val : $(val);
            $$.css({
                'display': 'block'
            })
        }
        // 盒子消失
        function hideModel(val) {
            var $$ = typeof val == "object" ? val : $(val);
            $$.css('display', 'none');
        }
        // 扩展对象
        function extend(defualt,source){
            var result= defualt;
            for (var key in source) {
                result[key] = typeof source[key] === 'object' ? extend(result[key],source[key]): source[key];
            } 
            return result; 
        }
        // ajax
        function getMethod(url,data,fn){
            $.get(url,data,function(data){
                fn(data);
            })       
        }
        // 创建$dom
        function $dom(){
            // 主体部分
            var $loginContainer = $("<div></div>").addClass("ui_login_container"); //容器
            var $loginSwitch = $("<div></div>").addClass("login-switch"); // 切换
            var $loginTitle = $("<div></div>").addClass("login-title"); // 标题部分
            var $loginWrapper = $("<div></div>").addClass("login-wrapper"); // 内容区域
            var $loginTip = $("<div></div>").addClass("login-tip"); // 提示区域
            // 切换部分
            var $switch = $("<a href='javascript:void(0)'></a>"); 
            $loginSwitch.append($switch);
            // 扫码部分
            var $scan = $("<div></div>").addClass("login-scan");
            // 扫描成功
            var $success = $("<div></div>").addClass("login-success");
            $loginWrapper.append($scan).append($success);
            $loginContainer.append($loginSwitch)
                            .append($loginTitle)
                            .append($loginWrapper)
                            .append($loginTip);
            return {
                $loginContainer: $loginContainer,
                $loginSwitch: $loginSwitch,
                $loginTitle: $loginTitle,
                $loginWrapper: $loginWrapper,
                $loginTip: $loginTip,
                $switch: $switch,
                $scan: $scan,
                $success: $success
            }
        }
        //    
        /****************** 封装 ***********************/ 
        var loginScan = function (opts){
            return new loginScan.prototype.init(opts);
        };
        loginScan.prototype = {
            // 构造函数指向
            constructor: loginScan,
            // 默认参数
            defualt: {
                el: "body",
                isShow: false,
                style: {
                    width: "340px",
                    height: "260px",
                    backgroundColor: "#fff"
                },
                message: {
                    switchBtn: '<img src="./images/login-here@2x.png" alt=""><img src="./images/conputer-3@2x.png" alt="">',
                    title: "小虫登陆，安全登陆",
                    tip: '<img src="./images/scan-2@2x.png" alt="" />打开<span>小虫</span>，扫码登陆'
                },
                urlData: {
                    url: "",
                    imgUrl: "",
                    checkurl: "",
                    data: {
                        appKey: "",// 第三方应用的标识
                        redirectUrl: ""//登录成功后的跳转页面地址
                    }
                }
            },
            // 初始化
            init:function (opts){
                this.$element = $dom();
                this.defualt = extend(this.defualt,opts); // 初始化当前值
                this.loginPageBind(); // 登录页面渲染
                this.onceGetQR(); // 再次获取二维码
                this.switchBtnEvent(); // 切换
                this.loginStyle(); // 样式
                if(!this.defualt.isShow)return; // 页面不显示直接返回
                this.getQR(); // 获取二维码
            },
            // 登录页面渲染
            loginPageBind:function (){
                var switchDoms = this.defualt.message.switchBtn;
                var titleDoms = this.defualt.message.title;
                var scanDoms =  '<img src="" alt="" class="login-img" id="scan_id">'+
                                '<div class="login-err">'+
                                '    <div class="login-layer"></div>'+
                                '    <div class="login-err-message">'+
                                '        <p class="login-err-tip">二维码已失效</p>'+
                                '        <div>'+
                                '            <input type="submit" value="重新获取" id="once_getAR">'+
                                '        </div>'+
                                '    </div>'+
                                '</div>';
                var success =   '<div class="login-success-message">'+
                                '    <img src="./images/success-3@2x.png" alt="">扫描成功'+
                                '</div>'+
                                '<p class="login-success-sure">请在手机上确认登陆</p>';
                var tip = this.defualt.message.tip;
                $(this.defualt.el).html(this.$element.$loginContainer);
                this.$element.$switch.html(switchDoms);
                this.$element.$loginTitle.html(titleDoms);   
                this.$element.$scan.html(scanDoms);
                this.$element.$success.html(success);
                this.$element.$loginTip.html(tip);
                if(this.defualt.isShow){
                    this.$element.$loginContainer.show();
                }else{
                    this.$element.$loginContainer.hide();
                }
            },
            // 获取二维码
            getQR: function (){
                var _that = this;
                var url = _that.defualt.urlData.url;
                var imgurl = _that.defualt.urlData.imgUrl;
                var urlData = _that.defualt.urlData.data;
                hideModel(_that.$element.$success);
                appearModel(_that.$element.$scan);
                hideModel(".login-err");
                $("#scan_id").attr("src","");
                if(!url) return;
                getMethod(url,urlData,Qrbind);
                function Qrbind(data) {
                    data = JSON.parse(data);
                    if(data.code == 0){ 
                        $("#scan_id").attr("src",imgurl+"?token="+ data.data.token +"&appKey="+urlData.appKey);
                        _that.checkQR(data.data.token);
                    }
                }
            },
            // 验证扫码或登录信息
            checkQR: function (token){
                var _that = this;
                var url = _that.defualt.urlData.checkurl;
                var urldata = {
                    appKey: _that.defualt.urlData.data.appKey,
                    token: token // 用于验证应用是否已获取二维码，且获取的是哪个二维码;
                };
                _that.timer = null; 
                if(!url) return;
                _that.flagScan = false;
                toUrl(Qrbind);
                function toUrl(Qrbind){
                    if(_that.flagScan){
                        return;
                    }
                    getMethod(url,urldata,Qrbind);
                    _that.timer = setTimeout(toUrl,2000,Qrbind);
                }
                // 扫描过程处理
                function Qrbind(data){ 
                    data = JSON.parse(data);
                    if(data.code == "5006" && data.data.scanned && !data.data.code){
                        clearTimeout(_that.timer);
                        hideModel(_that.$element.$scan);
                        appearModel(_that.$element.$success);
                        var backQR = $('<a href="javascript:void(0)" class="blue-text" id="once_getAR">返回二维码登陆</a>');
                        _that.$element.$loginTip.html(backQR);
                        _that.backQRPage(backQR);
                        toUrl(isLogin); // 登录处理        
                    }else if(data.code == 5002){
                        clearTimeout(_that.timer);                    
                        _that.flagScan = true;
                        appearModel(".login-err");
                        $(".login-err-tip").text("授权登录失败");
                    }else if(data.code == 5003){
                        clearTimeout(_that.timer);                    
                        _that.flagScan = true;
                        appearModel(".login-err");
                        $(".login-err-tip").text("二维码无效");
                    }else if(data.code == 5004){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        appearModel(".login-err");
                        $(".login-err-tip").text("二维码已过期");
                    }
                }
                // 登录过程处理
                function isLogin(data){
                    data = JSON.parse(data);
                    if(data.code == 0 && data.data.code){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        _that.$element.$loginTip.html("登录中...");
                        _that.defualt.success?_that.defualt.success(data.data.code):null;
                    }else if(data.code == 5002){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        hideModel(_that.$element.$success);
                        appearModel(_that.$element.$scan);
                        appearModel(".login-err");
                        $(".login-err-tip").text("授权登录失败");
                    }else if(data.code == 5003){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        hideModel(_that.$element.$success);
                        appearModel(_that.$element.$scan);
                        appearModel(".login-err");
                        $(".login-err-tip").text("二维码无效");
                    }else if(data.code == 5004){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        hideModel(_that.$element.$success);
                        appearModel(_that.$element.$scan);
                        appearModel(".login-err");
                        $(".login-err-tip").text("二维码已过期");
                    }
                }
            },
            // 再次获取二维码
            onceGetQR: function (){
                var _that = this;
                $("#once_getAR").on("click",function(){
                    _that.getQR();
                })
            },
            // 返回扫码页
            backQRPage: function ($dom){
                var _that = this;
                $dom.on("click",function(){
                    clearTimeout(_that.timer);
                    _that.getQR();
                    _that.flagScan = true;
                    _that.$element.$loginTip.html(_that.defualt.message.tip);
                })
            },
            // 切换按钮事件
            switchBtnEvent: function (){
                var _that = this;
                this.$element.$switch.on("click",function(){
                    _that.defualt.isShow = !_that.defualt.isShow;
                    if(_that.defualt.isShow){
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        _that.$element.$loginContainer.show();
                        _that.getQR();
                    }else{
                        clearTimeout(_that.timer);
                        _that.flagScan = true;
                        _that.$element.$loginContainer.hide();
                        _that.defualt.switchCallback?_that.defualt.switchCallback():null;
                    }
                })
            },
            // 切换
            switchPage: function (){
                this.$element.$switch.trigger("click");           
            },
            // 尺寸
            loginStyle: function(){
                this.$element.$loginContainer.css({
                    "width": this.defualt.style.width,
                    "minWidth": "220px",
                    "height": this.defualt.style.height,
                    "backgroundColor": this.defualt.style.backgroundColor
                })
            }   
        };
        loginScan.prototype.init.prototype = loginScan.prototype;
        var login = loginScan(opts);
        function switchPage(){
            login.switchPage();
        }
        return {
            switchPage: switchPage
        }
    }

    $.extend({
        loginScan: login
    })
})(jQuery)
