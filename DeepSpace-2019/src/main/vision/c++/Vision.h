#include <iostream>
#include "opencv2/opencv.hpp"
#include "Settings.h"

using namespace cv;

/**
 * The PostProcessor is the thing that takes the camera data and produces an output from it. 
 * The PostProcessor is written with the assumption that a JeVois camera is being used and 
 * the input image is already in binary.
 */
class PostProcessor {
    public:
    PostProcessor();
    void Loop(); //main loop that processes images and gives outputs
    void CleanUp(); //cleans up the postprocessor before disposal.
    bool Stop() { return stop; }

    private:
    bool stop = false;
    cv::VideoCapture cap;
};

/**
 * A collection of methods that simply makes PostProcessor code easier to read. 
 */
class Util {
    public:
    static bool IsElgible(cv::RotatedRect rect); //tests rotated rect to see if it is good for the target
    static bool IsPair(cv::RotatedRect rect1, cv::RotatedRect rect2);
    static double returnTrueDistanceScalar(cv::RotatedRect rectangle);
};

/**
 * Represents a recognized target as a pair of rotated rectangles.
 */
class PairData {
    public:
    PairData () {};
    PairData(cv::RotatedRect rect1, cv::RotatedRect rect2);
    cv::Point center();
    int area();
    int height();

    private:
    cv::RotatedRect rect1;
    cv::RotatedRect rect2;
};
