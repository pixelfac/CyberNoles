package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Auto_Foundation", group="Linear Opmode")
public class FoundationAuto extends LinearOpMode {

    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;
    private Servo dragger;

    public void move(String direction, long time){
        if (!direction.equals("none")) {
            int d = 0;
            if (direction.equals("forward"))
                d = 0;
            else if (direction.equals("down"))
                d = 1;
            else if (direction.equals("left"))
                d = 2;
            else if (direction.equals("right"))
                d = 3;
            motorFrontLeft.setPower(directions[d][0]);
            motorFrontRight.setPower(directions[d][1]);
            motorBackLeft.setPower(directions[d][2]);
            motorBackRight.setPower(directions[d][3]);
        } else {
            motorFrontLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackLeft.setPower(0);
            motorBackRight.setPower(0);
        }
        sleep(time);
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public void turn(String direction, long time){
        if (!direction.equals("none")) {
            int d = 0;
            if (direction.equals("right"))
                d = 4;
            else if (direction.equals("left"))
                d = 5;
            motorFrontLeft.setPower(directions[d][0]);
            motorFrontRight.setPower(directions[d][1]);
            motorBackLeft.setPower(directions[d][2]);
            motorBackRight.setPower(directions[d][3]);
        } else {
            motorFrontLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackLeft.setPower(0);
            motorBackRight.setPower(0);
        }
        sleep(time);
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    double[][] directions = {
            {0.7, -0.7, 0.7, -0.7},   /* up     */
            {-0.7, 0.7, -0.7, 0.7},   /* down     */
            {-0.7, -0.7, 0.7, 0.7},   /* left     */
            {0.7, 0.7, -0.7, -0.7},   /* right     */
            {-0.7, -0.7, -0.7, -0.7}, /* turn right */
            {0.7, 0.7, 0.7, 0.7},     /* turn left */
    };

    public void runOpMode()
    {
        motorFrontLeft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        motorFrontRight = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        motorBackLeft = hardwareMap.get(DcMotor.class, "leftBackDrive");
        motorBackRight = hardwareMap.get(DcMotor.class, "rightBackDrive");
        dragger = hardwareMap.get(Servo.class, "dragger");

        waitForStart();

        move("left",2000);
        sleep(300);
        dragger.setPosition(0.25);
        sleep(300);
        turn("left", 250);
        sleep(300);
        move("right", 2000);
        sleep(300);



    }
}

