package Sandbox;

public class EnumTest {

    public enum DurationType {DAILY  (  24, 1440, 86400, "DAILY"),
        HOURLY (   1,   60,  3600, "HOURLY"),
        MINUTES(  60,    1,    60, "MINUTES"),
        SECONDS(3600,   60,     1, "SECONDS");

        private final int hours;
        private final int minutes;
        private final int seconds;
        private final String type;

        DurationType(int hours, int minutes, int seconds, String type) {
            this.hours   = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.type    = type;
        }

        public int getHours() {
            return this.hours;
        }


        public int getMinutes() {
            return this.minutes;
        }


        public int getSeconds() {
            return this.seconds;
        }


        public String getType() {
            return this.type;
        }

    };
}
