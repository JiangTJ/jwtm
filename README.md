# JWTM
Json Web Token Microservice    
![status](https://img.shields.io/badge/status-dev-blue.svg)
![complete](http://progressed.io/bar/1?title=done)    

## 准备做什么
在开发过程中，用户权限一直是重要的问题，尤其是微服务。Jwt提供了一种签名式的鉴权方案。计划基于Jwt，提供简单可扩展的微服务。

## 初步规划
1. 用户名作为用户的唯一主键
2. 用户名密码登录作为主登录方式
3. 扩展第三方（式）登录，如邮箱、OAuth2.0
4. 仅Json交互，保证轻量
5. 危险操作（修改密码等），多重身份认证
