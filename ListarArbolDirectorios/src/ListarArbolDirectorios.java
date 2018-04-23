import java.io.File;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.io.*;
 
/*implements NativeKeyListener */
public class ListarArbolDirectorios {
	
    public static void main(String[] args) throws IOException {
    	//EscribirEnArchivoDeTexto();
    	
    	//ListarDirectorios(new File("C:/Users/Juan Pablo Abad/SO")); //Replace this with a suitable directory
    	
    	//ListarDirectoriosEnArchivoDeTexto(new File("C:/Users/Juan Pablo Abad/SO"));
    	
    	//DetectarNuevosArchivosEnDirectorio();
    	
    	//ListarMientrasDetectaNuevosArchivosEnDirectorio();
    }
 
    public static void ListarDirectorios(File dir) {
 
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                	ListarDirectorios(listFile[i]);
                } else {
                    System.out.println(listFile[i].getPath());
                }
            }
        }
    }
    
    public static void EscribirEnArchivoDeTexto() throws FileNotFoundException, IOException {
    	
    	FileWriter archivoDeTexto = null;
        PrintWriter pw = null;
        try
        {
        	archivoDeTexto = new FileWriter("F:/prueba/ESCRIBIR.txt",true);
            pw = new PrintWriter(archivoDeTexto);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != archivoDeTexto)
        	   archivoDeTexto.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
       
    }
    
    public static void ListarDirectoriosEnArchivoDeTexto(File dir) throws FileNotFoundException, IOException{
    	
    	FileWriter archivoDeTexto = null;
        PrintWriter pw = null;
        try{
        	archivoDeTexto = new FileWriter("F:/prueba/ESCRIBIR.txt",true);
	        pw = new PrintWriter(archivoDeTexto);
	    	
	        File listFile[] = dir.listFiles();
	        if (listFile != null) {
	            for (int i = 0; i < listFile.length; i++) {
	                if (listFile[i].isDirectory()) {
	                	ListarDirectoriosEnArchivoDeTexto(listFile[i]);
	                } else {
	                	pw.println(listFile[i].getPath());
	                    //System.out.println(listFile[i].getPath());
	                }
	            }
	        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally{
	    	try {
	            // Nuevamente aprovechamos el finally para 
	            // asegurarnos que se cierra el fichero.
	            if (null != archivoDeTexto)
	            	archivoDeTexto.close();
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
        }
    }
    
    public static void DetectarNuevosArchivosEnDirectorio()throws IOException{
    	Path dir = Paths.get("F:/prueba/");
        WatchService service = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(service, ENTRY_CREATE);

        System.out.println("Watching directory: "+dir.toString());
        for(;;){
            WatchKey key1;
            try {
                key1 = service.take();
            } catch (InterruptedException x) {
                break;
            }

            for (WatchEvent<?> event: key1.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                Path child = dir.resolve(filename);
                System.out.println("New file: "+child.toString()+" created.");
                try{
                    FileInputStream in = new FileInputStream(child.toFile());
                    System.out.println("File opened for reading");
                    in.close();
                    System.out.println("File Closed");
                }catch(Exception x){
                    x.printStackTrace();
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }
    
    //https://loquemeinteresadelared.wordpress.com/2016/02/15/como-detectar-si-un-fichero-ha-sido-creado-modificado-o-borrado-utilizando-java/
    //https://stackoverflow.com/questions/13998379/directory-watching-for-changes-in-java
    //https://docs.oracle.com/javase/tutorial/essential/io/notification.html
    //https://docs.oracle.com/javase/tutorial/essential/io/examples/WatchDir.java
    public static void ListarMientrasDetectaNuevosArchivosEnDirectorio()throws IOException{
    	Path dir = Paths.get("F:/prueba/");
        WatchService service = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(service, ENTRY_CREATE);

        System.out.println("Watching directory: "+dir.toString());
        for(;;){
            WatchKey key1;
            try {
                key1 = service.take();
            } catch (InterruptedException x) {
                break;
            }

            for (WatchEvent<?> event: key1.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                Path child = dir.resolve(filename);
                System.out.println("New file: "+child.toString()+" created.");
                try{
                    FileInputStream in = new FileInputStream(child.toFile());
                    System.out.println("File opened for reading");
                    in.close();
                    System.out.println("File Closed");
                }catch(Exception x){
                    x.printStackTrace();
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }
}
