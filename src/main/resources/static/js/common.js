/**
 * 自动初始化 CSS 样式
 */
(function injectStyles() {
    const css = `
        .custom-msg {
            position: fixed;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            background-color: rgba(0, 0, 0, 0.7);
            color: #fff;
            padding: 10px 20px;
            border-radius: 4px;
            z-index: 10000;
            font-size: 14px;
        }

        .g-load-container {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 9999;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 8px;
        }

        .g-load-loader {
            border: 4px solid rgba(0, 0, 0, 0.1);
            border-left-color: #59a782;
            border-radius: 50%;
            width: 40px;
            height: 40px;
            animation: spin 1s linear infinite;
        }

        .g-load-text {
            color: #59a782;
            font-size: 14px;
            font-family: sans-serif;
            font-weight: 500;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    `;
    const style = document.createElement('style');
    style.textContent = css;
    document.head.appendChild(style);
})();

/**
 * 显示消息弹窗。
 */
function msg(message, duration = 3000, callback = null) {
    const msgDiv = document.createElement('div');
    msgDiv.className = 'custom-msg';
    msgDiv.textContent = message;

    document.body.appendChild(msgDiv);

    const removeMsg = () => {
        if (msgDiv.parentNode) {
            msgDiv.remove();
        }
        if (callback && typeof callback === 'function') {
            callback();
        }
    };

    if (duration > 0) {
        setTimeout(removeMsg, duration);
    } else {
        const handleDocumentClick = function(event) {
            if (!msgDiv.contains(event.target)) {
                removeMsg();
                document.removeEventListener('click', handleDocumentClick);
            }
        };
        // 延迟添加监听，防止触发当前点击事件
        setTimeout(() => {
            document.addEventListener('click', handleDocumentClick);
        }, 0);
    }
}

/**
 * 控制加载动画的显示与隐藏。
 */
function loading(sign) {
    if (sign) {
        createLoading();
    } else {
        removeLoading();
    }
}

/**
 * 创建并显示加载动画。
 */
function createLoading() {
    removeLoading(); // 确保唯一性
    const container = document.createElement('div');
    container.id = 'g-loading-id';
    container.classList.add('g-load-container');

    const loader = document.createElement('div');
    loader.classList.add('g-load-loader');
    container.appendChild(loader);

    const text = document.createElement('div');
    text.classList.add('g-load-text');
    text.textContent = '加载中...';
    container.appendChild(text);

    document.body.appendChild(container);
}

/**
 * 移除加载动画。
 */
function removeLoading() {
    const loadDiv = document.getElementById('g-loading-id');
    if (loadDiv) {
        loadDiv.remove();
    }
}
/**
 * 创建并显示公告弹窗。
 * @param {string} [msg] 公告内容。如果未提供，将显示默认内容和当前时间。
 */
