package com.dvdrental.batch.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * date : 2020/11/26
 * file_name : DateUtil
 * package_name : com.dvdrental.batch.framework.common.util
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */
@Slf4j
public class DateUtil {
    public static final String DEFAULT_DATETIME_FORMAT = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_EFCT_ST_DT_FORMAT = "19700101000000";
    public static final String DEFAULT_EFCT_FNS_DT_FORMAT = "99991231000000";
    public static final String DEFAULT_SEPARATOR = "";
    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int DATE = 3;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    public static final long MILLIS_PER_FIVE_MINUTE = 5 * 60 * MILLIS_PER_SECOND;
    public static final long MILLIS_PER_TEN_MINUTE = 10 * MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    public static final int SECONDS_PER_DAY = (int) (MILLIS_PER_DAY / 1000);

    /**
     * 현재 시간(날짜 + 시간)을 "yyyy-MM-dd HH:mm:ss" 포맷으로 반환한다.
     *
     * @return String 현재 시간(날짜 + 시간)
     */
    public static String getTime() {
        return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT, Locale.getDefault()).format(new Date());
    }

    /**
     * 현재 시간을 주어진 포맷으로 반환한다.
     *
     * @param  formatter SimpleDateFormat Class에 맞는 포맷
     * @return String 현재 시간
     */
    public static String getTime(String formatter) {
        if ("".equals(formatter)) {
            return getTime();
        } else {
            return new SimpleDateFormat(formatter, Locale.getDefault()).format(new Date());
        }
    }

    /**
     * 현재 시스템 날짜를 가져와 기본 구분자로 반환한다.<br><br>
     *
     * ex) String nowDate = DateUtil.getDate();
     *
     * @return String 시스템 날짜
     */
    public static String getDate() {
        return getDate(DEFAULT_SEPARATOR);
    }

    /**
     * 현재 시스템 날짜를 가져와 구분자를 붙여 반환한다.<br><br>
     *
     * ex) String nowDate = DateUtil.getDate("/");
     *
     * @param gbn 년, 월, 일을 구분할 구분자
     * @return String 주어진 구분자로 년, 월, 일을 구분한 시스템 날짜
     */
    public static String getDate(String gbn) {
        String formatter = "yyyy" + gbn + "MM" + gbn + "dd";
        SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.getDefault());

        return sdf.format(new Date());
    }

    /**
     * 현재의 시스템 날짜 중 년도를 반환한다<br><br>
     *
     * ex) String nowYear = DateUtil.getYear();
     *
     * @return String 현재의 시스템 날짜 중 년도
     */
    public static String getYear() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.YEAR) + "";
    }

    /**
     * 현재의 시스템 날짜 중 월을 반환한다. 반환할때 2자리 수로 맞춘다.<br><br>
     *
     * ex) String nowMonth = DateUtil.getMonth();
     *
     * @return String 현재의 시스템 날짜 중 월
     */
    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        String month = (cal.get(Calendar.MONTH) + 1) >= 10 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);

        return month;
    }

    /**
     * 현재 시스템 날짜 중 주를 반환한다.<br><br>
     *
     * ex) String nowWeek = DateUtil.getWeek();
     *
     * @return String 현재 시스템 날짜 중 주
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.WEEK_OF_MONTH) + "";
    }

    /**
     * 현재의 시스템 날짜 중 일을 반환한다. 반환할때 2자리 수로 맞춘다.<br><br>
     *
     * ex) String nowDay = DateUtil.getDay();
     *
     * @return String 현재의 시스템 날짜 중 일
     */
    public static String getDay() {
        Calendar cal = Calendar.getInstance();
        String day = (cal.get(Calendar.DATE)) >= 10 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));

        return day;
    }

    /**
     * 현재의 시스템 날짜 중 시간을 반환한다. 반환할때 2자리 수로 맞춘다.<br><br>
     *
     * ex) String now_hour = DateUtil.getHour();
     *
     * @return String 현재의 시스템 날짜 중 시간
     */
    public static String getHour() {
        Calendar cal = Calendar.getInstance();
        String hour = (cal.get(Calendar.HOUR_OF_DAY)) >= 10 ? (cal.get(Calendar.HOUR_OF_DAY)) + "" : "0" + (cal.get(Calendar.HOUR_OF_DAY));

        return hour;
    }

    /**
     * 현재의 시스템 날짜 중 분을 반환한다. 반환할때 2자리 수로 맞춘다.<br><br>
     *
     * ex) String now_minute = DateUtil.getMinute();
     *
     * @return String 현재의 시스템 날짜 중 분
     */
    public static String getMinute() {
        Calendar cal = Calendar.getInstance();
        String minute = (cal.get(Calendar.MINUTE)) >= 10 ? (cal.get(Calendar.MINUTE)) + "" : "0" + (cal.get(Calendar.MINUTE));

        return minute;
    }

    /**
     * 현재의 시스템 날짜 중 초를 반환한다. 반환할때 2자리 수로 맞춘다.<br><br>
     *
     * ex) String now_second = DateUtil.getSecond();
     *
     * @return String 현재의 시스템 날짜 중 초
     */
    public static String getSecond() {
        Calendar cal = Calendar.getInstance();
        String second = (cal.get(Calendar.SECOND)) >= 10 ? (cal.get(Calendar.SECOND)) + "" : "0" + (cal.get(Calendar.SECOND));

        return second;
    }

    /**
     * 년도를 받아 윤년 여부를 판단한다.<br><br>
     *
     * ex) boolean leap_year = DateUtil.isLeapYear(2003);
     *
     * @param  year 판단 대상이 되는 년도
     * @return boolean true : 윤년, false : 평년
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 해당 월의 마지막 일자를 구한다.<br><br>
     *
     * ex) String last_day = DateUtil.getLastDay("2003", "03");
     *
     * @param year  해당 년도
     * @param month 해당 월
     * @return String 해당 년도, 해당 월의 마지막 일자를 반환한다.
     */
    public static String getLastDay(String year, String month) {
        int rtnValue = 0;

        Calendar cal = Calendar.getInstance();

        cal.set(Integer.parseInt(year),(Integer.parseInt(month) - 1),1);

        rtnValue = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return rtnValue + "";
    }

    public static Date getEndOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.add(Calendar.DATE,-1);
        return cal.getTime();
    }

    /**
     * 년, 월 or 달을 주어진 수 만큼 더한다.<br><br>
     *
     * ex) String calDate1 = DateUtil.getAdd(DateUtil.YEAR,  "yyyy-MM-dd", "2005-01-01", -1);<br>
     *     String calDate2 = DateUtil.getAdd(DateUtil.MONTH, "yyyy-MM-dd", "2005-01-01", -1);<br>
     *     String calDate3 = DateUtil.getAdd(DateUtil.DATE,  "yyyy-MM-dd", "2005-01-01", -1);
     *
     * @param gbn       DateUtil.YEAR  - 년도를 더한다.
     *                  DateUtil.MONTH - 월을 더한다.
     *                  DateUtil.DATE  - 일을 더한다. 대소문자를 구별하지 않는다.
     * @param formatter SimpleDateFormat에 사용할 포맷
     * @param date      format과 일치하는 포맷을 가진 날짜
     * @param addValue  더할 값
     *
     * @return String 주어진 필드에 값을 더해 formatter에 지정한 형태로 날짜를 반환한다.
     */
    public static String add(int gbn, String formatter, String date, int addValue) {
        String rtnValue = null;

        SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.getDefault());
        Calendar cal = Calendar.getInstance();

        try {
            Date receiveDate = sdf.parse(date);

            cal.setTime(receiveDate);

            if (gbn == YEAR) {
                cal.add(Calendar.YEAR, addValue);
            }
            else if (gbn == MONTH) {
                cal.add(Calendar.MONTH, addValue);
            }
            else {
                cal.add(Calendar.DATE, addValue);
            }

            rtnValue = sdf.format(cal.getTime());
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage(), e);
            }
        }

        return rtnValue;
    }

    /**
     * 주어진 일 수 만큼 더한다.
     *
     * @param date     대상이 되는 날짜. 년, 월, 일까지만 해당
     * @param addValue 더할 일 수
     * @return String  기본 포맷
     */
    public static String addDate(String date, int addValue) {
        return add(DATE, DEFAULT_DATE_FORMAT, getNumber(date), addValue);
    }

    /**
     * 주어진 일 수 만큼 더한다.
     *
     * @param formatter 날짜 형식
     * @param date      대상이 되는 날짜
     * @param addValue  더할 일 수
     * @return String   주어진 날짜 형식에 맞는 결과
     */
    public static String addDate(String formatter, String date, int addValue) {
        return add(DATE, formatter, date, addValue);
    }

    /**
     * 주어진 월 수 만큼 더한다.
     *
     * @param date     대상이 되는 날짜. 년, 월, 일까지만 해당
     * @param addValue 더할 월 수
     * @return String  기본 포맷
     */
    public static String addMonth(String date, int addValue) {
        return add(MONTH, DEFAULT_DATE_FORMAT, getNumber(date), addValue);
    }

    /**
     * 주어진 월 수 만큼 더한다.
     *
     * @param formatter 날짜 형식
     * @param date      대상이 되는 날짜
     * @param addValue  더할 월 수
     * @return String   주어진 날짜 형식에 맞는 결과
     */
    public static String addMonth(String formatter, String date, int addValue) {
        return add(MONTH, formatter, date, addValue);
    }

    /**
     * 주어진 년 수 만큼 더한다.
     *
     * @param date     대상이 되는 날짜. 년, 월, 일까지만 해당
     * @param addValue 더할 년 수
     * @return String  기본 포맷
     */
    public static String addYear(String date, int addValue) {
        return add(YEAR, DEFAULT_DATE_FORMAT, getNumber(date), addValue);
    }

    /**
     * 주어진 년 수 만큼 더한다.
     *
     * @param formatter 날짜 형식
     * @param date      대상이 되는 날짜
     * @param addValue  더할 년 수
     * @return String   주어진 날짜 형식에 맞는 결과
     */
    public static String addYear(String formatter, String date, int addValue) {
        return add(YEAR, formatter, date, addValue);
    }

    /**
     * 일요일을 기준으로 각 주의 시작일과 종료일을 구한다. 달을 무시한다.
     *
     * @param selectYear  해당 년
     * @param selectMonth 해당 달
     * @param selectWeek  해당 주
     * @param dateGubun   날짜 사이의 구분자
     *
     * @return String 배열 0:시작일, 1:종료일
     */
    public static String[] getStartEndDate(String selectYear, String selectMonth, String selectWeek, String dateGubun) {
        Calendar cal = Calendar.getInstance();

        String[] returnValue = new String[2];

        String startYear = "";
        String startMonth = "";
        String startDay = "";
        String endYear = "";
        String endMonth = "";
        String endDay = "";

        cal.set(Calendar.YEAR, Integer.parseInt(selectYear));
        cal.set(Calendar.MONTH, Integer.parseInt(selectMonth) - 1);
        cal.set(Calendar.WEEK_OF_MONTH, Integer.parseInt(selectWeek));

        // 주의 시작일 구하기
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        startYear = cal.get(Calendar.YEAR) + "";

        if (cal.get(Calendar.MONTH) + 1 < 10) {
            startMonth = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            startMonth = "" + (cal.get(Calendar.MONTH) + 1);
        }

        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            startDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
        } else {
            startDay = "" + cal.get(Calendar.DAY_OF_MONTH);
        }

        // 주의 종료일 구하기
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        endYear = cal.get(Calendar.YEAR) + "";

        if (cal.get(Calendar.MONTH) + 1 < 10) {
            endMonth = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            endMonth = "" + (cal.get(Calendar.MONTH) + 1);
        }

        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            endDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
        } else {
            endDay = "" + cal.get(Calendar.DAY_OF_MONTH);
        }

        // 결과 셋팅하기
        returnValue[0] = startYear + dateGubun + startMonth + dateGubun + startDay;
        returnValue[1] = endYear + dateGubun + endMonth + dateGubun + endDay;

        return returnValue;
    }

    /**
     * 주어진 날짜가 속한 주의 시작일과 종료일을 구한다.
     *
     * @param date      대상이 되는 날짜. 구분자를 제외하면 yyyyMMdd 형태여야 한다.
     * @param dateGubun 년, 월, 일을 구분할 구분자.
     * @return String[] 주어진 날짜가 속한 주의 시작일과 종료일. yyyy + 구분자 + MM + 구분자 + dd 형태이다.
     */
    public static String[] getStartEndDate(String date, String dateGubun) {
        String[] result = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        Calendar cal = Calendar.getInstance();

        try {
            Date receiveDate = sdf.parse(getNumber(date));

            cal.setTime(receiveDate);

            String year  = cal.get(Calendar.YEAR)          + "";
            String month = (cal.get(Calendar.MONTH) + 1)   + "";
            String week  = cal.get(Calendar.WEEK_OF_MONTH) + "";

            result = getStartEndDate(year, month, week, dateGubun);
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * 해당 날짜가 속한 주의 첫째날을 구한다. 달 무시
     *
     * @param date 날짜
     * @return 해당 날짜가 속한 주의 첫째날. 일요일 기준
     */
    public static String getStartDateInWeek(String date) {
        return getStartDateInWeek(date, DateUtil.DEFAULT_SEPARATOR);
    }

    /**
     * 해당 날짜가 속한 주의 첫째날을 구한다. 달 무시
     *
     * @param date 날짜
     * @param dateGubun 날짜 구분자
     * @return 해당 날짜가 속한 주의 첫째날. 일요일 기준
     */
    public static String getStartDateInWeek(String date, String dateGubun) {
        String result = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        Calendar cal = Calendar.getInstance();

        try {
            Date receiveDate = sdf.parse(getNumber(date));

            cal.setTime(receiveDate);

            String year  = cal.get(Calendar.YEAR)          + "";
            String month = (cal.get(Calendar.MONTH) + 1)   + "";
            String week  = cal.get(Calendar.WEEK_OF_MONTH) + "";

            String[] tempArray = getStartEndDate(year, month, week, dateGubun);
            result = tempArray[0];
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * 해당 날짜가 속한 주의 마지막날을 구한다. 달 무시
     *
     * @param date 날짜
     * @return 해당 날짜가 속한 주의 마지막날. 일요일 기준
     */
    public static String getEndDateInWeek(String date) {
        return getEndDateInWeek(date, DateUtil.DEFAULT_SEPARATOR);
    }

    /**
     * 해당 날짜가 속한 주의 마지막날을 구한다. 달 무시
     *
     * @param date 날짜
     * @param dateGubun 날짜 구분자
     * @return 해당 날짜가 속한 주의 마지막날. 일요일 기준
     */
    public static String getEndDateInWeek(String date, String dateGubun) {
        String result = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        Calendar cal = Calendar.getInstance();

        try {
            Date receiveDate = sdf.parse(getNumber(date));

            cal.setTime(receiveDate);

            String year  = cal.get(Calendar.YEAR)          + "";
            String month = (cal.get(Calendar.MONTH) + 1)   + "";
            String week  = cal.get(Calendar.WEEK_OF_MONTH) + "";

            String[] tempArray = getStartEndDate(year, month, week, dateGubun);
            result = tempArray[1];
        }
        catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error(e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * 주어진 날짜가 속한 주의 시작일과 종료일을 구한다.
     *
     * @param date      대상이 되는 날짜. 구분자를 제외하면 yyyyMMdd 형태여야 한다.
     * @return String[] 주어진 날짜가 속한 주의 시작일과 종료일. 기본 구분자로 분리된 값
     */
    public static String[] getStartEndDate(String date) {
        return getStartEndDate(date, DEFAULT_SEPARATOR);
    }

    /**
     * 주어진 날짜의 요일을 숫자값으로 반환한다.
     *
     * @param  selectDate 날짜
     * @return int 주어진 날짜의 요일값(1:일요일 ~ 7:토요일)
     */
    public static int getDayOfWeek(String selectDate) {
        Calendar cal = Calendar.getInstance();

        String tempDate = getNumber(selectDate);

        cal.set(Calendar.YEAR, Integer.parseInt(tempDate.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(tempDate.substring(4, 6)) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(tempDate.substring(6)));

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 주어진 날짜가 속한 그 달의 주를 숫자값으로 반환한다.
     *
     * @param  selectDate 날짜
     * @return int 주어진 날짜가 속한 그 달의 주
     */
    public static int getWeek(String selectDate) {
        Calendar cal = Calendar.getInstance();

        String tempDate = getNumber(selectDate);

        cal.set(Calendar.YEAR, Integer.parseInt(tempDate.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(tempDate.substring(4, 6)) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(tempDate.substring(6)));

        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 주어진 날짜를 주어진 포맷으로 변경한다. 주어지는 날짜는 구분자가 있을 경우 구분자를 제거한 결과가
     * 다음의 형식을 만족해야 한다.<br><br>
     *
     * yyyyMM, yyyyMMdd, yyyyMMddHH, yyyyMMddHHmm, yyyyMMddHHmmss
     *
     * @param date   포맷을 변경할 날짜
     * @param format 변경할 포맷
     * @return String 주어진 포맷으로 변경된 날짜
     * @throws Exception
     */
    public static String changeFormat(String date, String format) {
        String tempFormat = null;
        String tempDate = getNumber(date);

        int dateLength = tempDate.length();

        switch(dateLength) {
            case 6 :
                tempFormat = "yyyyMM";
                break;
            case 8 :
                tempFormat = "yyyyMMdd";
                break;
            case 10 :
                tempFormat = "yyyyMMddHH";
                break;
            case 12 :
                tempFormat = "yyyyMMddHHmm";
                break;
            case 14 :
                tempFormat = "yyyyMMddHHmmss";
                break;
            case 15 :
                tempFormat = "yyyyMMddHHmmssS";
                break;
            case 16 :
                tempFormat = "yyyyMMddHHmmssS";
                break;
            case 17 :
                tempFormat = "yyyyMMddHHmmssS";
                break;
            default :
                tempFormat = "yyyyMMdd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(tempFormat, Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat(format, Locale.getDefault());

        try {
            tempDate = sdf2.format(sdf.parse(tempDate));
        }
        catch (ParseException e) {
            if(log.isErrorEnabled()){
                log.error("Format Error.(" + date + ")", e);
            }
        }

        return tempDate;
    }

    /**
     * 주어진 년도와 월을 이용하여 그 달의 주의 갯수를 구한다.
     *
     * @param year  년도
     * @param month 월
     * @return int 주어진 달의 주의 갯수
     */
    public static int countWeek(String year, String month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, (Integer.parseInt(month) - 1));

        return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 주어진 날짜를 이용하여 그 달의 주의 갯수를 구한다.
     *
     * @param date 날짜. 최소한 6자리 필요
     * @return int 주어진 날짜가 속한 달의 주의 갯수
     */
    public static int countWeek(String date) {
        String tempDate = getNumber(date);

        return countWeek(tempDate.substring(0, 4), tempDate.substring(4, 6));
    }

    /**
     * 이번 달의 주의 갯수
     *
     * @return int 이번 달의 주의 갯수
     */
    public static int countWeek() {
        Calendar cal = Calendar.getInstance();

        return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 숫자만 추출한다. Regular Expression 적용.
     *
     * @param  extractDate 날짜
     * @return String 숫자만으로 된 문자열
     */
    private static String getNumber(String extractDate) {
        StringBuffer returnValue = new StringBuffer(8);
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(extractDate);

        for (int i = 0; matcher.find(i); i = matcher.end()) {
            returnValue.append(extractDate.substring(matcher.start(), matcher.end()));
        }

        return returnValue.toString();
    }

    /**
     * 문자열로 되어 있는 날짜를 지정한 포맷의 Date 형으로 변환한다.
     *
     * @param date 문자열 날짜
     * @param format 지정한 포맷
     * @return Date
     * @throws ParseException
     */
    @Deprecated
    public static Date stringToDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.parse(date);
    }

    /**
     * 문자열로 되어 있는 날짜를 기본 포맷의 Date 형(날짜)으로 변환한다.
     * @param date
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDate(String date) throws ParseException {
        return stringToDate(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 문자열로 되어 있는 일시를 기본 포맷의 Date 형(일시)으로 변환한다.
     * @param date
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDateTime(String date) throws ParseException {
        return stringToDate(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 19700101000000 형식의 유효시작일시를 Date 형으로  리턴한다.
     * @return Date
     * @throws ParseException
     */
    public static Date getEfctStDt() throws ParseException {
        return stringToDate(DEFAULT_EFCT_ST_DT_FORMAT, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 99991231000000 형식의 유효종료일시를 Date 형으로  리턴한다.
     * @return Date
     * @throws ParseException
     */
    public static Date getEfctFnsDt() throws ParseException {
        return stringToDate(DEFAULT_EFCT_FNS_DT_FORMAT, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 현재 시간을 조회한다.
     *
     * @return Date
     * @throws ParseException
     */
    public static Date currentDate( )throws ParseException{
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT ,Locale.getDefault());
        return stringToDate(sdf.format(now),DEFAULT_DATETIME_FORMAT);
    }


    /**
     * 일자의 차이 비교.
     *
     * @param beginDate
     * @return Date
     * @throws ParseException
     */
    public static long getDaysDiff(Date beginDate, Date endDate) {
        if (endDate.after(beginDate)) {
            return doDaysDiff(beginDate, endDate);
        } else {
            return -doDaysDiff(endDate, beginDate);
        }
    }

    private static long doDaysDiff(Date beginDate, Date endDate) {
        long diff = endDate.getTime() - beginDate.getTime();
        return (long) diff / (24 * 3600 * 1000);
    }

    public static String getCurrentTimeSS(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssS", Locale.getDefault());
        String timeStr = sdf.format(new Date());
        if (timeStr.length() == 9) {
            return timeStr.substring(0,8);
        } else {
            return timeStr;
        }
    }
    /**
     * getLastWeekSameDay
     * 지난주 동일 요일 구하기
     *
     * @param format
     * : 특정 일
     * @return 지난주 같은 요일 날짜
     */
    public static String getLastWeekSameDay(String format) {
        return getLastWeekSameDay(format, -7);
    }

    /**
     * getLastWeekSameDay
     * 지난주 동일 요일 구하기
     *
     * @param format
     * : 특정 일
     * @return 지난주 같은 요일 날짜
     */
    public static String getLastWeekSameDay(String format, int beforeDay) {
        String returnFormat = "";
        if (format == null) {
            returnFormat = "yyyy-MM-dd";
        } else {
            returnFormat = format;
        }

        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.add(Calendar.DATE, beforeDay);

        return getDateByFormat(cal.getTime(), returnFormat);
    }

    /**
     * 포맷에 따른 문자열 반환
     *
     * @param date
     * @param format
     * : yyyyMMddHHmmss
     * @return
     */
    public static String getDateByFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        return simpleDateFormat.format(date);
    }

    /**
     * 어제 일자 문자열 반환. 기본포맷: yyyy-MM-dd
     *
     * @return
     */
    public static String getYesterdy(String format) {
        String returnFormat = "";
        if (format == null) {
            returnFormat = "yyyy-MM-dd";
        } else {
            returnFormat = format;
        }

        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);

        return getDateByFormat(cal.getTime(), returnFormat);
    }

    /**
     * getLastMonthSameDate
     * 지난달 동일 일자 구하기
     *
     * @param format
     * : 특정 일
     * @return 지난달 동일 일자09
     */
    public static String getLastMonthSameDate(String format) {
        String returnFormat = "";
        if (format == null) {
            returnFormat = "yyyy-MM-dd";
        } else {
            returnFormat = format;
        }

        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.add(Calendar.MONTH, -1); // 1개월전

        return getDateByFormat(cal.getTime(), returnFormat);
    }

    public String getTime(String dateFormat, String plannedEnd) {
        // TODO Auto-generated method stub
        return null;
    }
}
