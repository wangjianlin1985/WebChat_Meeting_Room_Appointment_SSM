var utils = require("../../utils/common.js")
var config = require("../../utils/config.js")

Page({
  /**
   * 页面的初始数据
   */
  data: {
    placeTypeId: 0, //会议室类型id
    placeTypeName: "", //会议室类型名称
    placeTypeDesc: "", //会议室类型说明
    loadingHide: true,
    loadingText: "网络操作中。。",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (params) {
    var self = this
    var placeTypeId = params.placeTypeId
    var url = config.basePath + "api/placeType/get/" + placeTypeId
    utils.sendRequest(url, {}, function (placeTypeRes) {
      wx.stopPullDownRefresh()
      self.setData({
        placeTypeId: placeTypeRes.data.placeTypeId,
        placeTypeName: placeTypeRes.data.placeTypeName,
        placeTypeDesc: placeTypeRes.data.placeTypeDesc,
      })
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

