package org.grassrootsapp.grassroots.model.ProPublica.Bills;

public class BillDetailModel {

   private String bill_id;
   private String title;
   private String short_title;
   private String introduced_date;
   private String enacted;
   private String primary_subject;
   private String summary;
   private String summary_short;
   private boolean active;

    public String getBill_id() {
        return bill_id;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_title() {
        return short_title;
    }

    public String getIntroduced_date() {
        return introduced_date;
    }

    public boolean isActive() {
        return active;
    }

    public String getEnacted() {
        return enacted;
    }

    public String getPrimary_subject() {
        return primary_subject;
    }

    public String getSummary() {
        return summary;
    }

    public String getSummary_short() {
        return summary_short;
    }
}
