package technology.innovation.gstcalculator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView amount = null;
    TextView base_lbl =null, cgst_lbl =null, sgst_lbl=null, total_tax_lbl=null, total_amount_lbl=null;
    TextView base =null, cgst =null, sgst=null, total_tax=null, total_amount=null;
    Button btn_28plus = null, btn_18plus = null, btn_12plus = null,btn_5plus = null, btn_3plus= null;
    Button btn_28minus = null, btn_18minus = null, btn_12minus = null,btn_5minus = null, btn_3minus= null;
    Button btn_history = null;
    Button btn_plus = null, btn_minus = null, btn_mul = null,btn_div= null, btn_equal = null;
    DatabaseHandler db = null;

    public Character[] OPERATORS={'/','*','+','-'};

    public final String REGEXOPERATORS="[/+,-,/*,//,-]";
    public final String REGEXDIGITS="(\\d+(\\.\\d{1,2})?)";

    public ArrayList<Character> operators=new ArrayList<Character>();
    public ArrayList<Double> digits=new ArrayList<Double>();
    double fnum, snum, answer;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db = new DatabaseHandler(this);


        final Button btn_1 = (Button) findViewById(R.id.btn1);
        final Button btn_2 = (Button) findViewById(R.id.btn2);
        final Button btn_3 = (Button) findViewById(R.id.btn3);
        final Button btn_4 = (Button) findViewById(R.id.btn4);
        final Button btn_5= (Button) findViewById(R.id.btn5);
        final Button btn_6 = (Button) findViewById(R.id.btn6);
        final Button btn_7 = (Button) findViewById(R.id.btn7);
        final Button btn_8 = (Button) findViewById(R.id.btn8);
        final Button btn_9 = (Button) findViewById(R.id.btn9);
        final Button btn_0 = (Button) findViewById(R.id.btn0);
        final Button btn_00 = (Button) findViewById(R.id.btn00);
        final Button btn_dot = (Button) findViewById(R.id.btndot);

       // final Button btn_CE = (Button) findViewById(R.id.btnCE);
        final Button btn_AC = (Button) findViewById(R.id.btnAC);
        final Button btn_back = (Button) findViewById(R.id.btnBack);

        base =(TextView) findViewById(R.id.base_fare_value);
        cgst =(TextView) findViewById(R.id.central_tax_value);
        sgst =(TextView) findViewById(R.id.state_tax_value);
        total_tax =(TextView) findViewById(R.id.total_tax_value);
        total_amount =(TextView) findViewById(R.id.total_amount_value);


        base_lbl =(TextView) findViewById(R.id.base_fare);
        cgst_lbl =(TextView) findViewById(R.id.central_tax);
        sgst_lbl =(TextView) findViewById(R.id.state_tax);
        total_tax_lbl =(TextView) findViewById(R.id.total_tax);
        total_amount_lbl =(TextView) findViewById(R.id.total_amount);

        btn_28plus = (Button) findViewById(R.id.btn28Plus);
        btn_18plus = (Button) findViewById(R.id.btn18Plus);
        btn_12plus = (Button) findViewById(R.id.btn12Plus);
        btn_5plus = (Button) findViewById(R.id.btn5Plus);
        btn_3plus = (Button) findViewById(R.id.btn3Plus);

        btn_28minus = (Button) findViewById(R.id.btn28Minus);
        btn_18minus = (Button) findViewById(R.id.btn18Minus);
        btn_12minus = (Button) findViewById(R.id.btn12Minus);
        btn_5minus = (Button) findViewById(R.id.btn5Minus);
        btn_3minus = (Button) findViewById(R.id.btn3Minus);

        btn_history= (Button) findViewById(R.id.btnHistory);


        btn_plus = (Button) findViewById(R.id.btnPlus);
        btn_minus = (Button) findViewById(R.id.btnMinus);
        btn_mul = (Button) findViewById(R.id.btnMulti);
        btn_div = (Button) findViewById(R.id.btnDiv);
        btn_equal = (Button) findViewById(R.id.btnEqual);

        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/led_font.ttf");
        amount= (TextView) findViewById(R.id.amount_field);
        amount.setTypeface(tf);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("1");

                v.startAnimation(buttonClick);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("2");
                v.startAnimation(buttonClick);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("3");
                v.startAnimation(buttonClick);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("4");
                v.startAnimation(buttonClick);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("5");
                v.startAnimation(buttonClick);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("6");
                v.startAnimation(buttonClick);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("7");
                v.startAnimation(buttonClick);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("8");
                v.startAnimation(buttonClick);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("9");
                v.startAnimation(buttonClick);
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("0");
                v.startAnimation(buttonClick);

            }
        });

        btn_00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("00");
                v.startAnimation(buttonClick);
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount(".");
                v.startAnimation(buttonClick);
            }
        });

        /*btn_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                v.startAnimation(buttonClick);
            }
        });*/

        btn_AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                base.setText("");
                cgst.setText("");
                sgst.setText("");
                total_tax.setText("");
                total_amount.setText("");
                base_lbl.setText("Base Fare");
                cgst_lbl.setText("CGST");
                sgst_lbl.setText("SGST");
                total_tax_lbl.setText("Total GST Tax");
                total_amount_lbl.setText("Total Amount");
                v.startAnimation(buttonClick);
            }
        });

        btn_28plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(28);
                gstCalculation(28,"plus");
                view.startAnimation(buttonClick);

            }
        });

        btn_18plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(18);
                gstCalculation(18,"plus");
                view.startAnimation(buttonClick);
            }
        });

        btn_12plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(12);
                gstCalculation(12,"plus");
                view.startAnimation(buttonClick);

            }
        });

        btn_5plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(5);
                gstCalculation(5,"plus");
                view.startAnimation(buttonClick);

            }
        });

        btn_3plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(3);
                gstCalculation(3,"plus");
                view.startAnimation(buttonClick);

            }
        });

        btn_28minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(28);
                gstCalculation(0.28,"minus");
                view.startAnimation(buttonClick);
            }
        });

        btn_18minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(18);
                gstCalculation(0.18,"minus");
                view.startAnimation(buttonClick);
            }
        });

        btn_12minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(12);
                gstCalculation(0.12,"minus");
                view.startAnimation(buttonClick);
            }
        });

        btn_5minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(5);
                gstCalculation(0.05,"minus");
                view.startAnimation(buttonClick);
            }
        });

        btn_3minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGSTValue(3);
                gstCalculation(0.03,"minus");
                view.startAnimation(buttonClick);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ReportList.class);
                startActivity(intent);
                view.startAnimation(buttonClick);
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount.getText().length() > 0) {

                    StringBuilder strB = new StringBuilder(amount.getText());
                    strB.deleteCharAt(amount.getText().length() - 1);
                    amount.setText(strB.toString());

                    //Text Size
                    if(amount.getText().toString().length()< 9){
                        amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60 );

                    }
                }
                view.startAnimation(buttonClick);

            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("+");
                v.startAnimation(buttonClick);

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("-");
                v.startAnimation(buttonClick);

            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("*");
                v.startAnimation(buttonClick);
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAmount("/");
                v.startAnimation(buttonClick);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           performCalculation();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_us_menu) {
            Intent intent=new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);

            return true;
        }

        else if (id == R.id.contact_us_menu) {
            Intent intent=new Intent(MainActivity.this, ContactUs.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.contact_us) {
            Intent intent=new Intent(MainActivity.this, ContactUs.class);
            startActivity(intent);

        } else if (id == R.id.about_us) {
            Intent intent=new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void performCalculation(){
        Log.d(".....................","Clear Old Data");
        digits.clear();
        operators.clear();
        answer = 0;
        fnum =0;
        snum = 0;

        getDigits(amount.getText().toString());
        getOperators(amount.getText().toString());

        Iterator<Character> operator_value = operators.iterator();

        for (int i=0;i <digits.size();i++) {
            if (i == 0) {
                fnum=digits.get(i);
            }
            else{
                Log.d("Digits............", digits.get(i).toString() + ' ');
                if (operator_value.hasNext()) {
                    String op_value = operator_value.next().toString();
                    Log.d("Operators ............", op_value + ' ');
                    snum = digits.get(i);

                    if (op_value.equalsIgnoreCase("+")) {
                        fnum = addition();
                    } else if (op_value.equalsIgnoreCase("-")) {
                        fnum = subtraction();
                    } else if (op_value.equalsIgnoreCase("*")) {
                        fnum = multiplication();
                    } else if (op_value.equalsIgnoreCase("/")) {
                        fnum = division();
                    }
                }
                amount.setText(""+fnum);
            }
        }
        Log.d(".....................","");

    }

    private void displayAmount(String value){


        if(value.equalsIgnoreCase(".") && amount.getText().toString().contains(".")){
            Toast.makeText(MainActivity.this,"Already exists",Toast.LENGTH_SHORT).show();
        }else{
            if(amount.getText().toString().equalsIgnoreCase("0")){
                amount.setText(value);
            }else{
                if(amount.getText().toString().length()< 10){
                    amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60 );

                }else if(amount.getText().toString().length()>= 11 && amount.getText().toString().length()<24){
                    amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50 );
                }else if(amount.getText().toString().length()>= 24){
                    Toast.makeText(MainActivity.this,"Maximum limit reached.",Toast.LENGTH_SHORT).show();
                    return;
                }

                String value_data = amount.getText().toString();
                if(value_data!=null && value_data.trim() !=""){
                    String last_character = String.valueOf(value_data.charAt(value_data.length()-1));
                    if(value.equalsIgnoreCase("+")||
                            value.equalsIgnoreCase("-")||
                            value.equalsIgnoreCase("*")||
                            value.equalsIgnoreCase("/")){
                        if(last_character.equalsIgnoreCase("+")||
                                last_character.equalsIgnoreCase("-")||
                                last_character.equalsIgnoreCase("*")||
                                last_character.equalsIgnoreCase("/")){
                            StringBuilder strB = new StringBuilder(amount.getText());
                            strB.deleteCharAt(amount.getText().length() - 1);
                            amount.setText(strB.toString() +""+ value);
                        }else {
                            amount.setText(amount.getText() +""+ value);
                        }
                    }else {
                        amount.setText(amount.getText() +""+ value);
                    }

                }else {
                    amount.setText(amount.getText() +""+ value);
                }



            }




        }
    }

    private void gstCalculation(double percentage,String mode){
        performCalculation();
        if(amount.getText()==null||amount.getText().toString().trim().equals("")){
            amount.setText("0");
        }
        double value= Double.valueOf(amount.getText().toString());
        DecimalFormat f = new DecimalFormat("##.00");
        if(mode.equals("plus"))
        {
            double total_GST=0.0,total_amnt=0.0;
            base.setText(""+value);
            total_GST = value/100*percentage;
            total_tax.setText(""+f.format(total_GST ));
            cgst.setText(""+f.format(total_GST/2));
            sgst.setText(""+f.format(total_GST/2));
            total_amnt=value+total_GST;
            total_amount.setText(""+f.format(total_amnt));
        }else {
            double value_division=0.0,base_amnt=0.0,total_GST=0.0;
            value_division = 1 + percentage;
            base_amnt=value/value_division;
            base.setText(""+f.format(base_amnt));
            total_GST=value-base_amnt;
            total_tax.setText(""+f.format(total_GST ));
            cgst.setText(""+f.format(total_GST/2));
            sgst.setText(""+f.format(total_GST/2));
            total_amount.setText(""+f.format(value));

        }

        db.addExpenses(new GSTPojo(amount.getText().toString(),  base.getText().toString(),  cgst.getText().toString(),  sgst.getText().toString(),
                total_tax.getText().toString(), total_amount.getText().toString(),  String.valueOf(new Date()), mode));

    }


    private void displayGSTValue(double value){

        cgst_lbl.setText("CGST ("+value/2+"%)");
        sgst_lbl.setText("SGST ("+value/2+"%)");
        total_tax_lbl.setText("GST ("+value+"%)");

    }

    public void getDigits(String math){
        Log.d(".....................","Getting digits ...");

        Pattern r=Pattern.compile(REGEXDIGITS);
        Matcher m=r.matcher(math);
        while (m.find()){/*
            System.out.print("Start: "+m.start()+" ");
            System.out.print("End:"+m.end()+" ");*/

            double t =Double.valueOf(math.substring(m.start(), m.end()));
            //System.out.println(t);
            digits.add(t);
        }
        Log.d(".....................","\rFinished getting digits...");
    }
    public void getOperators(String math){
        Log.d(".....................","Getting Operators..");
        Pattern r=Pattern.compile(REGEXOPERATORS);
        Matcher m=r.matcher(math);
        while (m.find()){/*
            System.out.print("Start: "+m.start()+" ");
            System.out.print("End:"+m.end()+" ");
            System.out.println(math.charAt(m.end()));*/
            operators.add(math.charAt(m.start()));
        }
        Log.d(".....................","Finished getting Operators..");

    }

    public double addition(){
        answer = fnum + snum;
        Log.d(".....................",""+answer);
        return  answer;
    }

    public double subtraction(){
        answer = fnum - snum;
        Log.d(".....................",""+answer);
        return  answer;
    }

    public double division(){
        answer = fnum / snum;
        Log.d(".....................",""+answer);
        return  answer;
    }

    public double multiplication(){
        answer = fnum * snum;
        Log.d(".....................",""+answer);
        return  answer;
    }
}
