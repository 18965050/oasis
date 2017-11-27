package cn.xyz.chaos.mq.test;

import java.io.Serializable;

public class TestMessageEntity implements Serializable {
    private static final long serialVersionUID = -6586414875917184561L;

    private String oneField;
    private int twoField;

    public String getOneField() {
        return oneField;
    }

    public void setOneField(String oneField) {
        this.oneField = oneField;
    }

    public int getTwoField() {
        return twoField;
    }

    public void setTwoField(int twoField) {
        this.twoField = twoField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TestMessageEntity))
            return false;

        TestMessageEntity that = (TestMessageEntity) o;

        if (twoField != that.twoField)
            return false;
        return oneField != null ? oneField.equals(that.oneField) : that.oneField == null;
    }

    @Override
    public int hashCode() {
        int result = oneField != null ? oneField.hashCode() : 0;
        result = 31 * result + twoField;
        return result;
    }

    @Override
    public String toString() {
        return "TestMessageEntity{" + "oneField='" + oneField + '\'' + ", twoField=" + twoField + '}';
    }
}
