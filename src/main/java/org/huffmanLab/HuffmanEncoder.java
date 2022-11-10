package org.huffmanLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HuffmanEncoder {
  public static void main(String[] args) throws FileNotFoundException {
    // Read the original file and "compress" it
    String path = "src/main/java/org/huffmanLab/";
    Scanner scan = new Scanner(new File(path + "original.txt"));
    PrintWriter printer = new PrintWriter(path + "compressed.txt");

    String str = "";
    while (scan.hasNext()) {
      str += scan.next() + " ";
    }

    String[] code = Node.createCode(Node.createHuffmanTree(Node.countChars(str)));

    printer.println(Node.encode(str, code));
    printer.close();
    scan.close();

    // Read the compressed file and uncompress it
    scan = new Scanner(new File(path + "compressed.txt"));
    printer = new PrintWriter(path + "uncompressed.txt");

    str = "";
    while (scan.hasNext()) {
      str += scan.next() + " ";
    }

    printer.println(Node.decode(str, code));
    printer.close();
    scan.close();
  }
}
