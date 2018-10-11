package bin.manager.common.rabbitMQ;


import bin.manager.pojo.TbUser;

public class MiaoshaMessage {
    private TbUser user;
    private long goodsId;

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
