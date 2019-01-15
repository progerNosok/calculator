package ru.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCalculator extends JFrame
{
    private String firstNumber = "";
    private String command = "";
    private String secondNumber = "";
    private JPanel panel = new JPanel(new GridLayout(3, 5));
    private JTextArea display = new JTextArea();


    public FrameCalculator()
    {
        setTitle("Calculator");
//        setSize(800, 600);
        setBounds(300, 300, 300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        for(int i = 0; i < 10; i++)
        {
            addButton(i + "", new NumberListener());
        }
        addButton("+", new CommandListener());
        addButton("-", new CommandListener());
        addButton("*", new CommandListener());
        addButton("/", new CommandListener());

        addButton("C", new CListener());

        JButton start = new JButton("=");
        start.addActionListener(new ResultListener());

        add(panel, BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void addButton(String label, ActionListener e)
    {
        JButton button = new JButton(label);
        button.addActionListener(e);
        panel.add(button);
    }

    private class NumberListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(command.isEmpty())
            {
                firstNumber += e.getActionCommand();
                display.setText(firstNumber);
                System.out.println(firstNumber);
            }
            else
            {
                display.setText(null);
                secondNumber += e.getActionCommand();
                display.setText(secondNumber);
                System.out.println(secondNumber);
            }
        }
    }

    private class CommandListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            display.setText(null);
            command = e.getActionCommand();
            display.setText(command);
            System.out.println(command);
        }
    }

    private class CListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String temp = display.getText();

            if(temp.isEmpty()) return;

            temp = temp.substring(0, temp.length() - 1);
            firstNumber = temp;
            display.setText(firstNumber);

            System.out.println(display.getText());
        }
    }

    private class ResultListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String result = "";

            if(command.equals("+"))
            {
                result = plus() + "";
            }
            else if(command.equals("-"))
            {
                result = minus() + "";
            }
            else if(command.equals("*"))
            {
                result = multyply() + "";
            }
            else if(command.equals("/"))
            {
                result = divide() + "";
            }
            display.setText(result);
            setToZero();
            firstNumber = result;
        }

        private Double plus()
        {
            return Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
        }

        private Double minus()
        {
            return Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
        }

        private Double multyply()
        {
            return Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
        }

        private Double divide()
        {
            return Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        }

        private void setToZero()
        {
            firstNumber = "";
            secondNumber = "";
            command = "";
        }
    }
}
