var utils = require("../../utils/common.js");
var config = require("../../utils/config.js");

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
    placePhotoList: [],
    personNum: "", //容纳人数
    placeLocation: "", //会议室位置
    price: "", //会议室单价
    placeDesc: "", //详细介绍
    addTime: "", //发布时间
    loadingHide: true,
    loadingText: "网络操作中。。",
  },

  //初始化下拉框的信息
  init_select_params: function (options) { 
    var self = this;
    var url = null;
    url = config.basePath + "api/placeType/listAll";
    utils.sendRequest(url, null, function (res) {
      wx.stopPullDownRefresh();
      self.setData({
        placeTypes: res.data,
      });
    });
  },
  //会议室类型改变事件
  bind_placeTypeObj_change: function (e) {
    this.setData({
      placeTypeObj_Index: e.detail.value
    })
  },

  //选择发布时间
  bind_addTime_change: function (e) {
    this.setData({
      addTime: e.detail.value
    })
  },
  //清空发布时间
  clear_addTime: function (e) {
    this.setData({
      addTime: "",
    });
  },

  /*提交添加会议室到服务器 */
  formSubmit: function (e) {
    var self = this;
    var formData = e.detail.value;
    var url = config.basePath + "api/place/add";
    utils.sendRequest(url, formData, function (res) {
      wx.stopPullDownRefresh();
      wx.showToast({
        title: '发布成功',
        icon: 'success',
        duration: 500
      })

      //提交成功后重置表单数据
      self.setData({
        placeId: 0,
        placeTypeObj_Index: "0",
    placeName: "",
        placePhoto: "upload/NoImage.jpg",
        placePhotoList: [],
    personNum: "",
    placeLocation: "",
    price: "",
    placeDesc: "",
    addTime: "",
        loadingHide: true,
        loadingText: "网络操作中。。",
      })
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
          loadingHide: false
        });
        utils.sendUploadImage(config.basePath + "upload/image", tempFilePaths[0], function (res) {
          wx.stopPullDownRefresh()
          setTimeout(function () {
            self.setData({
              placePhoto: res.data,
              loadingHide: true
            });
          }, 200);
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.init_select_params(options);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var token = wx.getStorageSync('authToken');
    if (!token) {
      wx.navigateTo({
        url: '../mobile/mobile',
      })
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  }
})

