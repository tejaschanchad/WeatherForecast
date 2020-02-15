package com.tejas.weatherforecast.model;

public class NetworkRequestModel {

    private String url;

    public String getUrl() {
        return url;
    }

    private NetworkRequestModel(AppPojoBuilder builder) {
        this.url = builder.url;
    }

    //Builder Class
    public static class AppPojoBuilder {

        private String prefStr;

        public AppPojoBuilder setPerfStr(String prefStr) {
            this.prefStr = prefStr;
            return this;
        }

        private String url;

        public AppPojoBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public NetworkRequestModel build() {
            return new NetworkRequestModel(this);
        }
    }
}
