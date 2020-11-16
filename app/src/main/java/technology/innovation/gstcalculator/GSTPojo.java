package technology.innovation.gstcalculator;


public class GSTPojo{

    //private variables
    int id;
    String amount,base,cgst,sgst,tgst,total,date,gstType;

    public GSTPojo(){

    }


    public GSTPojo(int id, String amount, String base, String cgst, String sgst, String tgst, String total, String date, String gstType){
        this.id=id;
        this.amount=amount;
        this.base=base;
        this.cgst=cgst;
        this.sgst=sgst;
        this.tgst=tgst;
        this.total=total;
        this.date=date;
        this.gstType=gstType;
    }

    public GSTPojo( String amount, String base, String cgst, String sgst, String tgst,String total, String date, String gstType){
        this.amount=amount;
        this.base=base;
        this.cgst=cgst;
        this.sgst=sgst;
        this.tgst=tgst;
        this.total=total;
        this.date=date;
        this.gstType=gstType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getTgst() {
        return tgst;
    }

    public void setTgst(String tgst) {
        this.tgst = tgst;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGstType() {
        return gstType;
    }

    public void setGstType(String gstType) {
        this.gstType = gstType;
    }

}