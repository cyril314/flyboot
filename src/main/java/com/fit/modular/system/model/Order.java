package com.fit.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author Aim
 * @since 2018-06-05
 */
@TableName("qm_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    private String sn;
    /**
     * 子订单号
     */
    @TableField("sub_sn")
    private String subSn;
    /**
     * 付款时间
     */
    @TableField("pay_time")
    private Date payTime;
    /**
     * 宝贝名称
     */
    private String name;
    /**
     * 主图
     */
    @TableField("main_pic")
    private String mainPic;
    /**
     * 商品链接
     */
    private String link;
    /**
     * 属性商家编码
     */
    @TableField("sku_code")
    private String skuCode;
    /**
     * 商品属性
     */
    @TableField("sku_property")
    private String skuProperty;
    /**
     * 数量
     */
    @TableField("per_count")
    private Integer perCount;
    /**
     * 实付金额
     */
    @TableField("pay_price")
    private BigDecimal payPrice;
    /**
     * 总数量
     */
    @TableField("total_count")
    private Integer totalCount;
    /**
     * 总价格
     */
    @TableField("total_price")
    private BigDecimal totalPrice;
    /**
     * 买家备注
     */
    @TableField("buyer_remark")
    private String buyerRemark;
    /**
     * 卖家备注
     */
    @TableField("seller_remark")
    private String sellerRemark;
    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;
    /**
     * 买家昵称
     */
    @TableField("buyer_nickname")
    private String buyerNickname;
    /**
     * 买家昵称
     */
    @TableField("seller_nickname")
    private String sellerNickname;
    /**
     * 买家姓名
     */
    @TableField("buyer_name")
    private String buyerName;
    /**
     * 买家电话
     */
    @TableField("buyer_telephone")
    private String buyerTelephone;
    /**
     * 买家身份证
     */
    @TableField("buyer_idcard")
    private String buyerIdcard;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;
    /**
     * 快递UPS
     */
    @TableField("express_bag_name")
    private String expressBagName;
    /**
     * 快递UPS号
     */
    @TableField("express_bag_code")
    private String expressBagCode;
    /**
     * 快递公司名称
     */
    @TableField("express_name")
    private String expressName;
    /**
     * 快递公司号
     */
    @TableField("express_code")
    private String expressCode;
    /**
     * 备注
     */
    private String message;
    /**
     * 打包人员
     */
    @TableField("packer_name")
    private String packerName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSubSn() {
        return subSn;
    }

    public void setSubSn(String subSn) {
        this.subSn = subSn;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuProperty() {
        return skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
        this.skuProperty = skuProperty;
    }

    public Integer getPerCount() {
        return perCount;
    }

    public void setPerCount(Integer perCount) {
        this.perCount = perCount;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBuyerNickname() {
        return buyerNickname;
    }

    public void setBuyerNickname(String buyerNickname) {
        this.buyerNickname = buyerNickname;
    }

    public String getSellerNickname() {
        return sellerNickname;
    }

    public void setSellerNickname(String sellerNickname) {
        this.sellerNickname = sellerNickname;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone;
    }

    public String getBuyerIdcard() {
        return buyerIdcard;
    }

    public void setBuyerIdcard(String buyerIdcard) {
        this.buyerIdcard = buyerIdcard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getExpressBagName() {
        return expressBagName;
    }

    public void setExpressBagName(String expressBagName) {
        this.expressBagName = expressBagName;
    }

    public String getExpressBagCode() {
        return expressBagCode;
    }

    public void setExpressBagCode(String expressBagCode) {
        this.expressBagCode = expressBagCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPackerName() {
        return packerName;
    }

    public void setPackerName(String packerName) {
        this.packerName = packerName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", sn=" + sn +
        ", subSn=" + subSn +
        ", payTime=" + payTime +
        ", name=" + name +
        ", mainPic=" + mainPic +
        ", link=" + link +
        ", skuCode=" + skuCode +
        ", skuProperty=" + skuProperty +
        ", perCount=" + perCount +
        ", payPrice=" + payPrice +
        ", totalCount=" + totalCount +
        ", totalPrice=" + totalPrice +
        ", buyerRemark=" + buyerRemark +
        ", sellerRemark=" + sellerRemark +
        ", orderStatus=" + orderStatus +
        ", buyerNickname=" + buyerNickname +
        ", sellerNickname=" + sellerNickname +
        ", buyerName=" + buyerName +
        ", buyerTelephone=" + buyerTelephone +
        ", buyerIdcard=" + buyerIdcard +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", address=" + address +
        ", zipcode=" + zipcode +
        ", version=" + version +
        ", expressBagName=" + expressBagName +
        ", expressBagCode=" + expressBagCode +
        ", expressName=" + expressName +
        ", expressCode=" + expressCode +
        ", message=" + message +
        ", packerName=" + packerName +
        "}";
    }
}
