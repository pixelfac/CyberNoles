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


    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;
    private DcMotor wheelBoiLeft;
    private DcMotor wheelBoiRight;
    private DcMotor leftArm;
    private DcMotor rightArm;
    private CRServo rotateBoiLeft;
    private CRServo rotateBoiRight;
    private CRServo extendArmL;
    private CRServo extendArmR;
    private Servo dragger;
    private Servo locker;


    @Override
    public void runOpMode() throws InterruptedException {

        /* Here we tell the program what the motors actually are
         * in the real world, so that the program can manipulate them. */
        motorFrontLeft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        motorFrontRight = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        motorBackLeft = hardwareMap.get(DcMotor.class, "leftBackDrive");
        motorBackRight = hardwareMap.get(DcMotor.class, "rightBackDrive");
        wheelBoiLeft = hardwareMap.get(DcMotor.class, "wheelBoiLeft");
        wheelBoiRight = hardwareMap.get(DcMotor.class, "wheelBoiRight");
        rotateBoiLeft = hardwareMap.get(CRServo.class, "rotateBoiLeft");
        rotateBoiRight = hardwareMap.get(CRServo.class, "rotateBoiRight");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        dragger = hardwareMap.get(Servo.class, "dragger");
        locker = hardwareMap.get(Servo.class, "locker");
        extendArmL = hardwareMap.get(CRServo.class, "extendArmL");
        extendArmR = hardwareMap.get(CRServo.class, "extendArmR");


        double maxPower = 0.5;

        rightArm.setDirection(DcMotor.Direction.REVERSE);

        double[][] directions = {
                {-1, 1, -1, 1},   /* up     */
                {1, -1, 1, -1},   /* down     */
                {1, 1, -1, -1},   /* left     */
                {-1, -1, 1, 1},   /* right     */
        };

        //leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wheelBoiLeft.setPower(0);
        wheelBoiRight.setPower(0);
        rotateBoiLeft.setPower(0);
        rotateBoiRight.setPower(0);
        leftArm.setPower(0);
        rightArm.setPower(0);
        dragger.setPosition(0.75);
        locker.setPosition(0);
        extendArmL.setPower(0);
        extendArmR.setPower(0);
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

            //extendArmL.setPower(0.3);
            //extendArmR.setPower(0.3);

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
                FLpower = (FLpower - rightStickX) / 2;
                FRpower = (FRpower - rightStickX) / 2;
                BLpower = (BLpower - rightStickX) / 2;
                BRpower = (BRpower - rightStickX) / 2;
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

            if (gamepad1.a) {
                wheelBoiLeft.setPower(-1);
                wheelBoiRight.setPower(-1);
            }
            else if (gamepad1.b) {
                wheelBoiLeft.setPower(1);
                wheelBoiRight.setPower(1);
            }
            if (abs(gamepad2.left_stick_y) > 0.05) {
                leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                //pos += 20;

                /*leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                leftArm.setTargetPosition(leftArm.getCurrentPosition() + 10);
                rightArm.setTargetPosition(rightArm.getCurrentPosition() + 10);*/

                leftArm.setPower(gamepad2.left_stick_y/2);
                rightArm.setPower(gamepad2.left_stick_y/2);

                //leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            } else {
                leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //leftArm.setPower(1);
                //rightArm.setPower(1);
                leftArm.setTargetPosition(0);
                rightArm.setTargetPosition(0);
                leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if (gamepad1.left_bumper) {
                rotateBoiLeft.setPower(1);
                rotateBoiRight.setPower(1);
            }
            else if (gamepad1.right_bumper) {
                rotateBoiLeft.setPower(-1);
                rotateBoiRight.setPower(-1);
            }
            else if(!(gamepad1.left_bumper || gamepad1.right_bumper)){
                rotateBoiLeft.setPower(0);
                rotateBoiRight.setPower(0);
            }
            if(gamepad2.a){
                locker.setPosition(0.75);
            }
            else if(gamepad2.b){
                locker.setPosition(0);
            }
            if(gamepad2.x) {
                extendArmL.setPower(0.3);
                extendArmR.setPower(-0.3);
            }
            else if(gamepad2.y) {
                extendArmL.setPower(-0.3);
                extendArmR.setPower(0.3);
            }
            else if(!(gamepad2.x || gamepad2.y)) {
                extendArmL.setPower(0);
                extendArmR.setPower(0);
            }
            if (gamepad1.dpad_right){
                dragger.setPosition(0.75);
            }
            else if (gamepad1.dpad_left){
                dragger.setPosition(0.25);
            }
            else {
                wheelBoiLeft.setPower(0);
                wheelBoiRight.setPower(0);
                leftArm.setPower(0);
                rightArm.setPower(0);
            }
            /* Prevents the controller from being dead inside */
            idle();
        }
    }
}
