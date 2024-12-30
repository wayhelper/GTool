
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

