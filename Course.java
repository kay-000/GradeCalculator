import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

//public class Main {
     public class Course {
        // Instance Variables;
        ArrayList<ArrayList<Double>> assignmentTypes;
        // a list that holds different type of assignments (represented as lists) (e.g HWs, Exam 1, Final Exam, etc).
        // This is a 2d list. For example, HW would be a list containing all of your hw grades.
        ArrayList<Double> assignmentWeights; // holds the corresponding weight of the assignment type
                                             // (i.e HW is 10% of your grade)

        // Constructor
        public Course(ArrayList<ArrayList<Double>> assignmentTypes, ArrayList<Double> assignmentWeights) {
            this.assignmentTypes = assignmentTypes;
            this.assignmentWeights = assignmentWeights;
        }
        
        //precondition: assignmentTypes.length == assignmentWeights.length
        public double gradeCalculation(ArrayList<ArrayList<Double>> assignmentTypes, ArrayList<Double> assignmentWeights)
        {
            double totalGrade = 0;
            double avgGrade;
            double sum = 0;
            double individualGrade = 0;
            double weight = 0;

            for (int i = 0; i < assignmentTypes.size(); i++) {
                ArrayList<Double> assignmentType = assignmentTypes.get(i);
                System.out.println("current assignment type: " + assignmentType);
                weight = assignmentWeights.get(i);

                for (int j = 0; j < assignmentType.size(); j++) {
                    individualGrade = assignmentType.get(j);
                    sum += weight;
                    totalGrade += (individualGrade * weight);

                }
            }
            avgGrade = totalGrade/sum;
            return avgGrade;
        }

    //}
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // for integers
        Scanner scan2 = new Scanner(System.in); // for strings

        ArrayList<ArrayList<Double>> assignmentTypes = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> grades = new ArrayList<Double>();
        ArrayList<Double> weights = new ArrayList<Double>();

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\t\tWelcome To The Grade Calculator.");
        System.out.println("------------------------------------------------------------");
        System.out.println("\nThis Grade Calculator Program allows you to calculate the current grade you have in a " +
                           "given course\n"
                         + "\n------------------------------------------------------------"
                         + "\nHow to use this Program:\n1.Enter the name of the course\n2.Enter Assignment Type."
                         + " Here Assignment Type is defined as the components that make up your final grade in the " +
                           "course (i.e. Homework, Exam 1, Exam 2, Final Exam)."
                         + "\n3.Enter the weight of the corresponding Assignment Type. Give this as a percentage."
                         + "(i.e. Homework is 40%, Exam 1 is 15%, Exam 2 is 15%, Final Exam is 30%)"
                         + "\n4.Continually enter your grade/grades for the given Assignment Type."
                         + "\n5.Once finished, the program will return your current grade in the class. "
                         + "\n6.(Optional) You may enter another course to see your current grade in it."
                         + "\n------------------------------------------------------------");

            int flag = 0;
            int flag2 = 0;
            int flag3 = 0;

            while(flag != -1) {
                System.out.print("\nPlease enter course name:  ");
                String courseName = scan2.nextLine();

                while (flag2 != -1) {
                    int count = 1;

                    System.out.print("\nPlease enter Assignment Type:  ");
                    String assignmentType = scan2.nextLine();

                    while (true) {
                        System.out.print("\nPlease enter weight of " + assignmentType + ":  ");

                        try {
                            String s = scan2.nextLine();
                            double weight = Double.parseDouble(s);
                            weights.add(weight); //add the weight to the list.
                            break;

                        } catch (Exception e) {
                            System.out.println("Not a valid response. Try Again.");
                        }
                    }

                    while (flag3 != -1) {
                        System.out.print("\nEnter grade " + count + ".  ");

                        try {
                            String s = scan2.nextLine();
                            double grade = Double.parseDouble(s);
                            grades.add(grade);


                        } catch (Exception e) {
                            System.out.println("Not a valid response. Try Again.");
                            continue;
                        }

                        while (true) {
                            System.out.print("Press 'y' to continue entering grades. Press 'n' to stop. ");
                            String answer = scan2.nextLine();

                            if (answer.equals("n"))
                            {
                                //once done entering grades, copy the grades into a new list.
                                ArrayList<Double> gradesCopy = new ArrayList<Double>();
                                for (double item : grades)
                                {
                                    gradesCopy.add(item);
                                }

                                // add the list of grades to assignment types list.
                                assignmentTypes.add(gradesCopy);
                                System.out.println("\n" + assignmentType + ": " + grades);
                                grades.clear(); //clear the grades list for the next assignment type to be entered.
                                flag3 = -1;
                                break;
                            }
                            else if (answer.equals("y"))
                            {
                                count++;
                                break;
                            } else
                            {
                                System.out.println("Not a valid response. Try Again.");
                            }
                        }
                    }

                    while (true) {
                        System.out.print("\nPress 'y' to continue entering assignment types. Press 'n' to stop. ");
                        String response = scan2.nextLine();

                        if (response.equals("n")) {
                            flag2 = -1;
                            break;
                        } else if (response.equals("y")) {
                            flag3 = 0;
                            break;
                        } else {
                            System.out.println("Not a valid response. Try Again.");
                        }
                    }
                }
                System.out.println("grades again: " + grades);
                System.out.println("assignmentTypes: " + assignmentTypes);

                //now you can calculate the course grade.
                Course c = new Course(assignmentTypes, weights);
                double overallGrade = c.gradeCalculation(assignmentTypes, weights);
                System.out.println("\n------------------------------------------------------------");
                System.out.println("\t\tYour grade in " + courseName + " is: " + overallGrade);
                System.out.println("------------------------------------------------------------");

                while(true) {
                    System.out.print("\nWould you like to see your grade in another course?" +
                            "\nPress 'y' for yes. Press 'n' for no.");
                    String response = scan2.nextLine();

                    if (response.equals("y"))
                    {
                        //reset everything.
                        flag2 = 0;
                        flag3 = 0;
                        assignmentTypes.clear();
                        grades.clear();
                        weights.clear();
                        break;
                    }
                    else if (response.equals("n")) {
                        System.out.println("Goodbye!");
                        flag = -1;
                        break;
                    }
                    else
                    {
                        System.out.println("Not a valid response. Try Again.");
                    }
                }
            }


        }
}
