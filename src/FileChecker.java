import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileChecker {
    public static String inputFile;
    Scanner scanner = new Scanner(System.in);
    public static String path;
    public static String filename;
    File[] defaultFiles;
    File[] encryptedFiles;
    File[] decryptedFiles;
    ArrayList<File> attackedFiles = new ArrayList<>();
    ArrayList<File> filesList = new ArrayList<File>();
    public  FileChecker()
    {
        File file = new File(getDirectorySelection() + File.pathSeparator + getFileSelection());
        inputFile = file.getAbsolutePath();
        defaultFiles = file.listFiles();
    }


    /*public void directorySelection(String path){
        System.out.println("Enter file path: ");
        path = scanner.nextLine();
    }*/

    public void fileSelection(String filename){
        System.out.println("Enter file name: ");
        filename = scanner.nextLine();
    }


    public void fileArrayListWonderer(){
        for(int i=0; i<defaultFiles.length ; i++)
        {
            if(defaultFiles[i].exists())
            {
                if(defaultFiles[i].isFile())
                {
                    //File file = defaultFilesList[i];
                    System.out.println(defaultFiles[i].getAbsolutePath());
                }
            }
        }
    }

    public void setDefaultFilesList(){			//Reads all files from folder and keeps it in object array
		File f = new File(inputFile);
		
		defaultFiles = f.listFiles();
		
    }

    public void setEncryptedFilesList(){		//Reads only encrypted files from folder and keeps it in object array
		File f = new File(inputFile);
		
		FilenameFilter filter = new FilenameFilter(){
			
			@Override
			public boolean accept(File dir, String name){
				
				return name.endsWith(".encrypted");
			}
		};
		
		encryptedFiles = f.listFiles(filter);
    }

    public void setDecryptedFilesList(){		//Reads only decrypted files from folder and keeps it in object array
		File f = new File(inputFile);
		
		FilenameFilter filter = new FilenameFilter(){
			
			@Override
			public boolean accept(File dir, String name){
				
				return name.endsWith(".decrypted");
			}
		};
		
		decryptedFiles = f.listFiles(filter);
    }
	public String getEncryptedFile(int index) {
		return encryptedFiles[index-1].getPath();
	}

    public String getInputFile(){
        return inputFile;
    }

    public String getDirectorySelection() {
        return path;
    }

    public String getFileSelection(){
        return filename;
    }
	
	public void listFiles(){
		for(int i=0; i<defaultFiles.length; i++){
				System.out.println(defaultFiles[i]);
		}
	}
	
	public void listDecryptedFiles(){
		for(int i=0; i<decryptedFiles.length; i++){
				System.out.println(decryptedFiles[i]);
		}
	}
	
	public void listEncryptedFiles(){
		for(int i=0; i<encryptedFiles.length; i++){
				System.out.println(encryptedFiles[i]);
		}
	}
	
	public boolean checkDefaultList(){ //checks array have object or not returns true or false
		if(defaultFiles == null || defaultFiles.length == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkEncryptedList(){ //checks array have object or not returns true or false
		if(encryptedFiles == null || encryptedFiles.length == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkDecryptedList(){ //checks array have object or not returns true or false
		if(decryptedFiles == null || decryptedFiles.length == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public void deleteDecrypteds(){
		for(int i=0; i<decryptedFiles.length; i++){
			if(decryptedFiles[i].delete()){
				System.out.println("File "+decryptedFiles[i].getName()+" deleted!");
			}else{
				System.out.println("File "+decryptedFiles[i].getName()+" cannot deleted. It failed!");
			}
		}
	}

	public void compareFiles(int index) {
		for(int i=0; i<defaultFiles.length ; i++) {
			if(decryptedFiles[index-1].getName().startsWith(defaultFiles[i].getName())) {
				if(!defaultFiles[i].getName().contains("encrypted")) {
					boolean isEqual = isEqual(defaultFiles[i].toPath(), decryptedFiles[index-1].toPath());
					if(isEqual == false) {
						System.out.println("There are some changes in "+defaultFiles[i].getName()+ " file!");
						attackedFiles.add(defaultFiles[i]);
					}
				}
			}
		}
	}
	private static boolean isEqual(Path firstFile, Path secondFile)
	{
		try {
			if (Files.size(firstFile) != Files.size(secondFile)) {
				return false;
			}

			byte[] first = Files.readAllBytes(firstFile);
			byte[] second = Files.readAllBytes(secondFile);
			return Arrays.equals(first, second);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


}
