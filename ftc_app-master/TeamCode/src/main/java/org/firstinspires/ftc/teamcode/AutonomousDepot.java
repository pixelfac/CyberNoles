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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

/**
 * Autonomous Mode for when facing the Depot.
 * Cybernoles
 */


@Autonomous(name="Autonomous - Depot", group="Linear Opmode")
//@Disabled
public class AutonomousDepot extends LinearOpMode {

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

    public void setZero()
    {
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void forward(int time)
    {
        leftDriveFront.setPower(1);
        leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDriveBack.setPower(1);
        leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveFront.setPower(1);
        rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveBack.setPower(1);
        rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void reverse(int time)
    {
        leftDriveFront.setPower(1);
        leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDriveBack.setPower(1);
        leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveFront.setPower(1);
        rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveBack.setPower(1);
        rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        sleep(time);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void turn(int degrees)
    {
        if (theta>0) {
            leftDriveFront.setPower(1);
            leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
            leftDriveBack.setPower(1);
            leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
            rightDriveFront.setPower(1);
            rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightDriveBack.setPower(1);
            rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else{
            leftDriveFront.setPower(1);
            leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
            leftDriveBack.setPower(1);
            leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDriveFront.setPower(1);
            rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDriveBack.setPower(1);
            rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        sleep(Math.abs(degrees)/180 * 1000);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void strafeLeft (int t){
        leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDriveFront.setPower(1);
        leftDriveBack.setPower(1);
        rightDriveFront.setPower(1);
        rightDriveBack.setPower(1);
        sleep(t);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);

    }

    public void strafeRight (int t){
        leftDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDriveBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDriveFront.setPower(1);
        leftDriveBack.setPower(1);
        rightDriveFront.setPower(1);
        rightDriveBack.setPower(1);
        sleep(t);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);
    }


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        leftDriveFront = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        linearActuator.setPower(-1);
        linearActuator.setDirection(DcMotorSimple.Direction.REVERSE);
        sleep(21000);
        linearActuator.setPower(0);
        int Drive = 1;
        leftDriveFront.setPower(Drive);
        rightDriveFront.setPower(Drive);
        leftDriveBack.setPower(Drive);
        rightDriveBack.setPower(Drive);
        sleep(120);
        leftDriveFront.setPower(Drive);
        rightDriveFront.setPower(-Drive);
        leftDriveBack.setPower(-Drive);
        rightDriveBack.setPower(Drive);
        sleep(2030);



        //sleep(100);
        //strafeRight(3000);
        //markerServo.setPosition(1);
        //markerServo.setPosition(0);




        // Set the panel back to the default color
        //  relativeLayout.post(new Runnable() {
        //   public void run() {
        //   relativeLayout.setBackgroundColor(Color.WHITE);
    }
}