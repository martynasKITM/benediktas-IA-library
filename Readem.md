# JavaFX - Authors Management System

## Project Overview

This project is an educational example created for IB Computer Science students for their Internal Assessment (IA). The project demonstrates basic CRUD (Create, Read, Update, Delete) operations with a simple authors management system. The application allows users to perform operations such as registration, login, adding authors, viewing authors in a table, and editing or deleting author information.

## Objectives

- To provide a hands-on learning experience for students working on their IB Computer Science IA project.
- To implement a simple JavaFX-based application that interacts with a database.
- To teach students how to handle user authentication, data management, and basic user interfaces.

## Features

1. **User Authentication:**
    - Allows users to register and log in with their credentials.
    - Ensures secure user login with username and password.

2. **Author Management:**
    - Add new authors to the database.
    - View a list of all authors displayed in a table.
    - Edit existing author details (first name, last name, email, and city).
    - Delete authors from the database.

3. **User Interface:**
    - JavaFX-based interface for interacting with the application.
    - TableView to display authors.
    - Dialogs for adding and editing authors.

## Usage

1. **Registration:**
    - Users can register a new account by providing their username and password.

2. **Login:**
    - After registration, users can log in using their credentials.

3. **Author Operations:**
    - After logging in, users can:
        - Add new authors by entering the first name, last name, email, and city.
        - View all authors in a table.
        - Edit an author's details by double-clicking on their entry in the table.
        - Delete an author using the context menu.

## Changelog

### v1.0 - Initial Release
- Implemented user registration and login.
- Added functionality to add, edit, and delete authors.
- Created a table to display all authors.
- Provided basic error handling and user prompts for actions (e.g., confirmations for deletion).

### v1.1 - Improved UI & Bug Fixes
- Added better error handling for missing input fields during author creation.
- Improved confirmation dialogs for author deletion.
- Enhanced UI responsiveness and table refresh behavior.
- Fixed bugs related to the display of newly added authors without restarting the application.

### v1.2 - Database Connection Improvements
- Refined database connection management.
- Optimized query performance for loading authors.
- Added user authentication persistence across sessions.

### v1.3 - Final Touches
- Final bug fixes and optimization.
- Improved the user interface for a more seamless experience.
- Documentation update and added detailed comments to the code for better readability.

## How to Run the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/martynasKITM/benediktas-IA-library.git
   Load maven project in InteliJ and Build
