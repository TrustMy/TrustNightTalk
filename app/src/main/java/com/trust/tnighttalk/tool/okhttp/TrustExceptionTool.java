package com.trust.tnighttalk.tool.okhttp;

/**
 * Created by Trust on 2017/11/15.
 */

public class TrustExceptionTool {
    private static ExceptionBean [] exceptionBeen = new ExceptionBean[2];

    static {
        exceptionBeen[0] = new ExceptionBean("ConnectException","无法连接到服务器,请检查网络!");
        exceptionBeen[1] = new ExceptionBean("SocketTimeoutException","连接服务器超时,请检查网络!");
    }


    public String checkException(String exceptionName){
        String info = null;
        if (exceptionName == null) {
            return null;
        }else{
            for (ExceptionBean exceptionBean : exceptionBeen) {
                if (exceptionName.equals(exceptionBean.getType())) {
                    info = exceptionBean.getInfo();
                    break;
                }
            }
            return info;
        }
    }


    static  class ExceptionBean{
        String type;
        String info;

        public ExceptionBean(String type, String info) {
            this.type = type;
            this.info = info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
