# Mora Cafe Management System

A full-stack cafe management system built with Spring Boot and React.

## Features

- User authentication with JWT
- Dashboard with sales and order statistics
- Order management
- Menu management
- Responsive design for all devices

## Tech Stack

### Backend
- Spring Boot 3.3.2
- Spring Security with JWT
- Spring Data JPA
- MySQL Database
- Maven

### Frontend
- React with TypeScript
- Material-UI
- Redux Toolkit
- React Router
- Axios

## Prerequisites

- Java 17
- Node.js 16+
- MySQL 8.0+
- Maven
- npm or yarn

## Setup

### Backend Setup

1. Navigate to the root directory
2. Configure database connection in `src/main/resources/application.properties`
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The backend will start on `http://localhost:8081`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```
4. The frontend will start on `http://localhost:3000`

## API Endpoints

### Authentication
- POST `/api/auth/login` - Login
- POST `/api/auth/register` - Register new user

### Orders
- GET `/api/orders` - Get all orders
- GET `/api/orders/{id}` - Get order by ID
- POST `/api/orders` - Create new order
- PUT `/api/orders/{id}` - Update order
- DELETE `/api/orders/{id}` - Delete order

### Menu
- GET `/api/menu` - Get all menu items
- GET `/api/menu/{id}` - Get menu item by ID
- POST `/api/menu` - Add new menu item
- PUT `/api/menu/{id}` - Update menu item
- DELETE `/api/menu/{id}` - Delete menu item

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License. 