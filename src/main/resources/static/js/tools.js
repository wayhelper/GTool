//set language
const language = 'json';
// after load
 window.onload = function () {
     //open wellcome
     msg('欢迎使用~~', 1000);
     console.log('欢迎使用GTool工具站^_^');
 }

//listen tbar
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.route-button');
    if (buttons.length > 0) {
        buttons[0].classList.add('selected');
    }
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            buttons.forEach(btn => btn.classList.remove('selected'));
            this.classList.add('selected');
        });
    });
});

 function init(editor) {

     //设置风格和语言（更多风格和语言，请到github上相应目录查看）
     theme = "clouds"
     editor.setTheme("ace/theme/" + theme);

     editor.session.setMode("ace/mode/" + language);

     //字体大小
     editor.setFontSize(16);

     //设置只读（true时只读，用于展示代码）
     editor.setReadOnly(false);

     //自动换行,设置为off关闭
     editor.setOption("wrap", "free");
     editor.setValue('', -1);
     //启用提示菜单
     ace.require("ace/ext/language_tools");
     editor.setOptions({
         enableBasicAutocompletion: true,
         enableSnippets: true,
         enableLiveAutocompletion: true
     });
 }

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

    // clear button
    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }

    // add new button
    ops.forEach(op => {
        const button = document.createElement('button');
        button.textContent = op.name;
        button.value = op.op;
        button.onclick = function() { execute(this); };
        container.appendChild(button);
    });
}

function execute (op){
    const editor = ace.edit("code");;
    const route = document.querySelector('.selected');
    let formData = {
        route: route.value,
        op:op.value,
        data: editor.getValue()
    };
    $.ajax({
        type: 'POST',
        url: '/tools/execute',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                msg(response.msg, 1500);
                editor.setValue(response.data, 1);
                console.log(response.data);
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}

