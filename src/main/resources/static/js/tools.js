//监听工具栏按钮事件
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.op-button');
    const textarea = document.getElementById('in');
    // 设置第一个按钮为默认选中状态
    if (buttons.length > 0) {
        buttons[0].classList.add('selected');
        textarea.value = '';
    }
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            // 移除所有按钮的选中状态
            buttons.forEach(btn => btn.classList.remove('selected'));
            // 添加当前按钮的选中状态
            this.classList.add('selected');
            textarea.value = '';
        });
    });
});

function execute (){
    const textarea = document.getElementById('in');
    const route = document.querySelector('.selected');
    const op = document.getElementById("op");
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
                msg('成功调用', 0)
            } else {
                msg(response.msg, 0)
            }
        },
        error: function(error) {
            msg(error.msg, 0)
        }
    });
}
