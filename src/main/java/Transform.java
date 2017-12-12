/**
 * A class that runs implements several simple transformations on 2D image arrays.
 * <p>
 * This file contains stub code for your image transformation methods that are called by the main
 * class. You will do your work for this MP in this file.
 * <p>
 * Note that you can make several assumptions about the images passed to your functions, both by the
 * web front end and by our testing harness:
 * <ul>
 * <li>You will not be passed empty images.</li>
 * <li>All images will have even width and height.</li>
 * </ul>
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/4/">MP4 Documentation</a>
 */
public class Transform {

    /**
     * Default amount to shift an image's position. Not used by the testing suite, so feel free to
     * change this value.
     */
    public static final int DEFAULT_POSITION_SHIFT = 16;

    /**
     * Pixel value to use as filler when you don't have any valid data. All white with complete
     * transparency. DO NOT CHANGE THIS VALUE: the testing suite relies on it.
     */
    public static final int FILL_VALUE = 0x00FFFFFF;

    /**
     * Shift the image left by the specified amount.
     * <p>
     * Any pixels shifted in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to shift to the left
     * @param amount the amount to shift the image to the left
     * @return the shifted image
     */
    public static int[][] shiftLeft(final int[][] originalImage, final int amount) {
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        if (amount > newArr.length) {
            for (int x = 0; x < newArr.length; x++) {
                for (int y = 0; y < newArr[0].length; y++) {
                    newArr[x][y] = FILL_VALUE;
                }
            }
            return newArr;
        }
        for (int x = 0; x < newArr.length - amount; x++) {
            for (int y = 0; y < newArr[0].length; y++) {
                newArr[x][y] = originalImage[x + amount][y];
            }
        }
        for (int x = newArr.length - amount; x < newArr.length; x++) {
            for (int y = 0; y < newArr[0].length; y++) {
                newArr[x][y] = FILL_VALUE;
            }
        }
        return newArr;
    }

    /*
     * Shift right like above.
     */
    /**
     *
     * @param originalImage is the image to be shifted
     * @param amount is the amount of shift
     * @return the new array
     */
    public static int[][] shiftRight(final int[][] originalImage, final int amount) {
        int[][] flipped = flipHorizontal(originalImage);
        int[][] shifted = shiftLeft(flipped, amount);
        int[][] finalArr = flipHorizontal(shifted);
        return finalArr;
    }

    /**
     * Shift up like above.
     */
    /**
     *
     * @param originalImage is the image to be shifted
     * @param amount is the amount of shift
     * @return the new array
     */
    public static int[][] shiftUp(final int[][] originalImage, final int amount) {
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        if (amount > newArr[0].length) {
            for (int x = 0; x < newArr.length; x++) {
                for (int y = 0; y < newArr[0].length; y++) {
                    newArr[x][y] = FILL_VALUE;
                }
            }
            return newArr;
        }
        for (int y = 0; y < newArr[0].length - amount; y++) {
            for (int x = 0; x < newArr.length; x++) {
                newArr[x][y] = originalImage[x][y + amount];
            }
        }
        for (int y = newArr[0].length - amount; y < newArr[0].length; y++) {
            for (int x = 0; x < newArr.length; x++) {
                newArr[x][y] = FILL_VALUE;
            }
        }
        return newArr;
    }

    /**
     * Shift down like above.
     */
    /**
     *
     * @param originalImage is the image to be shifted
     * @param amount is the amount of shift
     * @return the new array
     */
    public static int[][] shiftDown(final int[][] originalImage, final int amount) {
        int[][] flipped = flipVertical(originalImage);
        int[][] shifted = shiftUp(flipped, amount);
        int[][] finalArr = flipVertical(shifted);
        return finalArr;

    }

