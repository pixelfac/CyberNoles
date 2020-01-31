package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous(name = "Auto_BlueColorSimple", group = "Linear OpMode")
//@Disabled                            // Comment this out to add to the opmode list
public class blueColorAutoSimple extends LinearOpMode {

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackLeft;
    private DcMotor motorBackRight;
    private CRServo extendWheelLeft;
    private CRServo extendWheelRight;
    private CRServo blockGrab;
    private DcMotor blockLift;
    private Servo dragger;


    double[][] directions = {
            {1, -1, 1, -1},   /* up     */
            {-1, 1, -1, 1},   /* down     */
            {-1, -1, 1, 1},   /* left     */
            {1, 1, -1, -1},   /* right     */
    };

    public boolean close(int currentPos, int targetPos) {
        if (Math.abs(currentPos - targetPos) < 3) return true;
        return false;
    }

    public void move(String direction, double power) {
        if (!direction.equals("none")) {
            int d = 0;
            if (direction.equals("forward"))
                d = 0;
            else if (direction.equals("backward"))
                d = 1;
            else if (direction.equals("left"))
                d = 2;
            else if (direction.equals("right"))
                d = 3;
            motorFrontLeft.setPower((directions[d][0]) * power);
            motorFrontRight.setPower((directions[d][1]) * power);
            motorBackLeft.setPower((directions[d][2]) * power);
            motorBackRight.setPower((directions[d][3]) * power);
        }
    }

    public void unmove() {
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public void coolWait(double seconds) {
        double debounce = runtime.seconds() + 0.0;
        while (debounce + seconds > runtime.seconds() && opModeIsActive()) {
            telemetry.addData("distance", (sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.update();
        }
    }

    public void moveUntilTime(String direction, int time, double power){
        move(direction, power);
        double debounce = runtime.seconds() + 0.0;
        while (debounce + (time / 1000.0) > runtime.seconds() && opModeIsActive()) {}

        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public void moveUntil(String direction, int property, String comparison, int value, double power){
        move(direction, power);
        while (((comparison.equals("lessthan") && property >= value) || (comparison.equals("greaterthan") && property <= value)) && opModeIsActive()) {}

        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public void moveUntil(String direction, double property, String comparison, double value, double power){
        /*
            example properties:
                ColorSensor.alpha(),
                ColorSensor.red(),
                ColorSensor.green(),
                ColorSensor.blue(),
                sensorDistance.getDistance(DistanceUnit.CM)
            comparisons:
                lessthan,
                greaterthan
         */
        move(direction, power);
        while (((comparison.equals("lessthan") && property >= value) || (comparison.equals("greaterthan") && property <= value)) && opModeIsActive()) {}

        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
    }

    public double average(double[] array) {
        double total = 0.0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        return (total / array.length);
    }

    @Override
    public void runOpMode() {

        motorFrontLeft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        motorFrontRight = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        motorBackLeft = hardwareMap.get(DcMotor.class, "leftBackDrive");
        motorBackRight = hardwareMap.get(DcMotor.class, "rightBackDrive");
        blockGrab = hardwareMap.get(CRServo.class, "blockGrab");
        blockLift = hardwareMap.get(DcMotor.class, "blockLift");
        extendWheelLeft = hardwareMap.get(CRServo.class, "extendWheelLeft");
        extendWheelRight = hardwareMap.get(CRServo.class, "extendWheelRight");

        dragger = hardwareMap.get(Servo.class, "dragger");

        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        int step = 0;
        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        telemetry.addData("Status: ", "Initialized");
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        // wait for the start button to be pressed.
        waitForStart();

        while (!opModeIsActive()) {}
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

        // Send the info back to driver station using telemetry function.
        telemetry.addData("Step: ", step);
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.update();

        extendWheelLeft.setPower(0.5);
        coolWait(3);
        extendWheelLeft.setPower(0.0);

        move("forward", 0.2);
        double[] distances = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        int distanceIndex = 0;
        boolean cycled = false;
        while ((!cycled || average(distances) <= 25.0) && opModeIsActive()) {
            distances[distanceIndex] = sensorDistance.getDistance(DistanceUnit.CM);
            distanceIndex++;
            if (distanceIndex == distances.length) {
                distanceIndex = 0;
                cycled = true;
            }
            telemetry.addData("distance", average(distances));
            telemetry.update();
        }
        //coolWait(2);
        unmove();
        move("right", 0.2);
        telemetry.addData("Alpha", sensorColor.alpha());
        telemetry.update();
        while (sensorColor.alpha() >= 100) {
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.update();
        }
        coolWait(0.5);
        unmove();
        blockGrab.setPower(1.0);
        coolWait(1.5);
        blockLift.setPower(-0.5);
        coolWait(1);
        blockLift.setPower(0.0);
            /*if (step == 0) {
                motorFrontLeft.setPower(0.2);
                motorFrontRight.setPower(-0.2);
                motorBackLeft.setPower(0.2);
                motorBackRight.setPower(-0.2);
            }
            while (step == 0 && opModeIsActive()){
                Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                        (int) (sensorColor.green() * SCALE_FACTOR),
                        (int) (sensorColor.blue() * SCALE_FACTOR),
                        hsvValues);
                Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                        (int) (sensorColor.green() * SCALE_FACTOR),
                        (int) (sensorColor.blue() * SCALE_FACTOR),
                        hsvValues);

                // Send the info back to driver station using telemetry function.
                telemetry.addData("Step: ", step);
                telemetry.addData("Hue", hsvValues[0]);
                telemetry.update();
                //if (hsvValues[0] > 150 ){ // Checks if it is red or blue
                if((int)(sensorDistance.getDistance(DistanceUnit.CM)) < 10)  {
                    step++;
                }
            }
            if (step == 1){
                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
                step++;
            }*/

           /* if (step == 2){
                motorFrontRight.setPower(-.5);
                motorFrontLeft.setPower(.5);
                motorBackLeft.setPower(.5);
                motorBackRight.setPower(-.5);
                sleep(200);
                step++;
            }

            if (step == 3){
                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            } */

            /*moveUntilTime("backward", 800);
            sleep(500);
            dragger.setPosition(1);
            sleep(1000);
            moveUntilTime("forward", 400);
            sleep(1000);
            moveUntilTime("right", 2500);
            sleep(1000);
            dragger.setPosition(0);
            sleep(1000);
            moveUntilTime("left", 3400);    //set time to 1000 and stop after for 1 block and park
            sleep(1000);
            moveUntilTime("backward", 350);
            sleep(1000);
            dragger.setPosition(1);
            sleep(1000);
            moveUntilTime("forward", 450);
            sleep(1000);
            moveUntilTime("right", 2000);
            sleep(1000);
            moveUntilTime("forward", 200);
            sleep(1000);
            moveUntilTime("right ", 1200);
            sleep(1000);
            dragger.setPosition(0);
            sleep(1000);
            moveUntilTime("left", 600);    //set time to 600 and stop after for 2 blocks and park
            sleep(1000);
*/
            idle();
    }
}