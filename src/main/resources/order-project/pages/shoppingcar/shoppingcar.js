//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    // 统计商品数量和价格
    orderCount: {
      num: 0,
      money: 0
    },
    bottomFlag: false,
    // 提交的订单
    orders: true,
    items: []
  },
  onShow: function () {
    let that = this;
    that.initshopcardata();
  },
  onLoad: function () {
    let that = this;
    // 取出订单传过来的数据
    // that.initshopcardata();
    // wx.getStorage({
    //   key: 'shoppingcar',
    //   success: function (res) {
    //     that.setData({
    //       items: res.data
    //     });
    //     // 价格统计汇总
    //     let money = 0;
    //     let num = res.data.length;
    //     res.data.forEach(item => {
    //       money += (item.price*item.num); // 总价格求和
    //     });
    //     let orderCount = {
    //       num,
    //       money
    //     }
    //     // 设置显示对应的总数和全部价钱
    //     that.setData({
    //       orderCount
    //     });
    //   }
    // })
  },
  //设置底部总价数据
  setnumdata: function (sc){
    // console.log(sc)
    let money = 0;
    for(var i=0;i<sc.length;i++){
      money += sc[i]["product"]["price"] * sc[i]["count"]
    }
    // console.log(money)
    // 价格统计汇总
    let num = sc.length;
    let orderCount = {
      num,
      money
    }
    // 设置显示对应的总数和全部价钱
    this.setData({
      orderCount
    });
  },
  initshopcardata: function (d) {
    var shoppingcar = wx.getStorageSync('shoppingcar');
    this.setnumdata(shoppingcar);
    this.setData({
      items: shoppingcar
    });
    // if (shoppingcar.length > 0) {
    //   for (var i = 0; i < shoppingcar.length;i++){
    //     console.log(shoppingcar[i])
    //   }
    // }
    // console.log(shoppingcar)
  },
  // 点击对应菜单添加按钮
  del: function (event) {
    var shoppingcar = wx.getStorageSync('shoppingcar');
    let that = this;
    let id = event.target.dataset.id;
    let index = event.target.dataset.index;
    let param = that.data.items[index];
    // console.log(param)
    if(param.count > 0){
      param.count--; // 每点一次减少1
    } else {
      param.count = 0; // 最低为0
    }
    // console.log(param)
    // // 改变添加按钮的状态
    that.data.items.splice(index, 1, param);
    that.setData({
      items: that.data.items
    });
    shoppingcar[index] = param;
    that.setnumdata(shoppingcar);
    wx.setStorageSync('shoppingcar', shoppingcar);
  },
  // 点击对应菜单添加按钮
  add: function (event) {
    var shoppingcar = wx.getStorageSync('shoppingcar');
    let that = this;
    let id = event.target.dataset.id;
    let index = event.target.dataset.index;
    let param = that.data.items[index];
    // console.log(param)
   
      param.count++; // 每点一次减少1
    
    // console.log(param)
    // // 改变添加按钮的状态
    that.data.items.splice(index, 1, param);
    that.setData({
      items: that.data.items
    });
    shoppingcar[index] = param;
    that.setnumdata(shoppingcar);
    wx.setStorageSync('shoppingcar', shoppingcar);
  },
  // 点击结账按钮
  pay: function() {
    let that = this;
    let str = '选中' + that.data.orderCount.num + '件商品，共' + that.data.orderCount.money + '元，是否要支付？'
    wx.showModal({
      title: '提示',
      content: str,
      success: function (res) {
        // 至少选中一个商品才能支付
        if (that.data.orderCount.num !== 0){
          if (res.confirm) {
            // 打开扫码功能
            wx.scanCode({
              onlyFromCamera: true,
              success: (res) => {
                wx.redirectTo({
                  url: '../pay/pay'
                });
              }
            });
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        } else {
          wx.showToast({
            title: '您未选中任何商品',
            icon: 'none',
            duration: 2000
          })
        }
      }
    })
  },
  
})