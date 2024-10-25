/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task02_3;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author 20281
 */
public class ScoreManager {
    private static final String FILE_PATH = "resources/T02_score.txt";
    private static Map<String, Integer> scores = new HashMap<>();

    public static void main(String[] args) {
        loadScores();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("请输入学生姓名（输入 'x' 退出）：");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("x")) {
                break;
            }

            System.out.print("请输入 " + name + " 的考试分数：");
            int mark = Integer.parseInt(scanner.nextLine());

            // 检查记录是否存在
            if (!scores.containsKey(name)) {
                // 记录不存在，直接添加并保存
                scores.put(name, mark);
                System.out.println("记录已添加：" + name + " 的分数是 " + mark);
                appendScoreToFile(name, mark); // 将新记录追加到文件
            } else {
                // 记录存在，询问是否追加
                System.out.print("记录已存在，是否追加分数？（y/n）：");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    // 追加分数（这里简单起见，将新的分数与原有分数相加）
                    int newMark = scores.get(name) + mark; // 将新的分数与原有分数相加
                    scores.put(name, newMark);
                    System.out.println("记录已更新：" + name + " 的新分数是 " + newMark);
                    updateScoreInFile(name, newMark); // 更新文件中的分数
                } else {
                    System.out.println("未更改记录。");
                }
            }
        }

        scanner.close();
    }

    private static void loadScores() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int mark = Integer.parseInt(parts[1].trim());
                    scores.put(name, mark);
                }
            }
        } catch (IOException e) {
            System.out.println("读取分数文件时出错：" + e.getMessage());
        }
    }

    private static void appendScoreToFile(String name, int mark) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND)) {
            writer.write(name + ": " + mark);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("保存分数文件时出错：" + e.getMessage());
        }
    }

    private static void updateScoreInFile(String name, int mark) {
        try {
            // 读取现有记录
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
                for (String line : lines) {
                    if (line.startsWith(name + ":")) {
                        // 如果是当前学生的记录，更新分数
                        writer.write(name + ": " + mark);
                    } else {
                        // 其他记录保持不变
                        writer.write(line);
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("更新分数文件时出错：" + e.getMessage());
        }
    }
}
