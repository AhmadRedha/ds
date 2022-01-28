package ir.ds.obj.tile;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */

import ir.ds.obj.bg.BG;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Design {
    private BG bg;
    private Border border;


}
