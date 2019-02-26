package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleOp3", group = "T3")
public class TeleOpV3 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveFront = null;
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveFront = null;
    private DcMotor rightDriveBack = null;
    private DcMotor linearActuator = null;
    private CRServo sweepSpin = null;
    private DcMotor sweepExtend = null;
    private DcMotor sweepRotate = null;
    private Servo markerServo = null;
    //private DcMotor leverArm = null;
    //private DcMotor extensionArm = null;
    //Servo markerServo = null;
    //CRServo spoolServo = null;
    //CRServo collectorServo = null;

    @Override
    public void runOpMode() throws InterruptedException {

        leftDriveFront = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDriveBack = hardwareMap.get(DcMotor.class, "left_drive_back");
        leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        linearActuator = hardwareMap.get(DcMotor.class, "linearActuator");
        sweepSpin = hardwareMap.get(CRServo.class, "sweepSpin");
        sweepExtend = hardwareMap.get(DcMotor.class, "sweepExtend");
        sweepRotate = hardwareMap.get(DcMotor.class, "sweepRotate");
        markerServo = hardwareMap.get(Servo.class, "markerServo");

        //leverArm = hardwareMap.get(DcMotor.class, "arm_lever");
        //extensionArm = hardwareMap.get(DcMotor.class, "arm_extension");
        //markerServo = hardwareMap.get(Servo.class, "servo1");
        //spoolServo = hardwareMap.get(CRServo.class, "servo2");
        //collectorServo = hardwareMap.get(CRServo.class, "servo3");

        double leftFrontPower;
        double leftBackPower;
        double rightFrontPower;
        double rightBackPower;


        final double thrust = 0.6;

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        waitForStart();

        //markerServo.setPosition(0);

        while (opModeIsActive()) {

            //Hook Up/Down
            if (gamepad1.y) {
                linearActuator.setDirection(DcMotorSimple.Direction.REVERSE);
                linearActuator.setPower(1);
            } else if (gamepad1.a) {
                linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
                linearActuator.setPower(1);
            } else
                linearActuator.setPower(0);

            //markerServo position
            if (gamepad1.b)
                //90 degrees
                markerServo.setPosition(.5);
            if (gamepad1.x)
                //0 degrees
                markerServo.setPosition(0);


            /*left stick drive
            if (gamepad1.dpad_up) {
                leftFrontPower = Math.abs(gamepad1.left_stick_y);
                leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                leftBackPower = Math.abs(gamepad1.left_stick_y);
                leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
                rightFrontPower = Math.abs(gamepad1.left_stick_y);
                rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                rightBackPower = Math.abs(gamepad1.left_stick_y);
                rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);

            } else if (gamepad1.dpad_down) {
                leftFrontPower = Math.abs(gamepad1.left_stick_y);
                leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                leftBackPower = Math.abs(gamepad1.left_stick_y);
                leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
                rightFrontPower = Math.abs(gamepad1.left_stick_y);
                rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                rightBackPower = Math.abs(gamepad1.left_stick_y);
                rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);

            } else {
                leftFrontPower = 0;
                leftBackPower = 0;
                rightFrontPower = 0;
                rightBackPower = 0;
            }
*/


            //Dpad stick strafing
            if (gamepad1.dpad_right) {

                //negative direction
                leftFrontPower = thrust;
                leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                leftBackPower = thrust;
                leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
                rightFrontPower = thrust;
                rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                rightBackPower = thrust;
                rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);

            } else if (gamepad1.dpad_left) {

                leftFrontPower = thrust;
                leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                leftBackPower = thrust;
                leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
                rightFrontPower = thrust;
                rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                rightBackPower = thrust;
                rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
            }


            if (gamepad1.right_stick_y < -0.05) {
                leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
                rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
                rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
            } else if (gamepad1.right_stick_y > 0.05){
                leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
                rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
                rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
            }

                //right stick turn

                leftFrontPower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, 0, 1);
                leftBackPower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, 0, 1);
                rightFrontPower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, 0, 1);
                rightBackPower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, 0, 1);
                //rightBackPower = Range.clip(gamepad1.right_stick_y - gamepad1.right_stick_x, 0, 1);


                leftDriveFront.setPower(leftFrontPower);
                leftDriveBack.setPower(leftBackPower);
                rightDriveFront.setPower(rightFrontPower);
                rightDriveBack.setPower(rightBackPower);
                //rightDrive.setPower(rightPower);
                //leverArm.setPower(leverArmPower);
                //spoolServo.setPower(spoolServoPower);


            if (gamepad2.dpad_up){
                sweepExtend.setDirection(DcMotorSimple.Direction.REVERSE);
                sweepExtend.setPower(1);
            }
            else if (gamepad2.dpad_down){
                sweepExtend.setDirection(DcMotorSimple.Direction.FORWARD);
                sweepExtend.setPower(1);
            }
            else{
                sweepExtend.setPower(0);
            }

            if (gamepad2.right_stick_y>0.05){
                sweepRotate.setDirection(DcMotorSimple.Direction.FORWARD);
                sweepRotate.setPower(Math.sqrt(gamepad2.right_stick_y));
            }
            else if (gamepad2.right_stick_y<0.05){
                sweepRotate.setDirection(DcMotorSimple.Direction.REVERSE);
                sweepRotate.setPower(Math.sqrt(Math.abs(gamepad2.right_stick_y)));
            }
            else{
                sweepRotate.setPower(0);
            }

            if (gamepad2.y){
                sweepSpin.setPower(1);

            }
            else{
                sweepSpin.setPower(0);
            }

        }
    }
}
