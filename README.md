# School App Pro 🏫

## 📌 Description

**School App Pro** is a web-based application for managing school information.  
It offers features such as:

- 📚 Course viewing  
- 📝 User registration  
- 👩‍🎓 Student profile display  
- 🗂️ Data management through a simple and intuitive web interface  

---

## 🏗️ Architecture

The architecture follows a **Service-Oriented Architecture (SOA)** approach, which promotes:

- 🔧 Better modularity  
- 🧩 Clear separation of concerns  
- 🔄 Independent services communicating via standardized **REST APIs**

This design makes the backend scalable, maintainable, and easy to extend as the system grows.

---

### 🔧 Technologies

| Layer          | Technology                               |
|--------------- |----------------------------------------  |
| **Backend**    | Java (Servlets & JSP)                    |
| **Frontend**   | HTML, JSP, CSS                           |
| **Build Tool** | Maven                                    |
| **Web Server** | Jetty (development), Tomcat (production) |
| **IDE**        | IntelliJ IDEA                            |

### 💡 Features

- Student and course management  
- Web-based interface using JSP pages  
- Client-server communication via Servlets  
- Modular architecture powered by Maven  
- Jetty support for local development
- User registration and authentication
- Course viewing and management
- Student profile display

### 🚀 Quick Start

1. Clone the repository:
---
bash
```
git clone https://github.com/grgks/school-app-pro.git
```
- cd school-app-pro

  ---
2. Prerequisites

Java 8+ installed
Maven installed
IntelliJ IDEA (recommended)

---

3. Build the Project
bash : mvn clean compile

---
4. Run the Application
bash Using Maven with Jetty : mvn jetty:run

- Or using Maven with Tomcat : mvn tomcat7:run

---
5. Access the Application
Open your browser and navigate to: ```http://localhost:8080```

---
🎯 Key Components

Backend:

- Servlets: Handle HTTP requests and responses
- JSP Pages: Dynamic web content generation
- Java Classes: Business logic and data models

Frontend:

- HTML/JSP: User interface templates
- CSS: Styling and layout
- Web Forms: User input handling

🔧 Configuration

- Web.xml: Servlet configuration and URL mappings
- Maven POM: Dependencies and build configuration
- Application Properties: Runtime configuration
  
---
🧪 Testing

bash:
- Run all tests :mvn test

-Run specific test class: mvn test -Dtest=TestClassName

---
📚 Usage Examples

Student Management:

- View student profiles
- Register new students
- Update student information

Course Management:

- Browse available courses
- View course details
- Manage course enrollment

> ## 🇬🇷 Ελληνική έκδοση :
> # School App Pro

Το **School App Pro** είναι μια web-based εφαρμογή διαχείρισης σχολικής πληροφορίας. Παρέχει λειτουργίες όπως προβολή μαθημάτων, εγγραφή χρηστών, εμφάνιση πληροφοριών φοιτητών και διαχείριση δεδομένων μέσω web interface.Η αρχιτεκτονική του School App Pro ακολουθεί τη Service-Oriented Architecture (SOA), η οποία επιτρέπει καλύτερη μοντελοποίηση και διαχωρισμό των υπηρεσιών. Το backend χωρίζεται σε ανεξάρτητες υπηρεσίες που επικοινωνούν μεταξύ τους μέσω τυποποιημένων διεπαφών (π.χ. REST APIs).

---

## 🔧 Τεχνολογίες

| Τμήμα         | Τεχνολογία                                |
|-------------- |----------------------------------         |
| **Backend**   | Java (Servlets & JSP)                     |
| **Frontend**  | HTML, JSP, CSS                            |
| **Build Tool**| Maven                                     |
| **Web Server**| Jetty (development), Tomcat (production)  |
| **IDE**       | IntelliJ IDEA                             |

---

## 💡 Λειτουργίες

- Διαχείριση φοιτητών και μαθημάτων
- Web-based interface με JSP σελίδες
- Επικοινωνία client-server μέσω Servlets
- Modular αρχιτεκτονική με Maven
- Υποστήριξη Jetty για τοπική ανάπτυξη

---


