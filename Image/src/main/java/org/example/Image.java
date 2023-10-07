package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

import static java.nio.file.Files.createDirectories;

public class Image {
    public static BufferedImage readImageLocal(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            return new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        }
        return image;
    }

    public static BufferedImage readImageURL(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new URL(path));
        } catch (IOException e) {
            return new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        }
        return image;
    }

    public static void saveImage(BufferedImage image, String outputPath) throws IOException {
        String fileName = UUID.randomUUID() + ".jpeg";
        createDirectories(Paths.get(outputPath));
        File outputFile = new File(outputPath, fileName);
        ImageIO.write(image, "jpeg", outputFile);
    }

    public static String getFont() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Варианты стиля шрифта: TimesRoman, Arial, Courier");
        System.out.println("Введите стиль шрифта:");
        String fontStyle = scanner.nextLine();
        while ((!fontStyle.equals("Arial")) && (!fontStyle.equals("Courier")) && (!fontStyle.equals("TimesRoman"))){
            System.out.println("Неверная команда, введите стиль шрифта:");
            fontStyle = scanner.nextLine();
        }
        return fontStyle;
    }

    public static void addText(BufferedImage image, String imagePathOutput, String text, String fontStyle, int size, int position) throws IOException {
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font(fontStyle, Font.BOLD, size));
        graphics.setColor(Color.BLACK);
        Point coordinates = getCoordinates(image, position);
        graphics.drawString(text, coordinates.x, coordinates.y);
        graphics.dispose();
        saveImage(image, imagePathOutput);
    }

    private static Point getCoordinates(BufferedImage image, int position) {
        int width = image.getWidth();
        int height = image.getHeight();
        Point center = new Point(width / 2, height / 2);
        Point topLeft = new Point(10, 50);
        Point topRight = new Point(width - 50, 50);
        Point bottomLeft = new Point(50, height);
        Point bottomRight = new Point(width, height);
        Point defaultPoint = new Point(100, 100);
        return switch (position) {
            case 1 -> center;
            case 2 -> topLeft;
            case 3 -> topRight;
            case 4-> bottomLeft;
            case 5 -> bottomRight;
            default -> defaultPoint;
        };
    }

}
