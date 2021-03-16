package softwaredesign;

import java.io.*;
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

    private String getPuzzleContent() {
        final int MIN_FILE_NUM = 1;
        final int MAX_FILE_NUM = 40;
        int fileNumber = getRandomNumber(MIN_FILE_NUM, MAX_FILE_NUM);
        String fileName = "puzzles/" + fileNumber + ".txt";
        String file = "";
        try {
            file = readFile(fileName);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return file;
    }

    private String readFile(String fileName) throws IOException {
        System.out.println("getResourcesAsStream : " + fileName);
        InputStream is = getFileFromResourceAsStream(fileName);
        String output = inputStreamToString(is);
        return output;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static String inputStreamToString(InputStream i) throws IOException {
        String fileContent = "";
        BufferedReader r = new BufferedReader(new InputStreamReader(i));
        String l;
        while((l = r.readLine()) != null) {
            System.out.println("LINE: "+l);
            fileContent += l + "\n";
        }
        i.close();
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
