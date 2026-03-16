package com.asutosh5941;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // variables
    private static boolean loop_run = true;

    // main program
    public static void main(String[] args) {
        
        // array of students info
        ArrayList<StudentData> students_list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        
        clearScreen(false, scan);

        while(loop_run) {
            // basic interactive menu
            System.out.println("========================================");
            System.out.println("       Student Management System");
            System.out.println("========================================\n");
            System.out.println("1) View all students");
            System.out.println("2) Add a student");
            System.out.println("3) Search a student by ID");
            System.out.println("4) Delete a student");
            System.out.println("5) Exit\n");

            // take input
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch(InputMismatchException ime) {
                scan.nextLine();
                System.out.println("Invalid input !");
                clearScreen(true, scan);
                continue;
            }

            switch(choice) {
                case 1 -> printStudentList(students_list, scan);
                
                case 2 -> addStudent(scan, students_list);

                case 3 -> searchStudentByID(students_list, scan, false);

                case 4 -> searchStudentByID(students_list, scan, true);

                case 5 -> loop_run = false;
                
                default -> {
                    System.out.println("Invalid input !");
                    clearScreen(true, scan);
                }
            }
        }

        scan.close();
    }

    // this function clears screen
    protected static void clearScreen(boolean interactive, Scanner scan) {
        if(interactive) {
            System.out.print("\nPress any key to continue ....");
            scan.nextLine();
        }
        System.out.print("\033[H\033[2J");
    }

    // this function adds a new student
    private static void addStudent(Scanner scan, ArrayList<StudentData> student_list) {
        boolean invalid_input = true;

        clearScreen(false, scan);

        // name
        System.out.print("Enter student's name: ");
        String name = scan.nextLine();

        // id
        int id = 0;
        do {
            System.out.print("Enter id: ");
            try {
                id = scan.nextInt();
                scan.nextLine();
                invalid_input = false;
                for(StudentData data :  student_list) {
                    if(data.getId() == id) {
                        System.out.println("Student with ID " + id + " already exists !");
                        invalid_input = true;
                        break;
                    }
                }
            } catch(InputMismatchException ime) {
                scan.nextLine();
                System.out.println("Invalid input !");
            }
        } while(invalid_input);
        
        invalid_input = true;

        // percentage
        float percentage = 0;
        do {
            System.out.print("Enter percentage: ");
            try {
                percentage = scan.nextFloat();
                scan.nextLine();
                invalid_input = !(percentage >= 0) || !(percentage <= 100);
                if(invalid_input) {
                    System.out.println("Invalid input !");
                }
            } catch(InputMismatchException ime) {
                scan.nextLine();
                System.out.println("Invalid input !");
            }
        } while(invalid_input);

        StudentData student_info = new StudentData(name, id, percentage);

        student_list.add(student_info);

        clearScreen(false, scan);
    }

    // this function searches a student or deletes it
    private static void searchStudentByID(ArrayList<StudentData> list, Scanner scan, boolean delete) {
        clearScreen(false, scan);

        boolean invalid_input = true;

        int id = 0;

        do {
            try {
                System.out.print("Enter student ID: ");
                id = scan.nextInt();
                scan.nextLine();
                invalid_input = (id < 0);
                if(invalid_input) {
                    System.out.println("Invalid input !");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input !");
                scan.nextLine();
            }
        } while(invalid_input);

        for(StudentData student : list) {
            if(id == student.getId()) {
                if(delete) {
                    list.remove(student);
                    System.out.println("Student with ID " + id + " has been deleted!");
                } else {
                    System.out.println("\nName : " + student.getName() + "\nID : " + student.getId() + "\nPercentage: " + student.getPercentage());
                }
                clearScreen(true, scan);
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found !");

        clearScreen(true, scan);
    }

    // this function prints the list of students
    private static void printStudentList(ArrayList<StudentData> student_list, Scanner scan) {
        clearScreen(false, scan);
        if(student_list.isEmpty()) {
            System.out.println("The list is empty !");
            clearScreen(true, scan);
            return;
        }

        for(StudentData student : student_list) {
            System.out.println("==============================================================");
            System.out.println("\nName: " + student.getName());
            System.out.println("ID: " + student.getId());
            System.out.println("Percentage: " + student.getPercentage() + "\n");
        }

        System.out.println("==============================================================");
        clearScreen(true, scan);
    }
}
