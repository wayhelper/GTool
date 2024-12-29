// 页面加载完成时执行
 window.onload = function () {
     //启用欢迎
     msg('欢迎~~', 1000);
 }

//监听工具栏按钮事件
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.route-button');
    //const textarea = document.getElementById('in');
    // 设置第一个按钮为默认选中状态
    if (buttons.length > 0) {
        buttons[0].classList.add('selected');
        //textarea.value = '';
    }
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            // 移除所有按钮的选中状态
            buttons.forEach(btn => btn.classList.remove('selected'));
            // 添加当前按钮的选中状态
            this.classList.add('selected');
            //textarea.value = '';
        });
    });
});

//自动行号
(function($) {
    var AutoRowsNumbers = function(element, config) {
        this.$element = $(element);
        this.$group = $('<div/>', { 'class': "textarea-group" });
        this.$ol = $('<div/>', { 'class': 'textarea-rows' });
        this.$wrap = $('<div/>', { 'class': 'textarea-wrap' });
        this.$group.css({
            "width": this.$element.outerWidth(true) + 'px',
            "display": config.display
        });
        this.$ol.css({
            "color": config.color,
            "width": config.width,
            "height": this.$element.height(),
            "font-size": this.$element.css("font-size"),
            "line-height": this.$element.css("line-height"),
            "position": "absolute",
            "overflow": "hidden",
            "margin": 0,
            "padding": 0,
            "text-align": "center",
            "font-family": this.$element.css("font-family")
        });
        this.$wrap.css({
            "padding": ((this.$element.outerHeight() - this.$element.height()) / 2) + 'px 0',
            "background-color": config.bgColor,
            "position": "absolute",
            "box-sizing": "border-box",
            "margin": 0,
            "width": config.width,
            "height": this.$element.height() + 'px'
        });
        this.$element.css({
            "white-space": "pre",
            "resize": "none",
            "margin": 0,
            "box-sizing": "border-box",
            "padding-left": (parseInt(config.width) - parseInt(this.$element.css("border-left-width")) + parseInt(this.$element.css("padding-left"))) + 'px',
            "width": (this.$element.width() - parseInt(config.width)) + 'px'
        });
    }

    AutoRowsNumbers.prototype = {
        constructor: AutoRowsNumbers,

        init: function() {
            var that = this;
            that.$element.wrap(that.$group);
            that.$ol.insertBefore(that.$element);
            this.$ol.wrap(that.$wrap);
            that.$element.on('input', { that: that }, that.inputText);  // Changed from 'keydown' to 'input'
            that.$element.on('scroll', { that: that }, that.syncScroll);
            that.inputText({data:{that:that}});
        },

        inputText: function(event) {
            var that = event.data.that;

            setTimeout(function() {
                var value = that.$element.val() || that.$element.text();
                if (value) {
                    var len = value.split(/\r\n|\n|\r/).length;
                    that.updateLine(len);
                    that.$ol.show();  // Show line numbers if there's input
                } else {
                    that.$ol.hide(); // Hide line numbers if no input
                }
                that.syncScroll({data: {that: that}});
            }, 0);
        },

        updateLine: function(count) {
            var html = '';
            for (var i = 1; i <= count; i++) {
                html += '<div>' + i + '</div>';
            }
            this.$ol.html(html);
        },

        syncScroll: function(event) {
            var that = event.data.that;
            that.$ol.children().eq(0).css("margin-top", -(that.$element.scrollTop()) + "px");
        }
    }

    $.fn.setTextareaCount = function(option) {
        var config = {};
        var option = arguments[0] ? arguments[0] : {};
        config.color = option.color ? option.color : "#FFF";
        config.width = option.width ? option.width : "30px";
        config.bgColor = option.bgColor ? option.bgColor : "#999";
        config.display = option.display ? option.display : "block";

        return this.each(function() {
            var $this = $(this),
                data = $this.data('autoRowsNumbers');

            if (!data) { $this.data('autoRowsNumbers', (data = new AutoRowsNumbers($this, config))); }

            if (typeof option === 'string') {
                return false;
            } else {
                data.init();
            }
        });
    }
})(jQuery);
//结束自动行号

function route(route){
    let formData = {
        route: route.value
    };
    $.ajax({
        type: 'POST',
        url: '/tools/route',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                updateOps(response.data);
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}

function updateOps(ops){
    const container = document.getElementById('ops');

    // 清除现有的所有按钮
    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }

    // 添加新的按钮
    ops.forEach(op => {
        const button = document.createElement('button');
        button.textContent = op.name;
        button.value = op.op;
        button.onclick = function() { execute(this); };
        container.appendChild(button);
    });
}


function execute (op){
    const textarea = document.getElementById('in');
    const route = document.querySelector('.selected');
    let formData = {
        route: route.value,
        op:op.value,
        data: textarea.value
    };
    $.ajax({
        type: 'POST',
        url: '/tools/execute',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                msg(response.msg, 3000);
                textarea.value=response.data;
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}

