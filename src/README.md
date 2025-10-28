# 🏡 Chatop - API Backend (Spring Boot)

Ce projet correspond à la partie **backend** de l’application de location immobilière Chatop, à réaliser en Java avec **Spring Boot**.
Il a pour objectif de valider le troisième projet chez OpenClassrooms.
---

## 🚀 Objectif

Développer une API REST sécurisée permettant :
- L’**authentification** des utilisateurs (locataires & propriétaires)
- La gestion d’**annonces de location**
- L’**envoi de messages** entre utilisateurs
- La gestion du **profil utilisateur**

> **Toutes les routes liées aux annonces sont protégées : l’utilisateur doit être authentifié pour y accéder.**

---

## 📁 Ce que vous trouverez dans ce repo

- **Environnement Mockoon** (pour simuler l’API lors du développement front)
- **Collection Postman** (pour tester vos endpoints)
- **Schéma de base de données** (fichier `.sql`)

---

## ⚙️ Prérequis

- Java 17+
- Maven 3.6+
- Un SGBD (MySQL)

---

## 🛠️ Installation

1. **Clonez le projet**
    ```bash
    git clone https://github.com/votre-organisation/chatop-backend.git
    cd chatop-backend
    ```

2. **Créez la base de données**
    - Utilisez le schéma fourni (`db-schema.sql`)
    - Mettez à jour `src/main/resources/application.properties` avec vos identifiants SGBD

3. **Lancez l’application**
    ```bash
    mvn spring-boot:run
    ```
   L’API sera alors accessible sur [http://localhost:8080](http://localhost:8080).

---

## 🔒 Authentification

- L’API utilise un système d’**authentification JWT**.
- Endpoints principaux :
    - `POST /auth/register` : Inscription
    - `POST /auth/login` : Connexion (retourne un token JWT)
    - `POST /auth/me` : 
- Ajoutez le JWT dans l’en-tête `Authorization` pour toutes les autres requêtes sécurisées.

---

## 🧪 Tester l’API

- **Importez** la collection Postman (`postman_collection.json`) dans Postman
- **Testez** vos routes à l’adresse `http://localhost:8080`

---

## 📝 Documentation API (Swagger)

- La documentation interactive de l’API est disponible ici :
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---