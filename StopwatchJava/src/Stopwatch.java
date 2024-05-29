import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int secondsElapsed = 0;
    int minutesElapsed = 0;
    int hoursElapsed = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", secondsElapsed);
    String minutes_string = String.format("%02d", minutesElapsed);
    String hours_string = String.format("%02d", hoursElapsed);

    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hoursElapsed = (elapsedTime/3600000);
            minutesElapsed = (elapsedTime/60000) % 60;
            secondsElapsed = (elapsedTime/1000) % 60;

            setTimeLabel();

        }
    });


    Stopwatch(){

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            if (!started) {
                started = true;
                startButton.setText("Stop");
                start();
            } else {
                started = false;
                startButton.setText("Start");
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("Start");
            reset();
        }
    }

    void start(){

        timer.start();

    }

    void stop(){

        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = 0;
        secondsElapsed = 0;
        minutesElapsed = 0;
        hoursElapsed = 0;
        setTimeLabel();
    }

    void setTimeLabel(){
        seconds_string = String.format("%02d", secondsElapsed);
        minutes_string = String.format("%02d", minutesElapsed);
        hours_string = String.format("%02d", hoursElapsed);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

}
