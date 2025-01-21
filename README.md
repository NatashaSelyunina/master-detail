# Документация по проекту

## Описание
Приложение для управления документами и их спецификациями с реализацией связки Master-Detail:
- **Master** — список документов.
- **Detail** — список спецификаций для выбранного документа.

**Основные функции:**
- CRUD операции для документов и спецификаций.
- Автоматический пересчет суммы документа на основе спецификаций.
- Проверка уникальности номеров документов и спецификаций с записью ошибок в отдельную таблицу БД.

---

## Технологии
- **Frontend**: React, React Query, Axios
- **Backend**: Java 17, Spring Boot 3, Spring Data JPA
- **База данных**: PostgreSQL 16
- **Сборка**: Maven

---

## Инструкция по запуску

### 1. Требования
Перед началом убедитесь, что установлены:
- **Java JDK 17** ([скачать](https://adoptium.net/))
- **Maven 3.9+** ([инструкция](https://maven.apache.org/install.html))
- **Node.js 18+** и **npm** ([скачать](https://nodejs.org/))
- **PostgreSQL 16+** ([установка](https://www.postgresql.org/download/))
- **Git** ([установка](https://git-scm.com/))

---

### 2. Клонирование репозитория
```bash`
git clone (https://github.com/NatashaSelyunina/master-detail.git)
cd master-detail

---

### 3. Настройка базы данных
Создайте базу данных и пользователя:
```sql`
CREATE DATABASE master_detail;
CREATE USER app_user WITH PASSWORD 'secure_pass';
GRANT ALL PRIVILEGES ON DATABASE master_detail TO app_user.

Настройте права доступа в pg_hba.conf при необходимости.

---

### 4. Запуск бэкенда
Перейдите в директорию бэкенда и настройте подключение к БД:
```bash`
cd backend
nano src/main/resources/application.properties

Укажите параметры в application.properties:
```properties`
spring.datasource.url=jdbc:postgresql://localhost:5432/master_detail
spring.datasource.username=app_user
spring.datasource.password=secure_pass
spring.jpa.hibernate.ddl-auto=update

Соберите и запустите приложение:
```bash`
mvn clean package
java -jar target/backend-1.0.0.jar

### 5. Запуск фронтеда
В новом терминале перейдите в директорию фронтенда:
```bash`
cd frontend

Установите зависимости и запустите:
```bash`
npm install
npm run dev

### 6. Проверка работы
Бэкенд: доступен на http://localhost:8080/api
Фронтенд: откройте http://localhost:3000 в браузере
