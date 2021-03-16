package softwaredesign;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle {
    private Matrix matrix;
    private Sequences sequences;

    private String currPuzzle;
    private int bufferLen;

    public void getNextPuzzle() {
        this.currPuzzle = getPuzzleContent();
        String[] puzzleParts = currPuzzle.split("\n\n");

        //puzzleParts[0] is buffer length
        try {
            this.bufferLen = Integer.parseInt(puzzleParts[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("This is not a valid format for buffer size.");
        }

        //puzzleParts[1] is the matrix
        String matrixTxt = puzzleParts[1];
        this.matrix = new Matrix(matrixTxt);

        //puzzleParts[2] are the sequences
        String seqTxt = puzzleParts[2];
        this.sequences = new Sequences(seqTxt);
    }

    private int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

    //TODO: Put puzzles into jar
    private String getPuzzleContent(){
        final int MIN_FILE_NUM = 1;
        final int MAX_FILE_NUM = 40;
        int fileNumber = getRandomNumber(MIN_FILE_NUM, MAX_FILE_NUM);
        String currentDirectory = System.getProperty("user.dir");
        String fullFilePath = currentDirectory + "\\puzzles\\" + fileNumber + ".txt";
        String puzzleTxt = readFile(fullFilePath);
        return puzzleTxt;
    }

    private String readFile(String file_name) {
        String fileContent = "";
        try {
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContent += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        fileContent = fileContent.substring(0, fileContent.length() - 1);
        return fileContent;
    }

    public int getBufferLen(){
        return this.bufferLen;
    }

    public String[] getCurrNSeq(int seqIndex) {
        return this.sequences.getNSeq(seqIndex);
    }

    public int getCurrNumberOfSeq() {
        return this.sequences.getNumberOfSeq();
    }

    public String getCurrMatrixElement(int row, int col) {
        return this.matrix.getMatrixElement(row, col);
    }

    public int getCurrMatrixDims() {
        return this.matrix.getMatrixDims();
    }
}
