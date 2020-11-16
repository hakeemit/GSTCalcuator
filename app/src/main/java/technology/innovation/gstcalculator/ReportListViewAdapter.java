package technology.innovation.gstcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ReportListViewAdapter extends BaseAdapter{

    private Context mContext;
    List<GSTPojo> data;

    public ReportListViewAdapter(Context context,List<GSTPojo> data_pojo)
    {
        super();
        mContext=context;
        data=data_pojo;

    }

    public int getCount()
    {
        return data.size();
    }

    public View getView(final int position, View view, ViewGroup parent)
    {
        final int position_value=position;
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_report_listview, null);

        // get the reference of textViews
        //final TextView textViewDate=(TextView)view.findViewById(R.id.report_text_date);
        final TextView date_value=(TextView)view.findViewById(R.id.date_value);
        final TextView amount_value=(TextView)view.findViewById(R.id.amount_value);
        final TextView base_value=(TextView)view.findViewById(R.id.base_value);
        final TextView cgst_value=(TextView)view.findViewById(R.id.cgst_value);
        final TextView sgst_value=(TextView)view.findViewById(R.id.sgst_value);
        final TextView tgst_value=(TextView)view.findViewById(R.id.tgst_value);
        final TextView gstType_value=(TextView)view.findViewById(R.id.gstType_value);
        final TextView total_amount_value=(TextView)view.findViewById(R.id.total_amount_value);

        final DatabaseHandler db=new DatabaseHandler(mContext);

        String split[]= data.get(position).getDate().split(" ");
       // textViewDate.setText(data.get(position).getExpensesDate());
        date_value.setText(split[2]+"/"+split[1]);
        amount_value.setText(data.get(position).getAmount());
        base_value.setText(data.get(position).getBase());
        cgst_value.setText(data.get(position).getCgst());
        sgst_value.setText(data.get(position).getSgst());
        tgst_value.setText(data.get(position).getTgst());
        if(data.get(position).getGstType().equalsIgnoreCase("plus")){
            gstType_value.setText("Excl. GST" );
        }else {
            gstType_value.setText("Incl. GST" );
        }

        total_amount_value.setText(data.get(position).getTotal());

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


}
