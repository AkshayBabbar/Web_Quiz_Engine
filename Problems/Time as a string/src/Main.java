class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    @Override
    public String toString() {
        return calculate(hours) + hours + ":" + calculate(minutes)  + minutes + ":" + calculate(seconds)  + seconds;
    }
    private String calculate(int time) {
        return time < 10 ? "0" : "";
    }


}