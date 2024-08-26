// 設置命名空間
var dietDiaryNamespace = dietDiaryNamespace || {};

let vue_dietDiary;
let vueApp_dietDiary;
let global_date;

dietDiaryNamespace.init = function(url_dietDiary, nowDate) {
    let url_dietDiary_date = `${url_dietDiary}/${nowDate}`;

    // 使用 AJAX 请求發送/獲取 JSON 数据
    fetch(url_dietDiary_date)
    .then(response => response.json())
    .then(data => {
        dietDiaryNamespace.createMyApp(data);
        vueApp_dietDiary = vue_dietDiary.mount('#vue_dietDiary');
    });

    init_datepicker();
}

function init_datepicker(){
    // 初始化時設置當天日期。
    global_date = new Date();

    // 初始化日期選擇器
    $('#datepicker').datepicker({
        language: 'zh-TW',  // 設置語言為繁體中文
        format: 'yyyy/mm/dd',  // 設置日期格式
        autoclose: true,  // 選擇日期後自動關閉日曆
        todayHighlight: true,  // 突出顯示今天的日期
        calendarWeeks: true, // 在周行左側顯示週數
        todayBtn: 'linked', // 切換到今天
    })
    // 設置顯示當天日期
    .datepicker('setDate', global_date)
    // 當日期更改時觸發
    .on('changeDate', function(e) {
        const year = e.date.getFullYear();
        const month = String(e.date.getMonth() + 1).padStart(2, '0');  // 月份從 0 開始，所以需要 +1
        const day = String(e.date.getDate()).padStart(2, '0');  // 確保日期為兩位數

        let formattedDate = `${year}-${month}-${day}`;
        global_date = formattedDate;

        vueApp_dietDiary.changeDate(url_dietDiary, formattedDate);
    });

    const arrow_yesterday_btn = document.querySelector('#arrow_yesterday_btn');
    const arrow_tomorrow_btn = document.querySelector('#arrow_tomorrow_btn');

    arrow_yesterday_btn.addEventListener('click', () => yesterdayOrTomorrow(-1));
    arrow_tomorrow_btn.addEventListener('click', () => yesterdayOrTomorrow(1));
}

dietDiaryNamespace.count = false;
dietDiaryNamespace.init_addDietDiaryForm = function(threeMeals){
    if(dietDiaryNamespace.count == 0){
        dietDiaryNamespace.init_datepicker_addDietDiary();
        dietDiaryNamespace.count = true;
    }else{
        $('#datepicker_addDietDiary').datepicker({
            language: 'zh-TW',  // 設置語言為繁體中文
            format: 'yyyy/mm/dd',  // 設置日期格式
            autoclose: true,  // 選擇日期後自動關閉日曆
            todayHighlight: true,  // 突出顯示今天的日期
            calendarWeeks: true, // 在周行左側顯示週數
            todayBtn: 'linked', // 切換到今天
        }).datepicker('setDate', global_date);
    }
    dietDiaryNamespace.init_dropdown(threeMeals);
}

dietDiaryNamespace.init_datepicker_addDietDiary = function(){
    // 初始化日期選擇器
    $('#datepicker_addDietDiary').datepicker({
        language: 'zh-TW',  // 設置語言為繁體中文
        format: 'yyyy/mm/dd',  // 設置日期格式
        autoclose: true,  // 選擇日期後自動關閉日曆
        todayHighlight: true,  // 突出顯示今天的日期
        calendarWeeks: true, // 在周行左側顯示週數
        todayBtn: 'linked', // 切換到今天
    })
    // 設置顯示當天日期
    .datepicker('setDate', global_date);
}

function yesterdayOrTomorrow(offset) {
    // 獲取當前日期選擇器的年月日部分
    const currentDate = $('#datepicker').datepicker('getDate').getDate();
    const currentMonth = $('#datepicker').datepicker('getDate').getMonth(); // ( 0-11，0 代表一月 )
    const currentYear = $('#datepicker').datepicker('getDate').getFullYear();

    // 更新日期
    $('#datepicker').datepicker('setDate', new Date(currentYear, currentMonth, currentDate + offset));
}

dietDiaryNamespace.getNowDate = function() {
    const date = new Date();

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');  // 月份從 0 開始，所以需要 +1
    const day = String(date.getDate()).padStart(2, '0');  // 確保日期為兩位數

    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate
}

