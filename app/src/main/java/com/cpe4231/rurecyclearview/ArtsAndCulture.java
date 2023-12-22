package com.cpe4231.rurecyclearview;
class ArtsAndCulture {
    private String title;
    private String info;
    private String detail;
    private String prov;
    private String place;
    private String period;
    private final int imageResource;

    public ArtsAndCulture(String title, String info, String detail, String prov, String place,  String period, int imageResource) {
        this.title = title;
        this.info = info;
        this.detail = detail;
        this.prov = prov;
        this.place = place;
        this.period = period;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    String getDetail(){
        return detail;
    }

    String getProv() {
        return prov;
    }

    String getPlace() {
        return place;
    }

    String getPeriod() {
        return period;
    }

    public int getImageResource() {
        return imageResource;
    }
}
