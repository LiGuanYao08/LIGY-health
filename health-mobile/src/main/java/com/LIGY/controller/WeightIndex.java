package com.LIGY.controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightIndex extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel titleLabel;
    private JPanel contentPanel;
    private JButton submitButton;
    private ButtonGroup bg;
    private JPanel sexPanel;
    private JRadioButton ChinaRadio;
    private JRadioButton AsiaRadio;
    private JRadioButton WHORadio;
    private JPanel whPanel;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JTextField heightText;
    private JTextField weightText;
    private JPanel consolePanel;
    private JLabel consoleLabel;
    private JTextField consoleText;

    /**
     * 体重
     */
    private double weight;
    /**
     * 身高
     */
    private double height;
    /**
     * BMI指数
     */
    private double BMI;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WeightIndex frame = new WeightIndex();
                frame.pack();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public WeightIndex() {
        setTitle("身高体重指数计算器v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        //主容器
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        //标题，主容器北
        titleLabel = new JLabel("身高体重指数计算器");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);
        //存放选项的panel，主容器中
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPane.add(contentPanel, BorderLayout.CENTER);
        //提交按钮，主容器南
        submitButton = new JButton("计算");
        contentPane.add(submitButton, BorderLayout.SOUTH);

        //存放性别选择的panel，选项北
        bg = new ButtonGroup();
        sexPanel = new JPanel();
        sexPanel.setLayout(new FlowLayout());
        contentPanel.add(sexPanel, BorderLayout.NORTH);
        ChinaRadio = new JRadioButton("中国标准");
        ChinaRadio.setSelected(true);
        AsiaRadio = new JRadioButton("亚洲标准");
        WHORadio = new JRadioButton("WHO（世界卫生组织）标准");
        bg.add(ChinaRadio);
        bg.add(AsiaRadio);
        bg.add(WHORadio);
        sexPanel.add(ChinaRadio);
        sexPanel.add(AsiaRadio);
        sexPanel.add(WHORadio);
        //存放身高体重的panel，选项中
        whPanel = new JPanel();
        whPanel.setLayout(new FlowLayout());
        contentPanel.add(whPanel, BorderLayout.CENTER);
        heightLabel = new JLabel("身高（米/m）：");
        weightLabel = new JLabel("体重（千克/kg）：");
        heightText = new JTextField(10);
        heightText.setToolTipText("请输入身高");
        weightText = new JTextField(10);
        weightText.setToolTipText("请输入体重");
        whPanel.add(heightLabel);
        whPanel.add(heightText);
        whPanel.add(weightLabel);
        whPanel.add(weightText);
        //结果
        consolePanel = new JPanel();
        consolePanel.setLayout(new FlowLayout());
        consoleLabel = new JLabel("你的身体质量指数为：");
        consoleText = new JTextField(28);
        consoleText.setEditable(false);
        consolePanel.add(consoleLabel);
        consolePanel.add(consoleText);
        contentPanel.add(consolePanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            String hstr = heightText.getText();
            String wstr = weightText.getText();

            Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
            Matcher hisNum = pattern.matcher(hstr);
            Matcher wisNum = pattern.matcher(wstr);
            boolean acc = true;
            if (!hisNum.matches() || !wisNum.matches()) {
                acc = false;
            }
            if (acc) {
                height = Double.parseDouble(hstr);
                weight = Double.parseDouble(wstr);
                BMI = weight / (height * height);
                DecimalFormat df = new DecimalFormat("#.0");
                String out = "";
                if (ChinaRadio.isSelected()) {
                    if (BMI < 18.5) {
                        out = "偏瘦，瘦骨嶙峋的，多吃点吧！";
                    } else if (BMI < 23.9) {
                        out = "正常，棒棒哒！";
                    } else if (BMI < 28) {
                        out = "偏胖，你胖你就运动减肥！";
                    } else if (BMI >= 28) {
                        out = "肥胖，现在减肥来的及！";
                    } else {
                        out = "外星人派来的你哦，请重新输入！";
                    }
                } else if (AsiaRadio.isSelected()) {
                    if (BMI < 18.5) {
                        out = "偏瘦，瘦骨嶙峋的，多吃点吧！";
                    } else if (BMI < 22.9) {
                        out = "正常，棒棒哒！";
                    } else if (BMI < 24.9) {
                        out = "偏胖，你胖你就运动减肥！";
                    } else if (BMI < 30) {
                        out = "肥胖，现在减肥来的及！";
                    } else if (BMI >= 30) {
                        out = "重度肥胖，不是一般人，赶紧减肥吧！";
                    } else {
                        out = "外星人派来的你哦，请重新输入！";
                    }
                } else {
                    if (BMI < 18.5) {
                        out = "偏瘦，瘦骨嶙峋的，多吃点吧！";
                    } else if (BMI < 24.9) {
                        out = "正常，棒棒哒！";
                    } else if (BMI < 29.9) {
                        out = "偏胖，你胖你就运动减肥！";
                    } else if (BMI < 34.9) {
                        out = "肥胖，现在减肥来的及！";
                    } else if (BMI < 39.9) {
                        out = "重度肥胖，不是一般人，赶紧减肥吧！";
                    } else if (BMI >= 40) {
                        out = "极重度肥胖,那可能得去医院看看咯！";
                    } else {
                        out = "外星人派来的你哦，请重新输入！";
                    }
                }

                consoleText.setText("你的指数为：" + df.format(BMI) + "，你的健康状况为：" + out);
            }

        });
    }

}

