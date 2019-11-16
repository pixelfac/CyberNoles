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
@TeleOp(name = "TeleOpS2", group = "T3")
public class TeleOpS2 extends LinearOpMode {

    /* Here we tell the program that we have motors, but
     * we don't tell the program what the motors are. */
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;
    private DcMotor wheelBoiLeft;
    private DcMotor wheelBoiRight;
    private DcMotor rotateBoiLeft;
    private DcMotor rotateBoiRight;
    private DcMotor scislift;
    private CRServo grabber;

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
        rotateBoiLeft = hardwareMap.get(DcMotor.class, "rotateBoiLeft");
        rotateBoiRight = hardwareMap.get(DcMotor.class, "rotateBoiRight");

 	scislift = hardwareMap.get(DcMotor.class, "scislift");
        grabber = hardwareMap.get(CRServo.class, "grabber");

        /* This sets the maximum power of the motors to half of their
         * actual maximum power, so our robot doesn't go supa fast. */
        double maxPower = 0.5;

        /* Here we give the program instructions for each direction
         * of travel: up, down, left, and right. */
        double[][] directions = {
            {1, 1, 1, 1},       /* up       */
            {-1, -1, -1, -1},   /* down     */
            {-1, 1, 1, -1},     /* left     */
            {1, -1, -1, 1},     /* right    */
        };

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

	//when the scissorlift motor is set to zero, it will resist rotating
        //so as to hold the lift in an elevated position
        scislift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	int[] liftlvls= [0, 10, 30, 60, 90];
  	int count = 0;


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
                FLpower = (FLpower + rightStickX) / 2;
                FRpower = (FRpower - rightStickX) / 2;
                BLpower = (BLpower - rightStickX) / 2;
                BRpower = (BRpower + rightStickX) / 2;
            }

            /* Applies maximum power setting */
            FLpower *= maxPower;
            FRpower *= maxPower;
            BLpower *= maxPower;
            BRpower *= maxPower;

            /* Applies the power to motors */
            motorFrontLeft.setPower(FLpower);
            motorFrontRight.setPower(FRpower);
            motorBackLeft.setPower(BLpower);
            motorBackRight.setPower(BRpower);

	    //scissorlift Movement
            //have different levels of elevation depending on how many blocks are already stacked
	    //use left and right bumper to iterate between levels

        if (gamepad2.a) {
            wheelBoiLeft.setPower(1);
            wheelBoiRight.setPower(-1);
        }
        if (gamepad2.left_bumper) {
            rotateBoiLeft.setPower(0.5);
            rotateBoiRight.setPower(-0.5);
        } else if (gamepad2.left_bumper) {
            rotateBoiLeft.setPower(-0.5);
            rotateBoiRight.setPower(0.5);
        }
        /* Prevents the controller from being dead inside */
        idle();
    }
}

/*
leftx = drive
lefty = turn
rightx = strafe

d + t + s
d + t - s
d - t + s
d - t - s


leftx = turn
lefty = drive
rightx = strafe




*/
