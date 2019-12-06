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

/*
    Autonomous for when robot is on Blue side, closest to loading zone
    By Nathan Harris
    CyberNoles #15401
 */

@Autonomous(name="Autonomous_Blue_LoadingZone", group="Linear Opmode")
public class AutonomousBlueLoadingZone extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveFront = null;
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveFront = null;
    private DcMotor rightDriveBack = null;
    private Servo grabber = null;
    private DcMotor scislift = null;


     //put custom methods here
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
    }


    @Override
    public void runOpMode() {

        leftDriveFront = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftDriveBack  = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightDriveFront = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightDriveBack = hardwareMap.get(DcMotor.class, "rightBackDrive");
        //grabber = hardwareMap.get(Servo.class, "grabber");
        scislift = hardwareMap.get(DcMotor.class, "scissor_lift");


        waitForStart();

        /*
            instructions
         */

        leftDriveFront.setPower(1);
        sleep(1000);
        leftDriveFront.setPower(0);

        leftDriveBack.setPower(1);
        sleep(1000);
        leftDriveBack.setPower(0);

        rightDriveFront.setPower(-1);
        sleep(1000);
        rightDriveFront.setPower(0);
        
        rightDriveBack.setPower(-1);
        sleep(1000);
        rightDriveBack.setPower(0);

    }
}
