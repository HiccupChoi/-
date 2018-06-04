
//画一个折线图
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

//画一个南丁格尔玫瑰图
function examScoreInit(examScoreECharts,StudentSubjectTitle,userName,StudentSubject,studentSubjectScore) {
    option = {
        title : {
            text: StudentSubjectTitle,
            subtext: userName
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:StudentSubject
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'成绩占比',
                type:'pie',
                radius : [30, 110],
                center : ['50%', '50%'],
                roseType : 'area',
                data:studentSubjectScore
            }
        ]
    };

    examScoreECharts.setOption(option)
}
