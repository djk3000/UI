package entity;

import java.util.List;

public class hotModel {
    public String code;
    private String msg;
    private List<hotData> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<hotData> getData() {
        return data;
    }

    public void setData(List<hotData> data) {
        this.data = data;
    }

    public class hotData {
        private String hot_word;
        private String hot_word_num;
        public String getHot_word() {
            return hot_word;
        }

        public void setHot_word(String hot_word) {
            this.hot_word = hot_word;
        }

        public String getHot_word_num() {
            return hot_word_num;
        }

        public void setHot_word_num(String hot_word_num) {
            this.hot_word_num = hot_word_num;
        }
    }

    @Override
    public String toString() {
        return "hotModel{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