dietDiaryNamespace.init_dropdown = function(threeMeals){
    /* 下拉式選單元件 ============================================= */
    const dropdownMenu = document.querySelector('#dropdown-menu');
    const selectedValue = document.querySelector('#dropdownMenuButton');
    selectedValue.textContent = threeMeals;

    // 在下拉式選單元素上監聽 click 事件。
    dropdownMenu.addEventListener('click', (event) => {
        // 取得被點選的選項元素和選項的文字內容。
        const selectedOption = event.target;
        const selectedText = selectedOption.textContent;

        // 將選項文字顯示在指定的元素上。
        selectedValue.textContent = selectedText;
    });
}

dietDiaryNamespace.addDietDiary = function(){
    // 獲取表單中的數據
    // 日期
    let day = $('#datepicker_addDietDiary').datepicker('getDate').getDate();
    let month = $('#datepicker_addDietDiary').datepicker('getDate').getMonth() + 1; // ( 0-11，0 代表一月 )
    let year = $('#datepicker_addDietDiary').datepicker('getDate').getFullYear();
    let formattedDate = `${year}-${month}-${day}`;
    // 食品名稱
    let foodName = document.getElementById("foodName").value;
    // 熱量
    let calories = parseFloat(document.getElementById("calories").value);
    // 碳水
    let carbohydrate = parseFloat(document.getElementById("carbohydrate").value);
    // 脂肪
    let fat = parseFloat(document.getElementById("fat").value);
    // 蛋白質
    let protein = parseFloat(document.getElementById("protein").value);
    // 每份量
    let portionSize_Food = document.getElementById("portionSize_Food").value;
    portionSize_Food = parseInt(portionSize_Food);
    if(portionSize_Food < 1){
        portionSize_Food = 1;
    }
    // 份數
    let portionSize_DietDiary = document.getElementById("portionSize_DietDiary").value;
    portionSize_DietDiary = parseInt(portionSize_DietDiary);
    if(portionSize_DietDiary < 1){
        portionSize_DietDiary = 1;
    }
    // 餐飲
    let threeMeals = document.getElementById("dropdownMenuButton").textContent;

    let addDietDiary_data = {
        "id": 0,
        "owner": 0,
        "date": formattedDate,
            "food": {
                "id": 0,
                "owner": 0,
                "foodName": foodName,
                "calories": calories,
                "carbohydrate": carbohydrate,
                "fat": fat,
                "protein": protein,
                "portionSize": portionSize_Food
            },
        "portionSize": portionSize_DietDiary,
        "threeMeals": threeMeals
    };

    fetch(url_myFood, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(addDietDiary_data)
    })
    .then(response => response.text())
    .then(html => {
        blockRelace_dietDiary();
    });
}

