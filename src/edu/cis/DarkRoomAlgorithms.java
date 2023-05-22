/*
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 *
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

package edu.cis;
import acm.graphics.GImage;
import java.lang.Math;
import javax.swing.*;


public class DarkRoomAlgorithms implements DarkRoomAlgorithmsInterface {

	/** Method rotaateLeft is called when user press the Rotaate Left button
	 *  It will copy image pixels in a 2D array into a anew 2D array with
	 *  90 degree counterclock-wise so it's rotataed left
	* */
	public GImage rotateLeft(GImage source) {
		int[][] original = source.getPixelArray(); // put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		int[][] left = new int[imgColumn][imgRow];
		for (int i = 0; i < imgColumn; i++) {
			for (int j = 0; j < imgRow; j++) {
				int x = j;
				int y = imgColumn - 1 - i;
				if (y >= 0) {
					left[i][j] = original[x][y];   // new 2D array is rorated left 90 degree
				}
			}
		}
		return new GImage(left);
	}

	/** Method rotaateRight is called when user press the Rotaate Right button
	 *  It will copy image pixels in a 2D array into a anew 2D array with
	 *  90 degree clock-wise so it's rotataed right
	 * */
	public GImage rotateRight(GImage source) {
		int[][] original = source.getPixelArray();// put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		int[][] right = new int[imgColumn][imgRow];
		for (int i = 0; i < imgColumn; i++) {
			for (int j = 0; j < imgRow; j++) {
				int x = imgRow - 1 - j;
				int y = i;
				if (y >= 0) {
					right[i][j] = original[x][y];  // new 2D araray is rotated right 90 degree
				}
			}
		}
		return new GImage(right);
	}

	/** Method flipHorizontal is called when user press the Flip Horizotal button
	 *  It will copy image pixels in a 2D array into a anew 2D array with
	 *  pixels flipped across an immamginaary cetered vertical line
	 * */
	public GImage flipHorizontal(GImage source) {
		int[][] flip = source.getPixelArray(); // put the image into 2D array
		int imgColumn = flip[0].length;
		for (int i = 0; i < flip.length; i++) {
			for (int row1 = 0; row1 < imgColumn / 2; row1++) {
				int row2 = imgColumn - row1 - 1;
				int tempPixel = flip[i][row1];
				flip[i][row1] = flip[i][row2];   // use tempPixel to temporarily store the pixel
				flip[i][row2] = tempPixel;       // flip the pixels into new 2D araray
			}
		}
		return new GImage(flip);
	}

	/** Method rotaateLeft is called when user press the Negative button
	 *  It will invert eaach pixel's color by changing it's color RGB to
	 *  255 minus curretn color number
	 * */
	public GImage negative(GImage source) {
		int[][] original = source.getPixelArray();// put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				int red = GImage.getRed(original[row][column]);   // get eaach pixel's RGB
				int green = GImage.getGreen(original[row][column]);
				int blue = GImage.getBlue(original[row][column]);
				int newRed = 255 - red;        // invert the RGB color
				int newGreen = 255 - green;
				int newBlue = 255 - blue;
				original[row][column] = GImage.createRGBPixel(newRed, newGreen, newBlue);  // replace pixel with new RGB
			}
		}
		return new GImage(original);
	}

	/** Method rotaateLeft is called when user press the Green Screen button
	 *  It remove all green pixels of the imaage stored in a 2D array
	 *  so it can be overlaied onto another imaage
	 * */
	public GImage greenScreen(GImage source) {

		int[][] original = source.getPixelArray();// put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				int red = GImage.getRed(original[row][column]);
				int green = GImage.getGreen(original[row][column]);
				int blue = GImage.getBlue(original[row][column]);
				int bigger = Math.max(red, blue);
				if (2 * bigger < green) {
					original[row][column] = GImage.createRGBPixel(0, 0, 0, 0);  // remove Green color
				}
			}
		}
		return new GImage(original);
	}

	/** Method rotaateLeft is called when user press the Blur button
	 *  It will replaace immage RGB of each pixels in the 2D array with the aaveraage RGB values
	 *  of its eight neighbor pixels to create a a softenning effect
	 * */
	public GImage blur(GImage source) {
		int[][] original = source.getPixelArray();// put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				int redCounter = 0;  // add up to averaage
				int greenCounter = 0;
				int blueCounter = 0;
				int counter = 0;  // keep track of nnumber of pixel's to average
				// each of the eight IF clause searches for the 8 neighbor pixels RGB value to aaverage
				// it also check if the pixel is at edge already then it's less than 8 neighbors
				if ((row - 1 <= imgRow) && (row - 1 >= 0) && (column - 1 <= imgColumn) && (column - 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row - 1][column - 1]);
					int green = GImage.getGreen(original[row - 1][column - 1]);
					int blue = GImage.getBlue(original[row - 1][column - 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row - 1 <= imgRow) && (row - 1 >= 0) && (column <= imgColumn) && (column >= 0)) {
					counter++;
					int red = GImage.getRed(original[row - 1][column]);
					int green = GImage.getGreen(original[row - 1][column]);
					int blue = GImage.getBlue(original[row - 1][column]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row - 1 <= imgRow) && (row - 1 >= 0) && (column + 1 <= imgColumn - 1) && (column + 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row - 1][column + 1]);
					int green = GImage.getGreen(original[row - 1][column + 1]);
					int blue = GImage.getBlue(original[row - 1][column + 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row <= imgRow) && (row >= 0) && (column - 1 <= imgColumn) && (column - 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row][column - 1]);
					int green = GImage.getGreen(original[row][column - 1]);
					int blue = GImage.getBlue(original[row][column - 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row <= imgRow) && (row >= 0) && (column + 1 <= imgColumn - 1) && (column + 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row][column + 1]);
					int green = GImage.getGreen(original[row][column + 1]);
					int blue = GImage.getBlue(original[row][column + 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row + 1 <= imgRow - 1) && (row + 1 >= 0) && (column - 1 <= imgColumn) && (column - 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row + 1][column - 1]);
					int green = GImage.getGreen(original[row + 1][column - 1]);
					int blue = GImage.getBlue(original[row + 1][column - 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row + 1 <= imgRow - 1) && (row + 1 >= 0) && (column <= imgColumn) && (column >= 0)) {
					counter++;
					int red = GImage.getRed(original[row + 1][column]);
					int green = GImage.getGreen(original[row + 1][column]);
					int blue = GImage.getBlue(original[row + 1][column]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				if ((row + 1 <= imgRow - 1) && (row + 1 >= 0) && (column + 1 <= imgColumn - 1) && (column + 1 >= 0)) {
					counter++;
					int red = GImage.getRed(original[row + 1][column + 1]);
					int green = GImage.getGreen(original[row + 1][column + 1]);
					int blue = GImage.getBlue(original[row + 1][column + 1]);
					redCounter += red;
					greenCounter += green;
					blueCounter += blue;
				}
				counter++;
				int red = GImage.getRed(original[row][column]);
				int green = GImage.getGreen(original[row][column]);
				int blue = GImage.getBlue(original[row][column]);
				redCounter += red;
				greenCounter += green;
				blueCounter += blue;

				redCounter = redCounter / counter;
				greenCounter = greenCounter / counter;
				blueCounter = blueCounter / counter;
				original[row][column] = GImage.createRGBPixel(redCounter, greenCounter, blueCounter);
			}
		}
		return new GImage(original);
	}

	/** Method rotaateLeft is called when user press the Crop button
	 *  It will remove pixels at outer region of the image specified by input paraamaeters
	 *  and return the imaage with reduced size
	 * */
	public GImage crop(GImage source, int cropX, int cropY, int cropWidth, int cropHeight) {
		int[][] original = source.getPixelArray();// put the image into 2D array
		int[][] newImage = new int[cropHeight][cropWidth];
		int imgRow = original.length;
		int imgColumn = original[0].length;
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				if ((row >= cropY) && (row < (cropY + cropHeight))) {
					if ((column >= cropX) && (column < (cropX + cropWidth))) {
						newImage[row - cropY][column - cropX] = original[row][column]; // remove outter portionn
					}
				}
			}
		}
		return new GImage(newImage);
	}

	/** Method rotaateLeft is called when user press the Equalize button
	 *  It will call existing library function computeLuminosity to get each pixel's luminosity
	 *  and store number of pixels with the same luminosity numbers in an array
	 *  it will then apply a formula to calculate a new RGB value for each pixel to increasae contrast
	 * */
	public GImage equalize(GImage source) {
		int[][] original = source.getPixelArray();// put the image into 2D array
		int imgRow = original.length;
		int imgColumn = original[0].length;
		int[] luminosityArray = new int[256];
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				int red = GImage.getRed(original[row][column]);
				int green = GImage.getGreen(original[row][column]);
				int blue = GImage.getBlue(original[row][column]);
				int luminosity = computeLuminosity(red, green, blue);
				luminosityArray[luminosity]++;  // populate the luminosity array
			}
		}
		int[] cumulativeArray = new int[256];  // convert the array into culmulative luminosity array for formula
		for (int row = 0; row < luminosityArray.length; row++) {
			if (row == 0) {
				cumulativeArray[row] = luminosityArray[row];
			} else {
				cumulativeArray[row] = cumulativeArray[row - 1] + luminosityArray[row];
			}
		}
		for (int row = 0; row < imgRow; row++) {
			for (int column = 0; column < imgColumn; column++) {
				int red = GImage.getRed(original[row][column]);
				int green = GImage.getGreen(original[row][column]);
				int blue = GImage.getBlue(original[row][column]);
				int luminosity = computeLuminosity(red, green, blue);
				int newRGB = (int) (255 * (cumulativeArray[luminosity] * 1.0 / (imgRow * imgColumn))); // formula
				original[row][column] = GImage.createRGBPixel(newRGB, newRGB, newRGB);  // new RGB pixel value
			}
		}
		return new GImage(original);
	}
}

