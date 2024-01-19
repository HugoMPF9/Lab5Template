package pt.pa.refactoring.model;

import java.time.LocalDateTime;

public class Review {
    private String reviewer;
    private String text;
    private double rating;
    private DateTime dateTime;

    public Review(String reviewer, String text, double rating) {
        this.reviewer = reviewer;
        this.text = text;
        this.rating = rating;
        this.dateTime = new DateTime();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Date: %s ", this.getDateTime().toString()))
                .append(String.format("Reviewer: %s ", this.getReviewer()))
                .append(String.format("Rating: %.1f ", this.getRating()))
                .append(String.format("Comment: %s", this.getText()));

        return sb.toString();
    }

    public class DateTime {
        private int day, month, year, hour, minute;

        public DateTime() {
            LocalDateTime date = LocalDateTime.now();

            this.day = date.getDayOfMonth();
            this.month = date.getMonthValue();
            this.year = date.getYear();
            this.hour = date.getHour();
            this.minute = date.getMinute();
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        @Override
        public String toString() {
            return String.format("%d/%d/%d @ %d:%d", this.day, this.month, this.year, this.hour, this.minute);
        }
    }
}
