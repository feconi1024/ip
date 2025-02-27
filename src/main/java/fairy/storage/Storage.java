package fairy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import fairy.task.Task;
import fairy.task.TaskList;
import fairy.ui.Ui;

/**
 * Represents the file used to store task records
 */
public class Storage {

    private static final String MESSAGE_RECORD_ADDED = "%d of %d lines added to the list of tasks.";
    private static final String MESSAGE_FAILURES_EXIST =
            " \nFailures may because of incorrect format or corrupted file.";
    private static final String MESSAGE_FILE_NOT_FOUND = "No record found. List starts empty.";
    private static final String MESSAGE_IO_EXCEPTION = "I/O exception: ";

    private static final String MESSAGE_RECORD_SAVED = "Tasks saved.";
    private static final String MESSAGE_ERROR_SAVING = "Error saving file: ";

    private final String directory;
    private final String filePath;

    /**
     * @param dir Directory (folder) where the file is stored.
     * @param filePath Path to the file.
     */
    public Storage(String dir, String filePath) {
        this.directory = dir;
        this.filePath = filePath;
    }

    /**
     * Loads the {@code TaskList} data from this storage file, and store in {@code TaskList} instance provided.
     *
     * @param taskList List of task where the task records from file will be loaded to.
     * @param ui User interface of the application.
     */
    public void readFile(TaskList taskList, Ui ui) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int effectiveLines = 0;
            int totalLines = 0;

            // read file
            while ((line = reader.readLine()) != null) {
                effectiveLines += taskList.addTaskFromRecord(line);
                totalLines += 1;
            }
            reader.close();

            assert effectiveLines <= totalLines;

            // UI output
            if (effectiveLines != totalLines) {
                ui.showStandardFormat(String.format(MESSAGE_RECORD_ADDED + MESSAGE_FAILURES_EXIST,
                        effectiveLines, totalLines));
            } else {
                ui.showStandardFormat(String.format(MESSAGE_RECORD_ADDED,
                        totalLines, effectiveLines));
            }

        } catch (FileNotFoundException e) {
            ui.showStandardFormat(MESSAGE_FILE_NOT_FOUND);
        } catch (IOException e) {
            ui.showStandardFormat(MESSAGE_IO_EXCEPTION + e.getMessage());
        }
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param taskList List of tasks that will be saved to the file.
     * @param ui User interface of the application.
     */
    public void saveFile(TaskList taskList, Ui ui) {
        File dir = new File(directory);

        // create target directory if does not exist
        if (!dir.exists()) {
            boolean hasMkDir = dir.mkdirs();
            assert hasMkDir;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Iterator<Task> it = taskList.iterator(); it.hasNext(); ) {
                Task task = it.next();
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
            ui.showStandardFormat(MESSAGE_RECORD_SAVED);
        } catch (IOException e) {
            ui.showStandardFormat(MESSAGE_ERROR_SAVING + e.getMessage());
        }
    }
}
