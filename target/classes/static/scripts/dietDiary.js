/*
    $(document).ready(function() { ... }); 是 jQuery 中的一個常見用法，
    用來確保在網頁的 DOM 結構完全加載和就緒之後再執行其中的 JavaScript 代碼。
*/
$(document).ready(function(){
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
    .datepicker('setDate', new Date())
    // 當日期更改時觸發
    .on('changeDate', function(e) {
        alert(e.date);
    });
});

const arrow_yesterday_btn = document.querySelector('#arrow_yesterday_btn');
const arrow_tomorrow_btn = document.querySelector('#arrow_tomorrow_btn');

function yesterdayOrTomorrow(offset) {
    // 獲取當前日期選擇器的年月日部分
    const currentDate = $('#datepicker').datepicker('getDate').getDate();
    const currentMonth = $('#datepicker').datepicker('getDate').getMonth(); // ( 0-11，0 代表一月 )
    const currentYear = $('#datepicker').datepicker('getDate').getFullYear();

    // 更新日期
    $('#datepicker').datepicker('setDate', new Date(currentYear, currentMonth, currentDate + offset));
}

arrow_yesterday_btn.addEventListener('click', () => yesterdayOrTomorrow(-1));
arrow_tomorrow_btn.addEventListener('click', () => yesterdayOrTomorrow(1));
