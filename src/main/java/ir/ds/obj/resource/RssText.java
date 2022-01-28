package ir.ds.obj.resource;

import lombok.Data;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@Data
public class RssText {
    String value;

    public RssText(String value) {
        this.value = value;
    }

}
