package cn.suxin.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;



/**
 * 对按指定格式对日期进行转换
 *
 */
public class DateUtil 

{
	/**
	 * 默认的日期格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
	
	public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String FMT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_YYYYMMDD_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String FMT_YYYYMMDD_HHMMSSMS = "yyyy-MM-dd HH:mm:ss.S";
	
	//   15/Jun/2016:16:42:17
	public static final String FMT_E_DDMYYHHMMSS = "dd/MMMM/yyyy:HH:mm:ss";//英文
	public static final String FMT_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
	public static final String FMT_YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String FMT_HHMM = "HH:mm";
	
	public static final int FMT_DATE_YYYY = 0;
	public static final int FMT_DATE_YYYYMMDD = 1;
	public static final int FMT_DATE_YYYYMMDD_HHMMSS = 2;
	public static final int FMT_DATE_HHMMSS = 3;
	public static final int FMT_DATE_HHMM = 4;
	public static final int FMT_DATE_SPECIAL = 5;
	/**
	 * 日期转换格式：MM-dd
	 */
	public static final int FMT_DATE_MMDD = 6;
	public static final int FMT_DATE_YYYYMMDDHHMM = 7;
	public static final int FMT_DATE_MMDD_HHMM = 8;
	public static final int FMT_DATE_MMMDDD = 9;
	public static final int FMT_DATE_YYYYMMDDHHMM_NEW = 10;
	public static final int FMT_DATE_YYYY年MM月DD日 = 11;
	public static final int FMT_DATE_YYYYMMDDHHMMSS = 12;
	public static final int FMT_DATE_YYMMDD = 13;
	public static final int FMT_DATE_YYMMDDHH = 14;
	public static final int FMT_DATE_MMDD_HHMM_CH = 15;
	/**
	 * @add by wangmeng 添加日期转换格式MMdd
	 */
	public static final int FMT_DATE_MMdd = 16;
	/**@add by dujp 添加YY/MM/DD*/
	public static final int FMT_DATE_YY_MM_DD = 17;
	/**@add by haoxq 添加YYYY/MM/DD*/
	public static final int FMT_DATE_YYYY_MM_DD = 18;
	public static final int FMT_DATE_EEEMMMddHHmmsszzzyyyy = 19;

	/** 静态常量值 用于获取 某一个日期的 年 月 日 时 分 秒 标识 **/
	public static final int GET_TIME_OF_YEAR = 100;// 获得 日期的年份
	public static final int GET_TIME_OF_MONTH = 200;// 获得 日期的月份
	public static final int GET_TIME_OF_DAY = 300;// 获取 日期的天
	public static final int GET_TIME_IF_HOUR = 400;// 获取日期的小时
	public static final int GET_TIME_OF_MINUTE = 500;
	public static final int GET_TIME_OF_SECOND = 600;
	
	public static final String defaultDate = "2000-01-01 00:00:00";
	public static final long defaultDateLong = 946656000000l;	//2000-01-01 00:00:00

	private DateUtil()
	{
	}

