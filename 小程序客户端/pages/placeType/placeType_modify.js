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

  //提交服务器修改会议室类型信息
  formSubmit: function (e) {
    var self = this
    var formData = e.detail.value
    var url = config.basePath + "api/placeType/update"
    utils.sendRequest(url, formData, function (res) {
      wx.stopPullDownRefresh();
      wx.showToast({
        title: '修改成功',
        icon: 'success',
        duration: 500
      })

      //服务器修改成功返回列表页更新显示为最新的数据
      var pages = getCurrentPages()
      var placeTypelist_page = pages[pages.length - 2];
      var placeTypes = placeTypelist_page.data.placeTypes
      for(var i=0;i<placeTypes.length;i++) {
        if(placeTypes[i].placeTypeId == res.data.placeTypeId) {
          placeTypes[i] = res.data
          break
        }
      }
      placeTypelist_page.setData({placeTypes:placeTypes})
      setTimeout(function () {
        wx.navigateBack({})
      }, 500) 
    })
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
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  },

})

