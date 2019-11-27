package org.firstinspires.ftc.teamcode;

/* Here we import cool API modules, which are basically
 * large amounts of free code that isn't made by us,
 * meant to make our lives easier since we don't have to
 * program them ourselves. */
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;

/* This is the start of the program for our
 * cool TeleOp controller. */
@TeleOp(name = "DualFly", group = "T3")
public class dualflytet extends LinearOpMode {

    /* Here we tell the program that we have motors, but
     * we don't tell the program what the motors are. */

    private DcMotor wheelBoiLeft;
    private DcMotor wheelBoiRight;
    private DcMotor leftArm;
    private DcMotor rightArm;
    private CRServo rotateBoiLeft;
    private CRServo rotateBoiRight;
    private CRServo extendArm;
    private Servo dragger;
    private Servo locker;


    @Override
    public void runOpMode() throws InterruptedException {

        /* Here we tell the program what the motors actually are
         * in the real world, so that the program can manipulate them. */
        wheelBoiLeft = hardwareMap.get(DcMotor.class, "wheelBoiLeft");
        wheelBoiRight = hardwareMap.get(DcMotor.class, "wheelBoiRight");
        rotateBoiLeft = hardwareMap.get(CRServo.class, "rotateBoiLeft");
        rotateBoiRight = hardwareMap.get(CRServo.class, "rotateBoiRight");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        dragger = hardwareMap.get(Servo.class, "dragger");
        locker = hardwareMap.get(Servo.class, "locker");
        extendArm = hardwareMap.get(CRServo.class, "extendArm");

        rightArm.setDirection(DcMotor.Direction.REVERSE);


        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelBoiLeft.setPower(0);
        wheelBoiRight.setPower(0);
        rotateBoiLeft.setPower(0);
        rotateBoiRight.setPower(0);
        leftArm.setPower(0);
        rightArm.setPower(0);
        dragger.setPosition(0.55);
        locker.setPosition(0);
        extendArm.setPower(0);
        telemetry.addData(">", "" + dragger.getPosition());

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        /* Doesn't start input events until program intializes */
        waitForStart();

        /* This makes it so that the program only runs when it
         * is meant to. */

        double eigthturn = 1120/8;
        int pos = 0;

        while (opModeIsActive()) {
            if (gamepad1.a) {
                wheelBoiLeft.setPower(-1);
                wheelBoiRight.setPower(-1);
            }
            if (gamepad1.b) {
                wheelBoiLeft.setPower(1);
                wheelBoiRight.setPower(1);
            }
            if (gamepad1.dpad_up) {
                //pos += 20;

                //leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //leftArm.setTargetPosition(leftArm.getCurrentPosition() + pos);
                //rightArm.setTargetPosition(rightArm.getCurrentPosition() + pos);

                leftArm.setPower(0.45);
                rightArm.setPower(0.45);
            } else if (gamepad1.dpad_down) {
                //pos -= 20;

                //leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                //leftArm.setTargetPosition(leftArm.getCurrentPosition() + pos);
                //rightArm.setTargetPosition(rightArm.getCurrentPosition() + pos);

                leftArm.setPower(-0.45);
                rightArm.setPower(-0.45);
            } else {
                leftArm.setPower(0);
                rightArm.setPower(0);
            }

            if (gamepad1.left_bumper) {
                rotateBoiLeft.setPower(1);
                rotateBoiRight.setPower(-1);
            }
            if (gamepad1.right_bumper) {
                rotateBoiLeft.setPower(-1);
                rotateBoiRight.setPower(1);
            }
            if(gamepad2.a){
                locker.setPosition(0.75);
            }
            if(gamepad2.b){
                locker.setPosition(0);
            }
            if(gamepad2.x) {
                extendArm.setPower(0.45);
            }
            if(gamepad2.y) {
                extendArm.setPower(-0.45);
            }
            if (gamepad1.dpad_right && dragger.getPosition()+0.01 <= 1){
                dragger.setPosition(dragger.getPosition()+0.01);
            }
            if (gamepad1.dpad_left && dragger.getPosition()-0.01>=0){
                dragger.setPosition(dragger.getPosition()-0.01);
            }
            else {
                wheelBoiLeft.setPower(0);
                wheelBoiRight.setPower(0);
                rotateBoiLeft.setPower(0);
                rotateBoiRight.setPower(0);
                leftArm.setPower(0);
                rightArm.setPower(0);
                extendArm.setPower(0);
            }
            /* Prevents the controller from being dead inside */
            idle();
        }
    }
}