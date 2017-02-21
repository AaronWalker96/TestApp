package uk.ac.tees.q5045715.lecturemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create database handler
        DatabaseHandler dh = new DatabaseHandler(this);

        // Empty any existing data
        dh.removeAll();

        Log.d("Database: ", "Inserting values..");
        dh.addLecturer(new Lecturer("Mark Truran", "2267"));
        dh.addLecturer(new Lecturer("Ahmad Perez", "2249"));
        dh.addLecturer(new Lecturer("Jane Colton", "2526"));
        dh.addLecturer(new Lecturer("Eudes Diemoz", "3002"));

        dh.deleteLecturer(2);

        dh.updateLecturer(1, "Eggo", "1234");

        Log.d("Database: ", "Reading all contacts...");
        List<Lecturer> list = dh.getAll();

        for (Lecturer lr : list) {  // for each record (lr) in the list
            String log = "ID:" + lr.getID() +" Name: " + lr.getName() + " Phone: " +
                    lr.getPhoneNumber();
            Log.d("Database", log);
        }


    }


}
