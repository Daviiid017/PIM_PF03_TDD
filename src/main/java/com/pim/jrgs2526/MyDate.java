package com.pim.jrgs2526;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";
    private int day = 0;
    private Months month;
    private int year = 0;
    public  MyDate(int day, Months month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    public MyDate() {
    }
    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }
    public void setMonth(Months month) {
        if(month == null)
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        if(this.day == 29 && month == Months.FEBRUARY && !thisYearIsLeap(this.year))
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        if(this.day > 29 && month == Months.FEBRUARY)
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        if(this.day == 31 && (month == Months.APRIL || month == Months.JUNE || month == Months.SEPTEMBER ||
                month == Months.NOVEMBER))
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        this.month = month;
    }
    public void setDay(int day) {
        if(day <= 0 || day > 31)
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        if(this.month != null) {
            if ((this.month == Months.APRIL || this.month == Months.JUNE ||
                    this.month == Months.SEPTEMBER || this.month == Months.NOVEMBER) && day > 30)
                throw new IllegalArgumentException(ERR_INVALID_DAY);

            if (this.month == Months.FEBRUARY && !thisYearIsLeap(this.year) && day > 28)
                throw new IllegalArgumentException(ERR_INVALID_DAY);

            if (this.month == Months.FEBRUARY && thisYearIsLeap(this.year) && day > 29)
                throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }
    public void setYear(int year) {
        if((day == 29  && month == Months.FEBRUARY && !thisYearIsLeap(year))){
            if (this.year == 0)
                throw new IllegalArgumentException(ERR_INVALID_DATE);
            else
                throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        this.year = year;
    }
    public boolean thisYearIsLeap(int year) {
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }
}
