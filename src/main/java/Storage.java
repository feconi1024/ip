import java.io.*;
import java.util.Iterator;

public class Storage {

    private final String directory;
    private final String fileName;

    public Storage(String dir, String filename) {
        directory = dir;
        fileName = filename;
    }

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int effectiveLines = 0;
            int totalLines = 0;
            while ((line = reader.readLine()) != null) {
                effectiveLines += Fairy.TASKS.addTaskFromRecord(line);
                totalLines += 1;
            }
            reader.close();
            if (effectiveLines != totalLines) {
                Ui.printStandardFormat(String.format("%d of %d lines added to the list of tasks. \n" +
                        "Failures may because of incorrect format or corrupted file.", effectiveLines, totalLines));
            } else {
                Ui.printStandardFormat(String.format("%d of %d lines added to the list of tasks.",
                        totalLines, effectiveLines));
            }

        } catch (FileNotFoundException e) {
            Ui.printStandardFormat("No record found. List starts empty.");
        } catch (IOException e) {
            Ui.printStandardFormat("I/O exception: " + e.getMessage());
        }
    }

    public void saveFile() {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Iterator<Task> it = Fairy.TASKS.iterator(); it.hasNext(); ) {
                Task task = it.next();
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
            Ui.printStandardFormat("Tasks saved.");
        } catch (IOException e) {
            Ui.printStandardFormat("Error saving file: " + e.getMessage());
        }
    }
}
