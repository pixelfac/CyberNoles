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
@Autonomous(name="Autonomous_BlueBuildingZone", group="Linear Opmode")
public class BlueBuildingZone {

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

        forward(2000); //drive towards foundation
        //set down clamps
        backward(2000); //move to zone
        //flip up clamps
        strafeRight(5000); //go to loading zone
        forward(2000); //move towards blocks
        //analyze and pick up correct skystone
        backward(2000); //return to wall
        strafeLeft(5000); //go back to foundation
        //deposit block
        strafeRight(2500); //park onto midzone
    }

}
