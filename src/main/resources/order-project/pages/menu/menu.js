//index.js
//获取应用实例
const app = getApp()
var comm = app.comm;
var tools = app.tools;
var config = app.config;

Page({
  data: {
    picPath:config.picPath,
    selected:[],
    selectCount:0,
    chosetaste:"",
    choseproduct: {},
    tasteList: [],
    tasteModal: false, // 显示modal弹窗
    tabIndex: 0,
    // 统计商品数量和价格
    orderCount: {
      num: 0,
      money: 0
    },
    bottomFlag: false,
    // 提交的订单
    orders: true,
    //菜品
    menus: [],
    //菜品种类
    items: [],
  },
  // 下拉刷新
  onPullDownRefresh: function () {
    setTimeout(()=>{
      wx.showToast({
        title: '成功加载数据',
        icon: 'success',
        duration: 500
      });
      wx.stopPullDownRefresh()
    }, 500);
  },
  tabMenu: function(event) {
    let that =this;
    var items=[];
    let index = event.target.dataset.index;
    items = that.data.menus[index].products
    console.log(items)
    that.setData({
      items: items,
      tabIndex: index
    });
  },
  // 点击去购物车结账
  card: function() {
    let that = this;
    // 判断是否有选中商品
    if (that.data.orderCount.num !== 0) {
      // 跳转到购物车订单也
      wx.redirectTo({
        url: '../order/order'
      });
    } else {
      wx.showToast({
        title: '您未选中任何商品',
        icon: 'none',
        duration: 2000
      })
    }
  },
  /**
   * （原）向购物车添加按钮动作
   */
  addOrder: function(event) {
    let that = this;
    
    let id = event.target.dataset.id;
    let index = event.target.dataset.index;
    let param = this.data.items[index];
    let subOrders = []; // 购物单列表存储数据
    param.active ? param.active = false : param.active = true;
    // 改变添加按钮的状态
    this.data.items.splice(index, 1, param);
    that.setData({
      items: this.data.items
    });
    // 将已经确定的菜单添加到购物单列表
    this.data.items.forEach(item => {
      if (item.active) {
        subOrders.push(item);
      }
    });
    // 判断底部提交菜单显示隐藏
    if (subOrders.length == 0) {
      that.setData({
        bottomFlag: false
      });
    } else {
      that.setData({
        bottomFlag: true
      });
    }
    let money = 0;
    let num = subOrders.length;
    subOrders.forEach(item => {
      money += item.price; // 总价格求和
    });
    let orderCount = {
      num,
      money
    }
    // 设置显示对应的总数和全部价钱
    this.setData({
      orderCount
    });
    // 将选中的商品存储在本地
    wx.setStorage({
      key: "orders",
      data: subOrders
    });
  },
  onReady: function () {
   
  },
  //{ "mess": JSON.stringify(mess) }
  onLoad: function() {
    this.loadMenuInfo(null,this);
  },
  // onShow: function () {
  //   // this.loadMenuInfo(null, this);
  //   this.loadMenuInfo(null, this);
  // },
  loadMenuInfo: function (select,that){
    var dd={};
    if(select!=null){
      dd["select"] = select;
    }
    comm.globalObj.requestGetHttps("/xcx/productType/datalist", dd, function (d) {
      if (d.statusCode == 1) {
        // console.log(d)
        var info=d.info;
        that.initProductTypeList(info, 0, that)
        // console.log(info)
      } else if (d.statusCode == -7) {
        //app.Tools.alert("提示", d.errorMessage, function () { })
        console.log(d.errorMessage)
      }
    })
  },
  /**
   * 菜品选择口味
   */
  selectTaste:function(e){
    var that = this;
    var p = this.getProductfromData(e.target.dataset.id, this);
    this.showtesteModal(p);
  },
  //选中某个口味
  chosetaste: function (e) {
    console.log(e)
    var that = this;
    this.setData({
      selected:true,
      chosetaste: e.detail.value,
    });
    // console.log(e.target.dataset.key)
  },
  //显示口味选择弹出框
  showtesteModal: function (p) {
    this.setData({
      tasteList: p.tastes,
      tasteModal: true, // 显示modal弹窗
      choseproduct:p
    });
  },
  //将选中的产品添加到购物车
  addProductToCar: function (s) {
    console.log(s)
    var shoppingcar=wx.getStorageSync("shoppingcar");
    // console.log(shoppingcar)
    if (shoppingcar.length==0){//购物车为空
        var shoppingcar = [];
        shoppingcar.push(s);
        wx.setStorageSync('shoppingcar', shoppingcar);
    } else {//第一次添加购物车
      this.judgeAndAddCar(s, shoppingcar);
    }
        // that.setData({
        //   items: res.data
        // });
        // // 价格统计汇总
        // let money = 0;
        // let num = res.data.length;
        // res.data.forEach(item => {
        //   money += (item.price * item.num); // 总价格求和
        // });
        // let orderCount = {
        //   num,
        //   money
        // }
        // // 设置显示对应的总数和全部价钱
        // that.setData({
        //   orderCount
        // });
        // var shoppingcar=[];
        // shoppingcar.push(s);
        // wx.setStorageSync({
        //   key: "shoppingcar",
        //   data: shoppingcar,
        // })
  },
  //判断购物车里是否有同种产品
  judgeAndAddCar: function (s,shoppingcar) {
    var res=false;
    for (var i = 0; i < shoppingcar.length;i++){
        var sci=shoppingcar[i];
        if (s.productStr == sci.productStr){
          sci["count"] = sci["count"] + s["count"]
          sci["childAmount"] = sci["count"] * sci["product"]["price"]
            res=true;
            shoppingcar[i]=sci;
            // console.log(s)
        }
    };
    shoppingcar.push(s)
    // console.log(!res)
    if (!res){
      // shoppingcar.push(s)
    }
    // console.log(shoppingcar)
    wx.setStorageSync('shoppingcar', shoppingcar);
  },

  //加号动作
  addCount: function (e) {
    this.setData({
      selectCount: this.data.selectCount+1, // 显示modal弹窗
    });
  }, 
  //减号动作
  delCount: function (e) {
    var selectCount=this.data.selectCount;
    if (selectCount>0){
      this.setData({
        selectCount: this.data.selectCount - 1, // 显示modal弹窗
      });
    }
  },
  //口味选择弹出框取消动作
  modalCancel: function () {
    this.setData({
      tasteModal: false, // 显示modal弹窗
      selectCount: 0,
      chosetaste: "",
      choseproduct: {},
      tasteList: [],
    });
  },
  //口味选择弹出框确定动作
  modalConfirm: function () {
    var that=this;
    var p = that.data.choseproduct;
    var chosetaste={};
    chosetaste["tasteKey"] = that.data.chosetaste;
    var selectCount = that.data.selectCount;
    //将选择产品设置到购物车中
    if (tools.notNull(chosetaste["tasteKey"]) && selectCount!=0){
      // console.log(chosetaste)
      var select = {};
      select["tastes"] = chosetaste ;//选择某种口味
      select["product"] = p;
      select["productStr"] = p.title+":"+ chosetaste.tasteKey;
      select["count"] = selectCount;
      select["childAmount"] = p.price * select["count"];
      // console.log(select)
      that.addProductToCar(select)
    }
    that.setData({
      tasteModal: false, // 显示modal弹窗
      selectCount: 0,
      chosetaste: "",
      choseproduct: {},
      tasteList: [],
    });

  },

  //初始化产品类型列表
  initProductTypeList: function (d,selectIndex,that){
      var menus=[];
      for(var i=0;i<d.length;i++){
        if(0==i){
          that.initProductList(d[i].products, that)
        }
        menus[i] = d[i];
      }
    that.setData({
        menus: menus
      });
  },
  //初始化菜品列表
  initProductList: function (d, that) {
    // console.log(d)
    var items = [];
    items = d
    that.setData({
      items: items
    });
  },
  //初始化菜品列表
  getProductfromData: function (id, that) {
    var items=that.data.items;
    for (var i = 0; i < items.length;i++){
      if (items[i].id == id){
        return items[i];
      }
    }
   
  },
})