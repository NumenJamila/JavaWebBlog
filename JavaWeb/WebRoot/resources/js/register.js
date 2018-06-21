$().ready(function() {
    $("#login_form").validate({
        rules: {
            username: "required.",
            password: {
                required: true,
                minlength: 5
            },
        },
        messages: {
            username: "This field is required.",
            password: {
                required: "This field is required.",
                minlength: jQuery.format("请输入至少 {0} 位密码."),
            }
        }
    });
    $("#register_form").validate({
        rules: {
            username: "required.",
            password: {
                required: true,
                minlength: 5
            },
            rpassword: {
                equalTo: "#register_password"
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            username: "This field is required.",
            password: {
                required: "This field is required.",
                minlength: jQuery.format("请输入至少 {0} 位密码.")
            },
            rpassword: {
                equalTo: "两次密码不一致."
            },
            email: {
                required: "This field is required.",
                email: "请输入邮箱地址."
            }
        }
    });
});