dietDiaryNamespace.createMyApp = function(data) {
    vue_dietDiary = createApp({
        setup() {
            let dietDiarys = ref(data);
            let breakfast = ref(calculateBreakfast(data));
            let lunch = ref(calculateLunch(data));
            let dinner = ref(calculateDinner(data));
            let total = ref(calculateTotal());
            let target = ref({
                targetCalories: 0,
                carbohydrateRatio: 0,
                fatRatio: 0,
                proteinRatio: 0,
            });

            function calculateBreakfast(data){
                let calories = 0;
                let carbohydrate = 0;
                let fat = 0;
                let protein = 0;

                for(let i = 0; i < data.length; ++i){
                    if(data[i].threemeals == "早餐"){
                        calories = calories + (data[i].myFood.calories * data[i].portionsize);
                        carbohydrate = carbohydrate + (data[i].myFood.carbohydrate * data[i].portionsize);
                        fat = fat + (data[i].myFood.fat * data[i].portionsize);
                        protein = protein + (data[i].myFood.protein * data[i].portionsize);
                    }
                }

                let calcuBreakfast = {
                    calories: calories,
                    carbohydrate: carbohydrate,
                    fat: fat,
                    protein: protein
                }

                return calcuBreakfast;
            }

            function calculateLunch(data){
                let calories = 0;
                let carbohydrate = 0;
                let fat = 0;
                let protein = 0;

                for(let i = 0; i < data.length; ++i){
                    if(data[i].threemeals == "午餐"){
                        calories = calories + (data[i].myFood.calories * data[i].portionsize);
                        carbohydrate = carbohydrate + (data[i].myFood.carbohydrate * data[i].portionsize );
                        fat = fat + (data[i].myFood.fat * data[i].portionsize);
                        protein = protein + (data[i].myFood.protein * data[i].portionsize);
                    }
                }

                let calcuLunch = {
                    calories: calories,
                    carbohydrate: carbohydrate,
                    fat: fat,
                    protein: protein
                }

                return calcuLunch;
            }

            function calculateDinner(data){
                let calories = 0;
                let carbohydrate = 0;
                let fat = 0;
                let protein = 0;

                for(let i = 0; i < data.length; ++i){
                    if(data[i].threemeals == "晚餐"){
                        calories = calories + (data[i].myFood.calories * data[i].portionsize);
                        carbohydrate = carbohydrate + (data[i].myFood.carbohydrate * data[i].portionsize);
                        fat = fat + (data[i].myFood.fat * data[i].portionsize);
                        protein = protein + (data[i].myFood.protein * data[i].portionsize);
                    }
                }

                let calcuDinner = {
                    calories: calories,
                    carbohydrate: carbohydrate,
                    fat: fat,
                    protein: protein
                }

                return calcuDinner;
            }

            function calculateTotal(){
                let calories = breakfast.value.calories + lunch.value.calories + dinner.value.calories;
                let carbohydrate = breakfast.value.carbohydrate + lunch.value.carbohydrate + dinner.value.carbohydrate;
                let fat = breakfast.value.fat + lunch.value.fat + dinner.value.fat;
                let protein = breakfast.value.protein + lunch.value.protein + dinner.value.protein;

                let calcuTotal = {
                    calories: calories,
                    carbohydrate: carbohydrate,
                    fat: fat,
                    protein: protein
                }

                return calcuTotal;
            }

            async function getUserInfoTarget(url_userInfo){
                let response = await fetch(url_userInfo);
                let data = await response.json();

                target.value = {
                    targetCalories: data.userInfo.targetcalories,
                    carbohydrateRatio: data.userInfo.carbohydrateratio,
                    fatRatio: data.userInfo.fatratio,
                    proteinRatio: data.userInfo.proteinratio,
                };
            }

            async function changeDate(url_dietDiary, formattedDate){
                let url_dietDiary_date = `${url_dietDiary}/${formattedDate}`;

                let response = await fetch(url_dietDiary_date);
                let data = await response.json();

                dietDiarys.value = data;
                breakfast.value = calculateBreakfast(dietDiarys.value);
                lunch.value = calculateLunch(dietDiarys.value);
                dinner.value = calculateDinner(dietDiarys.value);
                total.value = calculateTotal();
            }

            async function deleteDietDiary(dietDiaryId){
                let day = $('#datepicker').datepicker('getDate').getDate();
                let month = ($('#datepicker').datepicker('getDate').getMonth()) + 1; // ( 0-11，0 代表一月 )
                let year = $('#datepicker').datepicker('getDate').getFullYear();
                let formattedDate = `${year}-${month}-${day}`;

                let url_dietDiary_id_date = `${url_dietDiary}/${dietDiaryId}/${formattedDate}`;

                let formData = new URLSearchParams();
                formData.append("_method", "delete");

                let response = await fetch(url_dietDiary_id_date, {
                    method: 'POST', // 設置 HTTP 方法為 POST
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'  // 設置正確的 Content-Type
                    },
                    body: formData.toString()  // 將 URLSearchParams 轉換為字串
                });
                let data = await response.json();

                dietDiarys.value = data;
                breakfast.value = calculateBreakfast(dietDiarys.value);
                lunch.value = calculateLunch(dietDiarys.value);
                dinner.value = calculateDinner(dietDiarys.value);
                total.value = calculateTotal();
            }

            onMounted(() => {
                getUserInfoTarget(url_userInfo);
            });

            return {
                dietDiarys,
                breakfast,
                lunch,
                dinner,
                total,
                target,
                calculateBreakfast,
                calculateLunch,
                calculateDinner,
                calculateTotal,
                getUserInfoTarget,
                changeDate,
                deleteDietDiary
            }
        }
    });
};
