package uk.co.platitech.helpers;

import java.util.List;

/**
 * Created by samuel on 11/11/14.
 */
public class DataObject {

    private List data;

    public DataObject(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[list=" + data +
                ']';
    }
}
