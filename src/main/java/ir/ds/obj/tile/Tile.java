package ir.ds.obj.tile;


import ir.ds.obj.resource.Resource;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@Data
@Builder
public class Tile {
    private Position position;
    private Design design;
    private Resource resource;
}
