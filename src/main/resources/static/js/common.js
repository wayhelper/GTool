
    /**
    * show msg
    * @param {string} message
    * @param {number} duration ms
    */
    function msg(message, duration = 3000) {
        const msgDiv = document.createElement('div');
        msgDiv.className = 'custom-msg';
        msgDiv.textContent = message;

        document.body.appendChild(msgDiv);

        if (duration > 0) {
            setTimeout(() => {
                msgDiv.remove();
            }, duration);
        }else {
            const handleDocumentClick = function(event) {
                if (!msgDiv.contains(event.target)) {
                    msgDiv.remove();
                    document.removeEventListener('click', handleDocumentClick);
                }
            };
            document.addEventListener('click', handleDocumentClick);
        }
    }

    /*
    * 加载框的显示与关闭true: 开， false: 关
    * */
    function loading(sign){
        if(sign){
            removeLoading()
            createLoading();
        }else{
            removeLoading()
        }
    }
    /*创建loading*/
    function createLoading(){
        // 创建容器元素
        const container = document.createElement('div');
        container.id='g-loading-id';
        container.classList.add('g-load-container');
        // 创建加载动画元素
        const loader = document.createElement('div');
        loader.classList.add('g-load-loader');
        // 将 loader 添加到 container 中
        container.appendChild(loader);
        // 将 container 添加到 body 中
        document.body.appendChild(container);
    }
    /*删除loading*/
    function removeLoading() {
        // 获取 id 为 'g-loading-id' 的元素
        const loadDiv = document.getElementById('g-loading-id');
        // 检查元素是否存在
        if (loadDiv) {
            // 从父节点中移除该元素
            loadDiv.parentNode.removeChild(loadDiv);
        }
    }