    /**
     * Rotate the image left by 90 degrees around its center.
     * <p>
     * Any pixels rotated in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to rotate left 90 degrees
     * @return the rotated image
     */
    public static int[][] rotateLeft(final int[][] originalImage) {
        int xLength = originalImage.length;
        int yLength = originalImage[0].length;
        int[][] xyReversed = new int[xLength][yLength];
        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                xyReversed[x][y] = FILL_VALUE;
            }
        }
        if (xLength >= yLength) {
            int difference = xLength - yLength;
            for (int x = difference / 2; x < xLength - difference / 2; x++) {
                for (int y = 0; y < yLength; y++) {
                    xyReversed[y + difference / 2][x - difference / 2] = originalImage[x][y];
                }
            }
        } else {
            int difference = yLength - xLength;
            for (int y = difference / 2; y < yLength - difference / 2; y++) {
                for (int x = 0; x < xLength; x++) {
                    xyReversed[y - difference / 2][x + difference / 2] = originalImage[x][y];
                }
            }
        }
        int[][] flipped = flipVertical(xyReversed);

        return flipped;
    }

    /*
     * Rotate the image right like above.
     */
    /**
     *
     * @param originalImage is the image to be changed
     * @return changed image is the changed image
     */
    public static int[][] rotateRight(final int[][] originalImage) {
        return rotateLeft(rotateLeft(rotateLeft(originalImage)));
    }

    /*
     * Flip the image on the horizontal axis across its center.
     */
    /**
     *
     * @param originalImage is the original image to be changed
     * @return changed image is the changed image
     */
    public static int[][] flipHorizontal(final int[][] originalImage) {
        int[][] newImage = new int[originalImage.length][originalImage[0].length];
        for (int y = 0; y < originalImage[0].length; y++) {
            for (int x = 0; x < originalImage.length / 2; x++) {
                int temp = originalImage[x][y];
                newImage[x][y] = originalImage[originalImage.length - 1 - x][y];
                newImage[originalImage.length - 1 - x][y] = temp;
            }
        }
        return newImage;
    }

    /*
     * Flip the image on the vertical axis across its center.
     */
    /**
     *
     * @param originalImage is the original image to be changed
     * @return changed image is the changed image
     */
    public static int[][] flipVertical(final int[][] originalImage) {
        int[][] newImage = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < originalImage.length; x++) {
            for (int y = 0; y < originalImage[0].length / 2; y++) {
                int temp = originalImage[x][y];
                newImage[x][y] = originalImage[x][originalImage[0].length - 1 - y];
                newImage[x][originalImage[0].length - 1 - y] = temp;
            }
        }
        return newImage;
    }

    /**
     * Default amount to shift colors by. Not used by the testing suite, so feel free to change this
     * value.
     */
    public static final int DEFAULT_COLOR_SHIFT = 32;

    /*
     * a general function to deal with addition
     */
    /**
     * Add red to the image.
     * <p>
     * This function <i>does not modify the original image</i>. It should also not generate any new
     * filled pixels.
     *
     * @param red a boolean to decide whether to add red
     * @param green a boolean to decide whether to add green
     * @param blue a boolean to decide whether to add blue
     * @param alpha a boolean to decide whether to add alpha
     * @param originalImage the image to be changed
     * @param amount the amount of color/alpha to add
     * @return the changed image
     */
    public static int[][] generalAdd(final boolean red, final boolean green, final boolean blue,
            final boolean alpha, final int[][] originalImage, final int amount) {
        int value, shift, finalValue;
        shift = 0;
        final int greenShift = 8;
        final int blueShift = 16;
        final int alphaShift = 24;
        if (red) {
            shift = 0;
        }
        if (green) {
            shift = greenShift;
        }
        if (blue) {
            shift = blueShift;
        }
        if (alpha) {
            shift = alphaShift;
        }
        final int bound = 255;
        final int specificComponent = 0xff; // used to help get the specific color component
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < originalImage.length; x++) {
            for (int y = 0; y < originalImage[0].length; y++) {
                value = (originalImage[x][y] >>> shift) & specificComponent;
                if (value + amount > bound) {
                    finalValue = (bound - value);
                } else {
                    finalValue = amount;
                }
                newArr[x][y] = (finalValue << shift) + originalImage[x][y];
            }
        }
        return newArr;
    }
    /*
     * a general function to deal with color/alpha subtraction
     */
    /**
     *
     * @param red a boolean to decide whether to subtract red
     * @param green a boolean to decide whether to subtract green
     * @param blue a boolean to decide whether to subtract blue
     * @param alpha a boolean to decide whether to subtract alpha
     * @param originalImage the image to be changed
     * @param amount the amount of color/alpha to subtract
     * @return the changed image
     */
    public static int[][] generalSubtract(final boolean red, final boolean green,
            final boolean blue,
            final boolean alpha, final int[][] originalImage, final int amount) {
        int value, shift, finalValue;
        shift = 0;
        final int greenShift = 8;
        final int blueShift = 16;
        final int alphaShift = 24;
        if (red) {
            shift = 0;
        }
        if (green) {
            shift = greenShift;
        }
        if (blue) {
            shift = blueShift;
        }
        if (alpha) {
            shift = alphaShift;
        }
        final int bound = 0;
        final int specificComponent = 0xff; // used to help get the specific color component
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < originalImage.length; x++) {
            for (int y = 0; y < originalImage[0].length; y++) {
                value = (originalImage[x][y] >>> shift) & specificComponent;
                if (value - amount < bound) {
                    finalValue = value;
                } else {
                    finalValue = amount;
                }
                newArr[x][y] = originalImage[x][y] - (finalValue << shift);
            }
        }
        return newArr;
    }
    /**
     *
     * @param originalImage the image to change
     * @param amount the amount of color to add
     * @return the changed image
     */
    public static int[][] moreRed(final int[][] originalImage, final int amount) {
        return generalAdd(true, false, false, false, originalImage, amount);
    }

    /*
     * Remove red from the image.
     */
    /**
     *
     * @param originalImage is the image to be changed
     * @param amount is the amount of red to be deducted
     * @return the changed image
     */
    public static int[][] lessRed(final int[][] originalImage, final int amount) {
        return generalSubtract(true, false, false, false, originalImage, amount);
    }
    /*
     * Add green to the image.
     */
    /**
     * @param originalImage the image to add red to
     * @param amount the amount of green to add
     * @return the recolored image
     */
    public static int[][] moreGreen(final int[][] originalImage, final int amount) {
        return generalAdd(false, true, false, false, originalImage, amount);

    }

    /*
     * Remove green from the image.
     */
    /**
     *
     * @param originalImage is the image to be changed
     * @param amount is the amount of green to be deducted
     * @return the changed image
     */
    public static int[][] lessGreen(final int[][] originalImage, final int amount) {
        return generalSubtract(false, true, false, false, originalImage, amount);
    }

    /*
     * Add blue to the image.
     */
    /**
     * @param originalImage the image to add red to
     * @param amount the amount of blue to add
     * @return the recolored image
     */
    public static int[][] moreBlue(final int[][] originalImage, final int amount) {
        return generalAdd(false, false, true, false, originalImage, amount);
    }

    /*
     * Remove blue from the image.
     */
    /**
     *
     * @param originalImage is the image to be changed
     * @param amount is the amount of blue to be deducted
     * @return the changed image
     */
    public static int[][] lessBlue(final int[][] originalImage, final int amount) {
        return generalSubtract(false, false, true, false, originalImage, amount);
    }

    /*
     * Increase the image alpha channel.
     */
    /**
     * @param originalImage the image to add red to
     * @param amount the amount of transparency to add
     * @return the recolored image
     */
    public static int[][] moreAlpha(final int[][] originalImage, final int amount) {
        return generalAdd(false, false, false, true, originalImage, amount);
    }

    /*
     * Decrease the image alpha channel.
     */
    /**
     *
     * @param originalImage is the image to be changed
     * @param amount is the amount of transparency to be deducted
     * @return the changed image
     */
    public static int[][] lessAlpha(final int[][] originalImage, final int amount) {
        return generalSubtract(false, false, false, true, originalImage, amount);
    }

    /**
     * The default resize factor. Not used by the testing suite, so feel free to change this value.
     */
    public static final int DEFAULT_RESIZE_AMOUNT = 2;

    /**
     * Shrink in the vertical axis around the image center.
     * <p>
     * An amount of 2 will result in an image that is half its original height. An amount of 3 will
     * result in an image that's a third of its original height. Any pixels shrunk in from off
     * screen should be filled with FILL_VALUE. This function <i>does not modify the original
     * image</i>.
     *
     * @param originalImage the image to shrink
     * @param amount the factor by which the image's height is reduced
     * @return the shrunken image
     */
    public static int[][] shrinkVertical(final int[][] originalImage, final int amount) {
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < newArr.length; x++) {
            for (int y = 0; y < newArr[0].length; y++) {
                newArr[x][y] = FILL_VALUE;
            }
        }
        for (int x = 0; x < newArr.length; x++) {
            for (int y = 0; y < newArr[0].length; y++) {

                if (y % amount != 0) {
                    continue;
                }
                newArr[x][y / amount] = originalImage[x][y];

            }
        }

        return shiftDown(newArr, originalImage[0].length / 2 / amount);
    }

    /*
     * Expand in the vertical axis around the image center.
     */
    /**
     *
     * @param originalImage the image to be changed
     * @param amount the magnitude to expand vertically
     * @return the expanded image
     */

    public static int[][] expandVertical(final int[][] originalImage, final int amount) {
        int yLength = originalImage[0].length;
        int[][] newArr = new int[originalImage.length][yLength];
        for (int x = 0; x < originalImage.length; x++) {
            int remaining = yLength / 2 / amount + 1;
            for (int y = 0; y < remaining; y++) {
                for (int count = 0; count < amount; count++) {
                    if (yLength / 2 - amount * y - count - 1 < 0
                            || yLength / 2 + amount * y + count >= yLength) {
                        break;
                    }
                    newArr[x][yLength / 2 - amount * y - count - 1] = originalImage[x][yLength / 2
                            - y - 1];
                    newArr[x][yLength / 2 + amount * y + count] = originalImage[x][yLength / 2 + y];
                }
            }
        }
        return newArr;
    }

    /*
     * Shrink in the horizontal axis around the image center.
     */
    /**
     *
     * @param originalImage to be changed
     * @param amount the magnitude to shrink horizontally
     * @return the changed image
     */
    public static int[][] shrinkHorizontal(final int[][] originalImage, final int amount) {
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < newArr.length; x++) {
            for (int y = 0; y < newArr[0].length; y++) {
                newArr[x][y] = FILL_VALUE;
            }
        }
        for (int y = 0; y < newArr[0].length; y++) {
            for (int x = 0; x < newArr.length; x++) {
                if (x % amount != 0) {
                    continue;
                }
                newArr[x / amount][y] = originalImage[x][y];

            }
        }

        return shiftRight(newArr, originalImage.length / 2 / amount);
    }

    /*
     * Expand in the horizontal axis around the image center.
     */
    /**
     *
     * @param originalImage to be changed
     * @param amount the magnitude to expand horizontally
     * @return the changed image
     */
    public static int[][] expandHorizontal(final int[][] originalImage, final int amount) {
        int xLength = originalImage.length;
        int[][] newArr = new int[xLength][originalImage[0].length];
        for (int y = 0; y < originalImage[0].length; y++) {
            int remaining = xLength / 2 / amount + 1;
            for (int x = 0; x < remaining; x++) {
                for (int count = 0; count < amount; count++) {
                    if (xLength / 2 - amount * x - count - 1 < 0
                            || xLength / 2 + amount * x + count >= xLength) {
                        break;
                    }
                    newArr[xLength / 2 - amount * x - count - 1][y] = originalImage[xLength / 2 - x
                            - 1][y];
                    newArr[xLength / 2 + amount * x + count][y] = originalImage[xLength / 2 + x][y];
                }
            }
        }
        return newArr;
    }

    /**
     * Remove a green screen mask from an image.
     * <p>
     * This function should remove primarily green pixels from an image and replace them with
     * transparent pixels (FILL_VALUE), allowing you to achieve a green screen effect. Obviously
     * this function will destroy pixels, but it <i>does not modify the original image</i>.
     * <p>
     * While this function is tested by the test suite, only extreme edge cases are used. Getting it
     * work work will with real green screen images is left as a challenge for you.
     *
     * @param originalImage the image to remove a green screen from
     * @return the image with the green screen removed
     */
    public static int[][] greenScreen(final int[][] originalImage) {
        final int green = 0xff00ff00;
        int[][] newArr = new int[originalImage.length][originalImage[0].length];
        for (int x = 0; x < originalImage.length; x++) {
            for (int y = 0; y < originalImage[0].length; y++) {
                if (originalImage[x][y] == green) {
                    newArr[x][y] = FILL_VALUE;
                } else {
                    newArr[x][y] = originalImage[x][y];
                }
            }
        }
        return newArr;
    }

    /**
     * A wild and mysterious image transform.
     * <p>
     * You are free to implement this in any way you want. It is not tested rigorously by the test
     * suite, but it should do something (change the original image) and <i>not modify the original
     * image</i>.
     * <p>
     * Call this function mystery. It should take only the original image as a single argument and
     * return a modified image.
     *
     * @param originalImage the image to perform a strange and interesting transform on
     * @return the image transformed in wooly and frightening ways
     */
    public static int[][] mystery(final int[][] originalImage) {
         int[][] newArr = new int[originalImage.length][originalImage[0].length];
         for (int x = 0; x < newArr.length; x++) {
         for (int y = 0; y < newArr[0].length; y++) {
         newArr[x][y] = FILL_VALUE;
         }
         }
         return newArr;
    }
    /**
     *
     * @param originalImage The image to be slightly blurred
     * @return the blurred image
     */
    public static int[][] blur(final int[][] originalImage) {
        final int n = 4;
        final int special = 0xff;
        final int greenShift = 8;
        final int blueShift = 16;
        final int alphaShift = 24;
        int[][] newArr = new int[originalImage.length][originalImage[0].length];

        for (int x = 0; x < originalImage.length / 2; x++) {
            for (int y = 0; y < originalImage[0].length / 2; y++) {
                int r1 = originalImage[2 * x][2 * y] & special;
                int r2 = originalImage[2 * x][2 * y + 1] & special;
                int r3 = originalImage[2 * x + 1][2 * y] & special;
                int r4 = originalImage[2 * x + 1][2 * y + 1] & special;
                long aveR = (r1 + r2 + r3 + r4) / n;

                int g1 = originalImage[2 * x][2 * y] >> greenShift & special;
                int g2 = originalImage[2 * x][2 * y + 1] >> greenShift & special;
                int g3 = originalImage[2 * x + 1][2 * y] >> greenShift & special;
                int g4 = originalImage[2 * x + 1][2 * y + 1] >> greenShift & special;
                long aveG = (g1 + g2 + g3 + g4) / n;

                int b1 = originalImage[2 * x][2 * y] >> blueShift & special;
                int b2 = originalImage[2 * x][2 * y + 1] >> blueShift & special;
                int b3 = originalImage[2 * x + 1][2 * y] >> blueShift & special;
                int b4 = originalImage[2 * x + 1][2 * y + 1] >> blueShift & special;
                long aveB = (b1 + b2 + b3 + b4) / n;

                int a1 = originalImage[2 * x][2 * y] >>> alphaShift & special;
                int a2 = originalImage[2 * x][2 * y + 1] >>> alphaShift & special;
                int a3 = originalImage[2 * x + 1][2 * y] >>> alphaShift & special;
                int a4 = originalImage[2 * x + 1][2 * y + 1] >>> alphaShift & special;
                long aveA = (a1 + a2 + a3 + a4) / n;

                long average = (aveA << alphaShift) + (aveB << blueShift)
                        + (aveG << greenShift) + aveR;

                newArr[2 * x][2 * y] = (int) average;
                newArr[2 * x][2 * y + 1] = (int) average;
                newArr[2 * x + 1][2 * y] = (int) average;
                newArr[2 * x + 1][2 * y + 1] = (int) average;

            }
        }
        if (originalImage.length % n == 0 && originalImage[0].length % n == 0) {
            for (int x = 0; x < originalImage.length / n; x++) {
                for (int y = 0; y < originalImage[0].length / n; y++) {
                    int r1 = newArr[x * n][y * n] & special;
                    int r2 = newArr[x * n + 2][y * n] & special;
                    int r3 = newArr[x * n][y * n + 2] & special;
                    int r4 = newArr[x * n + 2][y * n + 2] & special;
                    long aveR = (r1 + r2 + r3 + r4) / n;

                    int g1 = newArr[x * n][y * n] >> greenShift & special;
                    int g2 = newArr[x * n + 2][y * n] >> greenShift & special;
                    int g3 = newArr[x * n][y * n + 2] >> greenShift & special;
                    int g4 = newArr[x * n + 2][y * n + 2] >> greenShift & special;
                    long aveG = (g1 + g2 + g3 + g4) / n;

                    int b1 = newArr[x * n][y * n] >> blueShift & special;
                    int b2 = newArr[x * n + 2][y * n] >> blueShift & special;
                    int b3 = newArr[x * n][y * n + 2] >> blueShift & special;
                    int b4 = newArr[x * n + 2][y * n + 2] >> blueShift & special;
                    long aveB = (b1 + b2 + b3 + b4) / n;

                    int a1 = newArr[x * n][y * n] >>> alphaShift & special;
                    int a2 = newArr[x * n + 2][y * n] >>> alphaShift & special;
                    int a3 = newArr[x * n][y * n + 2] >>> alphaShift & special;
                    int a4 = newArr[x * n + 2][y * n + 2] >>> alphaShift & special;
                    long aveA = (a1 + a2 + a3 + a4) / n;

                    long average = (aveA << alphaShift) + (aveB << blueShift)
                            + (aveG << greenShift) + aveR;

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            newArr[x * n + i][y * n + j] = (int) average;
                        }
                    }
                }
            }
        }
        return newArr;
    }
    /**
     *
     * @param originalImage the image to be modified
     * @return the monochrome image
     */
    public static int[][] monochrome(final int[][] originalImage) {
        final int reverse = 0xff;
        final int shift1 = 8;
        final int shift2 = 16;
        final int shift3 = 24;
        final int n = 3;

        int[][] image1 = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int a = 0; a < originalImage[0].length; a++) {
                image1[i][a] = originalImage[i][a];
            }

        }

        for (int i = 0; i < image1.length; i++) {
            for (int a = 0; a < image1[0].length; a++) {
                int blue = image1[i][a] & reverse;
                int green = (image1[i][a] >> shift1) & reverse;
                int red = (image1[i][a] >> shift2) & reverse;
                int alpha = (image1[i][a] >> shift3) & reverse;

                int avg = (red + green + blue) / n;

                image1[i][a] = (alpha << shift3 | avg << shift2 | avg << shift1 | avg);
            }
        }
        return image1;
    }

    /**
     *
     * @param originalImage the image to be transformed
     * @return the transformed image
     */
    public static int[][] increaseBrightness(final int[][] originalImage) {
        int[][] newArray = new int[originalImage.length][originalImage[0].length];
        final int greenShift = 8;
        final int blueShift = 16;
        final int alphaShift = 24;
        final int specialValue = 0xff;
        final int change = 10;
        final int max = 255;
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                int red = originalImage[i][j] & specialValue;
                int green = originalImage[i][j] >> greenShift & specialValue;
                int blue = originalImage[i][j] >> blueShift & specialValue;
                int alpha = originalImage[i][j] >>> alphaShift & specialValue;
                int newRed;
                if (red + change > max) {
                    newRed = max;
                } else {
                    newRed = red + change;
                }
                int newGreen;
                if (green + change > max) {
                    newGreen = max;
                } else {
                    newGreen = green + change;
                }
                int newBlue;
                if (blue + change > max) {
                    newBlue = max;
                } else {
                    newBlue = blue + change;
                }
                newArray[i][j] = (alpha << alphaShift) + (newBlue << blueShift)
                        + (newGreen << greenShift) + newRed;
            }
        }
        return newArray;
    }

    /**
     *
     * @param originalImage the image to be changed
     * @return the changed image
     */
    public static int[][] decreaseBrightness(final int[][] originalImage) {
        int[][] newArray = new int[originalImage.length][originalImage[0].length];
        final int greenShift = 8;
        final int blueShift = 16;
        final int alphaShift = 24;
        final int specialValue = 0xff;
        final int change = 10;
        final int min = 0;
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                int red = originalImage[i][j] & specialValue;
                int green = originalImage[i][j] >> greenShift & specialValue;
                int blue = originalImage[i][j] >> blueShift & specialValue;
                int alpha = originalImage[i][j] >>> alphaShift & specialValue;
                int newRed;
                if (red - change < min) {
                    newRed = min;
                } else {
                    newRed = red - change;
                }
                int newGreen;
                if (green - change < min) {
                    newGreen = min;
                } else {
                    newGreen = green - change;
                }
                int newBlue;
                if (blue - change < min) {
                    newBlue = min;
                } else {
                    newBlue = blue - change;
                }
                newArray[i][j] = (alpha << alphaShift) + (newBlue << blueShift)
                        + (newGreen << greenShift) + newRed;
            }
        }
        return newArray;
    }
}
