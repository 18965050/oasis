package cn.xyz.chaos.examples.demo.entity;

/**
 * 这种写法有问题. 需要用领域模型整改
 *
 * @author lvchenggang
 *
 */
public class FavoriteProd extends Favorite {

    private String prodName;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
