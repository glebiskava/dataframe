package com;

import java.io.IOException;

import com.Dataframe.CSVLoader;
import com.Dataframe.DataFrame;
import com.Dataframe.DataFrameBuilder;

public class Main {
    public static void main(String[] args) {
        DataFrame df1 = new DataFrameBuilder()
                .addStringColumn("Name", new String[] { "Alice", "Bob", "Charlie", "David" })
                .addIntColumn("Age", new Integer[] { 25, 30, 22, 35 })
                .addDoubleColumn("Salary", new Double[] { 50000.0, 60000.0, 45000.0, 70000.0 })
                .build();

        System.out.println("DataFrame 1:");
        df1.print();

        System.out.println("\nHead(2) of DataFrame 1:");
        df1.head(2);

        System.out.println("\nTail(2) of DataFrame 1:");
        df1.tail(2);

        System.out.println("\nSelected Rows (1, 3) of DataFrame 1:");
        df1.selectRows(1, 3).print();

        System.out.println("\nSelected Columns (Name, Salary) of DataFrame 1:");
        df1.selectColumns("Name", "Salary").print();

        System.out.println("\nFiltered DataFrame 1 (Age > 25):");
        df1.filter("Age > 25").print();

        System.out.println("\nMean Age: " + df1.mean("Age"));
        System.out.println("Max Salary: " + df1.max("Salary"));
        System.out.println("Min Salary: " + df1.min("Salary"));
        System.out.println("Sum Salary: " + df1.sum("Salary"));
        System.out.println("Count Age: " + df1.count("Age"));

        try {
            String csvContent = "Name,Age,Salary\n" +
                    "Alice,25,50000.0\n" +
                    "Bob,30,60000.0\n" +
                    "Charlie,22,45000.0\n" +
                    "David,35,70000.0\n" +
                    "Eve,null,55000.0\n" +
                    "Frank,40,null";

            java.nio.file.Files.write(java.nio.file.Paths.get("test.csv"), csvContent.getBytes());

            DataFrame df2 = CSVLoader.fromCSV("test.csv");
            System.out.println("\nDataFrame 2 (from CSV):");
            df2.print();

            System.out.println("\nFiltered DataFrame 2 (Age > 30):");
            df2.filter("Age > 30").print();

            System.out.println("\nFiltered DataFrame 2 (Name = Bob):");
            df2.filter("Name = Bob").print();

            System.out.println("\nMean Salary: " + df2.mean("Salary"));
            System.out.println("\nMean Age: " + df2.mean("Age"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
