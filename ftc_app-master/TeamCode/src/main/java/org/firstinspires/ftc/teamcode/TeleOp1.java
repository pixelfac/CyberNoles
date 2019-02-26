/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 The TeleOp Mode
 By Trevor Yates
 Cybernoles
 */




@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Linear Opmode")
//@Disabled
public class TeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveBack = null;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveFront = null;
    //private DcMotor leverArm = null;
    //private DcMotor extensionArm = null;
   // Servo markerServo = null;
    //CRServo spoolServo = null;
    //Servo collectorServo = null;

  //  private DcMotor hookLeft = null;
   // private DcMotor hookRight = null;
   // private DcMotor arm = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDriveBack  = hardwareMap.get(DcMotor.class, "left_drive_back");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        leftDriveFront  = hardwareMap.get(DcMotor.class, "left_drive_front");
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        //leverArm = hardwareMap.get(DcMotor.class, "arm_lever");
        //extensionArm = hardwareMap.get(DcMotor.class, "arm_extension");
        //markerServo = hardwareMap.get(Servo.class, "servo1");
        //spoolServo = hardwareMap.get(CRServo.class, "servo2");
        //collectorServo = hardwareMap.get(Servo.class, "servo3");
     /*   hookLeft = hardwareMap.get(DcMotor.class, "hook_left");
        hookRight = hardwareMap.get(DcMotor.class, "hook_right");
        arm = hardwareMap.get(DcMotor.class, "arm1");*/
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //set servo position to 90 degrees
        //markerServo.setPosition(0);
       // spoolServo.setPower(0);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftBackPower;
            double rightBackPower;
            double leftFrontPower;
            double rightFrontPower;
            double leverPower;
            double extendPower;
        /*    double hookPower = Range.clip(gamepad1.right_stick_y, -1.0, 1.0);
            double armPower = Range.clip(gamepad1.right_stick_x, -1.0, 1.0);*/
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.left_stick_x;
         //   double leverControl = gamepad1.right_stick_x;
          //  double extendControl = gamepad1.right_stick_y;
          //  leverPower = Range.clip(leverControl, -1.0, 1.0);
            leftBackPower    = Range.clip(drive + turn, -1.0, 1.0) ;
           rightBackPower   = Range.clip(drive - turn, -1.0, 1.0) ;
           leftFrontPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightFrontPower   = Range.clip(drive - turn, -1.0, 1.0) ;
          //  extendPower = Range.clip(extendControl, -1.0, 1.0);

            /*
            // Tank drive section
            if (Math.abs(gamepad1.left_stick_y) > 0.05){
                leftBackPower = gamepad1.left_stick_y;
            }
            else
                leftBackPower = 0;

            if (Math.abs(gamepad1.right_stick_y) > 0.05){
                rightFrontPower = gamepad1.right_stick_y;
            }
            else
                rightFrontPower = 0;*/


/*
            if (gamepad1.b) {
                markerServo.setPosition(.8);
            }
            if (gamepad1.y) {
                markerServo.setPosition(0);
            }
*/
         /*   if (gamepad1.left_bumper) {
                spoolServo.setPower(0);
            }
            if (gamepad1.right_bumper) {
                spoolServo.setPower(1);
            } else {
                spoolServo.setPower(0.5);
            }*/
/*
            if (gamepad1.a) {
                collectorServo.setPosition(-1);
            } if (gamepad1.x) {
                collectorServo.setPosition(1);
            } else {
                collectorServo.setPosition(0);
            }
*/

            // Send calculated power to wheels
            leftDriveBack.setPower(leftBackPower);
            rightDriveBack.setPower(rightBackPower);
            leftDriveFront.setPower(leftFrontPower);
            rightDriveFront.setPower(rightFrontPower);
            //leverArm.setPower(leverPower);
            //extensionArm.setPower(extendPower);
          /*  arm.setPower(armPower);
            hookRight.setPower(hookPower);
            hookLeft.setPower(hookPower);*/
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftBackPower, rightFrontPower);
            telemetry.update();
        }
    }
}
