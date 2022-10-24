define(function (require, exports, module) {
    console.log(utils);

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "focus .pwd": "hanlderPwd",
            "blur .pwd": "hanlderMove",
            "click .login-btn": "checkCaptcha",
            "click .captcha":"getCaptcha"
        },



        initialize: function () {
            this.model = new Backbone.Model();
            this.handlerKeyup();
            this.getCaptcha();
            this.checkCaptcha();
        },

        checkCaptcha:function (){
            var _this = this;
            var verify = $("#captcha").val();
            var uuid = localStorage.getItem("uuid");
            $.ajax({
                url: 'http://localhost:8088/checkCaptcha',
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify({
                    "uuid": uuid,
                    "verify": verify
                }),
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    var code = res.code;
                    if (code == "200") {
                        _this.hanlderSubmit();
                    }
                },
                fail: function () {
                }
            })
        },
        getCaptcha:function (){
            $.ajax({
                url: 'http://localhost:8088/captcha',
                type: 'GET',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    var code = res.code;
                    if (code == "200") {
                        // 保存验证码对应的uuid，用于验证
                        // 前端生成图片
                        localStorage.setItem("uuid",res.uuid);
                        $(".captcha").attr("src","data:image/png;base64,"+res.img);
                    }
                },
                fail: function () {
                }

            });
        },
        hanlderPwd: function () {
            $(".hand").addClass("active");
        },

        hanlderMove: function () {
            $(".hand").removeClass("active");
        },

        hanlderSubmit: function () {
            var name = $(".user-name").val();
            var password = $(".pwd").val();
            if(name == "") {
                utils.showTip("用户名不能为空");
                return;
            }
            if(password == "") {
                utils.showTip("密码不能为空");
                return;
            }
 
            utils.getLogin("/user/login", {
                "username": name,
                "password": password,

            }, function (res) {
                var token = res.token;
                var resourceList = JSON.stringify(res.resourceList);

                console.log(res);
                localStorage.setItem("token", token);
                localStorage.setItem("userName", res.name);
                localStorage.setItem("resourceList", resourceList);


                window.location.href = "../index/index.html";
            })
        },

        handlerKeyup:function(event) {
            $(document).keydown(function(event){
                if(event.keyCode == 13) {
                    this.hanlderSubmit();
                }
            }.bind(this));

        }


    });

    var home = new Home();

});

seajs.use('./login.js');
