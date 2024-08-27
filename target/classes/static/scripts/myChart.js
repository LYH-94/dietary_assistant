// 設置命名空間
var myChartNamespace = myChartNamespace || {};

let vue_myChart;

myChartNamespace.init = function(url_myChart) {
    fetch(url_myChart)
    .then(response => response.json())
    .then(data => {
        myChartNamespace.createMyApp(data);
        vue_myChart.mount('#myChart_vue_myChart');

        // 偵測 Food 按鈕使否被點擊並改變背景顏色
        let elements_myChart = document.querySelectorAll('.clickToChangeColor_myChart');
        elements_myChart.forEach(element_myChart => {
            element_myChart.addEventListener('click', () => {
                // 先將所有元素的背景顏色設置為淺色
                elements_myChart.forEach(el_myChart => el_myChart.classList.remove('darkColor-background_myChart'));
                elements_myChart.forEach(el_myChart => el_myChart.classList.add('lightColor-background_myChart'));

                // 為當前被點擊的元素設置背景為藍色
                element_myChart.classList.remove('lightColor-background_myChart');
                element_myChart.classList.add('darkColor-background_myChart');
            });
        });

        myChartNamespace.init_dropdown_BodyData("體重");
    });
}

myChartNamespace.count = false;
myChartNamespace.init_addBodyData = function(){
    if(myChartNamespace.count == 0){
        myChartNamespace.init_datepicker_addBodyData();
        myChartNamespace.count = true;
    }else{
        $('#datepicker_BodyData').datepicker({
            language: 'zh-TW', // 設置語言為繁體中文
            format: 'yyyy/mm/dd', // 設置日期格式
            autoclose: true, // 選擇日期後自動關閉日曆
            todayHighlight: true, // 突出顯示今天的日期
            calendarWeeks: true, // 在周行左側顯示週數
            todayBtn: 'linked', // 切換到今天
        }).datepicker('setDate', new Date());
    }
}

myChartNamespace.init_datepicker_addBodyData = function(){
    // 初始化日期選擇器
    $('#datepicker_BodyData').datepicker({
        language: 'zh-TW', // 設置語言為繁體中文
        format: 'yyyy/mm/dd', // 設置日期格式
        autoclose: true, // 選擇日期後自動關閉日曆
        todayHighlight: true, // 突出顯示今天的日期
        calendarWeeks: true, // 在周行左側顯示週數
        todayBtn: 'linked', // 切換到今天
    })
    // 設置顯示當天日期
    .datepicker('setDate', new Date());
}

myChartNamespace.init_dropdown_BodyData = function(items){
    /* 下拉式選單元件 ============================================= */
    const dropdownMenu_BodyData = document.querySelector('#dropdown-menu_BodyData');
    const selectedValue_BodyData = document.querySelector('#dropdownMenuButton_BodyData');
    selectedValue_BodyData.textContent = items;

    // 在下拉式選單元素上監聽 click 事件。
    dropdownMenu_BodyData.addEventListener('click', (event) => {
        // 取得被點選的選項元素和選項的文字內容。
        const selectedOption = event.target;
        const selectedText = selectedOption.textContent;

        // 將選項文字顯示在指定的元素上。
        selectedValue_BodyData.textContent = selectedText;
    });
}

myChartNamespace.addBodyData = function() {
    // 獲取表單中的數據
    // 日期
    let day = $('#datepicker_BodyData').datepicker('getDate').getDate();
    let month = $('#datepicker_BodyData').datepicker('getDate').getMonth() + 1; // ( 0-11，0 代表一月 )
    let year = $('#datepicker_BodyData').datepicker('getDate').getFullYear();
    let formattedDate = `${year}-${month}-${day}`;
    // 食品名稱
    let weight = document.getElementById("weight").value;
    // 熱量
    let bodyfat = document.getElementById("bodyfat").value;
    // 碳水
    let skeletalmusclemass = document.getElementById("skeletalmusclemass").value;

    let formData = new URLSearchParams();
    formData.append('id', 0);
    formData.append('owner', 0);
    formData.append('date', formattedDate);
    formData.append('weight', weight);
    formData.append('bodyFat', bodyfat);
    formData.append('skeletalMuscleMass', skeletalmusclemass);

    fetch(url_myChart, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData.toString() // 將 URLSearchParams 轉換為字串
    })
    .then(response => response.json())
    .then(data => {
        blockRelace_myChart();
    });
}