function createAnnouncementModal(msg) {
    // 创建模态弹窗的 HTML 结构
    const modal = document.createElement('div');
    modal.style.position = 'fixed';
    modal.style.top = '0';
    modal.style.left = '0';
    modal.style.width = '100%';
    modal.style.height = '100%';
    modal.style.backgroundColor = 'rgba(0, 0, 0, 0.4)';
    modal.style.display = 'flex';
    modal.style.justifyContent = 'center';
    modal.style.alignItems = 'center';
    modal.style.zIndex = '1000';

    // 创建弹窗内容框
    const modalContent = document.createElement('div');
    modalContent.style.backgroundColor = '#fff';
    modalContent.style.padding = '20px 30px';
    modalContent.style.borderRadius = '8px';
    modalContent.style.boxShadow = '0 5px 15px rgba(0, 0, 0, 0.1)';
    modalContent.style.maxWidth = '500px';
    modalContent.style.textAlign = 'center';
    modalContent.style.width = '80%';
    modalContent.style.position = 'relative';

    // 创建标题
    const modalTitle = document.createElement('h2');
    modalTitle.textContent = '公告';
    modalTitle.style.fontSize = '24px';
    modalTitle.style.color = '#333';
    modalTitle.style.marginBottom = '15px';
    modalTitle.style.fontWeight = '600';
    const hr = document.createElement('hr'); // 添加分隔线

    // 创建公告内容
    const modalMessage = document.createElement('p');
    modalMessage.textContent = msg ? msg : '公告'+ new Date().toLocaleString();
    modalMessage.style.fontSize = '16px';
    modalMessage.style.color = '#555';
    modalMessage.style.marginBottom = '20px';

    // 创建确认按钮
    const confirmBtn = document.createElement('button');
    confirmBtn.textContent = '我已知晓';
    confirmBtn.style.backgroundColor = '#007bff';
    confirmBtn.style.color = '#fff';
    confirmBtn.style.border = 'none';
    confirmBtn.style.padding = '10px 20px';
    confirmBtn.style.fontSize = '16px';
    confirmBtn.style.borderRadius = '5px';
    confirmBtn.style.cursor = 'pointer';
    confirmBtn.style.transition = 'background-color 0.3s ease';

    // 鼠标悬停效果
    confirmBtn.onmouseover = function() {
        confirmBtn.style.backgroundColor = '#0056b3';
    };
    confirmBtn.onmouseleave = function() {
        confirmBtn.style.backgroundColor = '#007bff';
    };

    // 关闭弹窗事件
    confirmBtn.onclick = function() {
        document.body.removeChild(modal);
    };

    // 创建关闭按钮 (右上角叉号)
    const closeBtn = document.createElement('span');
    closeBtn.textContent = '×';
    closeBtn.style.position = 'absolute';
    closeBtn.style.top = '10px';
    closeBtn.style.right = '10px';
    closeBtn.style.fontSize = '28px';
    closeBtn.style.color = '#aaa';
    closeBtn.style.cursor = 'pointer';

    // 关闭弹窗事件
    closeBtn.onclick = function() {
        document.body.removeChild(modal);
    };

    // 将所有元素添加到 modalContent 中
    modalContent.appendChild(closeBtn);
    modalContent.appendChild(modalTitle);
    modalContent.appendChild(hr); // 添加分隔线
    modalContent.appendChild(modalMessage);
    modalContent.appendChild(confirmBtn);

    // 将 modalContent 添加到 modal 中
    modal.appendChild(modalContent);

    // 将 modal 添加到页面的 body 中
    document.body.appendChild(modal);
}
// 展示base64图片模态框
function showImageModal(base64Image) {
    // 防重复创建
    if (document.getElementById('custom-modal')) return;

    // 创建遮罩
    const overlay = document.createElement('div');
    overlay.id = 'custom-overlay';
    Object.assign(overlay.style, {
        position: 'fixed',
        top: '0',
        left: '0',
        width: '100%',
        height: '100%',
        backgroundColor: 'rgba(0,0,0,0.5)',
        zIndex: '9998'
    });

    // 创建模态框
    const modal = document.createElement('div');
    modal.id = 'custom-modal';
    Object.assign(modal.style, {
        position: 'fixed',
        top: '50px',
        left: '50%',
        transform: 'translateX(-50%)',
        backgroundColor: '#fff',
        padding: '20px',
        borderRadius: '10px',
        zIndex: '9999',
        boxShadow: '0 2px 10px rgba(0,0,0,0.3)',
        textAlign: 'center'
    });

    // 创建图片并添加到 modal
    const img = document.createElement('img');
    img.src = base64Image;
    img.style.maxWidth = '300px';
    img.style.maxHeight = '300px';

    // 创建关闭按钮
    const closeBtn = document.createElement('button');
    closeBtn.innerText = '关闭';
    Object.assign(closeBtn.style, {
        marginTop: '10px',
        padding: '5px 10px',
        backgroundColor: '#333',
        color: '#fff',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer'
    });

    closeBtn.onclick = () => {
        document.body.removeChild(overlay);
        document.body.removeChild(modal);
    };

    // 装配结构
    modal.appendChild(img);
    modal.appendChild(document.createElement('br'));
    modal.appendChild(closeBtn);
    document.body.appendChild(overlay);
    document.body.appendChild(modal);
}
