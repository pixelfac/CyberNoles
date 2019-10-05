package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOpS2", group = "T3")
public class TeleOpS2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor scislift = null;
    private CRServo grabber = null;


    private double drive = 0;
    private double turn = 0;
    private double strafe = 0;


    private double limit (double power){
        if (power > 1)
            return 1;
        else
            return power;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");

        scislift = hardwareMap.get(DcMotor.class, "scislift");
        grabber = hardwareMap.get(CRServo.class, "grabber");

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        //when the scissorlift motor is set to zero, it will resist rotating
        //so as to hold the lift in an elevated position
        scislift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {

            //Drivetrain movement
            //left stick for drive and turn, right stick for strafe
            if (gamepad1.left_stick_x >= 0.5)
                turn = 1;
            else if (gamepad1.left_stick_x <= -0.5)
                turn = -1;
            else turn = 0;

            if (gamepad1.left_stick_y <= -0.5)
                drive = 1;
            else if (gamepad1.left_stick_y >= 0.5)
                drive = -1;
            else drive = 0;

            if (gamepad1.right_stick_x >= 0.5)
                strafe = 1;
            else if (gamepad1.right_stick_x <= -0.5)
                strafe = -1;
            else strafe = 0;



            leftFrontDrive.setPower(limit(drive + turn + strafe));
            leftBackDrive.setPower(limit(drive + turn - strafe));
            rightFrontDrive.setPower(limit(- drive + turn + strafe));
            rightBackDrive.setPower(limit( - drive + turn - strafe));


            //scissorlift Movement
            //DPAD up and down to raise and lower the lift
            if (gamepad2.dpad_up)
                scislift.setPower(0.5);
            else if (gamepad2.dpad_down)
                scislift.setPower(-0.5);
            else
                scislift.setPower(0);

            //grabber movement
            if (gamepad2.right_stick_y > 0.2)
                grabber.setPower(0.3);
            else if (gamepad2.right_stick_y < -0.2)
                grabber.setPower(-0.3);
            else
                grabber.setPower(0);

        }
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
