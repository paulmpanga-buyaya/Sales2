package sales.management.kiwamirembe.co.ug;

import androidx.appcompat.app.AppCompatActivity;
import android.text.format.Time;

public class BaseActivity extends AppCompatActivity {

   /* public class Clock {

        *//**
         * Get current time in human-readable form.
         * @return current time as a string.
         *//*
        public static String getNow() {
            Time now = new Time();
            now.setToNow();
            String sTime = now.format("%Y_%m_%d %T");
            return sTime;
        }
        *//**
         * Get current time in human-readable form without spaces and special characters.
         * The returned value may be used to compose a file name.
         * @return current time as a string.
         *//*
        public String getTimeStamp() {
            Time now = new Time();
            now.setToNow();
            String sTime = now.format("%Y_%m_%d_%H_%M_%S");
            return sTime;
        }

    }*/

}
