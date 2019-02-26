package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleOp2", group = "T3")
public class TeleOpV2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveBack = null;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveFront = null;
    private DcMotor linearActuator = null;
    //private DcMotor rightDrive = null;
    //private DcMotor leverArm = null;
    //private DcMotor extensionArm = null;
    //Servo markerServo = null;
    //CRServo spoolServo = null;
    //CRServo collectorServo = null;

    @Override
    public void runOpMode() throws InterruptedException{

        leftDriveBack  = hardwareMap.get(DcMotor.class, "left_drive_back");
        leftDriveFront  = hardwareMap.get(DcMotor.class, "left_drive_front");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearActuator = hardwareMap.get(DcMotor.class, "linearActuator");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        //leverArm = hardwareMap.get(DcMotor.class, "arm_lever");
        //extensionArm = hardwareMap.get(DcMotor.class, "arm_extension");
        //markerServo = hardwareMap.get(Servo.class, "servo1");
        //spoolServo = hardwareMap.get(CRServo.class, "servo2");
        //collectorServo = hardwareMap.get(CRServo.class, "servo3");

     /*   double leftPower;
        double rightPower;
        double leverArmPower;
        double linearSlideExtension;
        double spoolServoPower;
        double markerServoPosition;

        final double DPAD_TURN_POWER = 0.3;
        final double DPAD_STRAIGHT_POWER = 1;*/

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        waitForStart();

        //markerServo.setPosition(0);

        while(opModeIsActive()){

            double leftBackPower = 0;
            double rightBackPower = 0;
            double leftFrontPower = 0;
            double rightFrontPower = 0;

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.left_stick_x;
/*
            // Tank drive/Straight Drive Section
            if (gamepad1.dpad_up){
                leftPower = DPAD_STRAIGHT_POWER;
                rightPower = DPAD_STRAIGHT_POWER;
            }
            else if (gamepad1.dpad_down){
                leftPower = -DPAD_STRAIGHT_POWER;
                rightPower = -DPAD_STRAIGHT_POWER;
            }
            else if (gamepad1.dpad_left){
                rightPower = DPAD_TURN_POWER;
                leftPower = -DPAD_TURN_POWER;
            }
            else if (gamepad1.dpad_right){
                rightPower = -DPAD_TURN_POWER;
                leftPower = DPAD_TURN_POWER;
            }
*/

 /*           if (Math.abs(gamepad1.left_stick_y) > 0.05){
                leftPower = gamepad1.left_stick_y;
            }
            else
                leftPower = 0;

            if (Math.abs(gamepad1.right_stick_y) > 0.05){
                rightPower = gamepad1.right_stick_y;
            }
            else
                rightPower = 0;*/




            // Lever Arm Power
/*
            if (gamepad1.right_trigger > 0.07){
                leverArmPower = Math.sqrt(gamepad1.right_trigger * 5) / 5;
            }
            else if (gamepad1.left_trigger > 0.07){
                leverArmPower = -Math.sqrt(gamepad1.left_trigger * 5) / 5;
            }
            else
                leverArmPower = 0;

            // Collector Servo

            if (gamepad1.right_bumper){
                spoolServoPower = 1;
            }
            else if (gamepad1.left_bumper){
                spoolServoPower = 0;
            }
            else{
                spoolServoPower = 0.5;
            }

            // Linear Slide Extension

            if (gamepad1.x){
                linearSlideExtension = 0.5;
            }
            else if (gamepad1.y){
                linearSlideExtension = -0.5;
            }
            else
                linearSlideExtension = 0;

            // Marker Servo

            if (gamepad1.y){
                //markerServo.setPosition(0);
                linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
                linearActuator.setPower(1);
            }
            else if (gamepad1.a) {
                //markerServo.setPosition(0.8);
                linearActuator.setDirection(DcMotorSimple.Direction.REVERSE);
                linearActuator.setPower(1);

            }
            else
                linearActuator.setPower(0);
*/

            leftBackPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightBackPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            leftFrontPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightFrontPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            leftDriveBack.setPower(leftBackPower);
            leftDriveFront.setPower(leftFrontPower);
            rightDriveBack.setPower(rightBackPower);
            rightDriveFront.setPower(rightFrontPower);
            //leverArm.setPower(leverArmPower);
            //spoolServo.setPower(spoolServoPower);
        }

    }
}