	public static Date parseUtilDate(String strDate, int nFmtDate)
	{
		if (strDate == null || strDate.trim().length() == 0)
			return null;
		SimpleDateFormat fmtDate = null;
		switch (nFmtDate)
		{
			default:
			case FMT_DATE_YYYYMMDD:
				fmtDate = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case FMT_DATE_YYYYMMDD_HHMMSS:
				fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case FMT_DATE_HHMM:
				fmtDate = new SimpleDateFormat("HH:mm");
				break;
			case FMT_DATE_HHMMSS:
				fmtDate = new SimpleDateFormat("HH:mm:ss");
				break;
			case FMT_DATE_YYYYMMDDHHMMSS:
				fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
				break;
			case FMT_DATE_EEEMMMddHHmmsszzzyyyy:
				fmtDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
				break;
		}
		try
		{
			return fmtDate.parse(strDate);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static int getPartDate(Date date, int flag)
	{
		if (date == null)
		{
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int result = 0;
		switch (flag)
		{
			case GET_TIME_OF_YEAR:
				result = cal.get(Calendar.YEAR);
				break;

			case GET_TIME_OF_MONTH:
				result = cal.get(Calendar.MONTH) + 1;
				break;

			case GET_TIME_OF_DAY:
				result = cal.get(Calendar.DAY_OF_MONTH);
				break;

			case GET_TIME_IF_HOUR:
				result = cal.get(Calendar.HOUR_OF_DAY);
				break;

			case GET_TIME_OF_MINUTE:
				result = cal.get(Calendar.MINUTE);
				break;

			case GET_TIME_OF_SECOND:
				result = cal.get(Calendar.SECOND);
				break;

			default:// 注意默认返回一个时间的年份
				result = cal.get(Calendar.YEAR);
				break;

		}
		return result;
	}

	/**
	 * 获得一个date 类型的 某个 特殊的 内容
	 *
	 * 比如 返回 时间的 年 、月、日、时、分、秒
	 *
	 * @param date
	 * @param flag
	 * @return
	 */
	public static String getPartTime(Date date, int flag)
	{
		int result = getPartDate(date, flag);
		return String.valueOf(result);
	}

	public static String formatNowTime(int nFmt)
	{
		Calendar cal = Calendar.getInstance();

		return formatDate(cal.getTime(), nFmt);
	}

	public static String formatDate(Date date, int nFmt)
	{
		SimpleDateFormat fmtDate = new SimpleDateFormat();
		switch (nFmt)
		{
			default:
			case FMT_DATE_YYYY:
				fmtDate.applyLocalizedPattern("yyyy");
				break;
			case FMT_DATE_YYYYMMDD:
				fmtDate.applyPattern("yyyy-MM-dd");
				break;
			case FMT_DATE_YYYYMMDD_HHMMSS:
				fmtDate.applyPattern("yyyy-MM-dd HH:mm:ss");
				break;
			case FMT_DATE_HHMM:
				fmtDate.applyPattern("HH:mm");
				break;
			case FMT_DATE_HHMMSS:
				fmtDate.applyPattern("HH:mm:ss");
				break;
			case FMT_DATE_SPECIAL:
				fmtDate.applyPattern("yyyyMMdd");
				break;
			case FMT_DATE_MMDD:
				fmtDate.applyPattern("MM-dd");
				break;
			case FMT_DATE_MMdd:
				fmtDate.applyPattern("MMdd");
				break;
			case FMT_DATE_YYYYMMDDHHMM:
				fmtDate.applyPattern("yyyy-MM-dd HH:mm");
				break;
			case FMT_DATE_MMDD_HHMM:
				fmtDate.applyPattern("MM-dd HH:mm");
				break;
			case FMT_DATE_MMMDDD:
				fmtDate.applyPattern("MM月dd日");
				break;
			case DateUtil.FMT_DATE_YYYYMMDDHHMM_NEW:
				fmtDate.applyPattern("yyyyMMddHHmm");
				break;
			case DateUtil.FMT_DATE_YYYY年MM月DD日:
				fmtDate.applyPattern("yyyy年MM月dd日");
				break;
			case DateUtil.FMT_DATE_YYYYMMDDHHMMSS:
				fmtDate.applyPattern("yyyyMMddHHmmss");
				break;
			case FMT_DATE_YYMMDD:
				fmtDate.applyPattern("yyMMdd");
				break;
			case FMT_DATE_YYMMDDHH:
				fmtDate.applyPattern("yyyyMMddHH");
				break;
			case FMT_DATE_MMDD_HHMM_CH:
				fmtDate.applyPattern("MM月dd日HH时mm分");
				break;
			case FMT_DATE_YY_MM_DD:
				fmtDate.applyPattern("yy/MM/dd");
				break;
			case FMT_DATE_YYYY_MM_DD:
				fmtDate.applyPattern("yyyy/MM/dd");
				break;
		}
		return fmtDate.format(date);
	}

	public static Timestamp getCurrentTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getToday(Timestamp ts)
	{
		try
		{
			String dateStr = formatDate(ts, FMT_DATE_YYYYMMDD);
			SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");
			return new Timestamp(fmtDate.parse(dateStr).getTime());
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 获取当前日期的后N天
	 * @param after 天数
	 * @return
	 */
	public static Date getDateAfter(int after)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + after);

		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(calendar.getTime());
		try
		{
			return form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 获取当前日期的前N天
	 * @param before 天数
	 * @return
	 */
	public static Date getDateBefore(int before)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - before);

		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(calendar.getTime());
		try
		{
			return form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static Timestamp getYesterdayTimestamp()
	{
		return new Timestamp(getDateBefore(-1).getTime());
	}

	public static Date getYesterdayDate()
	{
		return getDateBefore(-1);
	}

	/**
	 * 将日期按照指定的天数增加或者减少，并转换为需要的日期格式
	 *
	 * @param sourceFormat
	 *            原始日期格式
	 * @param date2Get
	 *            需要转换成的日期
	 * @return date2Get 成功：转换后的日期，失败：null
	 */

	public static Date getIntervalDateFormat(String date2Get, String sourceFormat, int days)
	{
		try
		{
			SimpleDateFormat sorceFmt = new SimpleDateFormat(sourceFormat);
			Date date = new Date(sorceFmt.parse(date2Get).getTime() + days * 86400000L); // 一天的时间24*3600*1000
			return date;
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 按默认日期格式 格式化日期
	 *
	 * @param target
	 * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
	 */
	public static String formatDate(Date target)
	{
		return formatDate(target, DEFAULT_DATE_FORMAT);
	}

	public static String formatTime(Date target)
	{
		return formatDate(target, DEFAULT_TIME_FORMAT);
	}

	/**
	 * 保留小时，去掉分，秒
	 * @param target
	 * @return
	 */
	public static String formatShortDate(Date target)
	{
		return formatDate(target, "yyyy-MM-dd HH");
	}

	/**
	 * 去掉秒
	 * @param target
	 * @return
	 */
	public static String formatLongDate(Date target)
	{
		return formatDate(target, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 获得当前时间的n天前或后
	 *
	 * @param origin
	 * @param intervals
	 * @return
	 */
	public static Date getIntervalDate(Date origin, long intervals)
	{
		return new Date(origin.getTime() + intervals * 86400000);
	}

	/**
	 * 获得指定时间间隔的timestamp
	 *
	 * @param ts
	 * @param minutes
	 * @return
	 */
	public static Timestamp getIntervalTimestamp(Timestamp ts, int minutes)
	{
		return new Timestamp(ts.getTime() + minutes * 60 * 1000L);
	}

	/**
	 * 获得指定时间间隔的timestamp
	 *
	 * @param ts
	 * @param seconds
	 * @return
	 */
	public static Timestamp getIntervalTimestampBySeconds(Timestamp ts, long seconds)
	{
		return new Timestamp(ts.getTime() + seconds * 1000L);
	}

	@SuppressWarnings("deprecation")
	public static Date transToQueryDate(Date date)
	{
		Calendar c = new GregorianCalendar();// 新建日期对象
		c.set(date.getYear() + 1900, date.getMonth(), date.getDate(), 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 返回两个时间间隔的分钟数
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public static long getDiffMinutes(Timestamp from, Timestamp to)
	{
		return (to.getTime() - from.getTime()) / (60 * 1000);
	}

	/**
	 * 获得两个时间之内的毫秒数
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public static long getDiffMsecs(Timestamp from, Timestamp to)
	{
		return to.getTime() - from.getTime();
	}

	/**
	 * 获得两个时间之间的天数
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getDiffDays(Timestamp from, Timestamp to)
	{
		return (int) (getDiffMinutes(from, to) / 1440);
	}

	public static boolean isInThisDay(Timestamp day, Timestamp time)
	{
		Timestamp beginTime = getBeginOfCurrentDate(day);
		Timestamp endTime = getBeginOfCurrentDate(getIntervalTimestamp(beginTime, 60 * 24));

		if (time.before(beginTime) || time.after(endTime))
			return false;
		return true;

	}

	

	public static boolean isBetween(Date date, Date start, Date end)
	{
		boolean isBetween = date.compareTo(start) >= 0 && date.getTime() <= (end.getTime() + 999);
		return isBetween;
	}

	public static boolean isToday(Date start)
	{
		long beginOfToday = getBeginOfToday().getTime();
		long endOfToday = getEndOfToday().getTime();
		boolean result = start.getTime() >= beginOfToday && start.getTime() <= endOfToday;
		return result;
	}

	public static Timestamp getBeginOfToday()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(cal.getTime()) + " 00:00:00";
		Date date = null;
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Timestamp getEndOfToday()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(cal.getTime()) + " 23:59:59";
		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Timestamp get6ClockOfToday()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(cal.getTime()) + " 06:00:00";
		Date date = null;
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}
	
	public static boolean isYesterday(Date start)
	{
		Date today = getBeginOfToday();
		Date yesterday = getIntervalDate(today, -1);
		return start.getTime() >= yesterday.getTime() && start.getTime() < today.getTime();
	}

	/**
	 * 按自定义日期格式格式化日期
	 *
	 * @param target
	 * @param format
	 * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
	 */
	public static String formatDate(Date target, String format)
	{
		if (target == null)
		{
			return "";
		}
		return new SimpleDateFormat(format).format(target);
	}

	public static String formatTime(Timestamp target)
	{
		if (target == null)
		{
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(target);
	}

	public static String formatTime(Timestamp target, String format)
	{
		if (target == null)
		{
			return "";
		}
		return new SimpleDateFormat(format).format(target);
	}

	public static Timestamp formatString(String target, String format)
	{

		if (StringUtils.isEmpty(target))
		{
			return null;
		}
		Date date;
		try
		{
			date = new SimpleDateFormat(format).parse(target);
			return new Timestamp(date.getTime());
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * @author wangmeng
	 * 比较两个时间，if: firstDate before nextDate return true;
	 *  else: return true;
	 * @param firstDate
	 * @param nextDate
	 * @return
	 */
	public static boolean compareDateBoolean(Date firstDate, Date nextDate)
	{
		if (firstDate.before(nextDate))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 判断是否是同一天
	 * @param firstDate
	 * @param nextDate
	 * @return
	 */
	public static boolean compareIsSameDay(Date firstDate, Date nextDate){
		Calendar first = Calendar.getInstance();
		first.setTime(firstDate);

		Calendar next = Calendar.getInstance();
		next.setTime(nextDate);

		return first.get(Calendar.YEAR) == next.get(Calendar.YEAR)
		            && first.get(Calendar.MONTH) == next.get(Calendar.MONTH)
		            &&  first.get(Calendar.DAY_OF_MONTH) == next.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 将字符串格式化为日期对象
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Timestamp formatToTimestamp(String dateStr, String format)
	{

		if (StringUtils.isEmpty(dateStr))
		{
			return null;
		}
		try
		{
			SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
			return new Timestamp(sorceFmt.parse(dateStr).getTime()); // 一天的时间24*3600*1000
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 将字符串格式化为日期对象
	 * @param date
	 * @param format
	 * @return 如果date为空或格式不标准，返回NULL，否则返回对应的日期对象
	 */
	public static Date formatToDate(String date, String format)
	{
		try
		{
			if (StringUtils.isEmpty(date))
			{
				return null;
			}

			SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
			return new Date(sorceFmt.parse(date).getTime());
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 获得几天前或几天后的，制定格式的日期
	 */
	public static Timestamp getDatePoint(String format, int day)
	{
		Date d = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return new Timestamp(now.getTimeInMillis());
	}

	//获得当前月份。
	public static String getCurrentMonth()
	{
		return new SimpleDateFormat("yyyyMM").format(new Date());
	}

	//获得当前月份。
	public static String getCurrentHour()
	{
		return new SimpleDateFormat("HH").format(new Date());
	}

	/**
	 * 获得N年前的1月1号
	 * @param date
	 * @param before
	 * @return
	 */
	public static Date getYearBefore(Date date, int before)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - before);
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(calendar.getTime());
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return date;

	}

	public static Date getMonthBefore(Date date, int before)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - before);
		calendar.set(Calendar.DATE, 1);

		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(calendar.getTime());
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return date;

	}

	public static Integer getAge(Timestamp birthday)
	{
		Date now = new Date();
		Long i = (now.getTime() - birthday.getTime()) / (1000 * 60 * 60 * 24);
		Double result = (double) (i / 365);
		return result.intValue() + 1;
	}

	//获取N天前的凌晨时间。
	public static Date getIntervalBeginOfDay(Date date, int i)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - i);

		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(calendar.getTime()) + " 00:00:00";
		try
		{
			date = form.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return date;

	}

	/**
	 * 取得月份 yyyyMM
	 * @param time
	 * @return
	 */
	public static String getMonth(Timestamp time)
	{
		return new SimpleDateFormat("yyyyMM").format(time);
	}

	public static Timestamp getTimestampFromStr(String dateStr, String dateFormat)
	{
		if (StringUtils.isEmpty(dateStr))
		{
			return null;
		}

		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static String getWeekDay(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int result = cal.get(Calendar.DAY_OF_WEEK);
		String weekDay = "";
		switch (result)
		{
			case 2:
				weekDay = "星期一";
				break;
			case 3:
				weekDay = "星期二";
				break;
			case 4:
				weekDay = "星期三";
				break;
			case 5:
				weekDay = "星期四";
				break;
			case 6:
				weekDay = "星期五";
				break;
			case 7:
				weekDay = "星期六";
				break;
			case 1:
				weekDay = "星期日";
				break;
		}
		return weekDay;
	}

	//获取年月日
	public static String[] getDateInfo(Timestamp time)
	{
		String[] dateInfo = new String[3];
		String formatDate = formatDate(time);
		dateInfo = formatDate.split("-");
		return dateInfo;
	}

	public static String getDateMonth(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int result = 0;
		result = cal.get(Calendar.MONTH) + 1;
		return String.valueOf(result);
	}

	/**
	 * 获得当前时间的n秒前或后
	 *
	 * @param origin
	 * @return
	 */
	public static Date getIntervalSeconds(Date origin, long seconds)
	{
		return new Date(origin.getTime() + seconds * 1000L);
	}

	/***
	 * 将秒数转化成 mm:ss格式例如37:02，当分钟和秒数小于10则补0
	 * @param seconds
	 * @return
	 */
	public static String getFormatedMinutesBySeconds(long seconds)
	{
		return (seconds / 60 > 9 ? "" : "0") + seconds / 60 + ":" + (seconds % 60 > 9 ? "" : "0") + seconds % 60;
	}

	/**
	 * Description:
	 * 获得给定时间戳的当天的开始时间戳(凌晨)
	 * 2013-07-17 00:00:00.0
	 * Date:2013-7-17
	 * @author zhangt@corp.netease.com
	 */
	public static Timestamp getBeginOfCurrentDate(Timestamp date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(cal.getTime()) + " 00:00:00";
		Date BeginOfCurrentDate = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			BeginOfCurrentDate = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(BeginOfCurrentDate.getTime());
	}

	/**
	 * Description:
	 * 获得给定时间戳的当天的结束时间戳
	 * 2013-07-17 23:59:59.0
	 * @param date
	 * Date:2013-7-17
	 * @author zhangt@corp.netease.com
	 */
	public static Timestamp getEndOfCurrentDate(Timestamp date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(cal.getTime()) + " 23:59:59";
		Date endOfCurrentDate = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endOfCurrentDate = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(endOfCurrentDate.getTime());

	}
	
	
	
	public static Date getLastDateOfMonth(int year, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getFirstDateOfMonth(Integer year, Integer month)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return cal.getTime();
	}
	
	/**
	 * ori:15/Jun/2016:16:42:17
	 * chg:2016-06-15 16:42
	 * @param dateStr
	 * @return
	 */
	public static String formatEng2Str(String dateStr){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(FMT_E_DDMYYHHMMSS, Locale.ENGLISH );
			return new SimpleDateFormat(FMT_YYYYMMDD_HHMM).format(sdf.parse(dateStr));
		}
		catch (ParseException e){
			return null;
		}
	}
	
	
	

	/**
	 * 得到区间内的工作日
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String[] queryWorkDayDuring(Timestamp startTime , Timestamp endTime){
		List<String> dayList = new ArrayList<String>();
		Timestamp cureentDay = startTime;
		
		while(true){
			if(cureentDay.compareTo(endTime) > 0){
				break;
			}
			dayList.add(DateUtil.formatTime(cureentDay, DateUtil.DEFAULT_DATE_FORMAT));
			
			cureentDay = DateUtil.getIntervalTimestamp(cureentDay, 60 * 24);
		}
		
		if(dayList.size() > 0){
			return dayList.toArray(new String[dayList.size()]);
		}
		return null;
	}
	
	
	/**
	 * 得到区间内的分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String[] queryMinuateDuring(Timestamp startTime , Timestamp endTime){
		List<String> minList = new ArrayList<String>();
		Timestamp cureentMin = startTime;
		
		while(true){
			if(cureentMin.compareTo(endTime) > 0){
				break;
			}
			minList.add(DateUtil.formatTime(cureentMin,DateUtil.DEFAULT_DATE_FORMAT+" "+DateUtil.DEFAULT_TIME_FORMAT));
			
			cureentMin = DateUtil.getIntervalTimestamp(cureentMin, 1);
		}
		
		if(minList.size() > 0){
			return minList.toArray(new String[minList.size()]);
		}
		return null;
	}
	
	public static boolean isTradeTime(Timestamp queryTime){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(queryTime.getTime());
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(dayOfWeek == 7){
			if(hourOfDay > 5){
				return false;
			}
		}else if(dayOfWeek == 1){
			return false;
		}else if(dayOfWeek == 2){
			if(hourOfDay < 5){
				return false;
			}
		}
		return true;
	}

	
	
	/**
	 * 得到某天6点
	 * @param queryTime
	 * @return
	 */
	public static Timestamp get5ClockOfDay(Timestamp queryTime){
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = form.format(queryTime.getTime()) + " 05:00:00";
		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 得到两个时间之间的分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static  String[] createMinTimeXZone(Timestamp startTime,Timestamp endTime){
		List<String> retList = new ArrayList<String>();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startTime);
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endTime);
		
		while(startCal.compareTo(endCal) < 0){
			retList.add(DateUtil.formatDate(startCal.getTime(), DateUtil.FMT_YYYYMMDD_HHMM));
			startCal.add(Calendar.MINUTE, 1);
		}
		return retList.toArray(new String[retList.size()]);
	}
	
    /**
     * 获得最近一个广贵交易日, 时分秒为0 
     * @param date
     * @return
     */
    public static Date getLastFdatePmec(Date date){
        if (date == null) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
   /*     // 如果是星期六, 0-4点，算星期四
        if(dayOfWeek == 7){
        	if(hour < 6){
        		calendar.add(Calendar.DAY_OF_MONTH, -2);
        	}else{
        		// 星期五
        		calendar.add(Calendar.DAY_OF_MONTH, -1);
        	}
        	*/
        // 如果星期六到星期二早点前，算星期5
        if(dayOfWeek == 1){
        	calendar.add(Calendar.DAY_OF_MONTH, -2);
        }else if(dayOfWeek == 2){
        	calendar.add(Calendar.DAY_OF_MONTH, -3);
        }else if(dayOfWeek == 3 && hour < 6){
        	calendar.add(Calendar.DAY_OF_MONTH, -4);
        }else{
        	if (hour >= 6) {
        		calendar.add(Calendar.DAY_OF_MONTH, -1);
            } else {
                calendar.add(Calendar.DAY_OF_MONTH, -2);
            }
        }
        return calendar.getTime();
    }
	
    /**
     * 此时把04:00 ~ 05:00之间的时间，计算为闭市时间
     * @param querTime
     * @return
     */
    public static boolean isPmecCloseTime(Timestamp querTime){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(querTime.getTime());
		
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		if(hourOfDay == 4){
			return true;
		}
		return false;
    }
    
	public static void main(String[] args)
	{
		// Date now = new Date();
		// Calendar c=new GregorianCalendar();//新建日期对象
		// int year=c.get(Calendar.YEAR);//获取年份
		// int month=c.get(Calendar.MONTH);//获取月份
		// int day=c.get(Calendar.DATE);//获取日期
		// int minute=c.get(Calendar.MINUTE);//分
		// int hour=c.get(Calendar.HOUR);//小时
		// int second=c.get(Calendar.SECOND);//秒
		// c.set(year, month, day, 0 ,0 ,0);
		// System.out.println(c.getTime());
		// c.set(1900+now.getYear(), now.getMonth(), now.getDate());
		// System.out.println(c.getTime());
		// System.out.println(getIntervalDate(new Date(), -30));
		//		SimpleDateFormat sorceFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
		// System.out.println(sorceFmt.parse("2010-08-23 14:23:08").getTime());
		//		System.out.println(sorceFmt.parse("2010-08-23 14:23:08:008").getTime());
		//System.out.println(DateUtil.getIntervalDateFormat(getBeginOfToday().get, "yyyy-MM-dd HH:mm:ss", 0));

		//		System.out.println(formatDate(getBeginOfToday(), "yyyyMMddHHmmss"));
		//		System.out.println(Timestamp.valueOf("2010-08-23 14:23:08.008").getTime());
		//		System.out.println(formatDate(new Date(), 13));
		//		System.out.println(getIntervalDateFormat("100827", "yyMMdd", 1));
		//		System.out.println(DateUtil.formatDate(DateUtil.getIntervalDateFormat("100827", "yyMMdd", 1), 13));
		//		System.out.println(getDateInfo(getCurrentTimestamp())[0]);
		//		System.out.println(getDateInfo(getCurrentTimestamp())[1]);
		//		System.out.println(getDateInfo(getCurrentTimestamp())[2]);
		
		
		
		
	}

	public static Timestamp format2Obj(Timestamp target, String format)
	{

		Calendar cal = Calendar.getInstance();
		cal.setTime(target);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTime().getTime());
	}
	
	public static int getYearweek(Timestamp date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	public static Date getBeginOfThisWeek(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		int dateOfweek = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, -dateOfweek + 1);
		return cal.getTime();
	}
	
	public static Date getEndOfThisWeek(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		int dateOfweek = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, 7-dateOfweek);
		return cal.getTime();
	}
	
	public static Timestamp getCurrentMin(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}
	

	
	public static int getHour(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
	    return	cal.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取某日期区间的所有日期  日期倒序
	 *
	 * @param startDate  开始日期
	 * @param endDate    结束日期
	 * @param dateFormat 日期格式
	 * @return 区间内所有日期
	 */
	public static List<String> getPerDaysByStartAndEndDate(String startDate, String endDate, String dateFormat) {
	    DateFormat format = new SimpleDateFormat(dateFormat);
	    try {
	        Date sDate = format.parse(startDate);
	        Date eDate = format.parse(endDate);
	        long start = sDate.getTime();
	        long end = eDate.getTime();
	        if (start > end) {
	            return null;
	        }
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(eDate);
	        List<String> res = new ArrayList<String>();
	        while (end >= start) {
	            res.add(format.format(calendar.getTime()));
	            calendar.add(Calendar.DAY_OF_MONTH, -1);
	            end = calendar.getTimeInMillis();
	        }
	        return res;
	    } catch (ParseException e) {
	    	
	    }

	    return null;
	}

}

