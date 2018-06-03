
function studentInit(studentChart,title,subtitle,yAllDate,scoreDate,min) {

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: title,
            textStyle: {
                fontFamily: 'Raleway'
            },
            subtext:subtitle
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['最高分']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {
                    readOnly: false
                },
                magicType: {
                    type: ['line', 'bar']
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: yAllDate
        },
        yAxis: {
            type: 'value',
            min:min,
            boundaryGap: ['10%', '10%'],
            axisLabel: {
                formatter: '{value} 分'
            }
        },
        series: [{
            name: '总分',
            type: 'line',
            data: scoreDate,
            markPoint: {
                data: [{
                    type: 'max',
                    name: '最大值'
                },
                    {
                        type: 'min',
                        name: '最小值'
                    }
                ]
            },
            markLine: {
                data: [{
                    type: 'average',
                    name: '平均值'
                }]
            },
            itemStyle : {
                normal : {
                    color: '#002147',
                    lineStyle:{
                        color:'#002147'
                    }
                }
            }
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    studentChart.setOption(option);

}

