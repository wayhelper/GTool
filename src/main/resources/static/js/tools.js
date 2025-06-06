let edit = true;
let bgcolor ='#f4f4f9';

// after load
 window.onload = function () {
     let bulletin = localStorage.getItem("bulletin");
     if (!bulletin) {
         localStorage.setItem("bulletin", "感谢您访问我们的工具网站！我们提供多种实用工具，帮助您更高效地完成工作。请随时探索和使用！如您需要更多定制功能请自行fork本项目进行二次开发");
         createAnnouncementModal(localStorage.getItem("bulletin"));
     }
     if (localStorage.getItem('theme')==='') {
         localStorage.setItem('theme', 'chrome')
     }
     if (localStorage.getItem('bgcolor')==='') {
         localStorage.setItem('bgcolor', '#f4f4f9');
     } else {
         ['body', '.container', '.footer'].forEach(selector => {
             const element = document.querySelector(selector);
             if (element) {
                 element.style.backgroundColor = localStorage.getItem('bgcolor');
             }
         });
     }
     //open wellcome
     msg('欢迎使用~~', 1000);
     console.log('欢迎使用GTool工具站^_^');
 }

//listen tbar
document.addEventListener('DOMContentLoaded', function () {
    const routeValue = document.getElementById('ro')?.value || ''; // 确保元素存在且有值
    const buttons = document.querySelectorAll('.route-button');

    if (buttons.length > 0) {
        if (!routeValue) {
            // 如果 routeValue 为空，默认选中第一个按钮
            buttons[0].classList.add('selected');
        } else {
            // 尝试找到与 routeValue 对应的按钮
            const btn = Array.from(buttons).find(button => button.value === routeValue);
            if (btn) {
                btn.classList.add('selected');
                route(btn); // 调用 route 函数
            }
        }
    }

    // 为每个按钮绑定点击事件
    buttons.forEach(button => {
        button.addEventListener('click', function () {
            buttons.forEach(btn => btn.classList.remove('selected')); // 清除所有按钮的选中状态
            this.classList.add('selected'); // 设置当前按钮为选中状态
            route(this); // 调用 route 函数
        });
    });
});
 // init aceeditor
 function init(language, value) {
     editor = ace.edit("code");
     editor.setTheme("ace/theme/" + localStorage.getItem('theme'));
     editor.session.setMode("ace/mode/" + language);
     editor.setFontSize(16);
     editor.setReadOnly(false);
     editor.setOption("wrap", "free");
     if (edit) {
         if (value===null||value===''){
             editor.setValue('', -1);
         }else {
             editor.setValue(value, -1);
         }
     }
     edit = false;
     editor.setShowPrintMargin(false);
     ace.require("ace/ext/language_tools");
     editor.setOptions({
         enableBasicAutocompletion: true,
         enableSnippets: true,
         enableLiveAutocompletion: true
     });
 }

 // change theme
 function changeTheme() {
     theme = localStorage.getItem('theme') === 'chrome' ? 'monokai' : 'chrome';
     editor = ace.edit("code");
     editor.setTheme("ace/theme/" + theme);
     localStorage.setItem('theme', theme);

     const bgColor = localStorage.getItem('bgcolor') === '#f4f4f9' ? '#2C2C2C' : '#f4f4f9';
     ['body', '.container', '.footer'].forEach(selector => {
         const element = document.querySelector(selector);
         if (element) {
             element.style.backgroundColor = bgColor;
         }
     });
     localStorage.setItem('bgcolor', bgColor);
}
//route
function route(route){
    loading(true);
    let formData = {
        route: route.value
    };
    $.ajax({
        type: 'POST',
        url: '/gtool/tools/route',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                updateOps(route.value, response.data);
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        },
        complete: function() {
            loading(false);
        }
    });
}
// update ops
function updateOps(op, ops){
     if (op==='JSON') {
         init('json',null);
     }else if (op==='TRANSLATE') {
         init('markdown',null)
     } else {
         init('sql', null);
     }
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
// execute method
function execute (op){
     let key = handle(op);
     loading(true);
    const editor = ace.edit("code");
    const route = document.querySelector('.selected');
    const sharebutton = document.querySelector('.share-btn');
    const themeBtn = document.querySelector('.theme-btn');
    let formData = {
        route: route.value,
        op:op.value,
        data: key ===null ? editor.getValue() : editor.getValue()+','+key
    };
    $.ajax({
        type: 'POST',
        url: '/gtool/tools/execute',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            if (response.code === 200) {
                msg(response.msg, 1500);
                editor.setValue(response.data, 1);
                themeBtn.style.display = 'none';
                sharebutton.style.display = 'inline-block';
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        },
        complete: function() {
            loading(false);
        }
    });
}
// share gtool
function share (){
    const themeBtn = document.querySelector('.theme-btn');
    themeBtn.style.display = 'inline-block';
    loading(true);
    const editor = ace.edit("code");
    const sharebutton = document.querySelector('.share-btn');
    const route = document.querySelector('.selected');
    let formData = {
        route: route.value,
        data: editor.getValue()
    };
    $.ajax({
        type: 'POST',
        url: '/gtool/tools/share',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            sharebutton.style.display = 'none';
            if (response.code === 200) {
                navigator.clipboard.writeText(response.data)
                    .then(() => {
                        msg(response.msg, 1500);
                    })
                    .catch(err => {
                        msg(response.msg, 1500);
                    });
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        },
        complete: function() {
            loading(false);
        }
    });
}
// handle key
function handle(op){
    let key = null;
    let promptMessage = '';

    if (op.value === 'EDES') {
        promptMessage = "请输入加密密钥：";
    } else if (op.value === 'DDES') {
        promptMessage = "请输入解密密钥：";
    } else {
        return key;
    }

    key = prompt(promptMessage);
    if (!key) {
        alert("使用默认密钥");
        return;
    }

    return key;
}


