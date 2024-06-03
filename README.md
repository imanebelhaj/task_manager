<h1 align="center">
<!--   <img src="https://yourlogo.png" alt="Tasky" width="200px">
  <br> -->
  Tasky
</h1>

<p align="center">
  Welcome to <b>Tasky</b> - Your ultimate task management tool designed to help you stay organized and boost your productivity. 🚀
</p>

## 🎨 Features

- **Task Management**: Create, organize, and prioritize your tasks effortlessly.
- **Subtasks**: Break down your tasks into smaller, manageable subtasks.
- **User Authentication**: Secure user registration, login, and logout functionalities using Spring Security.
- **Profile Management**: Update user profiles, change passwords, and manage account settings.
- **Responsive Design**: Seamless navigation and user experience across all devices.

## ⚙️ Tech Stack

- **Backend**: Spring Boot, Spring Security, Hibernate
- **Frontend**: HTML, CSS (Bootstrap), JavaScript
- **Database**: MySQL
- **Build Tool**: Maven

## 🚀 Getting Started

### Prerequisites

- Java 21.0.1
- Maven
- MySQL

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/imanebelhaj/tasky.git
    cd tasky
    ```

2. **Configure the database:**
    - Create a MySQL database named `todolistdb`.
    - Update the `src/main/resources/application.properties` file with your database credentials.

3. **Build the project:**
    ```bash
    mvn clean install
    ```

4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

5. **Open your browser and navigate to `http://localhost:8084`.**

## 💡 Usage

### User Registration

1. Open the registration page at `http://localhost:8084/users/signup`.
2. Fill in the required details and submit the form to create a new account.

### User Login

1. Open the login page at `http://localhost:8084/users/signin`.
2. Enter your username and password to log in.

### Task Management

1. Once logged in, you can create new tasks, edit existing tasks, and mark tasks as completed.
2. Use the subtask feature to break down tasks into smaller steps for better organization.

### Profile Management

1. Access your profile settings to update personal information, change your password, and manage account settings.

## 🤝 Contributing

We welcome contributions from the community. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request and describe your changes.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📧 Contact

If you have any questions, suggestions, or feedback, feel free to reach out to us at support@tasky.com.
