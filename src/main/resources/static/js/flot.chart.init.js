var data7_1 = [];
var data7_2 = [];

$(function () {
        $.ajax({
            async: false,
            url: "/statisticsCarMessage",
            success: function (data) {
                var parse = JSON.parse(data);
                for (var i = 0; i <= 12; i++) {
                    data7_1.push([i, Object.values(parse)[i]]);
                }
                console.log(data7_1);
            }
        })
        $.ajax({
            async: false,
            url: "/statisticsEmployeeMessage",
            success: function (data) {
                var parse = JSON.parse(data);
                for (var i = 0; i <= 12; i++) {
                    data7_2.push([i, Object.values(parse)[12-i]]);
                }
                console.log(data7_2);
            }
        })
    }
);

$(function () {
    $.plot($("#visitors-chart #visitors-container"), [{
            data: data7_1,
            label: "车辆售出总额",
            lines: {
                fill: true
            }
        }
        ],
        {
            series: {
                lines: {
                    show: true,
                    fill: false
                },
                points: {
                    show: true,
                    lineWidth: 2,
                    fill: true,
                    fillColor: "#ffffff",
                    symbol: "circle",
                    radius: 5
                },
                shadowSize: 0
            },
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#f9f9f9",
                borderWidth: 1,
                borderColor: "#eeeeee"
            },
            colors: ["#65CEA7", "#424F63"],
            tooltip: true,
            tooltipOpts: {
                defaultTheme: false
            },

            xaxis: {
                mode: "text"


            },
            yaxes: [{}, {
                position: "right"
            }]
        }
    );
});
$(function () {
    $.plot($("#visitors-chart1 #visitors-container1"), [{
            data: data7_2,
            label: "员工工资支出总额",
            lines: {
                fill: true
            }
        }
        ],
        {
            series: {
                lines: {
                    show: true,
                    fill: false
                },
                points: {
                    show: true,
                    lineWidth: 2,
                    fill: true,
                    fillColor: "#ffffff",
                    symbol: "circle",
                    radius: 5
                },
                shadowSize: 0
            },
            grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#f9f9f9",
                borderWidth: 1,
                borderColor: "#eeeeee"
            },
            colors: ["#65CEA7", "#424F63"],
            tooltip: true,
            tooltipOpts: {
                defaultTheme: false
            },
            xaxis: {
                mode: "text"


            },
            yaxes: [{}, {
                position: "right"
            }]
        }
    );
});