// 設置命名空間
var myFoodNamespace = myFoodNamespace || {};

let vue_myFood;

myFoodNamespace.init = function(url_myFood) {
    // 使用 AJAX 请求發送/獲取 JSON 数据
    fetch(url_myFood)
    .then(response => response.json())
    .then(data => {
        myFoodNamespace.createMyApp(data);
        vue_myFood.mount('#myFood_vue_myFood');

        // 偵測 Food 按鈕使否被點擊並改變背景顏色
        let elements = document.querySelectorAll('.clickToChangeColor');
        elements.forEach(element => {
            element.addEventListener('click', () => {
                // 先將所有元素的背景顏色設置為淺色
                elements.forEach(el => el.classList.remove('darkColor-background'));
                elements.forEach(el => el.classList.add('lightColor-background'));

                // 為當前被點擊的元素設置背景為藍色
                element.classList.remove('lightColor-background');
                element.classList.add('darkColor-background');
            });
        });
    });
}

function init_addDietDiaryFromMyFood(){
    indexNamespace.init_addDietDiaryForm(indexNamespace.myFood_threemeals);
}

indexNamespace.count = false;
indexNamespace.init_addDietDiaryForm = function(threeMeals){
    if(indexNamespace.count == 0){
        indexNamespace.init_datepicker_addDietDiary();
        indexNamespace.count = true;
    }else{
        $('#datepicker_addDietDiary_myFood').datepicker({
            language: 'zh-TW',  // 設置語言為繁體中文
            format: 'yyyy/mm/dd',  // 設置日期格式
            autoclose: true,  // 選擇日期後自動關閉日曆
            todayHighlight: true,  // 突出顯示今天的日期
            calendarWeeks: true, // 在周行左側顯示週數
            todayBtn: 'linked', // 切換到今天
        }).datepicker('setDate', global_date);
    }
    indexNamespace.init_dropdown(threeMeals);
}

