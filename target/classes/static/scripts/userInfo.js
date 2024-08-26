// 設置命名空間
var userInfoNamespace = userInfoNamespace || {};

let vue_userInfo;

userInfoNamespace.init = function(url_userInfo) {
    // 使用 AJAX 请求發送/獲取 JSON 数据
    fetch(url_userInfo)
    .then(response => response.json())
    .then(data => {
        userInfoNamespace.createMyApp(data);
        vue_userInfo.mount('#vue_userInfo');

        // 在外部 javascript 調用 Vue3 組件內部函式的方法。關鍵在於使用掛載後的物件實例來調用即可，其他一樣。
        //let vm = vue_userInfo.mount('#vue_userInfo');
        //vm.test();
    });
}

userInfoNamespace.createMyApp = function(user) {
    vue_userInfo = createApp({
        setup() {
    		let account = ref(user.account);
    		let password = ref(user.password);
    		let nickName = ref(user.userInfo.nickname);
    		let email = ref(user.userInfo.email);
    		let targetCalories = ref(user.userInfo.targetcalories);
    		let carbohydrateRatio = ref(user.userInfo.carbohydrateratio);
    		let fatRatio = ref(user.userInfo.fatratio);
    		let proteinRatio = ref(user.userInfo.proteinratio);

            function updateUserInfo() {
                let formData = new URLSearchParams();
                formData.append('_method', "put");
                formData.append('account', account.value);
                formData.append('password', password.value);
                formData.append('nickName', nickName.value);
                formData.append('email', email.value);
                formData.append('targetCalories', targetCalories.value);
                formData.append('carbohydrateRatio', carbohydrateRatio.value);
                formData.append('fatRatio', fatRatio.value);
                formData.append('proteinRatio', proteinRatio.value);

                if((carbohydrateRatio.value + fatRatio.value + proteinRatio.value) == 100){
                    fetch(url_userInfo, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'  // 設置正確的 Content-Type
                        },
                        body: formData.toString()  // 將 URLSearchParams 轉換為字串
                    })
                    .then(response => response.json())
                    .then(data => {
                        changeAvailability_userInfo();
                    });
                }else{
                    alert("三種比例相加必須為 100%");
                }
    		}

            return {
                account,
                password,
                nickName,
                email,
                targetCalories,
                carbohydrateRatio,
                fatRatio,
                proteinRatio,
                updateUserInfo
            }
        }
    });
};

function changeAvailability_userInfo(){

    let inputElements = document.querySelectorAll('.js_inputElement');
    let btnElements = document.querySelectorAll('.js_btnElement');

    // 設置from表單中所有<input>元素為可用。預設皆為不可用。
    inputElements.forEach(inputElement => {
        inputElement.disabled = !inputElement.disabled;
    });

    // 設置from表單中button元素為隱藏or不隱藏。預設修改為不隱藏、取消與確定為隱藏。
    btnElements.forEach(btnElement => {
        btnElement.hidden = !btnElement.hidden;
    });
}
