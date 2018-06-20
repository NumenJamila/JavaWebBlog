$().ready(function() {
    $("#login_form").validate({
        rules: {
            username: "required",
            password: {
                required: true,
                minlength: 5
            },
        },
        messages: {
            username: "This field is required.",
            password: {
                required: "This field is required.",
                minlength: jQuery.format("Please enter at least {0} characters."),
            }
        }
    });
    $("#register_form").validate({
        rules: {
            username: "required",
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
                minlength: jQuery.format("Please enter at least {0} characters.")
            },
            rpassword: {
                equalTo: "Your new password and confirmed new password do not match."
            },
            email: {
                required: "This field is required.",
                email: "Please enter a valid email address."
            }
        }
    });
});
