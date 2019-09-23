package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class linear_slide extends LinearOpMode {
    private DcMotor linear_slide = null;
    private double power = .4; //arbitrary number


    @Override
    public void runOpMode() throws InterruptedException {

        while (opModeIsActive()) {
            if (gamepad1.y)
                linear_slide.setPower(power);

            if (gamepad1.a)
                linear_slide.setPower(-power);

        }
    }

}
