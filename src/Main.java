import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

	int select = 0;
	int i=0;
	String filename = null;
	String filename2 = null;

	FileChecker filechecker = new FileChecker(); //filechecker object
	Scanner scanner = new Scanner(System.in);
    ArrayList<String> fileNameArray = new ArrayList<String>();
    ArrayList<String> encryptedFileArray = new ArrayList<String>();
    ArrayList<String> decryptedFileArray = new ArrayList<String>();
        //filechecker.setDecryptedFilesList(i,encryptedFile,decryptedFile);

        System.out.println("Enter Working Directory: ");
        String path = scanner.nextLine();
        File inputFile = new File(path);
        System.out.println("Enter Encryption key: ");
        String encryptKey = scanner.nextLine();
        System.out.println("Enter Decryption key: ");
        String decryptKey = scanner.nextLine();


        while(select!=0);{
            System.out.println("-------------------------------------------");
            System.out.println("1-Encrypt Files");
            System.out.println("2-Decrypt Files");
            System.out.println("3-Compare");
            System.out.println("4-List Files");
            System.out.println("0-Quit");
            System.out.println("Choice: ");
    select = scanner.nextInt();
        switch (select) {
            case 1: //Encrypt files
                filechecker.fileSelection(filename);
                File encryptedFile1 = new File(filechecker.getInputFile() + ".encrypt");
                try {
                    Encryption.encrypt(encryptKey, inputFile, encryptedFile1);
                } catch (CryptoException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case 2: //Decrypt files

                filechecker.fileSelection(filename2);

                if(filechecker.checkEncryptedList())
                {
                    System.out.println("Choice which file to decrypt: ");
                    i = scanner.nextInt();

                    File encryptedFile = new File(filechecker.getEncryptedFile(i));
                    File decryptedFile = new File(filechecker.getInputFile() + ".decrypt");
                    try {
                        Decryption.decrypt(decryptKey, encryptedFile, decryptedFile);
                    } catch (CryptoException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                else{
                    System.out.println("Cannot find any file in folder");
                }
                break;

            case 3: //Check Current State and Compare
                if(filechecker.checkDecryptedList() == true) {
                    filechecker.setDecryptedFilesList();
                    System.out.println("Choice which file to decrypt: ");
                    i = scanner.nextInt();
                    filechecker.compareFiles(i);
                }else {
                    filechecker.setDecryptedFilesList();
                }
                break;
            case 4: //Files List
				if(filechecker.checkDefaultList()){
					filechecker.listFiles();
				}else{
					System.out.println("Cannot find any file in folder");
				}
                break;
            case 0: //Exit program

                break;


            default:
                throw new IllegalStateException("Unexpected value: " + select);
        }
        }


    }
}
