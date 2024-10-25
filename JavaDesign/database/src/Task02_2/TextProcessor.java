/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task02_2;

import java.io.*;
import java.nio.file.*;
import java.util.List;
/**
 *
 * @author 20281
 */
public class TextProcessor {
    public static void main(String[] args) {
        try {
            Path inputPath = Paths.get("resources/T02_input.txt");
            Path outputPath = Paths.get("resources/T02_output.txt");

            // Read lines from input file
            List<String> lines = Files.readAllLines(inputPath);
            try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                for (String line : lines) {
                    // Keep only letters, reverse the order, and convert to uppercase
                    StringBuilder filteredLine = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        if (Character.isLetter(c)) {
                            filteredLine.append(c);
                        }
                    }
                    // Reverse and convert to uppercase
                    String reversedLine = filteredLine.reverse().toString().toUpperCase();
                    writer.write(reversedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}