# 🛒 ShopSphere – Microservices E-Commerce System

ShopSphere is a **Spring Boot based Microservices E-Commerce application** designed to demonstrate how different business functionalities can be separated into independent services.

The project follows a **Microservices Architecture**, where each major business domain is implemented as an independent Spring Boot application.

The system contains services for:

- User Management
- Product Management
- Order Management
- Inventory Management
- Payment Processing
- Notification
- API Gateway

---

## 🚀 Architecture

ShopSphere follows a Microservices Architecture.

```text
                         ┌──────────────────────┐
                         │      API Gateway     │
                         │      Port: 8080      │
                         └──────────┬───────────┘
                                    │
             ┌──────────────────────┼──────────────────────┐
             │          │           │          │            │
             ▼          ▼           ▼          ▼            ▼
        ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌────────────┐
        │  User   │ │ Product │ │  Order  │ │ Payment │ │ Inventory  │
        │ Service │ │ Service │ │ Service │ │ Service │ │  Service   │
        │  8081   │ │  8082   │ │  8083   │ │  8084   │ │   8085     │
        └─────────┘ └─────────┘ └────┬────┘ └─────────┘ └────────────┘
                                     │
                                     ▼
                              ┌───────────────┐
                              │ Notification  │
                              │    Service    │
                              │     8086      │
                              └───────────────┘
