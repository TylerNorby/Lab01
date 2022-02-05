import java.io.*;
import java.util.Scanner;


public class main {

    private static File wordFile;
    private static MultiLayerCache<String> multiLayerCache;

    public static void main(String[] args) {
        if (args.length < 3 || args.length > 4) {
            System.out.println("pls");
        }

        if (args[0].equals("1")) {

            int capacity = Integer.parseInt(args[1]);
            multiLayerCache = new MultiLayerCache<>(capacity, capacity);

            wordFile = new File(args[2]);
        } else if (args[0].equals("2")) {

            int cap1 = Integer.parseInt(args[1]);
            int cap2 = Integer.parseInt(args[2]);

            multiLayerCache = new MultiLayerCache<>(cap1, cap2);

            wordFile = new File(args[3]);
        }

        //TODO check file, check bounds of args, check bounds of capacity

      /*  //parse through the file
        try {

            Scanner scanner = new Scanner(wordFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");

                for(String e: words){
                    if (words.length > 0){
                        multiLayerCache.cacheSearch(e);
                    }
                }
            }


            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

*/

        try {
            // Scan through the file line by line
            BufferedReader scanner = new BufferedReader(new FileReader(wordFile));
            String line = scanner.readLine();
            while (line != null){
                String[] words = line.split("\\s+");

                for (String word : words){
                    if (word.length() > 0){ // Skip empty strings
                        multiLayerCache.cacheSearch(word);
                    }
                }

                line = scanner.readLine();
            }
        } catch (FileNotFoundException e){
           System.out.println("Shit broke lol no file found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("First level cache with " + multiLayerCache.getLayerOne().getCount() + " entries has been created");
        System.out.println("Second level cache with " + multiLayerCache.getLayerTwo().getCount() + " entries has been created" + "\n");
        System.out.println("The number of global references: "); //TODO make global hits and accessess
        System.out.println("The number of global cache hits: ");
        System.out.println("The global hit ratio: \n");
        System.out.println("The number of 1st-level references: " + multiLayerCache.getLayerOne().getAccesses());
        System.out.println("The number of 1st-level cache hits: " + multiLayerCache.getLayerOne().getHits());
        System.out.println("The 1st-level cache hit ratio: " + (double) multiLayerCache.getLayerOne().getHits() / multiLayerCache.getLayerOne().getAccesses() + "\n");
        System.out.println("The number of 2nd-level references: " + multiLayerCache.getLayerTwo().getAccesses());
        System.out.println("The number of 2nd-level cache hits: " + multiLayerCache.getLayerTwo().getHits());
        System.out.println("The 2nd-level cache hit ratio: " + (double) multiLayerCache.getLayerTwo().getHits() / multiLayerCache.getLayerTwo().getAccesses());


    }
}
