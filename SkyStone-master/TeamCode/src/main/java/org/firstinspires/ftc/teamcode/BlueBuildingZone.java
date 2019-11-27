package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

//imports packages to make FTC programs easier
@Autonomous(name="Auto_BlueBuildingZone", group="Linear Opmode")
public class BlueBuildingZone extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;
    private Servo grabber = null;
    private DcMotor scisliftLeft = null;
    private DcMotor scisliftRight = null;


    /*//put custom methods here
    public void setZero()
    {
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void forward(int time)
    {
        leftDriveFront.setPower(1);
        leftDriveBack.setPower(1);
        rightDriveFront.setPower(-1);
        rightDriveBack.setPower(-1);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void backward(int time)
    {
        leftDriveFront.setPower(-1);
        leftDriveBack.setPower(-1);
        rightDriveFront.setPower(1);
        rightDriveBack.setPower(1);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    //positive is clockwise. negative is counterclockwise
    public void turn(int theta)
    {
        if (theta>0) {
            leftDriveFront.setPower(1);
            leftDriveBack.setPower(1);
            rightDriveFront.setPower(1);
            rightDriveBack.setPower(1);
        }
        else{
            leftDriveFront.setPower(-1);
            leftDriveBack.setPower(-1);
            rightDriveFront.setPower(-1);
            rightDriveBack.setPower(-1);
        }
        sleep(Math.abs(theta)/180 * 1000);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void strafeLeft (int time){
        leftDriveFront.setPower(-1);
        leftDriveBack.setPower(1);
        rightDriveFront.setPower(1);
        rightDriveBack.setPower(-1);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);

    }

    public void strafeRight (int time){
        leftDriveFront.setPower(1);
        leftDriveBack.setPower(-1);
        rightDriveFront.setPower(-1);
        rightDriveBack.setPower(1);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }*/

    double[][] directions = {
            {-1, 1, 1, -1},       /* up       */
            {1, -1, -1, 1},   /* down     */
            {-1, -1, 1, 1},     /* left     */
            {1, 1, -1, -1},     /* right    */
    };

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
    }


    @Override
    public void runOpMode()throws InterruptedException{

        motorFrontLeft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        motorFrontRight = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        motorBackLeft = hardwareMap.get(DcMotor.class, "leftBackDrive");
        motorBackRight = hardwareMap.get(DcMotor.class, "rightBackDrive");

        waitForStart();

        sleep(10000);

        move("forward", 2000);

        move("right", 2000);

        move("forward",2000);

        move("left", 2000);

        move("down", 2500);

        move("none",0);


        /*forward(2000); //drive towards foundation
        strafeLeft(2000); //move around
        forward(2000); //move around
        strafeRight(2000);
        backward(3000); //push
        strafeLeft(3000); //park*/
    }

}