package org.firstinspires.ftc.teamcode;

/* Import the cool API modules */
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/* Program for controller */
@TeleOp(name = "TeleOp4", group = "T3")
public class TeleOpTutorial extends LinearOpMode {

    /* Declares motors */
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;

    @Override
    public void runOpMode() throws InterruptedException {

        /* Defines motors */
        motorFrontLeft = hardwareMap.doMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.doMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.doMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.doMotor.get("motorBackRight");

        /* Sets the max power of the motors */
        double maxPower = 0.5;

        /* Doesn't start input events until program intializes */
        waitForStart();

        /* Cool program */
        while (opModeIsActive()) {
            /* Inverts joystick inputs */
            double leftStickX = -gamepad1.left_stick_x;
            double leftStickY = -gamepad1.left_stick_y;
            double rightStickX = -gamepad1.right_stick_x;

            /* Placeholder power variables */
            double FLpower = 0.f;
            double FRpower = 0.f;
            double BLpower = 0.f;
            double BRpower = 0.f;

            /* Current inputs */
            boolean moving = false;
            boolean rotating = false;

            /* Cool tricks for circular stick input */
            if (leftStickY > 0) {
                if (leftStickX < 0) {
                    moving = true;
                    FLpower = leftStickY + leftStickX;
                    FRpower = leftStickY;
                    BLpower = leftStickY;
                    BRpower = leftStickY + leftStickX;
                } else if (leftStickX > 0) {
                    moving = true;
                    FLpower = leftStickY;
                    FRpower = leftStickY - leftStickX;
                    BLpower = leftStickY - leftStickX;
                    BRpower = leftStickY;
                }
            } else if (leftStickY < 0) {
                /* I can't do this this is making me lose brain cells */
            }

            if (abs(rightStickX) > 0.05) {
                rotating = true;
                FLpower = (FLpower + rightStickX) / (moving + rotating);
                FRpower = (FRpower - rightStickX) / (moving + rotating);
                BLpower = (BLpower - rightStickX) / (moving + rotating);
                BRpower = (BRpower + rightStickX) / (moving + rotating);
            }

            /* Applies maximum power setting */
            FLpower *= maxPower;
            FRpower *= maxPower;
            BLpower *= maxPower;
            BRpower *= maxPower;

            /* Applies power to motors */
            motorFrontLeft.setPower(FLpower);
            motorFrontRight.setPower(FRpower);
            motorBackLeft.setPower(BLpower);
            motorBackRight.setPower(BRpower);

            /* Prevents the controller from being dead inside */
            idle();
        }
    }
}