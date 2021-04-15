package com.yxz.base.common.utils.echarts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class EchartsUtils<T extends BaseEchartsStatisticsVo> {
	
	
	public EchartsOption setEcharts(String title,List<T> statisticsData) {
		// 3. create EchartsStatisticsVo
		EchartsOption echartsStatisticsVo = new EchartsOption();

		// 3.1 set title
		echartsStatisticsVo.setTitle(title);

		// 3.2-1 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		Set<String> legendSet = new HashSet<>();

		// 3.3-1 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		Set<String> xAxisSet = new HashSet<>();

		// set legend and xAxis
		for (T entity : statisticsData) {
			// 3.2-2. set legend data
			legendSet.add(entity.getLegend());
			// 3.3-2. set aAxis data
			xAxisSet.add(entity.getXAxis());
		}
		;

		// sort [ '2019', '2018', '2021', '2020', '2022' ] => [ '2018', '2019', '2020',
		// '2021', '2022' ]
		List<String> xAxisList = new ArrayList<>(xAxisSet);
		Collections.sort(xAxisList);
		System.out.println(xAxisList);

		// 3.2-3 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		echartsStatisticsVo.setLegend(new ArrayList<String>(legendSet));
		// 3.3-3 set xAxis [ '2019', '2018', '2021', '2020', '2022' ] => [ '2018',
		// '2019', '2020', '2021', '2022' ]
		List<String> xAxislist = new ArrayList<String>(xAxisSet);
		Collections.sort(xAxislist);
		echartsStatisticsVo.setXAxis(xAxislist);

		// 3.4 set series
		String tempLegend = null;
		Boolean newSeries = true;
		List<EchartsOptionSeries> echartsSeriesList = new ArrayList<>();
		for (T statisticsVo : statisticsData) {
			// the same class type
			if (tempLegend == null || tempLegend == statisticsVo.getLegend()) {
			}
			// not same class type
			else {
				newSeries = true;				
			}

			if (newSeries) {
				if (echartsSeriesList.size() > 0
						&& echartsSeriesList.get(echartsSeriesList.size() - 1).getData().size() > 0) {
					while (echartsSeriesList.get(echartsSeriesList.size() - 1).getData().size() < xAxisList.size()) {
						echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(0);
					}
				}

				EchartsOptionSeries echartsSeriesVo = new EchartsOptionSeries();
				List<Integer> dataList = new ArrayList<>();
				echartsSeriesVo.setData(dataList);
//						echartsSeriesVo.setType("line");
//						echartsSeriesVo.setStack("total");
				echartsSeriesVo.setName(statisticsVo.getLegend());
				echartsSeriesList.add(echartsSeriesVo);
				newSeries = false;
			}
			// if xAxisList=['第一季度','第三季度','第三季度','第四季度'] and statisticsVostatisticsVo
			// =[["第二季度", 11],["第三季度",22]] then series.data[0,11,22,0]
			int idx = xAxisList.indexOf(statisticsVo.getXAxis());
			while (echartsSeriesList.get(echartsSeriesList.size() - 1).getData().size() < idx) {
				echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(0);
			}
			echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(statisticsVo.getValue());

//			int indexOf = xAxisList.indexOf(statisticsVo.getQuarter());
//			echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(indexOf, statisticsVo.getTotalCount());
			tempLegend = statisticsVo.getLegend();
		}
		;

		echartsStatisticsVo.setSeries(echartsSeriesList);
		
		return echartsStatisticsVo;
	}
	

}
