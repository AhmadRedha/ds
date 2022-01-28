package ir.ds.obj.resource;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@Data
@Builder
public class RssResource implements Resource {
    private String icon;
    private String font;
    private String color;
    private float speed=1;
    private Direction direction;
    private int fontWeight=20;
    private List<RssText> texts;

    public void addToRss(String str) {
        if (texts == null) {
            texts = new ArrayList<>();
        }
        texts.add(new RssText(str));
    }

}
