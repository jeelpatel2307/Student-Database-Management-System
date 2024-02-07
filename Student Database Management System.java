import sqlite3

# Function to create a new database and table
def create_database():
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS students
                 (roll_number INTEGER PRIMARY KEY, name TEXT, age INTEGER, grade TEXT)''')
    conn.commit()
    conn.close()

# Function to add a new student to the database
def add_student(roll_number, name, age, grade):
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute("INSERT INTO students VALUES (?, ?, ?, ?)", (roll_number, name, age, grade))
    conn.commit()
    conn.close()

# Function to display all students in the database
def display_students():
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute("SELECT * FROM students")
    students = c.fetchall()
    for student in students:
        print(student)
    conn.close()

# Function to search for a student by roll number
def search_student(roll_number):
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute("SELECT * FROM students WHERE roll_number=?", (roll_number,))
    student = c.fetchone()
    if student:
        print(student)
    else:
        print("Student not found.")
    conn.close()

# Function to update student information
def update_student(roll_number, name, age, grade):
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute("UPDATE students SET name=?, age=?, grade=? WHERE roll_number=?", (name, age, grade, roll_number))
    conn.commit()
    conn.close()

# Function to delete a student from the database
def delete_student(roll_number):
    conn = sqlite3.connect('students.db')
    c = conn.cursor()
    c.execute("DELETE FROM students WHERE roll_number=?", (roll_number,))
    conn.commit()
    conn.close()

# Main function to interact with the user
def main():
    create_database()
    while True:
        print("\nStudent Database Management System")
        print("1. Add Student")
        print("2. Display All Students")
        print("3. Search Student")
        print("4. Update Student")
        print("5. Delete Student")
        print("6. Exit")

        choice = input("Enter your choice: ")

        if choice == '1':
            roll_number = int(input("Enter Roll Number: "))
            name = input("Enter Name: ")
            age = int(input("Enter Age: "))
            grade = input("Enter Grade: ")
            add_student(roll_number, name, age, grade)
            print("Student added successfully.")
        elif choice == '2':
            display_students()
        elif choice == '3':
            roll_number = int(input("Enter Roll Number to search: "))
            search_student(roll_number)
        elif choice == '4':
            roll_number = int(input("Enter Roll Number to update: "))
            name = input("Enter New Name: ")
            age = int(input("Enter New Age: "))
            grade = input("Enter New Grade: ")
            update_student(roll_number, name, age, grade)
            print("Student information updated successfully.")
        elif choice == '5':
            roll_number = int(input("Enter Roll Number to delete: "))
            delete_student(roll_number)
            print("Student deleted successfully.")
        elif choice == '6':
            print("Exiting...")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
