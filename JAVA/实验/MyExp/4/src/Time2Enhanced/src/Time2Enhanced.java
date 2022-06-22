public class Time2Enhanced{
    private final int SECONDS_IN_MINUTE = 60;
    private final int MINUTES_IN_HOUR   = 60;
    private final int SECONDS_IN_HOUR   = 3600;
    private final int HOURS_IN_DAY      = 24;

    private int secondsSinceMidnight;

    // Time2Enhanced无参数构造函数:
    // 将每个实例变量初始化为零
    public Time2Enhanced(){
        this(0, 0, 0);  //使用三个参数调用 Time2Enhanced 构造函数
    }
    // Time2Enhanced构造函数：提供小时，分钟和秒默认为 0
    public Time2Enhanced(int h){
        this(h, 0, 0);
    }
    // Time2Enhanced 构造函数：提供小时和分钟，秒默认为 0
    public Time2Enhanced(int h, int m){
        this(h, m, 0);
    }
    // Time2Enhanced 构造函数：提供小时、分钟和秒
    public Time2Enhanced(int h, int m, int s){
        setTime(h, m, s);
    }
    // Time2Enhanced 构造函数：提供另一个 Time2Enhanced 对象
    public Time2Enhanced(Time2Enhanced time){
    	// 调用 Time2Enhanced 三参数构造函数
        this(time.getHour(), time.getMinute(), time.getSecond());
    }
    
    // SETTERS
    // 使用通用时间设置新的时间值
    // 验证数据
    public void setTime(int h, int m, int s){
        setHour(h);
        setMinute(m);
        setSecond(s);
    }
    // 验证并设置 hour
    public void setHour(int h){
        if(h >= 0 && h < HOURS_IN_DAY)
            secondsSinceMidnight += h * SECONDS_IN_HOUR;
        else
            throw new IllegalArgumentException("hour must be 0-23");
    }
    // 验证并设置 minute
    public void setMinute(int m){
        if(m >= 0 && m < MINUTES_IN_HOUR)
            secondsSinceMidnight += m * SECONDS_IN_MINUTE;
        else
            throw new IllegalArgumentException("minute must be 0-59");
    }
    // 验证并设置 second
    public void setSecond(int s){
        if(s >= 0 && s < SECONDS_IN_MINUTE)
            secondsSinceMidnight += s;
        else
            throw new IllegalArgumentException("second must be 0-59");
    }
    
    // 增加分钟
    public void incrementMinute(){
        // 确保没有溢出
        if(getMinute() < MINUTES_IN_HOUR - 1)
            secondsSinceMidnight += SECONDS_IN_MINUTE;
        // if so reset minutes to zero and increment hour
        else{
            secondsSinceMidnight -= (MINUTES_IN_HOUR - 1) * SECONDS_IN_MINUTE;
            incrementHour();
        }
    }
    // 增加小时
    public void incrementHour(){
        // 确保没有溢出
        if(getHour() < HOURS_IN_DAY - 1)
            secondsSinceMidnight += SECONDS_IN_HOUR;
        // if so reset hours to zero
        else
            secondsSinceMidnight -= (HOURS_IN_DAY - 1) * SECONDS_IN_HOUR;
    }
    
    // GETTERS
    // get hour
    public int getHour(){
        return secondsSinceMidnight / SECONDS_IN_HOUR;
    }
    // get minute
    public int getMinute(){
        return (secondsSinceMidnight % SECONDS_IN_HOUR) / MINUTES_IN_HOUR;
    }
    // get second
    public int getSecond(){
        return secondsSinceMidnight % SECONDS_IN_MINUTE;
    }

    // 转换为通用时间格式的字符串 (HH:MM:SS)
    public String toUniversalString(){
        return String.format(
                "%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }
    // 转换为标准时间格式的字符串 (H:MM:MM AM/PM)
    public String toString(){
    	//hour是0则设为12
        return String.format("%d:%02d:%02d %s",
                ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
                getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
    }
}