const url_userInfoHTML = "/dietary_assistant/page/userInfo.html";
const url_dietDiaryHTML = "/dietary_assistant/page/dietDiary.html";
const url_myFoodHTML = "/dietary_assistant/page/myFood.html";
const url_myChartHTML = "/dietary_assistant/page/myChart.html";

const url_getUserInfoNickName = "/dietary_assistant/index";
const url_userInfo = "/dietary_assistant/userInfo";
const url_dietDiary = "/dietary_assistant/dietDiary";
const url_myFood = "/dietary_assistant/myFood";
const url_Edamam = "/dietary_assistant/Edamam";
const url_myChart = "/dietary_assistant/myChart";

let vue_nickName;

// 設置命名空間
var indexNamespace = indexNamespace || {};

indexNamespace.myFood_threemeals = '';

/*
    $(document).ready(function() { ... }); 是 jQuery 中的一個常見用法，
    用來確保在網頁的 DOM 結構完全加載和就緒之後再執行其中的 JavaScript 代碼。
*/
$(document).ready(function(){
    getUserInfoNickName(url_getUserInfoNickName);
    blockRelace_dietDiary();
});

// 創建一個 MutationObserver 物件
const observer = new MutationObserver((mutationsList, observer) => {
    mutationsList.forEach(mutation => {
        // 檢查是否有子節點被添加。
        if (mutation.type === 'childList') {
            mutation.addedNodes.forEach(node => {
                // 如果新添加的節點是目標元素。
                if (node.id === 'dietDiary') {
                    let nowDate = dietDiaryNamespace.getNowDate();
                    dietDiaryNamespace.init(url_dietDiary, nowDate);
                }else if(node.id === 'userInfo'){
                    userInfoNamespace.init(url_userInfo);
                }else if(node.id === 'myFood_vue_myFood'){
                    myFoodNamespace.init(url_myFood);
                }else if(node.id === 'myChart_vue_myChart'){
                    myChartNamespace.init(url_myChart);
                }

                // 可選：停止觀察
                //observer.disconnect();
            });
        }
    });
});

// 取得要觀察的目標節點 id。
const replace = document.getElementById('replace');

// 配置觀察選項。
const config = { childList: true, subtree: false };

// 開始觀察。
observer.observe(replace, config);

function blockRelace_userInfo(){
    // 使用 fetch API 获取 HTML 内容
    fetch(url_userInfoHTML)
    .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(html => {
        // 將獲取到的 HTML 插入到頁面的元素中
        document.getElementById('replace').innerHTML = html;

        // 改變 nav 按鈕的背景顏色
        navButton_changeBackgroundColor(0);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}

function blockRelace_dietDiary(){
    // 使用 fetch API 获取 HTML 内容
    fetch(url_dietDiaryHTML)
    .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(html => {
        // 將獲取到的 HTML 插入到頁面的元素中
        document.getElementById('replace').innerHTML = html;

        // 改變 nav 按鈕的背景顏色
        navButton_changeBackgroundColor(1);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}

function blockRelace_myFood(threemeals){
    // 使用 fetch API 获取 HTML 内容
    fetch(url_myFoodHTML)
    .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(html => {
        // 將獲取到的 HTML 插入到頁面的元素中
        document.getElementById('replace').innerHTML = html;

        // 設置點擊的三餐
        indexNamespace.myFood_threemeals = threemeals;

        // 改變 nav 按鈕的背景顏色
        navButton_changeBackgroundColor(2);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}

function blockRelace_myChart(){
    // 使用 fetch API 获取 HTML 内容
    fetch(url_myChartHTML)
    .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(html => {
        // 將獲取到的 HTML 插入到頁面的元素中
        document.getElementById('replace').innerHTML = html;

        // 改變 nav 按鈕的背景顏色
        navButton_changeBackgroundColor(3);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}

function navButton_changeBackgroundColor(option){
    let button_dietDiary = document.getElementById("button_dietDiary");
    let button_myFood = document.getElementById("button_myFood");
    let button_myChart = document.getElementById("button_myChart");

    if(option == 0){
        button_dietDiary.style.backgroundColor = "#247AA0";
        button_myFood.style.backgroundColor = "#247AA0";
        button_myChart.style.backgroundColor = "#247AA0";
    }else if(option == 1){
        button_dietDiary.style.backgroundColor = "#66CBE9";
        button_myFood.style.backgroundColor = "#247AA0";
        button_myChart.style.backgroundColor = "#247AA0";
    }else if(option == 2){
        button_dietDiary.style.backgroundColor = "#247AA0";
        button_myFood.style.backgroundColor = "#66CBE9";
        button_myChart.style.backgroundColor = "#247AA0";
    }else if(option == 3){
        button_dietDiary.style.backgroundColor = "#247AA0";
        button_myFood.style.backgroundColor = "#247AA0";
        button_myChart.style.backgroundColor = "#66CBE9";
    }
}

function getUserInfoNickName(url){
    // 使用 fetch API 请求發送/獲取 JSON 数据
    fetch(url)
    .then(response => response.json())
    .then(data => {
        indexNamespace.createMyApp(data.nickName);
        vue_nickName.mount('#vue_nickName');
    });
}

const { createApp, ref, onMounted, computed, expose, nextTick } = Vue;

indexNamespace.createMyApp = function(name) {
    vue_nickName = createApp({
        setup() {
    		let nickName = ref(name)
            return {
                nickName
            }
        }
    });
};