// 用於生成新的應用實例。
myChartNamespace.createMyApp = function(data) {
    vue_myChart = createApp({
        setup() {
            let data_process = ref(data_process_function(data));
            let dateFiltered_data = ref(filterDate(0));

    		let labels = ref(labels_array(dateFiltered_data.value));
    		let datas = ref(datas_array_weight(dateFiltered_data.value));
    		let chart = ref(null);

            const chartData = ref({
    			labels: labels, // 每項數據的標籤。
                datasets: [{
                    label: '體重', // 線的標籤。
                    data: datas, // 數據。
                    borderColor: '#66CBE9', // 線的邊框顏色。
                    backgroundColor: '#66CBE9', // 線上的數據點的顏色。
                    borderWidth: 1, // 線的寬度。
    				tension: 0 // 線的曲度。
                }]
            });

            const chartOptions = ref({
                scales: {
                    y: {
                        //beginAtZero: true // 是否從零顯示數據。
                    }
                }
            });

            let BodyCompositionData_list = ref(data_process.value);

    		let temp_BodyDataList = [];
    		let data_item = "體重";

            let id_myChartList = ref(0);
            let date_myChartList = ref("");
            let weight_myChartList = ref(0);
            let bodyFat_myChartList = ref(0);
            let skeletalMuscleMass_myChartList = ref(0);

            function data_process_function(data){
                let object;
                let objectArray = [];

                for(let i = 0; i < data.length; ++i){
                    let date = new Date(data[i].date);

                    let year = date.getFullYear();
                    let month = String(date.getMonth() + 1).padStart(2, '0'); // 月份從 0 開始，所以需要 +1
                    let day = String(date.getDate()).padStart(2, '0'); // 確保日期為兩位數
                    let formattedDate = `${year}-${month}-${day}`;

                    object = {
            			id: data[i].id,
            			date: formattedDate,
            			weight: data[i].weight,
            			bodyfat: data[i].bodyfat,
            			skeletalmusclemass: data[i].skeletalmusclemass
                    }

                    objectArray.unshift(object);
                }
                return objectArray;
            }

            function filterDate(days){
                if(days > 0){
                    // 取得當前日期
                    let currentDate = new Date();

                    // 計算過去 days 天的日期
                    let pastDate = new Date();
                    pastDate.setDate(currentDate.getDate() - days);

                    temp_BodyDataList = [];
                    for(let i = 0; i < data_process.value.length; ++i){
                        let dp_temp = new Date(data_process.value[i].date)
                        if(dp_temp >= pastDate){
                            temp_BodyDataList.push(data_process.value[i]);
                        }
                    }
                    return temp_BodyDataList;
                }else{
                    return data_process.value;
                }
            }

            function labels_array(data){
                let array = [];
                for(let i = 0; i < data.length; ++i){
                    array.unshift(data[i].date.slice(5));
                }
                return array;
            }

            function datas_array_weight(data){
                let array = [];
                for(let i = 0; i < data.length; ++i){
                    array.unshift(data[i].weight);
                }

                return array;
            }

            function datas_array_bodyfat(data){
                let array = [];
                for(let i = 0; i < data.length; ++i){
                    array.unshift(data[i].bodyfat);
                }

                return array;
            }

            function datas_array_skeletalmusclemass(data){
                let array = [];
                for(let i = 0; i < data.length; ++i){
                    array.unshift(data[i].skeletalmusclemass);
                }

                return array;
            }

            function daysAgo(days){
                dateFiltered_data.value = filterDate(days);
                let f_data_DateArray = labels_array(dateFiltered_data.value);
                chart.data.labels = f_data_DateArray;

                changeDatas(data_item);
            }

            function changeDatas(items) {
    		    if(items === "體重"){
    		        data_item = items;

    		        chart.data.datasets[0].label = "體重"; // 新標籤
    		        chart.data.datasets[0].data = datas_array_weight(dateFiltered_data.value);
    		    }else if(items === "體脂肪率"){
    		        data_item = items;

    		        chart.data.datasets[0].label = "體脂肪率"; // 新標籤
    		        chart.data.datasets[0].data = datas_array_bodyfat(dateFiltered_data.value);
    		    }else if(items === "骨筋骼率"){
    		        data_item = items;

    		        chart.data.datasets[0].label = "骨筋骼率"; // 新標籤
    		        chart.data.datasets[0].data = datas_array_skeletalmusclemass(dateFiltered_data.value);
    		    }
    		    chart.update();
    		}

            function chooseBodyData(BodyCompositionData){
                id_myChartList.value = BodyCompositionData.id;
                date_myChartList.value = BodyCompositionData.date;
                weight_myChartList.value = BodyCompositionData.weight;
                bodyFat_myChartList.value = BodyCompositionData.bodyfat;
                skeletalMuscleMass_myChartList.value = BodyCompositionData.skeletalmusclemass;
            }

            function updateBodyData(){
                let formData = new URLSearchParams();
                formData.append('_method', "put");
                formData.append('id', id_myChartList.value);
                formData.append('owner', 0);
                formData.append('date', date_myChartList.value);
                formData.append('weight', weight_myChartList.value);
                formData.append('bodyFat', bodyFat_myChartList.value);
                formData.append('skeletalMuscleMass', skeletalMuscleMass_myChartList.value);

                fetch(url_myChart, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded' // 設置正確的 Content-Type
                    },
                    body: formData.toString() // 將 URLSearchParams 轉換為字串
                })
                .then(response => response.json())
                .then(data => {
                    blockRelace_myChart();
                });
            }

            function deleteBodyData(){
                let url_myChart_id = `${url_myChart}/${id_myChartList.value}`

                let formData = new URLSearchParams();
                formData.append('_method', "delete");

                fetch(url_myChart_id, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: formData.toString() // 將 URLSearchParams 轉換為字串
                })
                .then(response => response.json())
                .then(data => {
                    blockRelace_myChart();
                });
            }

            onMounted(() => {
                const ctx = document.getElementById('Chart').getContext('2d');
    			chart = new Chart(ctx, {
                    type: 'line',
                    data: chartData.value,
                    options: chartOptions.value
                });
            });

            return {
                chartData,
                chartOptions,
                data_process,
                BodyCompositionData_list,
                dateFiltered_data,

                id_myChartList,
                date_myChartList,
                weight_myChartList,
                bodyFat_myChartList,
                skeletalMuscleMass_myChartList,

                data_process_function,
                labels_array,
                datas_array_weight,
                datas_array_bodyfat,
                datas_array_skeletalmusclemass,
                filterDate,
                daysAgo,
                changeDatas,
                chooseBodyData,
                updateBodyData,
                deleteBodyData
            };
        }
    });
};
