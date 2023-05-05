# WebChat_Meeting_Room_Appointment_SSM
基于微信小程序会议室预约系统后端SSM可升级SpringBoot毕业源码案例设计

## 开发技术：微信小程序客户端 + Java后台服务器端 + mysql数据库

  项目一共2个身份，用户和管理员。用户在小程序客户端绑定手机号登录后，可以首页查看新闻公告信息，可以查询查找会议室信息，每个会议室的单价都不一样，用户选择自己满意的会议室开始下单，选择要预约的日期及时段提交订单，后端订单处理业务会自动判断订单是否有时间冲突，如果没有冲突就计算订单总金额然后提交成功，用户可以查询自己的订单信息，发布留言及管理自己的留言，修改个人信息等。管理员登录后端后可以对所有信息进行管理，包括用户管理，会议室管理，预约订单管理，系统参数管理，留言回复管理，新闻公告管理等待。
## 实体ER属性:
用户: 用户名,登录密码,姓名,性别,出生日期,用户照片,联系电话,邮箱,家庭地址,注册时间,微信openid

会议室类型: 会议室类型id,会议室类型名称,会议室类型说明

会议室: 会议室id,会议室类型,会议室名称,会议室照片,容纳人数,会议室位置,会议室单价,详细介绍,发布时间

预约订单: 订单编号,下单用户,预约会议室,预定日期,预约时段,订单总金额,支付方式,支付账号,订单状态,下单时间,收货人,收货人电话,收货人地址,订单备注

支付方式: 支付方式id,支付方式名称

时段: 时段id,时段名称,商品数量

留言: 留言id,留言标题,留言内容,留言人,留言时间,管理回复,回复时间

新闻公告: 公告id,标题,公告内容,发布时间