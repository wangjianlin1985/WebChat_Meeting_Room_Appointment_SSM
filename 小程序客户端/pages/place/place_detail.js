var utils = require("../../utils/common.js")
var config = require("../../utils/config.js")

Page({
  /**
   * 页面的初始数据
   */
  data: {
    placeId: 0, //会议室id
    placeTypeObj: "", //会议室类型
    placeName: "", //会议室名称
    placePhotoUrl: "", //会议室照片
    personNum: "", //容纳人数
    placeLocation: "", //会议室位置
    price: "", //会议室单价
    placeDesc: "", //详细介绍
    addTime: "", //发布时间
    loadingHide: true,
    loadingText: "网络操作中。。",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (params) {
    var self = this
    var placeId = params.placeId
    var url = config.basePath + "api/place/get/" + placeId
    utils.sendRequest(url, {}, function (placeRes) {
      wx.stopPullDownRefresh()
      self.setData({
        placeId: placeRes.data.placeId,
        placeTypeObj: placeRes.data.placeTypeObj.placeTypeName,
        placeName: placeRes.data.placeName,
        placePhoto: placeRes.data.placePhoto,
        placePhotoUrl: placeRes.data.placePhotoUrl,
        personNum: placeRes.data.personNum,
        placeLocation: placeRes.data.placeLocation,
        price: placeRes.data.price,
        placeDesc: placeRes.data.placeDesc,
        addTime: placeRes.data.addTime,
      })
    })
  },


  addOrder: function() {
    var self = this
    wx.navigateTo({
      url: '../orderInfo/orderInfo_user_add?placeId=' + self.data.placeId ,
    })
  },


  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  }

})

