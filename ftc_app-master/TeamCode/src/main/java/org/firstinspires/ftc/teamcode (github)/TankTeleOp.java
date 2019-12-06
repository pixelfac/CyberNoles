package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TankTeleOp", group = "T3")
public class TankTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveFront = null;
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveFront = null;
    private DcMotor rightDriveBack = null;
    //private DcMotor linearActuator = null;
    //private CRServo sweepSpin = null;
    //private DcMotor sweepExtend = null;
    //private DcMotor sweepRotate = null;
    //private Servo markerServo = null;
    //private DcMotor leverArm = null;
    //private DcMotor extensionArm = null;
    //Servo markerServo = null;
    //CRServo spoolServo = null;
    //CRServo collectorServo = null;

    @Override
    public void runOpMode() throws InterruptedException {

        leftDriveFront = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftDriveBack = hardwareMap.get(DcMotor.class, "leftBackDrive");
       rightDriveFront = hardwareMap.get(DcMotor.class, "rightFrontDrive");
       rightDriveBack = hardwareMap.get(DcMotor.class, "rightBackDrive");
        //linearActuator = hardwareMap.get(DcMotor.class, "linearActuator");
        //sweepSpin = hardwareMap.get(CRServo.class, "sweepSpin");
        //sweepExtend = hardwareMap.get(DcMotor.class, "sweepExtend");
        //sweepRotate = hardwareMap.get(DcMotor.class, "sweepRotate");
        //markerServo = hardwareMap.get(Servo.class, "markerServo");

        //leverArm = hardwareMap.get(DcMotor.class, "arm_lever");
        //extensionArm = hardwareMap.get(DcMotor.class, "arm_extension");
        //markerServo = hardwareMap.get(Servo.class, "servo1");
        //spoolServo = hardwareMap.get(CRServo.class, "servo2");
        //collectorServo = hardwareMap.get(CRServo.class, "servo3");

        double leftFrontPower;
        double leftBackPower;
        double rightFrontPower;
        double rightBackPower;

        double drive;
        double turn;
        double strafe;

        double maxPower;

        final double thrust = 0.6;

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        waitForStart();

        //markerServo.setPosition(0);

        while (opModeIsActive()) {


            //Hook Up/Down
            if (gamepad1.y) {
                //linearActuator.setDirection(DcMotorSimple.Direction.REVERSE);
                //linearActuator.setPower(1);
            } else if (gamepad1.a) {
                //linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
                //linearActuator.setPower(1);
            } else
                //linearActuator.setPower(0);



            //markerServo position
            if (gamepad1.b)
                //90 degrees
                //markerServo.setPosition(.5);
            if (gamepad1.x)
                //0 degrees
                //markerServo.setPosition(0);



            //sweepExtend
            if (gamepad2.left_stick_y > 0.05){
                //sweepExtend.setDirection(DcMotorSimple.Direction.REVERSE);
                //sweepExtend.setPower(.6);
            }
            else if (gamepad2.left_stick_y < -0.05) {
                //sweepExtend.setDirection(DcMotorSimple.Direction.FORWARD);
                //sweepExtend.setPower(.6);
            }
            else{
                //sweepExtend.setPower(0);
            }


            //IF NOT WORKING, CHANGE gamepad2.right_stick_y to .6 for a constant, but jerky, power output...
            //sweepRotate
            if (gamepad2.right_stick_y > 0.05)
            {
                //sweepRotate.setDirection(DcMotorSimple.Direction.FORWARD);
                //sweepRotate.setPower(gamepad2.right_stick_y);
            }//
            else if (gamepad2.right_stick_y < -0.05)
            {
                //sweepRotate.setDirection(DcMotorSimple.Direction.REVERSE);
                //sweepRotate.setPower(gamepad2.right_stick_y);
            }
            else
            {
                //sweepRotate.setPower(0);
            }


            //sweepSpin
            if (gamepad2.right_bumper)
            {
                //sweepSpin.setDirection(CRServo.Direction.FORWARD);
                //sweepSpin.setPower(1);
            }
            else if (gamepad2.left_bumper)
            {
                //sweepSpin.setDirection(CRServo.Direction.REVERSE);
                //sweepSpin.setPower(1);
            }
            else
                //sweepSpin.setPower(0);




            drive = 0;
            if (Math.abs(gamepad1.left_stick_y) > 0.05){
                drive = gamepad1.left_stick_y;
            }
            else {
                drive = 0;
            }

            if (Math.abs(gamepad1.left_stick_x) > 0.05){
                turn = gamepad1.left_stick_x;
            }
            else {
                turn = 0;
            }

            if (Math.abs(gamepad1.right_stick_x) > 0.05){
                strafe = gamepad1.right_stick_x;
            }
            else {
                strafe = 0;
            }

            leftBackPower = drive + turn +  strafe;
            leftFrontPower = drive + turn - strafe;
            rightBackPower = drive - turn - strafe;
            rightFrontPower = drive - turn + strafe;

            double[] power = {leftBackPower, leftFrontPower, rightBackPower, rightFrontPower};
            maxPower = Double.MIN_VALUE;
            for (double pow : power){
                if (pow > maxPower){
                    maxPower = pow;
                }
            }

            if (Math.abs(maxPower) > 1){
                for (int i = 0; i < power.length; i++){
                    power[i] = power[i]/Math.abs(maxPower);
                }
            }

            leftDriveBack.setPower(power[0]);
            leftDriveFront.setPower(power[1]);
            rightDriveBack.setPower(power[2]);
            rightDriveFront.setPower(power[3]);
        }
    }
}
