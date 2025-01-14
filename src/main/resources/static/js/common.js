
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
    * loading bar open: true， close: false
    * */
    function loading(sign){
        if(sign){
            removeLoading()
            createLoading();
        }else{
            removeLoading()
        }
    }
    function createLoading(){
        const container = document.createElement('div');
        container.id='g-loading-id';
        container.classList.add('g-load-container');
        const loader = document.createElement('div');
        loader.classList.add('g-load-loader');
        container.appendChild(loader);
        document.body.appendChild(container);
    }
    function removeLoading() {
        const loadDiv = document.getElementById('g-loading-id');
        if (loadDiv) {
            loadDiv.parentNode.removeChild(loadDiv);
        }
    }