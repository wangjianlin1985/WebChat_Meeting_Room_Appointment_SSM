var utils = require("../../utils/common.js")
var config = require("../../utils/config.js")

Page({
  /**
   * 页面的初始数据
   */
  data: {
    placeId: 0, //会议室id
    placeTypeObj_Index: "0", //会议室类型
    placeTypes: [],
    placeName: "", //会议室名称
    placePhoto: "upload/NoImage.jpg", //会议室照片
    placePhotoUrl: "",
    placePhotoList: [],
    personNum: "", //容纳人数
    placeLocation: "", //会议室位置
    price: "", //会议室单价
    placeDesc: "", //详细介绍
    addTime: "", //发布时间
    loadingHide: true,
    loadingText: "网络操作中。。",
  },

  //选择发布时间事件
  bind_addTime_change: function (e) {
    this.setData({
      addTime: e.detail.value
    })
  },
  //清空发布时间事件
  clear_addTime: function (e) {
    this.setData({
      addTime: "",
    });
  },

  //选择会议室照片上传
  select_placePhoto: function (e) {
    var self = this
    wx.chooseImage({
      count: 1,   //可以上传一张图片
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePaths = res.tempFilePaths
        self.setData({
          placePhotoList: tempFilePaths,
          loadingHide: false, 
        });

        utils.sendUploadImage(config.basePath + "upload/image", tempFilePaths[0], function (res) {
          wx.stopPullDownRefresh()
          setTimeout(function () {
            self.setData({
              placePhoto: res.data,
              loadingHide: true
            })
          }, 200)
        })
      }
    })
  },

  //会议室类型修改事件
  bind_placeTypeObj_change: function (e) {
    this.setData({
      placeTypeObj_Index: e.detail.value
    })
  },

  //提交服务器修改会议室信息
  formSubmit: function (e) {
    var self = this
    var formData = e.detail.value
    var url = config.basePath + "api/place/update"
    utils.sendRequest(url, formData, function (res) {
      wx.stopPullDownRefresh();
      wx.showToast({
        title: '修改成功',
        icon: 'success',
        duration: 500
      })

      //服务器修改成功返回列表页更新显示为最新的数据
      var pages = getCurrentPages()
      var placelist_page = pages[pages.length - 2];
      var places = placelist_page.data.places
      for(var i=0;i<places.length;i++) {
        if(places[i].placeId == res.data.placeId) {
          places[i] = res.data
          break
        }
      }
      placelist_page.setData({places:places})
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
    var placeId = params.placeId
    var url = config.basePath + "api/place/get/" + placeId
    utils.sendRequest(url, {}, function (placeRes) {
      wx.stopPullDownRefresh()
      self.setData({
        placeId: placeRes.data.placeId,
        placeTypeObj_Index: 1,
        placeName: placeRes.data.placeName,
        placePhoto: placeRes.data.placePhoto,
        placePhotoUrl: placeRes.data.placePhotoUrl,
        personNum: placeRes.data.personNum,
        placeLocation: placeRes.data.placeLocation,
        price: placeRes.data.price,
        placeDesc: placeRes.data.placeDesc,
        addTime: placeRes.data.addTime,
      })

      var placeTypeUrl = config.basePath + "api/placeType/listAll" 
      utils.sendRequest(placeTypeUrl, null, function (res) {
        wx.stopPullDownRefresh()
        self.setData({
          placeTypes: res.data,
        })
        for (var i = 0; i < self.data.placeTypes.length; i++) {
          if (placeRes.data.placeTypeObj.placeTypeId == self.data.placeTypes[i].placeTypeId) {
            self.setData({
              placeTypeObj_Index: i,
            });
            break;
          }
        }
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

