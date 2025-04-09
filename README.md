# 🛒 SpringBoot Mall 電商後端系統

這是一個使用 Spring Boot 開發的簡易電商後端系統，提供使用者註冊、登入、商品管理、下單與查詢訂單等 RESTful API 功能。可作為學習 Spring Boot、RESTful 架構設計、電商系統流程的參考專案。

## 🚀 專案功能

- ✅ 使用者註冊與登入
- ✅ 商品 CRUD 管理
- ✅ 商品查詢（分類、關鍵字搜尋、排序、分頁）
- ✅ 建立訂單 / 查詢訂單紀錄
- ✅ 輸出統一 API 回應格式
- ✅ 整合 Swagger (OpenAPI) 文件

## 🛠 技術棧

- Java 17
- Spring Boot 3.x
- Spring Web MVC
- Spring Validation (Jakarta)
- Spring Data JDBC / JDBCTemplate
- MySQL / H2 資料庫（可切換）
- Maven 管理依賴
- Swagger (使用 springdoc-openapi-starter-webmvc-ui)
- SLF4J + Logback 紀錄日誌

## 📦 架構設計

com.kether.springbootmall │ ├── controller // REST API 控制器 ├── dto // 封裝傳輸資料（Request / Params） ├── model // 資料模型（資料庫對應） ├── service // 商業邏輯處理 ├── util // 分頁工具等 └── config // Swagger 等配置類

bash
複製
編輯

## ⚙️ 環境需求

- Java 17+
- Maven 3.8+
- MySQL 8（或使用內建 H2）

## 🧪 建置與執行方式

```bash
# Clone 專案
git clone https://github.com/Kether0228/springboot-mall.git
cd springboot-mall

# 修改 application.properties 連線資訊（預設為 MySQL）
# 或改用 application-h2.properties 開發測試

# 使用 Maven 編譯並執行
mvn spring-boot:run
🗂 Swagger API 文件
專案整合了 Swagger UI，可透過下列網址瀏覽 API 文件：

📘 http://localhost:8080/swagger-ui/index.html

🗄 資料庫初始化（MySQL）
請先建立資料庫（預設為 springboot_mall）並設定帳密於 application.properties：

sql
複製
編輯
CREATE DATABASE springboot_mall CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
啟動專案後將自動建立表格結構。

🧾 預設 API 路由一覽
功能	路由	方法
註冊	/user/register	POST
登入	/user/login	POST
商品列表查詢	/products	GET
單一商品查詢	/products/{productId}	GET
建立商品	/products	POST
修改商品	/products/{productId}	PUT
刪除商品	/products/{productId}	DELETE
建立訂單	/user/{userId}/orders	POST
查詢訂單	/user/{userId}/orders	GET
👤 作者
Kether（@Kether0228）

歡迎點星星⭐、Fork 或提 Issue！
