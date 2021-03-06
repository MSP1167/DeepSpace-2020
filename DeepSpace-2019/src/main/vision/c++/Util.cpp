#include "Vision.h"
//#include "Settings.h"

/**
 * Utils file for 2019 DeepSpace vision
 */


/** Tests a rotated rectangle to see if it could be part of a 2019 target.
 * @return true if yes, false if no.
 */
using namespace std;
bool Util::IsElgible(cv::RotatedRect rect) {
    bool ratioTest;
    bool areaTest;
    bool angleTest;

    double width = (double) rect.size.width;
    double height = (double) rect.size.height;

    //the aspect ratio test
    double aspect_ratio = 0.0;
    
    if(width > height) {
        aspect_ratio = height/width;
    } else {
        aspect_ratio = width/height; //make sure aspect ratio is consistent so we don't have to have two different settings
    }
    double ARMax = Settings::Aspect_Ratio_Max();
    double ARMin = Settings::Aspect_Ratio_Min();
    ratioTest = (aspect_ratio < ARMax && aspect_ratio > ARMin);

    //the area test
    int areaMax = Settings::Area_Max();
    int areaMin = Settings::Area_Min();
    int area = (int) (width * height);
    
    //cout << area << "\n";
    //cout.flush();
    
    areaTest = (area < areaMax && area > areaMin);
    

    //the angle test
    int left_angle_max = Settings::LEFT_ANGLE + Settings::ANGLE_ERROR;
    int left_angle_min = Settings::LEFT_ANGLE - Settings::ANGLE_ERROR;
    int right_angle_max = Settings::RIGHT_ANGLE + Settings::ANGLE_ERROR;
    int right_angle_min = Settings::RIGHT_ANGLE - Settings::ANGLE_ERROR;
    int angle = rect.angle;

    angleTest = (angle < left_angle_max && angle > left_angle_min)
              || (angle < right_angle_max && angle > right_angle_min); //true is angle is cool, false if no.
    
    return ratioTest && areaTest && angleTest; //returns true if both tests are true, otherwise returns false
}

/**
 * compares the two rects to see if they are pairs or not
 * @return true if the two rects are pairs, false if no
 */
bool Util::IsPair(cv::RotatedRect rect1, cv::RotatedRect rect2) {
    //run an angle test to see if they are opposites. using Settings:Opposite_angle, we can simply feed the angles into the method and see if they are different

    int rect1_opposite_angle = Settings::Opposite_Angle(rect1.angle);
    int rect2_opposite_angle = Settings::Opposite_Angle(rect2.angle);
    bool Opposite_Angles = (rect1_opposite_angle != rect2_opposite_angle); //if the angles are different, they are opposite, since the rects have already passed the test.
    //make sure that the contours are in the right places (i.e. Right on the right, left on left.)
    if(Opposite_Angles) {
        bool angles_are_correct = false; //will be true if the contours are on the correct sides of each other
        int distance_between_rects = rect1.center.x - rect2.center.x; //will be positive if rect1 is rightmost, negative if rect2 is rightmost

        if(distance_between_rects > 0) //positive, rect1 is the rightmost rect
            angles_are_correct = Settings::Closest_Angle(rect2.angle) == Settings::RIGHT_ANGLE;
        else
            angles_are_correct = Settings::Closest_Angle(rect1.angle) == Settings::RIGHT_ANGLE;

        //now we can move on to the distance testing
        if(angles_are_correct) {
            double pixels_to_inches = Util::returnTrueDistanceScalar(rect1);
                   pixels_to_inches += Util::returnTrueDistanceScalar(rect2);
            pixels_to_inches /= 2; //average the scalar for maximum happiness
            distance_between_rects = abs(distance_between_rects); //use our var from before to measure distance. Multiply by our scalar to measure the relationship
            double distance_in_inches = (double) (distance_between_rects * pixels_to_inches); //scale the distance to inches 
            //quickly define our range for distance here
            double distance_high = Settings::DISTANCE + Settings::DISTANCE_ERROR;
            double distance_low  = Settings::DISTANCE - Settings::DISTANCE_ERROR;
            if(distance_in_inches < distance_high && distance_in_inches > distance_low)
                return true;
        }
    }

    return false;
}


/**
 * Assuming the passed rotated rect is a possible target, returns a scalar converting pixels
 * to inches.
 */
double Util::returnTrueDistanceScalar(cv::RotatedRect rectangle) {
    
    if(rectangle.size.width < rectangle.size.height) //the aspect ratio is correct, so just go on normally....
        return 5.5 / rectangle.size.height;
    else 
        return 5.5 / rectangle.size.width;
}
