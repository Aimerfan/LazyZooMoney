package edu.fcumselab.lazyzoomoney;

public class WalletEntity {
    private int imageId;
    private String name;
    private String money;

    public WalletEntity(int imageId, String name, String money) {
        this.imageId = imageId;
        this.name = name;
        this.money = money;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getMoney() {
        return money;
    }

}