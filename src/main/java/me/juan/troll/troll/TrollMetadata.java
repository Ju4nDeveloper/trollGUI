package me.juan.troll.troll;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TrollMetadata {

    private final String name;
    private final Material material;
    private final int data;
    private final List<String> lore;

    public TrollMetadata(String name, Material material, int data, List<String> lore) {
        this.name = name;
        this.material = material;
        this.data = data;
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }

    public List<String> getLore() {
        return lore;
    }
}
