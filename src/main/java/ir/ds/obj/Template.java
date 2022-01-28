package ir.ds.obj;

import ir.ds.obj.tile.Tile;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@Builder
@Data
public class Template {
    @Singular
    public List<Tile> tiles;
}
