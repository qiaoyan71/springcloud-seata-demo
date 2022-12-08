# springcloud-seata-demo

SpringCloud 下的 Seata 分布式事务， AT 模式、TCC 模式 和 SAGA 模式

## 场景说明

用户购买商品的业务逻辑。整个业务逻辑由3个微服务提供支持：

- 仓储服务：对给定的商品扣除仓储数量。
- 订单服务：根据采购需求创建订单。
- 帐户服务：从用户帐户中扣除余额。

### 架构图
![img.png](doc/images/01.png)

### SEATA 的分布式交易解决方案
![img.png](doc/images/02.png)

## 环境说明-版本
![img.png](doc/images/img.png)
```yaml
mysql: 8.0.30
seata: 1.5.2
nacos: 2.1.2
spring-cloud: 2.2.9.RELEASE
```

## 工程目录结构

```
springcloud-seata-demo
├── seata-common               // 公共
├── at-spring-cloud            // AT 模式工程示例
│  ├── at-account-service      // AT-账户服务
│  ├── at-business-service     // AT-业务服务
│  ├── at-order-service        // AT-订单服务
│  └── at-product-service      // AT-仓库服务
├── saga-spring-cloud          // SAGA 模式工程示例 
│  ├── saga-account-service    // SAGA-账户服务
│  ├── saga-business-service   // SAGA-业务服务
│  ├── saga-order-service      // SAGA-订单服务
│  └── saga-product-service    // SAGA-仓库服务
├── tcc-spring-cloud           // TCC 模式工程示例 
│  ├── tcc-account-service     // TCC-账户服务
│  ├── tcc-business-service    // TCC-业务服务
│  ├── tcc-order-service       // TCC-订单服务
│  └── tcc-product-service     // TCC-仓库服务
├── tcc-transfer               // TCC 模式转账工程示例
│  ├── sql                     // 转账示例 sql 脚本
│  ├── tcc-transfer-in         // 收钱方服务 
│  └── tcc-trnasfer-out        // 转账方服务
└── zsql                       // 订单库存示例 sql
```

### SAGA 模式下的流程图
![img.png](doc/images/03.png)