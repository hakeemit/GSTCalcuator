package technology.innovation.gstcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ReportList extends AppCompatActivity {

    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);


        simpleList =(ListView) findViewById(R.id.report_list_view);
        Button btn_delete=(Button) findViewById(R.id.button_delete);


        final DatabaseHandler db=new DatabaseHandler(this);

        ReportListViewAdapter arrayAdapter1=new ReportListViewAdapter(ReportList.this, db.getAllGST());
        simpleList.setAdapter(arrayAdapter1);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteRecords();
                ReportListViewAdapter  arrayAdapter1=new ReportListViewAdapter(ReportList.this, db.getAllGST());
                simpleList.setAdapter(arrayAdapter1);
            }
        });

    }


}
