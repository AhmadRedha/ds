package ir.ds.obj.tile;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@Data
@Builder
public class Position {
    private float top;
    private float left;
    private float height;
    private float width;
}
