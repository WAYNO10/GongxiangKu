/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author W.A.Y.N.O
 */
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * CustomScrollBarUI 类，负责美化滚动条外观
 */
public class CustomScrollBarUI extends BasicScrollBarUI {

    // 定义滚动条的颜色
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Color SCROLL_BAR_BACKGROUND = Color.LIGHT_GRAY; 
    private final Color SCROLL_BAR_THUMB = new Color(169, 169, 169); 

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = SCROLL_BAR_THUMB; // 滑块的颜色
        this.trackColor = SCROLL_BAR_BACKGROUND; // 滚动条轨道的颜色
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0)); // 设置按钮尺寸为 0
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // 绘制滚动条的轨道
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // 绘制滚动条的滑块
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10); // 使用圆角矩形
        g2.dispose();
    }
}
