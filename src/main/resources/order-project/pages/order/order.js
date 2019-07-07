// pages/user/dingdan.js
//index.js  
//获取应用实例  
const app = getApp()
var comm = app.comm;
var tools = app.tools;
var config = app.config;

Page({
  data: {
    picPath: config.picPath,
    winWidth: 0,
    winHeight: 0,
    // tab切换  
    currentTab: 0,
    isStatus: 'pay',//10待付款，20待发货，30待收货 40、50已完成
    page: 0,
    refundpage: 0,
    orderList: [],
    orderList1: [],
    orderList3: [],
    orderList4: [],
    orderList5: [],
    // orderList4: [],
  },
  onShow: function (options){
    this.loadOrderList(options);
  },
  onLoad: function (options) {
    if (options.length==null){
      options["currentTab"]=0;
      options["otype"] = 'pay';
    }
    this.initSystemInfo();
    this.setData({
      currentTab: parseInt(options.currentTab),
      isStatus: options.otype
    });
    if (this.data.currentTab == 4) {
      this.loadReturnOrderList();
    } else {
      this.loadOrderList(options);
    }
  },
  getOrderStatus: function () {
    return this.data.currentTab == 0 ? 1 : this.data.currentTab == 2 ? 2 : this.data.currentTab == 3 ? 3 : 0;
  },

  //取消订单
  removeOrder: function (e) {
    var that = this;
    var orderId = e.currentTarget.dataset.orderId;
    wx.showModal({
      title: '提示',
      content: '你确定要取消订单吗？',
      success: function (res) {
        console.log(res)
        if (res.confirm){
          var dd={};
          dd["id"] = orderId;
          comm.globalObj.requestGetHttps("/xcx/order/delete", dd, function (d) {
            console.log(d.info)
            if (d.statusCode == 1) {
              that.setData({
                orderList: d.info,
              });
              var info = d.info;
              // that.initProductTypeList(info, 0, that)
              // console.log(info)
            } else if (d.statusCode == -7) {
              //app.Tools.alert("提示", d.errorMessage, function () { })
              console.log(d.errorMessage)
            }
          })
        }
        // res.confirm && wx.request({
        //   url: app.d.ceshiUrl + '/Api/Order/orders_edit',
        //   method: 'post',
        //   data: {
        //     id: orderId,
        //     type: 'cancel',
        //   },
        //   header: {
        //     'Content-Type': 'application/x-www-form-urlencoded'
        //   },
        //   success: function (res) {
        //     //--init data
        //     var status = res.data.status;
        //     if (status == 1) {
        //       wx.showToast({
        //         title: '操作成功！',
        //         duration: 2000
        //       });
        //       that.loadOrderList();
        //     } else {
        //       wx.showToast({
        //         title: res.data.err,
        //         duration: 2000
        //       });
        //     }
        //   },
        //   fail: function () {
        //     // fail
        //     wx.showToast({
        //       title: '网络异常！',
        //       duration: 2000
        //     });
        //   }
        // });

      }
    });
  },

  //确认收货
  recOrder: function (e) {
    var that = this;
    var orderId = e.currentTarget.dataset.orderId;
    wx.showModal({
      title: '提示',
      content: '你确定已收到宝贝吗？',
      success: function (res) {
        res.confirm && wx.request({
          url: app.d.ceshiUrl + '/Api/Order/orders_edit',
          method: 'post',
          data: {
            id: orderId,
            type: 'receive',
          },
          header: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          success: function (res) {
            //--init data
            var status = res.data.status;
            if (status == 1) {
              wx.showToast({
                title: '操作成功！',
                duration: 2000
              });
              that.loadOrderList();
            } else {
              wx.showToast({
                title: res.data.err,
                duration: 2000
              });
            }
          },
          fail: function () {
            // fail
            wx.showToast({
              title: '网络异常！',
              duration: 2000
            });
          }
        });

      }
    });
  },

  loadOrderList: function (o) {
    // console.log(o.otype)
    var dd={};
    if (o.otype=='pay'){//待付款
      dd["isPay"]=0;
    } else if (o.otype == 'deliver'){
      dd["isPay"] = 1;
      dd["isTake"] = 0;
    } else if (o.otype == 'finish') {
      dd["isTake"] = 1;
    } else if (o.otype == 'all') {//全部订单

    } else if (o.otype == 'today') {//当日订单
      dd["payDate"] = new Date();
    }
    var that = this;
    dd["pageSize"]=10;
    dd["pageNumber"] = 1;
    comm.globalObj.requestPostHttps("/xcx/order/datalist", dd, function (d) {
      console.log(d.info)
      if (d.statusCode == 1) {
        that.setData({
          orderList: d.info, 
        });
        var info = d.info;
        // that.initProductTypeList(info, 0, that)
        // console.log(info)
      } else if (d.statusCode == -7) {
        //app.Tools.alert("提示", d.errorMessage, function () { })
        console.log(d.errorMessage)
      }
    })
    // wx.request({
    //   url: app.d.ceshiUrl + '/Api/Order/index',
    //   method: 'post',
    //   data: {
    //     uid: app.d.userId,
    //     order_type: that.data.isStatus,
    //     page: that.data.page,
    //   },
    //   header: {
    //     'Content-Type': 'application/x-www-form-urlencoded'
    //   },
    //   success: function (res) {
    //     //--init data        
    //     var status = res.data.status;
    //     var list = res.data.ord;
    //     switch (that.data.currentTab) {
    //       case 0:
    //         that.setData({
    //           orderList0: list,
    //         });
    //         break;
    //       case 1:
    //         that.setData({
    //           orderList1: list,
    //         });
    //         break;
    //       case 2:
    //         that.setData({
    //           orderList2: list,
    //         });
    //         break;
    //       case 3:
    //         that.setData({
    //           orderList3: list,
    //         });
    //         break;
    //       case 4:
    //         that.setData({
    //           orderList4: list,
    //         });
    //         break;
    //     }
    //   },
    //   fail: function () {
    //     // fail
    //     wx.showToast({
    //       title: '网络异常！',
    //       duration: 2000
    //     });
    //   }
    // });
  },

  loadReturnOrderList: function () {
    var that = this;
    wx.request({
      url: app.d.ceshiUrl + '/Api/Order/order_refund',
      method: 'post',
      data: {
        uid: app.d.userId,
        page: that.data.refundpage,
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //--init data        
        var data = res.data.ord;
        var status = res.data.status;
        if (status == 1) {
          that.setData({
            orderList4: that.data.orderList4.concat(data),
          });
        } else {
          wx.showToast({
            title: res.data.err,
            duration: 2000
          });
        }
      },
      fail: function () {
        // fail
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    });
  },

  // returnProduct:function(){
  // },
  initSystemInfo: function () {
    var that = this;

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
  },
  bindChange: function (e) {
    var that = this;
    that.setData({ currentTab: e.detail.current });
  },
  swichNav: function (e) {
    var that = this;
    if (that.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      var current = e.target.dataset.current;
      that.setData({
        currentTab: parseInt(current),
        isStatus: e.target.dataset.otype,
        orderList:[]
      });

      //没有数据就进行加载
      var options={};
      options["currentTab"] = that.data.currentTab;
      options["otype"] = e.target.dataset.otype;
      switch (that.data.currentTab) {
        case 0:
          !that.data.orderList.length && that.loadOrderList(options);
          break;
        case 1:
          !that.data.orderList.length && that.loadOrderList(options);
          break;
        case 3:
          !that.data.orderList.length && that.loadOrderList(options);
          break;
        case 4:
          !that.data.orderList.length && that.loadOrderList(options);
          break;
        case 5:
          that.data.orderList.length = 0;
          that.loadReturnOrderList();
          break;
      }
    };
  },
  /**
   * 微信支付订单
   */
  // payOrderByWechat: function(event){
  //   var orderId = event.currentTarget.dataset.orderId;
  //   this.prePayWechatOrder(orderId);
  //   var successCallback = function(response){
  //     console.log(response);
  //   }
  //   common.doWechatPay("prepayId", successCallback);
  // },

  payOrderByWechat: function (e) {
    var order_id = e.currentTarget.dataset.orderId;
    var order_sn = e.currentTarget.dataset.ordersn;
    if (!order_sn) {
      wx.showToast({
        title: "订单异常!",
        duration: 2000,
      });
      return false;
    }
    wx.request({
      url: app.d.ceshiUrl + '/Api/Wxpay/wxpay',
      data: {
        order_id: order_id,
        order_sn: order_sn,
        uid: app.d.userId,
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }, // 设置请求的 header
      success: function (res) {
        if (res.data.status == 1) {
          var order = res.data.arr;
          wx.requestPayment({
            timeStamp: order.timeStamp,
            nonceStr: order.nonceStr,
            package: order.package,
            signType: 'MD5',
            paySign: order.paySign,
            success: function (res) {
              wx.showToast({
                title: "支付成功!",
                duration: 2000,
              });
              setTimeout(function () {
                wx.navigateTo({
                  url: '../user/dingdan?currentTab=1&otype=deliver',
                });
              }, 3000);
            },
            fail: function (res) {
              wx.showToast({
                title: res,
                duration: 3000
              })
            }
          })
        } else {
          wx.showToast({
            title: res.data.err,
            duration: 2000
          });
        }
      },
      fail: function (e) {
        // fail
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    })
  },

  /**
   * 调用服务器微信统一下单接口创建一笔微信预订单
   */
  //   prePayWechatOrder: function(orderId){
  //     var uri = "/ztb/userZBT/GetWxOrder";
  //     var method = "post";
  //     var dataMap = {
  //       SessionId: app.globalData.userInfo.sessionId,
  //       OrderNo: orderId
  //     }
  //     console.log(dataMap);
  //     var successCallback = function (response) {
  //       console.log(response);
  //     };
  //     common.sentHttpRequestToServer(uri, dataMap, method, successCallback);
  //   }
})