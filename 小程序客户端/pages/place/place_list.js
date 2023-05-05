var utils = require("../../utils/common.js");
var config = require("../../utils/config.js");

Page({
  /**
   * 页面的初始数据
   */
  data: {
    queryViewHidden: true, //是否隐藏查询条件界面
    placeTypeObj_Index:"0", //会议室类型查询条件
    placeTypes: [{"placeTypeId":0,"placeTypeName":"不限制"}],
    placeName: "", //会议室名称查询关键字
    placeLocation: "", //会议室位置查询关键字
    addTime: "", //发布时间查询关键字
    places: [], //界面显示的会议室列表数据
    page_size: 8, //每页显示几条数据
    page: 1,  //当前要显示第几页
    totalPage: null, //总的页码数
    loading_hide: true, //是否隐藏加载动画
    nodata_hide: true, //是否隐藏没有数据记录提示
  },

  // 加载会议室列表
  listPlace: function () {
    var self = this
    var url = config.basePath + "api/place/list"
    //如果要显示的页码超过总页码不操作
    if(self.data.totalPage != null && self.data.page > self.data.totalPage) return
    self.setData({
      loading_hide: false,  //显示加载动画
    })
    //提交查询参数到服务器查询数据列表
    utils.sendRequest(url, {
      "placeTypeObj.placeTypeId": self.data.placeTypes[self.data.placeTypeObj_Index].placeTypeId,
      "placeName": self.data.placeName,
      "placeLocation": self.data.placeLocation,
      "addTime": self.data.addTime,
      "page": self.data.page,
      "rows": self.data.page_size,
    }, function (res) { 
      wx.stopPullDownRefresh()
      setTimeout(function () {  
        self.setData({
          places: self.data.places.concat(res.data.list),
          totalPage: res.data.totalPage,
          loading_hide: true
        })
      }, 500)
      //如果当前显示的是最后一页，则显示没数据提示
      if(self.data.page == self.data.totalPage) {
        self.setData({
          nodata_hide: false,
        })
      }
    })
  },

  //显示或隐藏查询视图切换
  toggleQueryViewHide: function () {
    this.setData({
      queryViewHidden: !this.data.queryViewHidden,
    })
  },

  //点击查询按钮的事件
  queryPlace: function(e) {
    var self = this
    self.setData({
      page: 1,
      totalPage: null,
      places: [],
      loading_hide: true, //隐藏加载动画
      nodata_hide: true, //隐藏没有数据记录提示 
      queryViewHidden: true, //隐藏查询视图
    })
    self.listPlace()
  },

  //查询参数发布时间选择事件
  bind_addTime_change: function (e) {
    this.setData({
      addTime: e.detail.value
    })
  },
  //清空查询参数发布时间
  clear_addTime: function (e) {
    this.setData({
      addTime: "",
    })
  },

  //绑定查询参数输入事件
  searchValueInput: function (e) {
    var id = e.target.dataset.id
    if (id == "placeName") {
      this.setData({
        "placeName": e.detail.value,
      })
    }

    if (id == "placeLocation") {
      this.setData({
        "placeLocation": e.detail.value,
      })
    }

  },

  //查询参数会议室类型选择事件
  bind_placeTypeObj_change: function(e) {
    this.setData({
      placeTypeObj_Index: e.detail.value
    })
  },

  init_query_params: function() { 
    var self = this
    var url = null
    //初始化会议室类型下拉框
    url = config.basePath + "api/placeType/listAll"
    utils.sendRequest(url,null,function(res){
      wx.stopPullDownRefresh();
      self.setData({
        placeTypes: self.data.placeTypes.concat(res.data),
      })
    })
  },

  //删除会议室记录
  removePlace: function (e) {
    var self = this
    var placeId = e.currentTarget.dataset.placeid
    wx.showModal({
      title: '提示',
      content: '确定要删除placeId=' + placeId + '的记录吗？',
      success: function (sm) {
        if (sm.confirm) {
          // 用户点击了确定 可以调用删除方法了
          var url = config.basePath + "api/place/delete/" + placeId
          utils.sendRequest(url, null, function (res) {
            wx.stopPullDownRefresh();
            wx.showToast({
              title: '删除成功',
              icon: 'success',
              duration: 500
            })
            //删除会议室后客户端同步删除数据
            var places = self.data.places;
            for (var i = 0; i < places.length; i++) {
              if (places[i].placeId == placeId) {
                places.splice(i, 1)
                break
              }
            }
            self.setData({ places: places })
          })
        } else if (sm.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.listPlace()
    this.init_query_params()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    var self = this
    self.setData({
      page: 1,  //显示最新的第1页结果
      places: [], //清空会议室数据
      nodata_hide: true, //隐藏没数据提示
    })
    self.listPlace()
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var self = this
    if (self.data.page < self.data.totalPage) {
      self.setData({
        page: self.data.page + 1, 
      })
      self.listPlace()
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }

})

