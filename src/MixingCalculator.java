import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MixingCalculator extends JFrame {
    private JTextField diameterField;
    private JTextField heightField;
    private JTextField densityField;
    private JTextField viscosityField;
    private JTextField someValueField;
    private JButton calculateButton;

    public MixingCalculator() {
        setTitle("Расёт сопел");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 15, 15));

        JLabel diameterLabel = new JLabel("Диаметр бака (Ф) в метрах:");
        diameterField = new JTextField();
        JLabel heightLabel = new JLabel("Высота бака (H) в метрах:");
        heightField = new JTextField();
        JLabel densityLabel = new JLabel("Плотность жидкости (ρ) в кг/м3:");
        densityField = new JTextField();
        JLabel viscosityLabel = new JLabel("Вязкость жидкости(η) в :");
        viscosityField = new JTextField();
        JLabel someValue = new JLabel("Радиус покрытия (r) в м :");
        someValueField = new JTextField();

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        add(diameterLabel);
        add(diameterField);
        add(heightLabel);
        add(heightField);
        add(densityLabel);
        add(densityField);
        add(viscosityLabel);
        add(viscosityField);
        add(someValue);
        add(someValueField);
        add(new JLabel());
        add(calculateButton);

        pack();
        setVisible(true);
    }

    private void calculate() {
        double diameter = Double.parseDouble(diameterField.getText());
        double height = Double.parseDouble(heightField.getText());
        double density = Double.parseDouble(densityField.getText());
        double viscosity = Double.parseDouble(viscosityField.getText());
        double someValue = Double.parseDouble(someValueField.getText());

        double g = 9.8;
        double l = Math.PI * diameter;
        double nozzleDiameter = 0.02 * diameter / height;


        int nozzleCount = (int) Math.ceil(l / (0.03 * l + nozzleDiameter));

        double nozzleHeight1 = 0.01 * height;
        double nozzleHeight2 = 0.4 * nozzleHeight1;


        double nozzlePressure = density * g * height;

        double nozzleVelocity = Math.sqrt(2 * nozzlePressure / density);

        double desiredCoverageRadius = someValue;
        double nozzleAngle1 = Math.toDegrees(2 * Math.atan(desiredCoverageRadius / diameter));
        double nozzleAngle2 = Math.toDegrees(2 * Math.atan(desiredCoverageRadius / (height - nozzleHeight1)));

        double nozzleArea = Math.PI * Math.pow((nozzleDiameter / 2), 2);

        double nozzleFlowRate = nozzleCount * nozzleArea * nozzleVelocity;


        System.out.println(String.format("Количество сопел: %d", nozzleCount));
        System.out.println(String.format("Угол 1: %.4f  градусов, угол 2: %.4f градусов", nozzleAngle1, nozzleAngle2));
        System.out.println(String.format("Высота расположения сопла 1: %.2f метров, сопла 2: %.2f метров", nozzleHeight1, nozzleHeight2));
        System.out.println("Диаметр сопла: " + nozzleDiameter + " метров");
        System.out.println(String.format("Давление сопла: %.2f Па", nozzlePressure));
        System.out.println(String.format("Расход жидкости через сопла: %.8f м^3/с", nozzleFlowRate));

    }
}