package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

        telemetry.addData(">", "Press Start To Run TeleOp");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.left_stick_x >= 0.5)
                drive = 1;
            else if (gamepad1.left_stick_x <= -0.5)
                drive = -1;
            else drive = 0;

            if (gamepad1.left_stick_y <= -0.5)
                turn = 1;
            else if (gamepad1.left_stick_y >= 0.5)
                turn = -1;
            else turn = 0;

            if (gamepad1.right_stick_x >= 0.5)
                strafe = 1;
            else if (gamepad1.right_stick_x <= -0.5)
                strafe = -1;
            else strafe = 0;



            leftFrontDrive.setPower(limit(drive + turn + strafe));
            leftBackDrive.setPower(limit(drive + turn - strafe));
            rightFrontDrive.setPower(limit(drive - turn + strafe));
            rightBackDrive.setPower(limit(drive - turn - strafe));




        }
    }
}
