package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerate {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "org.nicehiro.ShopCar");
        addTable(schema);
        new DaoGenerator().generateAll(schema, "../ShopCar/app/src/main/java-gen");
    }

    private static void addTable(Schema schema) {
        Entity shopCar = schema.addEntity("shopcar");
        shopCar.addByteArrayProperty("id").primaryKey().notNull();
        shopCar.addByteArrayProperty("profile");
        shopCar.addFloatProperty("price");
        shopCar.addByteArrayProperty("imagelink");
        shopCar.addIntProperty("remain");
        shopCar.addDateProperty("time");
    }
}
