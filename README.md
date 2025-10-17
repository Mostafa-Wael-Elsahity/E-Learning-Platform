# Zakker — E-Learning Platform
This is the wiki for this project https://deepwiki.com/Mostafa-Wael-Elsahity/Zakker 

## Overview  
Zakker is a Spring Boot–based e-learning platform enabling course creation, video delivery, payments, and student engagement (reviews, comments, notes). :contentReference[oaicite:0]{index=0}

## Features  
- Course / section / lesson management with video hosting (BunnyCDN) :contentReference[oaicite:1]{index=1}  
- Payment enrollment via PayPal with coupon & refund support :contentReference[oaicite:2]{index=2}  
- Multi-method authentication (email/password + OAuth2) :contentReference[oaicite:3]{index=3}  
- Reviews, comments, replies, and personal notes for content interaction :contentReference[oaicite:4]{index=4}  

## Tech Stack  
- Spring Boot, Spring MVC, Spring Security  
- Spring Data JPA (Hibernate) + PostgreSQL :contentReference[oaicite:5]{index=5}  
- BunnyCDN for video streaming, Cloudinary for image storage :contentReference[oaicite:6]{index=6}  
- PayPal REST API, Gmail SMTP, OAuth2 (Google & GitHub) integrations :contentReference[oaicite:7]{index=7}  

## Architecture  
Layered architecture: Controller → Service → Repository → Domain.  
Transactional consistency managed via `@Transactional` on key service methods. :contentReference[oaicite:8]{index=8}  

## Setup & Running  
1. Clone the repository  
2. Configure `application.properties` (DB, PayPal, OAuth2, BunnyCDN, email) :contentReference[oaicite:9]{index=9}  
3. Build and run the app (via Maven or IDE)  
4. Access APIs and UI (e.g. via Spring MVC + Thymeleaf)  

---

*Built by Mostafa Wael Elsahity*  
