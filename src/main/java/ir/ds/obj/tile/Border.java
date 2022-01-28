package ir.ds.obj.tile;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */

@Data
@Builder
public class Border {
    private float width;
    private float round;
    private String color;
}
