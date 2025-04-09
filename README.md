# 🛒 SpringBoot Mall 電商後端系統

這是一個使用 Spring Boot 開發的練習用簡易電商後端系統，提供使用者註冊、登入、商品管理、下單與查詢訂單等 RESTful API 功能。可作為學習 Spring Boot、RESTful 架構設計、電商系統流程的參考專案。

## 🚀 專案功能

- ✅ 使用者註冊與登入
- ✅ 商品 CRUD 管理
- ✅ 商品查詢（分類、關鍵字搜尋、排序、分頁）
- ✅ 建立訂單 / 查詢訂單紀錄
- ✅ 輸出統一 API 回應格式
- ✅ 整合 Swagger (OpenAPI) 文件
- ✅ 單元測試

## 🛠 技術棧

- Java 17
- Spring Boot 3.x
- Spring Web MVC
- Spring Validation (Jakarta)
- Spring Data JDBC / JDBCTemplate
- MySQL / H2 資料庫（正式/測試 環境）
- Maven 管理依賴
- Swagger (使用 springdoc-openapi-starter-webmvc-ui)
- SLF4J + Logback 紀錄日誌

## 📦 架構設計

com.kether.springbootmall │ ├── controller // REST API 控制器 ├── dto // 封裝傳輸資料（Request / Params） ├── model // 資料模型（資料庫對應） ├── service // 商業邏輯處理 ├── util // 分頁工具等 └── config // Swagger 等配置類


## ⚙️ 環境需求

- Java 17+
- Maven 3.8+
- MySQL 8
- Spring Boot 3.x

## TODO

-新增前端畫面<br>
-JWT驗證<br>
-導入Spring Security<br>
