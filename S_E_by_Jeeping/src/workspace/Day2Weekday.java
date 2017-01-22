package workspace;

public class Day2Weekday {
	public String trans(int day) {
		String weekday = null;
		switch(day){
			case 0:{
				weekday = "日";
				break;
			}
			case 1:{
				weekday = "一";
				break;
			}
			case 2:{
				weekday = "二";
				break;
			}
			case 3:{
				weekday = "三";
				break;
			}
			case 4:{
				weekday = "四";
				break;
			}
			case 5:{
				weekday = "五";
				break;
			}
			case 6:{
				weekday = "六";
				break;
			}
		}
		return weekday;
	}
}