indexNamespace.init_datepicker_addDietDiary = function(){
    // 初始化日期選擇器
    $('#datepicker_addDietDiary_myFood').datepicker({
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

indexNamespace.init_dropdown = function(threeMeals){
    /* 下拉式選單元件 ============================================= */
    const dropdownMenu_myFood = document.querySelector('#dropdown-menu_myFood');
    const selectedValue_myFood = document.querySelector('#dropdownMenuButton_myFood');
    selectedValue_myFood.textContent = threeMeals;

    // 在下拉式選單元素上監聽 click 事件。
    dropdownMenu_myFood.addEventListener('click', (event) => {
        // 取得被點選的選項元素和選項的文字內容。
        const selectedOption = event.target;
        const selectedText = selectedOption.textContent;

        // 將選項文字顯示在指定的元素上。
        selectedValue_myFood.textContent = selectedText;
    });
}

indexNamespace.addDietDiaryFromMyFood = function(){
    // 獲取表單中的數據
    // 日期
    let day = $('#datepicker_addDietDiary_myFood').datepicker('getDate').getDate();
    let month = $('#datepicker_addDietDiary_myFood').datepicker('getDate').getMonth() + 1; // ( 0-11，0 代表一月 )
    let year = $('#datepicker_addDietDiary_myFood').datepicker('getDate').getFullYear();
    let formattedDate = `${year}-${month}-${day}`;
    // 食品名稱
    let foodName = document.getElementById("foodName_myFood").value;
    // 熱量
    let calories = parseFloat(document.getElementById("calories_myFood").value);
    // 碳水
    let carbohydrate = parseFloat(document.getElementById("carbohydrate_myFood").value);
    // 脂肪
    let fat = parseFloat(document.getElementById("fat_myFood").value);
    // 蛋白質
    let protein = parseFloat(document.getElementById("protein_myFood").value);
    // 每份量
    let portionSize_Food = document.getElementById("portionSize_Food_myFood").value;
    portionSize_Food = parseInt(portionSize_Food);
    if(portionSize_Food < 1){
        portionSize_Food = 1;
    }
    // 份數
    let portionSize_DietDiary = document.getElementById("portionSize_DietDiary_myFood").value;
    portionSize_DietDiary = parseInt(portionSize_DietDiary);
    if(portionSize_DietDiary < 1){
        portionSize_DietDiary = 1;
    }
    // 餐飲
    let threeMeals = document.getElementById("dropdownMenuButton_myFood").textContent;

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

indexNamespace.update_MyFood = function(){
    // id
    let id = document.getElementById("update_id_myFood").value;
    // 食品名稱
    let foodName = document.getElementById("update_foodName_myFood").value;
    // 熱量
    let calories = parseFloat(document.getElementById("update_calories_myFood").value);
    // 碳水
    let carbohydrate = parseFloat(document.getElementById("update_carbohydrate_myFood").value);
    // 脂肪
    let fat = parseFloat(document.getElementById("update_fat_myFood").value);
    // 蛋白質
    let protein = parseFloat(document.getElementById("update_protein_myFood").value);
    // 每份量
    let portionSize_Food = document.getElementById("update_portionSize_Food_myFood").value;
    portionSize_Food = parseInt(portionSize_Food);
    if(portionSize_Food < 1){
        portionSize_Food = 1;
    }

    let formData = new URLSearchParams();
    formData.append('_method', "put");
    formData.append('id', id);
    formData.append('owner', 0);
    formData.append('foodName', foodName);
    formData.append('calories', calories);
    formData.append('carbohydrate', carbohydrate);
    formData.append('fat', fat);
    formData.append('protein', protein);
    formData.append('portionSize', portionSize_Food);

    fetch(url_myFood, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'  // 設置正確的 Content-Type
        },
        body: formData.toString()  // 將 URLSearchParams 轉換為字串
    })
    .then(response => response.json())
    .then(data => {
        blockRelace_myFood(indexNamespace.myFood_threemeals);
    });

}

function init_addDietDiaryFromMyFood_Edamam(){
    indexNamespace.init_addDietDiaryForm_Edamam(indexNamespace.myFood_threemeals);
}

indexNamespace.count2 = false;
indexNamespace.init_addDietDiaryForm_Edamam = function(threeMeals){
    if(indexNamespace.count2 == 0){
        indexNamespace.init_datepicker_addDietDiary_Edamam();
        indexNamespace.count2 = true;
    }else{
        $('#datepicker_addDietDiary_myFood_Edamam').datepicker({
            language: 'zh-TW',  // 設置語言為繁體中文
            format: 'yyyy/mm/dd',  // 設置日期格式
            autoclose: true,  // 選擇日期後自動關閉日曆
            todayHighlight: true,  // 突出顯示今天的日期
            calendarWeeks: true, // 在周行左側顯示週數
            todayBtn: 'linked', // 切換到今天
        }).datepicker('setDate', global_date);
    }
    indexNamespace.init_dropdown_Edamam(threeMeals);
}

indexNamespace.init_datepicker_addDietDiary_Edamam = function(){
    // 初始化日期選擇器
    $('#datepicker_addDietDiary_myFood_Edamam').datepicker({
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

indexNamespace.init_dropdown_Edamam = function(threeMeals){
    /* 下拉式選單元件 ============================================= */
    const dropdownMenu_myFood_Edamam = document.querySelector('#dropdown-menu_myFood_Edamam');
    const selectedValue_myFood_Edamam = document.querySelector('#dropdownMenuButton_myFood_Edamam');
    selectedValue_myFood_Edamam.textContent = threeMeals;

    // 在下拉式選單元素上監聽 click 事件。
    dropdownMenu_myFood_Edamam.addEventListener('click', (event) => {
        // 取得被點選的選項元素和選項的文字內容。
        const selectedOption = event.target;
        const selectedText = selectedOption.textContent;

        // 將選項文字顯示在指定的元素上。
        selectedValue_myFood_Edamam.textContent = selectedText;
    });
}

indexNamespace.addDietDiaryFromMyFood_Edamam = function(){
    // 獲取表單中的數據
    // 日期
    let day = $('#datepicker_addDietDiary_myFood_Edamam').datepicker('getDate').getDate();
    let month = $('#datepicker_addDietDiary_myFood_Edamam').datepicker('getDate').getMonth() + 1; // ( 0-11，0 代表一月 )
    let year = $('#datepicker_addDietDiary_myFood_Edamam').datepicker('getDate').getFullYear();
    let formattedDate = `${year}-${month}-${day}`;
    // 食品名稱
    let foodName = document.getElementById("foodName_myFood_Edamam").value;
    // 熱量
    let calories = parseFloat(document.getElementById("calories_myFood_Edamam").value);
    // 碳水
    let carbohydrate = parseFloat(document.getElementById("carbohydrate_myFood_Edamam").value);
    // 脂肪
    let fat = parseFloat(document.getElementById("fat_myFood_Edamam").value);
    // 蛋白質
    let protein = parseFloat(document.getElementById("protein_myFood_Edamam").value);
    // 每份量
    let portionSize_Food = document.getElementById("portionSize_Food_myFood_Edamam").value;
    portionSize_Food = parseInt(portionSize_Food);
    if(portionSize_Food < 1){
        portionSize_Food = 1;
    }
    // 份數
    let portionSize_DietDiary = document.getElementById("portionSize_DietDiary_myFood_Edamam").value;
    portionSize_DietDiary = parseInt(portionSize_DietDiary);
    if(portionSize_DietDiary < 1){
        portionSize_DietDiary = 1;
    }
    // 餐飲
    let threeMeals = document.getElementById("dropdownMenuButton_myFood_Edamam").textContent;

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

myFoodNamespace.createMyApp = function(data) {
    vue_myFood = createApp({
        setup() {
    		let myFoods = ref(data);
    		let myFoodList = ref(data);
    		let myFoods_Edamam = ref(null);

            let id_myFoodList = ref(0);
    		let foodName_myFoodList = ref("");
    		let calories_myFoodList = ref(0);
    		let carbohydrate_myFoodList = ref(0);
    		let fat_myFoodList = ref(0);
    		let protein_myFoodList = ref(0);
    		let portionsize_myFoodList = ref(0);
    		let portionSize_DietDiary_myFoodList = ref(1);

            let id_myFoodList_Edamam = ref(0);
    		let foodName_myFoodList_Edamam = ref("");
    		let calories_myFoodList_Edamam = ref(0);
    		let carbohydrate_myFoodList_Edamam = ref(0);
    		let fat_myFoodList_Edamam = ref(0);
    		let protein_myFoodList_Edamam = ref(0);
    		let portionsize_myFoodList_Edamam = ref(0);
            let portionSize_DietDiary_myFoodList_Edamam = ref(1);

    		let search_myFood = ref("");
    		let search_Edamam = ref("");

    		let temp_myFoodList = [];

    		function myFoodList_search(){
    		    // 當點擊搜尋時，將以下重置。
    		    foodName_myFoodList.value = "";
                calories_myFoodList.value = 0;
                carbohydrate_myFoodList.value = 0;
                fat_myFoodList.value = 0;
                protein_myFoodList.value = 0;
                portionsize_myFoodList.value = 0;
                portionSize_DietDiary_myFoodList.value = 1;

                const elements = document.querySelectorAll('.clickToChangeColor');
                elements.forEach(element => {
                    // 將所有元素的背景顏色設置為淺色
                    element.classList.remove('darkColor-background');
                    element.classList.add('lightColor-background');
                });

                // 判斷搜尋字串。
    		    if(search_myFood.value != ""){
    		        temp_myFoodList = [];
    		        for(let i = 0; i < myFoods.value.length; ++i){
    		            if(myFoods.value[i].foodname.includes(search_myFood.value)){
    		                temp_myFoodList.push(myFoods.value[i]);
    		            }
    		        }
    		        myFoodList.value = temp_myFoodList;
    		    }else{
    		        myFoodList.value = myFoods.value;
    		    }
    		}

            async function myFoodList_search_Edamam(){
                // 當點擊搜尋時，將以下重置。
    		    foodName_myFoodList_Edamam.value = "";
                calories_myFoodList_Edamam.value = 0;
                carbohydrate_myFoodList_Edamam.value = 0;
                fat_myFoodList_Edamam.value = 0;
                protein_myFoodList_Edamam.value = 0;
                portionsize_myFoodList_Edamam.value = 0;
                portionSize_DietDiary_myFoodList_Edamam.value = 1;

                if(search_Edamam.value != ""){
                    let url_Edamam_foodName = `${url_Edamam}/${search_Edamam.value}`;

                    let response = await fetch(url_Edamam_foodName);
                    let data = await response.json();
                    myFoods_Edamam.value = data;

                    // 使用 nextTick() 等待 DOM 更新完成後才執行後續的程式碼。
                    await nextTick();

                    let elements_Edamam = document.querySelectorAll('.clickToChangeColor_Edamam');
                    elements_Edamam.forEach(element_Edamam => {
                        element_Edamam.addEventListener('click', () => {
                            // 先將所有元素的背景顏色設置為淺色
                            elements_Edamam.forEach(el_Edamam => el_Edamam.classList.remove('darkColor-background_Edamam'));
                            elements_Edamam.forEach(el_Edamam => el_Edamam.classList.add('lightColor-background_Edamam'));

                            // 為當前被點擊的元素設置背景為藍色
                            element_Edamam.classList.remove('lightColor-background_Edamam');
                            element_Edamam.classList.add('darkColor-background_Edamam');
                        });
                    });

                    // 先將所有元素的背景顏色設置為淺色
                    elements_Edamam.forEach(el_Edamam => el_Edamam.classList.remove('darkColor-background_Edamam'));
                    elements_Edamam.forEach(el_Edamam => el_Edamam.classList.add('lightColor-background_Edamam'));
    		    }else{
    		        myFoods_Edamam.value = null;
    		    }
    		}

            function chooseFood(myFood){
                id_myFoodList.value = myFood.id;
                foodName_myFoodList.value = myFood.foodname;
                calories_myFoodList.value = myFood.calories;
                carbohydrate_myFoodList.value = myFood.carbohydrate;
                fat_myFoodList.value = myFood.fat;
                protein_myFoodList.value = myFood.protein;
                portionsize_myFoodList.value = myFood.portionsize;
            }

            function chooseFood_Edamam(myFood_Edamam){
                id_myFoodList_Edamam.value = myFood_Edamam.id;
                foodName_myFoodList_Edamam.value = myFood_Edamam.foodname;
                calories_myFoodList_Edamam.value = myFood_Edamam.calories;
                carbohydrate_myFoodList_Edamam.value = myFood_Edamam.carbohydrate;
                fat_myFoodList_Edamam.value = myFood_Edamam.fat;
                protein_myFoodList_Edamam.value = myFood_Edamam.protein;
                portionsize_myFoodList_Edamam.value = myFood_Edamam.portionsize;
            }

            function deleteFood(foodId){
                const userConfirmed = confirm("刪除食品會連同曾經新增過該食品的飲食日記也一併刪除，您確定要刪除此食品嗎？");

                if (userConfirmed) {
                    let url_myFood_foodId = `${url_myFood}/${foodId}`;

                    let formData = new URLSearchParams();
                    formData.append("_method", "delete");

                    fetch(url_myFood_foodId, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'  // 設置正確的 Content-Type
                        },
                        body: formData.toString()  // 將 URLSearchParams 轉換為字串
                    })
                    .then(response => response.json())
                    .then(data => {
                        blockRelace_myFood(indexNamespace.myFood_threemeals);
                    });
                } else {
                    // 無操作。
                }
            }

            return {
                myFoods,
                myFoodList,
                myFoods_Edamam,

                id_myFoodList,
                foodName_myFoodList,
                calories_myFoodList,
                carbohydrate_myFoodList,
                fat_myFoodList,
                protein_myFoodList,
                portionsize_myFoodList,
                portionSize_DietDiary_myFoodList,

                id_myFoodList_Edamam,
                foodName_myFoodList_Edamam,
                calories_myFoodList_Edamam,
                carbohydrate_myFoodList_Edamam,
                fat_myFoodList_Edamam,
                protein_myFoodList_Edamam,
                portionsize_myFoodList_Edamam,
                portionSize_DietDiary_myFoodList_Edamam,

                search_myFood,
                search_Edamam,
                myFoodList_search,
                myFoodList_search_Edamam,
                chooseFood,
                chooseFood_Edamam,
                deleteFood
            }
        }
    });
};