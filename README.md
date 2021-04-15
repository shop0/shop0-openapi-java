## xshop-openapi-java
xshop open api java sdk client

## 如何使用
### 环境
命令:
1、通过 scheme 文件生成 java代码
mvn generate-sources

### 添加依赖
- maven
```xml
<dependency>
    <groupId>com.shop0</groupId>
    <artifactId>shop0-openapi-java</artifactId>
    <version>0.0.1</version>
</dependency>
```
- gradle
```bash
//这里使用的是2.12
compile group: 'com.shop0', name: 'shop0-openapi', version: '1.0.0'
```


### 使用
具体可以参考 examples，这是使用 Java 构建的示例项目