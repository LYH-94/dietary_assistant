const { createApp, ref, onMounted } = Vue;

const app = createApp({
    setup() {
		let labels = ref(["5/21", "5/22", "5/23", "5/24", "5/25", "5/26"]);
		let datas = ref([73, 72.5, 72, 72, 70.3, 69]);
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

		function changeData(label) {
			chart.data.datasets[0].label = label; // 新標籤
			chart.data.datasets[0].data = [19, 3, 5, 19, 3, 5, 19, 3, 5]; // 新數據
			chart.data.labels = [1,2,3,4,5,6,7,8,9] // 新數據的標籤
			chart.update();
		}

        onMounted(() => {
            const ctx = document.getElementById('myChart').getContext('2d');
			chart = new Chart(ctx, {
                type: 'line',
                data: chartData.value,
                options: chartOptions.value
            });
        });

        return {
			changeData,
            chartData,
            chartOptions
        };
    }
});

app.mount('#app');