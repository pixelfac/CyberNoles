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
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

/* This is the start of the program for our
 * cool TeleOp controller. */
@TeleOp(name = "Comp3S2", group = "T3")
public class Comp3S2 extends LinearOpMode {

    /* Here we tell the program that we have motors, but
     * we don't tell the program what the motors are. */

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;


    //extend the flywheels
    private CRServo extendWheelLeft;
    private CRServo extendWheelRight;
    //spin the flywheels
    private DcMotor rotateWheelLeft;
    private DcMotor rotateWheelRight;


    //grabs the block and lifts it respectively
    private CRServo blockGrab;
    private DcMotor blockLift;

    private Servo dragger;



    @Override
    public void runOpMode() throws InterruptedException {

        /* Here we tell the program what the motors actually are
         * in the real world, so that the program can manipulate them. */
        motorFrontLeft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        motorFrontRight = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        motorBackLeft = hardwareMap.get(DcMotor.class, "leftBackDrive");
        motorBackRight = hardwareMap.get(DcMotor.class, "rightBackDrive");

        rotateWheelLeft = hardwareMap.get(DcMotor.class, "rotateWheelLeft");
        rotateWheelRight = hardwareMap.get(DcMotor.class, "rotateWheelRight");
        extendWheelLeft = hardwareMap.get(CRServo.class, "extendWheelLeft");
        extendWheelRight = hardwareMap.get(CRServo.class, "extendWheelRight");

        blockGrab = hardwareMap.get(CRServo.class, "blockGrab");
        blockLift = hardwareMap.get(DcMotor.class, "blockLift");
        dragger = hardwareMap.get(Servo.class, "dragger");

        blockLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        double maxPower = 1;

        double[][] directions = {
                {-1, 1, -1, 1},   /* up     */
                {1, -1, 1, -1},   /* down     */
                {1, 1, -1, -1},   /* left     */
                {-1, -1, 1, 1},   /* right     */
        };

        rotateWheelLeft.setPower(0);
        rotateWheelRight.setPower(0);
        extendWheelLeft.setPower(0);
        extendWheelRight.setPower(0);

        double draggerPos = 0.0;
        double maxDragger = 1;
        double minDragger = 0.60;
        double debounce = runtime.seconds() + 0.0;

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        /* Doesn't start input events until program intializes */
        waitForStart();

        /* This makes it so that the program only runs when it
         * is meant to. */

        while (opModeIsActive()) {

            /* This inverts the joystick inputs,
             * since the joystick inputs are gay. */
            /* Up, down, left, and right on the joystick are
             * not static values. It's not just "joystick is up
             * or joystick isn't up", the joystick can be
             * halfway up, causing the value to be 0.5 (to represent half),
             * and making the robot move at half the speed. Also,
             * if the joystick is all the way to the right and you
             * move it upwards, the value for right will decrease
             * even if you don't move the joystick left, since the
             * joystick moves in a circular movement, not a square. */
            double leftStickX = -gamepad1.left_stick_x;
            double leftStickY = -gamepad1.left_stick_y;
            double rightStickX = -gamepad1.right_stick_x;

            /* These variables are placeholders for the values
             * we will set the power of the motors to. We will
             * use these are a medium for our calculations.*/
            double FLpower = 0.f;
            double FRpower = 0.f;
            double BLpower = 0.f;
            double BRpower = 0.f;

            double totalPower = abs(leftStickX) + abs(leftStickY);

            /* This only makes the code move the robot if the
             * LEFT joystick is being used. This section of the
             * code will be used for movement (strafing) of the
             * robot, using ONLY the LEFT joystick. */
            if (leftStickX != 0 || leftStickY != 0) {
                /* These are placeholders for the directions
                 * that are actually being inputted, which will
                 * be used for the instructions for the directions
                 * used in the beginning of the code. */
                int dir1 = 0;
                int dir2 = 0;

                /* This checks where the joystick is in an x and
                 * y location. This is used to assign values for
                 * the direction placeholders.*/
                if (leftStickX > 0) {
                    dir1 = 3;
                } else if (leftStickX < 0) {
                    dir1 = 2;
                }
                if (leftStickY > 0) {
                    dir2 = 1;
                } else if (leftStickY < 0) {
                    dir2 = 0;
                }

                /* Since movement is not static, as said earlier, the
                 * instructions for the directions that the joystick
                 * is at are applied to the motor's power by a certain
                 * amount, depending on how far the joystick is pressed.
                 * For example, pushing the joystick halfway up will
                 * only using the instructions for "up" at a half amount,
                 * resulting in the robot moving forward at half speed.*/
                FLpower += (directions[dir1][0] * totalPower * abs(leftStickX)) + (directions[dir2][0] * totalPower * abs(leftStickY));
                FRpower += (directions[dir1][1] * totalPower * abs(leftStickX)) + (directions[dir2][1] * totalPower * abs(leftStickY));
                BLpower += (directions[dir1][2] * totalPower * abs(leftStickX)) + (directions[dir2][2] * totalPower * abs(leftStickY));
                BRpower += (directions[dir1][3] * totalPower * abs(leftStickX)) + (directions[dir2][3] * totalPower * abs(leftStickY));
            }

            if (abs(rightStickX) > 0.05) {
                /* This code changes the power of each wheel by the same
                 * amount, depending on how far the RIGHT joystick is
                 * pressed, which will rotate the robot at a certain speed. */
                //{-1, 1, -1, 1},   /* up     */
                if (leftStickX != 0 || leftStickY != 0) {
                    FLpower = (FLpower - rightStickX) / 2;
                    FRpower = (FRpower - rightStickX) / 2;
                    BLpower = (BLpower - rightStickX) / 2;
                    BRpower = (BRpower - rightStickX) / 2;
                } else {
                    FLpower = -rightStickX;
                    FRpower = -rightStickX;
                    BLpower = -rightStickX;
                    BRpower = -rightStickX;
                }
            }

            /* Applies maximum power setting */
            FLpower *= maxPower;
            FRpower *= maxPower;
            BLpower *= maxPower;
            BRpower *= maxPower;

            /* Applies the power to motors */
            //telemetry.addData(">", String.valueOf(FLpower));
            motorFrontLeft.setPower(FLpower);
            motorFrontRight.setPower(FRpower);
            motorBackLeft.setPower(BLpower);
            motorBackRight.setPower(BRpower);


            //rotates the flywheels in tandem
            //Down rotates inwards, Up rotates outwards
            if (gamepad2.dpad_down) {
                rotateWheelLeft.setPower(-1);
                rotateWheelRight.setPower(-1);
            }
            else if (gamepad2.dpad_up) {
                rotateWheelLeft.setPower(1);
                rotateWheelRight.setPower(1);
            } else {
                rotateWheelLeft.setPower(0);
                rotateWheelRight.setPower(0);
            }

            //Left stick extends/retracts left flywheel
            if (abs(gamepad2.left_stick_y) > 0.05f) {
                extendWheelLeft.setPower(gamepad2.left_stick_y);
            }
            else {
                extendWheelLeft.setPower(0);
            }

            //Right stick extends/retracts right flywheel
            if (abs(gamepad2.right_stick_y) > 0.05f) {
                extendWheelRight.setPower(gamepad2.right_stick_y);
            }
            else {
                extendWheelRight.setPower(0);
            }

            //block lifter arm
            //a lifts, b lowers, defaults at brake
            if (gamepad2.a)
            {
                //reverts to normal functionality
                //blockLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                //turns on
                blockLift.setPower(0.5);
            }
            else if (gamepad2.b)
            {
                //reverts to normal functionality
                //blockLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                //turns on
                blockLift.setPower(-0.5);
            }
            else
            {
                //turns on encorder functionality
                //blockLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //sets current position as target
                //blockLift.setTargetPosition(0);
                //tries to stay at current position
                //blockLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                blockLift.setPower(0.0);
            }

            //block grabbing mechanism
            //x closes, y opens
            if (gamepad2.x) {
                blockGrab.setPower(1);
            }
            else if (gamepad2.y) {
                blockGrab.setPower(-1);
            }
            else {
                blockGrab.setPower(0);
            }
            /*if (debounce + 0.1 <= runtime.seconds()) {
                if (gamepad1.dpad_right && draggerPos < maxDragger)
                    draggerPos += 0.05;
                else if (gamepad1.dpad_left && draggerPos > minDragger)
                    draggerPos -= 0.05;
                if (draggerPos > maxDragger)
                    draggerPos = maxDragger;
                else if (draggerPos < minDragger)
                    draggerPos = minDragger;
                debounce = runtime.seconds() + 0.0;
                //dragger.setPosition(draggerPos);
            }*/
            if (gamepad1.dpad_up) {
               draggerPos = minDragger;
               dragger.setPosition(draggerPos);
            }
            if (gamepad1.dpad_down) {
                draggerPos = maxDragger;
                dragger.setPosition(draggerPos);
            }
            telemetry.addData(">", "draggerpos: " + draggerPos);



            //need to program functionality for dragger

            telemetry.update();
            /* Prevents the controller from being dead inside */
            idle();
        }
    }
}
